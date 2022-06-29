package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import kopo.poly.dto.MemberDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMemberMapper;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("MemberMapper")
public class MemberMapper extends AbstractMongoDBComon implements IMemberMapper {


	@Override
	public int leaveMember(String pEmail) throws Exception{

		log.info(this.getClass().getName()+"leaveMember start");

		int res = 0;

		String colNm = "Member";

		MongoCollection<Document> col = mongodb.getCollection(colNm);
		Document query = new Document();

		query.append("email", EncryptUtil.encAES128CBC(pEmail));

		Document rs = col.findOneAndDelete(query);


		log.info(this.getClass().getName()+"   "+rs.toString());
		res = 1;

		log.info(this.getClass().getName()+"leaveMember end");

		return res;
	}



	/*이메일확인*/
	@Override
	public int emailOk(String pEmail) throws Exception {

		log.info(this.getClass().getName() + "emailOk Start");

		int res = 0;

		String colNm = "Member";
		MongoCollection<Document> col = mongodb.getCollection(colNm);
		Document query = new Document();

		query.append("email", EncryptUtil.encAES128CBC(pEmail));

		Document projection = new Document();
		projection.append("email", "$email");
		projection.append("auth", "$auth");

		projection.append("_id", 0);
		MongoCursor<Document> cur = col.find(query).projection(projection).iterator();


		if(cur.hasNext()){
			log.info("db에 email 확인");

			Document doc = cur.next();
			log.info(this.getClass().getName()+"\t"+doc.get("auth"));

			String auth =(String) doc.get("auth");

			/**
			 * res -1 //email 미등록
			 * res -2 //email 미인증
			 * res 1  //email 정상
			 */
			/*인증이 완료된 계정*/
			if(auth.equals("true")){
				log.info(this.getClass().getName()+"emailOk email 정상");
				res = 1;
			}
			/*인증이 안 된 계정*/
			else{
				res = -2;
				log.info(this.getClass().getName()+"emailOk email 인증이 완료되지 않았습니다.");
			}
		}
		/*db에 email 없음*/
		else {
			log.info(this.getClass().getName()+"emailOk 등록된 email이 없습니다.");
			res = -1;
		}

		log.info(this.getClass().getName() + "emailOk END");


		return res;

	}


	/*회원가입 email과 비밀번호를 저장*/
	@Override
	public int joinMember(MemberDTO pDTO, String colNm) throws Exception {

		log.info(this.getClass().getName() + ".joinMember Start");

		int res = 0;
		if (pDTO == null) {
			pDTO = new MemberDTO();
		}

		//데이터를 저장할 컬렉션 생성
		super.createCollection(colNm, "Member");

		//저장할 컬렉션 객체생성
		MongoCollection<Document> col = mongodb.getCollection(colNm);

		/*id 확인*/
		Document query = new Document();
		query.append("id", pDTO.getId());
		Document projection = new Document();
		projection.append("id", "$id");
		projection.append("_id", 0);
		MongoCursor<Document> cur = col.find(query).projection(projection).iterator();

		if(cur.hasNext()){
			log.info(this.getClass().getName()+"id 중복이 확인");
			log.info(this.getClass().getName() + ".joinMember END");
			return 7;
		}



		/*email 확인*/
		 query = new Document();
		query.append("email", EncryptUtil.encAES128CBC(pDTO.getEmail()));
		 projection = new Document();
		projection.append("email", "$email");
		projection.append("_id", 0);
		cur = col.find(query).projection(projection).iterator();


		 if(cur.hasNext()){
			 log.info("email 중복");
			 res = 2;
		 }else {
			 pDTO.setEmail(EncryptUtil.encAES128CBC(pDTO.getEmail()));
			 pDTO.setPassword(EncryptUtil.encHashSHA256(pDTO.getPassword()));
			 log.info("등록된 eamil이 없습니다.");
			 res = 1;
			 col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));
		 }

		log.info(this.getClass().getName() + ".joinMember END");

		return res;

	}

	/**
	 * @param pDTO,what
	 * @return res
	이메일 인증에 성공한 계정의 auth/password update*/
	@Override
	public int authMember(MemberDTO pDTO, String what) throws Exception {
		log.info(this.getClass().getName() + ".authMember Start!");
		int res = 0;
		String update;
		String pColNm = "Member";

		MongoCollection<Document> col = mongodb.getCollection(pColNm);
		log.info("pColNm : " + pColNm);

		Document query = new Document();
		query.append("email", EncryptUtil.encAES128CBC(pDTO.getEmail()));


		/*회원가입 email인증 auth -> true */
		if(what.equals("auth")){
			update = "true";
		}
		/*비밀번호 초기화 password -> get("password")*/
		else{
			update = EncryptUtil.encHashSHA256(pDTO.getPassword());
		}
		log.info(this.getClass().getName()+String.format("authMember\n%s를 기준으로 %s를 %s로 update 실행",EncryptUtil.encAES128CBC(pDTO.getEmail()),what, update));
		col.findOneAndUpdate(query, new Document("$set",new Document(what, update)));
		res = 1;
		log.info(this.getClass().getName() + ".authMember End!");
		return res;
	}

	/**
	 * res -1 //email 미등록
	 * res -2 //email 미인증
	 * res -3 //password 오류
	 * res 1  //로그인 성공
	 */
	/*회원가입 email과 비밀번호를 저장*/
	@Override
	public int loginMember(MemberDTO pDTO) throws Exception {

		log.info(this.getClass().getName() + ".loginMember Start");

		int res = 0;
		if (pDTO == null) {
			pDTO = new MemberDTO();
		}



		String colNm = "Member";
		MongoCollection<Document> col = mongodb.getCollection(colNm);
		Document query = new Document();

		query.append("email", EncryptUtil.encAES128CBC(pDTO.getEmail()));

		Document projection = new Document();
		projection.append("email", "$email");
		projection.append("password", "$password");
		projection.append("auth", "$auth");

		projection.append("_id", 0); // 의문???
		MongoCursor<Document> cur = col.find(query).projection(projection).iterator();


		if(cur.hasNext()){
			log.info("db에 계정이 확인");

			Document doc = cur.next();
			log.info(this.getClass().getName()+"\t"+doc.get("email"));
			log.info(this.getClass().getName()+"\t"+doc.get("password"));
			log.info(this.getClass().getName()+"\t"+doc.get("auth"));

			String ansPassword =(String) doc.get("password");
			String auth = CmmUtil.nvl((String) doc.get("auth"));


			log.info(this.getClass().getName()+"db에 저장된 pass"+ansPassword);
			log.info(this.getClass().getName()+"db에 저장된 auth"+auth);

			if(auth.equals("true")){

				log.info(this.getClass().getName()+"auth.equals(\"true\")");

				log.info(this.getClass().getName()+"파라미터 pass "+pDTO.getPassword());

				log.info(this.getClass().getName()+"파라미터 pass enc"+EncryptUtil.encHashSHA256(pDTO.getPassword()));
				if (ansPassword.equals(EncryptUtil.encHashSHA256(pDTO.getPassword()))){
					res = 1;
					log.info(this.getClass().getName()+"로그인 정상");
				}else {
					res = -3;

					log.info(this.getClass().getName()+"비밀번호가 틀렸습니다.");
				}
			}else{
				res = -2;

				log.info(this.getClass().getName()+"email 인증이 완료되지 않았습니다.");
			}
		}else {

			log.info(this.getClass().getName()+"등록된 email이 없습니다.");
			res = -1;
		}

		log.info(this.getClass().getName() + ".loginMember END");


		return res;

	}
}

