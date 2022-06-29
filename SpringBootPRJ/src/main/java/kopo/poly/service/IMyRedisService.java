package kopo.poly.service;

public interface IMyRedisService {

    /*유효 시간동안 (인증번호, 이메일)저장*/
    int setData(String rediskey, String value);

    /*인증번호로 이메일 가져오기*/
    String getData(String rediskey);

    /*삭제*/
    int deleteData(String rediskey);
//    /**
//     * Redis에 String 구조로 저장된 데이터 삭제하기
//     * */
//    boolean deleteDataString() throws Exception;
//
//    /**
//     * JSON 구조로 저장된 데이터 삭제하기
//     */
//    boolean deleteDataJSON() throws Exception;
//    /**
//     * ZSet 타입의 JSON 형태로 저장된 값 가져오기*/
//    Set<RedisDTO> getRedisZSetJSON() throws Exception;
//
//    /**
//     * Zset 타입에 JSON 형태로 저장하기*/
//    int saveRedisZSetJSON() throws Exception;
//    /**
//     * set 타입에 JSON 형태로 람다식을 이용하여 저장하기
//     * */
//    int saveRedisSetJSONRamda() throws Exception;
//
//    /**
//     * Hash 타입에 문자열 형태로 저장된 값 가져오기*/
//    RedisDTO getRedisHash() throws Exception;
//
//    /**
//     * Hash 타입에 문자열 형태로 저장하기
//     * */
//    int saveRedisHash() throws Exception;
//
//    /**
//     * List타입에 JSON 형태로 저장된 데이터 가져오기
//     *
//     * 람다식에 저장된 Redis 키 값이 달라서 함수 별도로 만듦
//     * 매퍼 호출은 앞서 만든 getRedisListJSON 호출함
//     */
//    List<RedisDTO> getRedisListJSONRamda() throws Exception;
//
//    /**
//     * List 타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
//     * @return res
//     */
//    int saveRedisListJSONRamda() throws Exception;
//
//    /**
//     * List 타입에 JSON 형태로 저장된 데이터 가져오기
//     * @return List<RedisDTO>
//     **/
//    List<RedisDTO> getRedisListJSON() throws Exception;
//    /**
//     * List타입에 JSON 형태로 저장하기(동기화)
//     * @return res
//     * */
//    int saveRedisListJSON() throws Exception;
//
//    /**List 타입에 여러 문자열로 저장된 데이터 가져오기*/
//    List<String> getRedisList() throws Exception;
//
//    /**
//    List 타입에 여러 문자열로 저장하기(동기화)
//    * */
//    int saveRedisList() throws Exception;
//
//    /**
//    * String 타입 저장하기
//    * */
//    int saveRedisString() throws Exception;
//
//    /**
//    * String 타입 가져오기
//    * */
//    RedisDTO getRedisString() throws Exception;
//
//    /**
//    * String JSON 타입 저장하기*/
//    int saveRedisStringJSON() throws Exception;
}
