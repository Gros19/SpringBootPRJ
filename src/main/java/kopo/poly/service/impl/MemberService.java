package kopo.poly.service.impl;


import kopo.poly.dto.MemberDTO;
import kopo.poly.persistance.mongodb.IMemberMapper;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMemberService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Slf4j
@Service("MemberService")
public class MemberService implements IMemberService {


    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;

    @Resource(name = "MemberMapper")
    private IMemberMapper memberMapper; // MongoDB에 저장할 Mapper

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public int leaveMember(String pEmail) throws Exception {


        log.info(this.getClass().getName()+"leaveMember start");

        log.info(this.getClass().getName()+"삭제할 계정 이메일 : " + pEmail);
        int res = 0;

        res = memberMapper.leaveMember(pEmail);

        log.info(this.getClass().getName()+"leaveMember end");
        return res;
    }

    @Override
    public int emailOk(String pEmail) throws Exception {
        log.info(this.getClass().getName()+"EmailOk start");
        log.info(this.getClass().getName()+"EmailOk end");
        return memberMapper.emailOk(pEmail);
    }

    @Override
    public int loginMember(MemberDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+"loginMember start");
        log.info(this.getClass().getName()+"loginMember end");
        return memberMapper.loginMember(pDTO);
    }

    @Override
    public int joinMember(MemberDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".joinMember Start!");

        int res = 0;

        // 생성할 컬렉션명
        String colNm = "Member";

        // MongoDB에 데이터저장하기
        res = memberMapper.joinMember(pDTO, colNm);



        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".joinMember End!");

        return res;
    }


    /*이메일 전송*/
    @Override
    public int authEmail(String email, String url) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        /*임의의 authKey 생성*/
        Random random = new Random();
        String outhKey = String.valueOf(random.nextInt(88888888)+11111111);

        /*희박한 확률로 key가 존재한다면 다시 뽑기*/
        while(myRedisMapper.getData(outhKey)!=null){
            outhKey = String.valueOf(random.nextInt(88888888)+11111111);
        }

        /*이메일 발송*/
        return  sendAuthEmail(email, outhKey, url);
    }

    /*
    * 이메일 인증 링크를 통한 이메일 인증 확인
    * true -> password update*/
    @Override
    public int authMemberResetPass(MemberDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+"authMemberResetPass start");

        int res = 0;
        String email = pDTO.getEmail();
        String auth = pDTO.getAuth();
        String password = pDTO.getPassword();


        log.info(this.getClass().getName()+" authMemberResetPass  email"+email);
        log.info(this.getClass().getName()+" authMemberResetPass  auth"+auth);
        log.info(this.getClass().getName()+" authMemberResetPass  password"+ password);

        /*authcode를 통해 레디스에 저장된 email을 반환*/
        String ens = CmmUtil.nvl(myRedisMapper.getData(auth));

        log.info(this.getClass().getName()+"ens : " + ens);


        /*이메일 인증 성공*/
        if (ens.equals(EncryptUtil.encAES128CBC(email))){
            log.info(this.getClass().getName()+"authMemberResetPass 이메일 인증 성공");
            log.info(this.getClass().getName()+"authMemberResetPass pw reset 시작");
            res = memberMapper.authMember(pDTO, "password");

            /*인증 후 인증 키 삭제
            * 유효시간 5분을 지나면 자동 삭제 되지만*/
            myRedisMapper.deleteData(auth);
        }else {
            log.info(this.getClass().getName()+"authMember 이메일 인증 실패");
        }

        log.info(this.getClass().getName()+"authMemberResetPass end");

        return res;
    }

    /*이메일 인증 링크를 통해 이메일 인증 확인
    * true -> auth update*/
    @Override
    public int authMember(MemberDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+"start");

        int res = 0;
        String email = pDTO.getEmail();
        String auth = pDTO.getAuth();
        String password = pDTO.getPassword();


        log.info(this.getClass().getName()+" authMember  email"+email);
        log.info(this.getClass().getName()+" authMember  auth"+auth);
        log.info(this.getClass().getName()+" authMember  password"+ password);


        /*authcode를 통해 레디스에 저장된 email을 반환*/
        String ens = CmmUtil.nvl(myRedisMapper.getData(auth));

        log.info(this.getClass().getName()+"ens : " + ens);


        /*이메일 인증 성공*/
        if (ens.equals(EncryptUtil.encAES128CBC(email))){
            log.info(this.getClass().getName()+"이메일 인증 성공");
            res = memberMapper.authMember(pDTO, "auth");
            /*인증 후 인증 키 삭제
             * 유효시간 5분을 지나면 자동 삭제 되지만*/
            myRedisMapper.deleteData(auth);
        }else {
            log.info(this.getClass().getName()+"authMember 이메일 인증 실패");
        }

        log.info(this.getClass().getName()+"end");

        return res;
    }

    public int sendAuthEmail(String email, String auth, String url) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        log.info(this.getClass().getName()+ "메일 전송 시작");


        HtmlEmail email1 = new HtmlEmail();
        email1.setHostName("smtp.naver.com");
        email1.setSmtpPort(465);
        email1.setAuthentication("wnnahd112@naver.com", "");

        email1.setSSLOnConnect(true);
        email1.setStartTLSEnabled(true);

        int res = 0;




        String code = "?auth="+auth+"&email="+email;
        try{
            email1.setFrom("wnnahd112@naver.com", "FreeFund manager", "utf-8");
            email1.addTo(email, "FreeFund", "utf-8");
            email1.setSubject("authentication");

            StringBuffer msg = new StringBuffer();

            msg.append("<p>I'm FreeFund manager.</p>");
            msg.append("<p>you can certify</p>");
            msg.append("<a href='" + url + code + "'>plz click this link</a>");


            email1.setHtmlMsg(msg.toString());
            email1.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

        myRedisMapper.setData(auth,(email));

        log.info(this.getClass().getName()+ "메일 전송 완료");
        return  res;
    }


}
