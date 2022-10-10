package kopo.poly.controller;

import kopo.poly.dto.MemberDTO;
import kopo.poly.service.IMemberService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MemberController {

    @Resource(name = "MemberService")
    IMemberService memberService;


    @GetMapping(value="/index")
    public String index() {
        return "/member/index";
    }

    @GetMapping(value="/signup")
    public String signup() {
        return "/member/signup";
    }

    @GetMapping(value="/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping(value = "/resetrequest")
    public String resetRequest(){return "/member/pw_reset_request";}


    @ResponseBody
    @PostMapping(value = "/leave")
    public String Leave(HttpServletRequest request) throws Exception {

        String email =  request.getParameter("email");

        log.info("Param email: "+email);

        int res = 0;

        res = memberService.leaveMember((email));
        String msg = (res==1)?  "1": "0";
        return msg;
    }


    @ResponseBody
    @PostMapping(value="/resetProc")
    public String reset(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName()+"resetProc start");


        String email = CmmUtil.nvl(request.getParameter("email"));
        String password = CmmUtil.nvl(request.getParameter("password"));

        String auth = CmmUtil.nvl(request.getParameter("auth"));

        log.info(this.getClass().getName()+"email "+email);
        log.info(this.getClass().getName()+"password "+ password);
        log.info(this.getClass().getName()+"auth "+ auth);

        MemberDTO pDTO = new MemberDTO();
        pDTO.setPassword(password);
        pDTO.setEmail(email);
        pDTO.setAuth(auth);

        int res = memberService.authMemberResetPass(pDTO);
        log.info(this.getClass().getName()+"resetProc end");
        return String.valueOf(res);
    }


    /*
     * 전송된 이메일을 통해 회원 인증처리
     * */
    @GetMapping(value = "/EmailAuthPWRestProc")
    public String EmailAuthPWRestProc(@RequestParam Map<String, String> map, ModelMap model) throws Exception {

        log.info(this.getClass().getName()+" EmailAuthPWRestProc start");

        String auth = CmmUtil.nvl(map.get("auth"));
        String email =  CmmUtil.nvl(map.get("email"));

        log.info(this.getClass().getName()+"email + "+email);
        log.info(this.getClass().getName()+"auth" + auth);

        MemberDTO pDTO = new MemberDTO();
        pDTO.setEmail(email);
        pDTO.setAuth(auth);
        model.addAttribute("MemberDTO",pDTO);


        log.info(this.getClass().getName()+"emil auth를 담아서 페이지 로딩");
        log.info(this.getClass().getName()+"EmailAuthPWRestProc end");
        return "/member/pw_reset";
    }

    /*비밀번호 초기화 요청*/
    @ResponseBody
    @PostMapping(value = "/resetRequest")
    public String resetRequest(HttpServletRequest request) throws Exception {
        log.info("########"+this.getClass().getName()+ "resetRequest start");
        String email = CmmUtil.nvl(request.getParameter("email"));

        log.info("email :" + email);

        int res = memberService.emailOk(email);


        log.info(this.getClass().getName()+"resetRequest emailCheck 결과 : " + res);

        /*email 링크 클릭 시 접속 주소*/
        String Durl = "https://gros24.click/EmailAuthPWRestProc";

        String result = "";

        /*
         * res -1 //email 미등록
         * res -2 //email 미인증
         * res  0 //server error
         * res 1  //email 확인
        * */
        if (res==1){
            log.info(this.getClass().getName()+"메일 전송 시작");
            /*메일 전송*/
            memberService.authEmail(email, Durl);
        }
        result = String.valueOf(res);


        log.info(this.getClass().getName()+"resetRequest ajax return 결과 : " + result);
        log.info(this.getClass().getName()+"resetRequest end");
        return result;
    }

    /*로그인 진행*/
    @ResponseBody
    @PostMapping(value = "/loginProc")
    public String loginProc(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String email = CmmUtil.nvl(request.getParameter("email"));
        String password = CmmUtil.nvl(request.getParameter("password"));


        log.info(this.getClass().getName()+"loginProc 시작");


        log.info(this.getClass().getName()+"email " + email);

        log.info(this.getClass().getName()+"password" + password);


        MemberDTO pDTO = new MemberDTO();
        pDTO.setEmail(email);
        pDTO.setPassword(password);


        /**
         * res -1 //email 미등록
         * res -2 //email 미인증
         * res -3 //password 오류
         * res 1  //
         *
         */
        int res = memberService.loginMember(pDTO);

        String msg = "로그인 성공";
        switch (res){
            case -1:
                msg = "email 미등록";
                break;
            case -2:
                msg = "email 미인증";
                break;
            case -3:
                msg = "password 오류";
                break;
            case 1:
                log.info(this.getClass().getName()+"###res   " +res);
                Cookie cookie2 = new Cookie("email",EncryptUtil.encAES128CBC(pDTO.getEmail()));
                cookie2.setPath("/");
                cookie2.setHttpOnly(true);
                response.addCookie(cookie2);
                break;
        }




        log.info(this.getClass().getName()+"res" + res);
        log.info(this.getClass().getName()+"loginProc 종료");


        return msg;
    }

    /*회원가입 실행*/
    @ResponseBody
    @PostMapping(value = "/signupProc")
    public String signupProc(HttpServletRequest request) throws Exception {
        log.info("########"+this.getClass().getName());
        String email = CmmUtil.nvl(request.getParameter("email"));
        String password = CmmUtil.nvl(request.getParameter("password"));
        String id = CmmUtil.nvl(request.getParameter("id"));

        log.info("id :" + id);
        log.info("email :" + email);
        log.info("password :" + password);
        MemberDTO pDTO = new MemberDTO();

        pDTO.setId(id);
        pDTO.setEmail((email));
        pDTO.setPassword((password));

        int res = memberService.joinMember(pDTO);

        String url = "https://gros24.click/EmailAuthProc";

        String result = "";
        if (res==1){
             result = "result success";
             /*메일 전송*/
            memberService.authEmail(email, url);
        }else if(res==2){
             result = "result duplicated";
        }else if(res==7){
            result = "id duplicated";
        }
        else{
            result = "error";
        }

        return result;

    }

    /*
    * 전송된 이메일을 통해 회원 인증처리
    * */
    @ResponseBody
    @GetMapping(value = "/EmailAuthProc")
    public String EmailAuthProc(@RequestParam Map<String, String> map) throws Exception {

        log.info(this.getClass().getName()+"start");


        String auth = CmmUtil.nvl(map.get("auth"));
        String email =  CmmUtil.nvl(map.get("email"));

        log.info(this.getClass().getName()+"email + "+email);
        log.info(this.getClass().getName()+"authKey" + auth);

        MemberDTO pDTO = new MemberDTO();
        pDTO.setEmail(email);
        pDTO.setAuth(auth);
        int res = memberService.authMember(pDTO);

        String msg = "";
        if(res == 1){

            log.info(this.getClass().getName()+" auth update 성공");
            msg = "이메일 인증 성공";
        }else {

            log.info(this.getClass().getName()+" auth update 실패");
            msg = "이메일 인증에 문제가 발생";
        }

        log.info(this.getClass().getName()+"end");
        return msg;
    }

}
