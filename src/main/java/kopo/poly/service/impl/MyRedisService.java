package kopo.poly.service.impl;

import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service("MyRedisService")

public class MyRedisService implements IMyRedisService {

    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;

    @Override
    public int setData(String rediskey, String value) {
        return 0;
    }

    @Override
    public String getData(String rediskey) {
        return null;
    }

    @Override
    public int deleteData(String rediskey) {
        return 0;
    }

//    /**
//     * Redis에 String 구조로 저장된 데이터 삭제하기
//     */
//    @Override
//    public boolean deleteDataString() throws Exception {
//        log.info(this.getClass().getName() + ".deleteDataString start !");
//        String rediskey = "myRedis_Hash";
//        boolean res = myRedisMapper.deleteDataJSON(rediskey);
//        log.info(this.getClass().getName() + ".deleteDataString end !");
//        return res;
//    }
//
//    /**
//     * JSON 구조로 저장된 데이터 삭제하기
//     */
//    @Override
//    public boolean deleteDataJSON() throws Exception {
//        log.info(this.getClass().getName() + ".deleteDataJSON start !");
//        String rediskey = "myRedis_Zset_JSON";
//        boolean res = myRedisMapper.deleteDataJSON(rediskey);
//        log.info(this.getClass().getName() + ".deleteDataJSON end !");
//
//        return res;
//
//    }
//
//    /**
//     * ZSet 타입의 JSON 형태로 저장된 값 가져오기
//     */
//    @Override
//    public Set<RedisDTO> getRedisZSetJSON() throws Exception {
//        log.info(this.getClass().getName() + ".getRedisZSetJSON start !");
//
//        String redisKey = "myRedis_Zset_JSON";
//
//        Set<RedisDTO> rSet = myRedisMapper.getRedisZSetJSON(redisKey);
//
//        if (rSet ==null){
//            rSet = new HashSet<>();
//        }
//        log.info(this.getClass().getName() + ".getRedisZSetJSON start !");
//        return rSet;
//    }
//
//    /**
//     * Zset 타입에 JSON 형태로 저장하기
//     */
//    @Override
//    public int saveRedisZSetJSON() throws Exception {
//        log.info(this.getClass().getName() + ".saveRedisZSetJSON start !");
//
//        String redisKey = "myRedis_Zset_JSON";
//
//        List<RedisDTO> pList = new LinkedList<>();
//
//        for(int i = 0; i < 10; i++){
//            RedisDTO pDTO = new RedisDTO();
//            pDTO.setTest_text(i + "번째 데이터 입니다.");
//            pDTO.setName("ㄱㅂㄱ[" + i + "]");
//            pDTO.setAddr("서울");
//            pDTO.setEmail("@naver.com");
//
//            pList.add(pDTO);
//            pDTO = null;
//        }
//
//        int res = myRedisMapper.saveRedisZSetJSON(redisKey, pList);
//
//        log.info(this.getClass().getName() + ".saveRedisZSetJSON end !");
//
//        return res;
//    }
//
//    /**
//     * set 타입에 JSON 형태로 람다식을 이용하여 저장하기
//     */
//    @Override
//    public int saveRedisSetJSONRamda() throws Exception {
//        log.info(this.getClass().getName() + ".saveRedisSetJSONRamda start !");
//
//        String redisKey = "myRedis_Set_JSON";
//
//        Set<RedisDTO> pSet = new HashSet<>();
//        for(int i = 0; i < 10; i++){
//            RedisDTO pDTO = new RedisDTO();
//            pDTO.setTest_text(i + "번째 데이터 입니다.");
//            pDTO.setName("ㄱㅂㄱ[" + i + "]");
//            pDTO.setAddr("서울");
//            pDTO.setEmail("@naver.com");
//
//            pSet.add(pDTO);
//            pDTO = null;
//        }
//
//        int res = myRedisMapper.saveRedisSetJSONRamda(redisKey, pSet);
//
//        log.info(this.getClass().getName() + ".saveRedisSetJSONRamda end !");
//
//        return res;
//    }
//
//    /**
//     * Hash 타입에 문자열 형태로 저장된 값 가져오기
//     */
//    @Override
//    public RedisDTO getRedisHash() throws Exception {
//        log.info(this.getClass().getName() + "getRedisHash start !");
//
//        String redisKey = "myRedis_Hash";
//        RedisDTO rDTO = myRedisMapper.getRedisHash(redisKey);
//
//        if(rDTO == null){
//            rDTO = new RedisDTO();
//        }
//        log.info(this.getClass().getName() + "getRedisHash end !");
//        return rDTO;
//    }
//
//    /**
//     * Hash 타입에 문자열 형태로 저장하기
//     */
//    @Override
//    public int saveRedisHash() throws Exception {
//        log.info(this.getClass().getName() + "saveRedisHash start !");
//        String redisKey = "myRedis_Hash";
//
//        RedisDTO pDTO = new RedisDTO();
//        pDTO.setName("ㄱㅂㄱ");
//        pDTO.setAddr("서울");
//        pDTO.setEmail("@naver.com");
//
//        int res = myRedisMapper.saveRedisHash(redisKey, pDTO);
//
//        log.info(this.getClass().getName() + "saveRedisHash end !");
//        return res;
//    }
//
//    @Override
//    public List<RedisDTO> getRedisListJSONRamda() throws Exception {
//        log.info(this.getClass().getName() + ".getRedisListJSONRamda start");
//
//        String redisKey = "myRedis_List_JSON_Ramda";
//
//        List<RedisDTO> rList = myRedisMapper.getRedisListJSON(redisKey);
//
//        if(rList.size() < 1){
//            rList = new ArrayList<>();
//        }
//
//
//        log.info(this.getClass().getName() + ".getRedisListJSONRamda end");
//        return rList;
//    }
//
//    @Override
//    public int saveRedisListJSONRamda() throws Exception {
//        log.info(this.getClass().getName() + ".saveRedisListJSONRamda start !");
//
//        String redisKey = "myRedis_List_JSON_Ramda";
//
//        List<RedisDTO> pList = new ArrayList<>();
//
//        for(int i = 0; i < 100; i++){
//            RedisDTO pDTO = new RedisDTO();
//            pDTO.setName("ㄱㅂ["+i+"]");
//            pDTO.setEmail("@naver.com");
//            pDTO.setTest_text(i + "번째 데이터입니다.");
//            pDTO.setAddr("서울");
//            pList.add(pDTO);
//            pDTO = null;
//        }
//
//        int res = myRedisMapper.saveRedisListJSONRamda(redisKey, pList);
//
//        log.info(this.getClass().getName() + ".saveRedisListJSONRamda end !");
//        return res;
//    }
//
//    @Override
//    public List<RedisDTO> getRedisListJSON() throws Exception {
//        log.info(this.getClass().getName() + ".getRedisListJSON start!");
//        String redisKey = "myRedis_List_JSON";
//
//        List<RedisDTO> rList = myRedisMapper.getRedisListJSON(redisKey);
//
//        if( rList == null ){
//            rList = new LinkedList<>();
//        }
//
//
//        log.info(this.getClass().getName() + ".getRedisListJSON end!");
//        return rList;
//    }
//
//    @Override
//    public int saveRedisListJSON() throws Exception {
//        log.info(getClass().getName() + "saveRedisListJSON start");
//
//        String redisKey = "myRedis_List_JSON";
//
//        List<RedisDTO> pList = new LinkedList<>();
//
//        for(int i = 0; i < 10; i++){
//            RedisDTO pDTO = new RedisDTO();
//            pDTO.setTest_text(i + "번째 데이터입니다.");
//            pDTO.setEmail("@naver.com");
//            pDTO.setAddr("서울");
//            pDTO.setName("ㄱㅂ");
//            pList.add(pDTO);
//            pDTO = null;
//        }
//        int res = myRedisMapper.saveRedisListJSON(redisKey, pList);
//
//        log.info(getClass().getName() + "saveRedisListJSON end");
//
//        return res;
//    }
//
//    @Override
//    public List<String> getRedisList() throws Exception {
//
//        log.info(getClass().getName() + ".getRedisList start");
//
//        String redisKey = "myRedis_List";
//        List<String> rList = myRedisMapper.getRedisList(redisKey);
//
//        if(rList == null){
//            rList = new LinkedList<>();
//        }
//        log.info(getClass().getName() + ".getRedisList end");
//        return rList;
//    }
//
//    @Override
//    public int saveRedisList() throws Exception {
//        int res = 0;
//        log.info(getClass().getName() + ".saveRedisList start!");
//
//        String redisKey = "myRedis_List";
//
//        List<RedisDTO> pList = new LinkedList<>();
//
//        for(int i = 0; i < 10; i++){
//
//            RedisDTO pDTO = new RedisDTO();
//            pDTO.setTest_text(i + "번째 데이터입니다.");
//
//            pList.add(pDTO);
//            pDTO = null;
//        }
//        res =myRedisMapper.saveRedisList(redisKey, pList);
//
//        log.info(getClass().getName() + ".saveRedisList end!");
//
//        return res;
//    }
//
//    @Override
//    public int saveRedisString() throws Exception {
//        log.info(this.getClass().getName() + ".saveRedisString Start!");
//        String redisKey = "myRedis_String";
//        RedisDTO pDTO = new RedisDTO();
//        pDTO.setTest_text("난 String타입으로 저장할 일반 문자열");
//
//        int res = myRedisMapper.saveRedisString(redisKey, pDTO);
//
//        log.info(this.getClass().getName() + ".saveRedisString End!");
//        return res;
//    }
//
//    @Override
//    public RedisDTO getRedisString() throws Exception {
//        log.info(getClass().getName() + ".getRedisString Start!");
//        String redisKey = "myRedis_String";
//        RedisDTO rDTO = myRedisMapper.getRedisString(redisKey);
//
//        if(rDTO == null){
//            rDTO = new RedisDTO();
//        }
//        log.info(getClass().getName() + ".getRedisString End!");
//        return rDTO;
//
//    }
//
//
//    @Override
//    public int saveRedisStringJSON() throws Exception {
//        log.info(this.getClass().getName() + "saveRedisStringJSON start!");
//        String redisKey =  "myRedis_String_JSON";
//        RedisDTO pDTO = new RedisDTO();
//
//        pDTO.setTest_text("난 String타입에 JSON구조로 저장할 문자열");
//        pDTO.setAddr("김포시");
//        pDTO.setEmail("asdf@asdf.com");
//        pDTO.setName("도훈");
//
//        int res = myRedisMapper.saveRedisStringJSON(redisKey, pDTO);
//        log.info(this.getClass().getName() + "saveRedisStringJSON end!");
//        return res;
//    }
}
