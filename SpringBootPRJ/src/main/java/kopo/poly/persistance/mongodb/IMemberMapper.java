package kopo.poly.persistance.mongodb;

import kopo.poly.dto.MemberDTO;

public interface IMemberMapper {



	/*회원 탈퇴
	* return 1이면 성공*/
	int leaveMember(String pEmail) throws Exception;

    /*이메일 확인*/
	int emailOk(String pEmail) throws Exception;

	/*회원가입 email과 비밀번호를 저장*/
	int joinMember(MemberDTO pDTO, String colNm) throws Exception;

	/*이메일 인증에 성공한 계정의 auth/password update*/
	int authMember(MemberDTO pDTO, String what) throws Exception;

    /*회원가입 email과 비밀번호를 저장*/
    int loginMember(MemberDTO pDTO) throws Exception;


}
