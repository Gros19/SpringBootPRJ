package kopo.poly.persistance.redis.impl;

import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper {

    public final RedisTemplate<String, Object> redisDB;
    public MyRedisMapper(RedisTemplate<String, Object> redisDB){
        this.redisDB = redisDB;
    }



    /*유효 시간동안 (인증번호, 이메일)저장*/
    @Override
    public int setData(String rediskey, String value) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        int res = 0;
        redisDB.setValueSerializer(new StringRedisSerializer());
        redisDB.setKeySerializer(new StringRedisSerializer());

        log.info(this.getClass().getName()+"setData"+ rediskey);
        log.info(this.getClass().getName()+"setData" +value);
        value = EncryptUtil.encAES128CBC(value);
        log.info(this.getClass().getName()+"setData enc" +value);


        if(!redisDB.hasKey(rediskey)){
            redisDB.opsForValue().set(rediskey, value);
            res = 1;
        }
        redisDB.expire(rediskey, 5, TimeUnit.MINUTES);
        return res = 1;
    }
    /*인증번호로 이메일 가져오기*/
    @Override
    public String getData(String rediskey) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setKeySerializer(new StringRedisSerializer());



        String res =  (String)redisDB.opsForValue().get(rediskey);
        log.info(this.getClass().getName()+"getData"+ rediskey);
        log.info(this.getClass().getName()+"getData" +res);
        return res;
    }

    /*삭제*/
    @Override
    public int deleteData(String rediskey){
        int res =0;
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setKeySerializer(new StringRedisSerializer());

        redisDB.delete(rediskey);
        res = 1;

        return res;
    }


}
