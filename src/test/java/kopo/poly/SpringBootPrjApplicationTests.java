package kopo.poly;

import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.util.DateUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
class SpringBootPrjApplicationTests {

    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;


    @Test
    void contextLoads() throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {


        log.info(this.getClass().getName() + "SpringBootPrjApplicationTests :start");
        log.info(this.getClass().getName() + "SpringBootPrjApplicationTests :start");

        log.info(this.getClass().getName() + "SpringBootPrjApplicationTests Long.parseLong(\"-5784353000\") = " + Long.parseLong("-5784353000"));
        log.info(this.getClass().getName() + "SpringBootPrjApplicationTests Long.parseLong(\"-5784353000\") = " + Integer.parseInt("-5784353000"));

    }

}
