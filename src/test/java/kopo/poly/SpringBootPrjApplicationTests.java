package kopo.poly;

import kopo.poly.persistance.redis.IMyRedisMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@SpringBootTest
class SpringBootPrjApplicationTests {

    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;

    @Test
    void contextLoads() throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        log.info(this.getClass().getName()+"##########");
        log.info(this.getClass().getName()+"##########");
        log.info(this.getClass().getName()+"##########");
        log.info(this.getClass().getName()+"##########");

        myRedisMapper.setData("32", "asdfasd");
        String res = myRedisMapper.getData("32");
        log.info(this.getClass().getName()+"myRedisMapper.getData(\"32\"); :: " + res);
        if(res == null){

            log.info(this.getClass().getName()+"yes res is null #####");
        }else{
            myRedisMapper.deleteData("33333");

            log.info(this.getClass().getName()+"  #####"+myRedisMapper.getData("32"));
        }


    }

}
