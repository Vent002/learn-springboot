package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.test.constant.UserSession;
import cn.hsmxg1204.test.entity.CurrentUser;
import cn.hsmxg1204.test.entity.UserInfo;
import cn.hsmxg1204.test.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import static cn.hsmxg1204.test.constant.RedisConstant.KEY_USERS;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-16 14:29
 */
@Service
public class UserInfoService extends BaseService{
    @Autowired
    StringRedisTemplate redisTemplate;


    public CurrentUser getUserInfoByRedisKey(String token){
        CurrentUser userInfo = new CurrentUser();
        try {
            UserInfo info = getUserFromRedis(token);
            userInfo.setUserInfo(info);

        } catch (JsonProcessingException e) {
            UserInfo user = iUserService.selectUserById(token);
            if(user != null){
                userInfo.setUserInfo(user);
            }
        }
        return userInfo;
    }

    private UserInfo getUserFromRedis(String token) throws JsonProcessingException {
        if(token != null && !token.isEmpty()){
            String s = redisService.hget(KEY_USERS, token);
            if(s != null){
                return objectMapper.readValue(s,UserInfo.class);
            }
        }
        return null;
    }
}
