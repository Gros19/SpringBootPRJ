package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import kopo.poly.dto.CorpDTO;
import kopo.poly.dto.MemberDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMemberMapper;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("CorpMapper")
public class CorpMapper extends AbstractMongoDBComon {


    public int insertData(String year, List<LinkedHashMap<String, Object>> obj) throws Exception {

        log.info(this.getClass().getName() + "CorpMapper :start");
        int res = 0;
        mongodb.getCollection(year).drop();
        //index 설정 필요
        super.createCollection(year);
        //저장할 컬렉션 객체생성
        MongoCollection<Document> col = mongodb.getCollection(year);


        Iterator it = obj.iterator();
        List<Document> list = new ArrayList<>();

        while (it.hasNext()) {
            Document doc = new Document();
            doc.putAll((LinkedHashMap<String, Object>) it.next());
            list.add(doc);
        }

        col.insertMany(list);
        log.info(this.getClass().getName() + ".CorpMapper END");
        res = 1;
        return res;
    }

    public ArrayList<CorpDTO> getData() throws Exception {

        log.info(this.getClass().getName() + "CorpMapper :start");


        MongoCollection<Document> col = mongodb.getCollection(String.valueOf(Integer.parseInt(DateUtil.getDateTime("yyyy"))-1));

        Document projection = new Document();
        projection.append("amount", "$amount");
        projection.append("corp_code", "$corp_code");
        projection.append("corp_name", "$corp_name");

        projection.append("_id", 0);

        FindIterable<Document> rs = col.find(new Document()).projection(projection);

        ArrayList rlist = new ArrayList<CorpDTO>();
        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }

            String m = CmmUtil.nvl(doc.getString("amount"));

            if(48 > m.charAt(0) || m.charAt(0) > 57){
                m = m.replace(m.charAt(0), '-');
            }

            Long amount = Long.parseLong(m);
            String corpCode = CmmUtil.nvl(doc.getString("corp_code"));
            String corpName = CmmUtil.nvl(doc.getString("corp_name"));

            CorpDTO corpDTO = new CorpDTO();
            corpDTO.setCorpCode(corpCode);
            corpDTO.setCorpName(corpName);
            corpDTO.setAmount(amount);

            rlist.add(corpDTO);
        }


        log.info(this.getClass().getName() + "CorpMapper :end");
        return rlist;
    }

}

