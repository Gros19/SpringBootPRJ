package kopo.poly.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.CorpDTO;
import kopo.poly.dto.MemberDTO;
import kopo.poly.persistance.mongodb.IMemberMapper;
import kopo.poly.persistance.mongodb.impl.CorpMapper;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMemberService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Service(value = "CorpService")
@NoArgsConstructor
public class CorpService{

    @Resource(name = "CorpMapper")
    CorpMapper corpMapper;


    /*초 분 시 일 월 요*/
    @Scheduled(cron = "0 0 10 * * *")
    public void insertCorp(){
        log.info(this.getClass().getName() + "CorpService :start" );


        ObjectMapper objectMapper = new ObjectMapper();
        Object objValue = null;

        try {
            URI uri = new URI("http://127.0.0.1:5000/");
            uri = new URIBuilder(uri).build();

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setMaxConnTotal(100)
                    .setMaxConnPerRoute(100)
                    .build();

            HttpResponse httpResponse = httpClient.execute(new HttpGet(uri));
            HttpEntity entity = httpResponse.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println("content = " + content);
            objValue = objectMapper.readValue(content, Object.class);
            System.out.println("objValue = " + objValue);

            HashMap object = (LinkedHashMap<String, Object>) objValue;

            String year = object.get("year")+"";
//            List<CorpDTO> olist = (ArrayList<CorpDTO>)object.get("data");


            List<LinkedHashMap<String, Object>> olist = (ArrayList<LinkedHashMap<String, Object>>)object.get("data");
            if(olist == null){
                olist = new ArrayList<>();
            }

            corpMapper.insertData(year, olist);


            Iterator it = olist.iterator();

            while(it.hasNext()){
                log.info(this.getClass().getName() + "getCorpCode() = " + new ObjectMapper().convertValue(it.next(), CorpDTO.class).getCorpCode());;
            }

        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("e.getMessage() = " + e.getCause());
            System.out.println("e.getMessage() = " + e.getStackTrace());
        }
        log.info(this.getClass().getName() + "CorpService :end");
    }



    public ArrayList<CorpDTO> getCorp() throws  Exception{
        ArrayList<CorpDTO> rlist = corpMapper.getData();

        if(rlist == null){
            new ArrayList<CorpDTO>();
        }

        return rlist;
    }

}
