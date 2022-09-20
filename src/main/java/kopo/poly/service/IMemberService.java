package kopo.poly.service;

import kopo.poly.dto.MemberDTO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public interface IMemberService {


    /*회원 탈퇴*/
    int leaveMember(String pEmail) throws Exception;

    /*이메일 확인*/
    int emailOk(String pEmail) throws Exception;

    /*로그인 기능*/
    int loginMember(MemberDTO pDTO) throws Exception;

    int joinMember(MemberDTO pDTO) throws Exception;


    /*이메일 전송*/
    int authEmail(String email, String url) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    /*
    * 이메일 인증 링크를 통한 이메일 인증 확인
    * true -> password update*/
    int authMemberResetPass(MemberDTO pDTO) throws Exception;

    int authMember(MemberDTO pDTO) throws Exception;

}
