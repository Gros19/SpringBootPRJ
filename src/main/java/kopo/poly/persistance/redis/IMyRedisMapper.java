package kopo.poly.persistance.redis;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IMyRedisMapper {

    /*유효 시간동안 (인증번호, 이메일)저장*/
    int setData(String rediskey, String value) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    /*인증번호로 이메일 가져오기*/
    String getData(String rediskey) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    /*삭제*/
    int deleteData(String rediskey);
//
//    /**
//     * Redis에 String 구조로 저장된 데이터 삭제하기
//     * @param redisKey 삭제할 RedisKey
//     * @return 결과값
//     * */
//    boolean deleteDataString(String redisKey) throws Exception;
///**
// * Redis에 JSON 구조로 저장된 데이터 삭제하기
// * @param redisKey 삭제할 RedisKey
// * @return 결과 값
// * */
//boolean deleteDataJSON(String redisKey) throws Exception;
//
//
//    /**
//     * ZSet 타입에 JSON 형태로 저장된 값 가져오기
//     * @param redisKey 가져올 RedisKey
//     * @return 결과 값
//    * */
//    Set<RedisDTO> getRedisZSetJSON(String redisKey) throws Exception;
//
//    /**
//     * ZSet 타입에 JSON 형태로 저장하기
//     * @param redisKey Redis 저장키
//     * @param pList 저장할 정보들
//     * @return res (저장 성공여부)
//     * */
//    int saveRedisZSetJSON(String redisKey, List<RedisDTO> pList) throws Exception;
//
//    /**
//     * set 타입에 JSON 형태로 람다식을 이용하여 저장하기
//     * @param (redisKey Redis저장키)
//     * @param (pSet 저장할 정보들)
//     * @return //저장 성공여부
//     * */
//    int saveRedisSetJSONRamda(String redisKey, Set<RedisDTO> pSet) throws Exception;
//
//    /**
//     * Hash 타입에 문자열 형태로 저장된 값 가져오기
//     * @param (redisKey 가져올 RedisKey)
//     * @return 결과값
//     * */
//    RedisDTO getRedisHash(String redisKey) throws Exception;
//
//
//    /**
//     * @param redisKey Redis 저장 키
//     * @param pDTO 저장할 정보들
//     * @return 저장 성공 여부
//     * */
//    int saveRedisHash(String redisKey, RedisDTO pDTO) throws Exception;
//
//
//
//    /**
//     * List타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
//     * @param redisKey Redis저장 키
//     * @param pList 저장할 정보들
//     * @return 저장 성공 여부
//     */
//    int saveRedisListJSONRamda(String redisKey, List<RedisDTO> pList) throws Exception;
//
//    /**
//    * List타입에 JSON 형태로 저장된 데이터 가져오기
//    * @param redisKey 가져올 RedisKey
//    * @return 결과 값
//     * */
//    List<RedisDTO> getRedisListJSON(String redisKey) throws Exception;
//
//    /**
//    * List타입에 Json형태로 저장하기(동기화)
//    * @param redisKey Redis 저장 키
//    * @param pList 저장할 정보들
//    * @return 저장 성공 여부
//    * */
//    int saveRedisListJSON(String redisKey, List<RedisDTO>pList)throws Exception;
//
//    /**List타입에 여러 문자열로 저장된 데이터 가져오기
//    * @param redisKey 가져올 RedisKey
//    * @return 결과값
//    * */
//    List<String> getRedisList(String redisKey) throws Exception;
//
//    /**
//     * List 타입에 여러 문자열로 저장하기(동기화)
//     *
//     * @param redisKey Redis 저장 키
//     * @param pList 저장할 정보들
//     * @return 저장 성공 여부
//     * */
//    int saveRedisList(String redisKey, List<RedisDTO> pList)throws Exception;
//
//
//    /**
//    * String 타입 저장하기
//    * @param redisKey Redis저장 키
//    * @param pDTO 저장할 정보
//    * @return 저장 성공 여부*/
//    int saveRedisString(String redisKey, RedisDTO pDTO) throws Exception;
//
//    /**
//    * String 타입 가져오기
//    * @param redisKey 가져올 RedisKey
//    * @return 결과 값*/
//    RedisDTO getRedisString(String redisKey) throws Exception;
//
//    /**
//    * String 타입에 JSON 형태로 저장하기
//    * @param redisKey Redis저장 키
//    * @param pDTO 저장할 젖ㅇ보
//    * @return 결과 값*/
//    int saveRedisStringJSON(String redisKey, RedisDTO pDTO) throws Exception;

}
