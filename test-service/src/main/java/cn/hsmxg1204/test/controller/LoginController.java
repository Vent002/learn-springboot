package cn.hsmxg1204.test.controller;

import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.test.entity.UserInfo;
import cn.hsmxg1204.test.service.impl.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static cn.hsmxg1204.test.constant.RedisConstant.KEY_USERS;
import static cn.hsmxg1204.test.constant.RedisConstant.KEY_USER_ID;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-03-14 14:07
 */
@RestController
@RequestMapping("/login")
@Api(value = "login",description = "login")
public class LoginController extends BaseService {

    @ApiOperation(value = "/user")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    private String login(@RequestParam(value = "userName") String username, @RequestParam(value = "password") String password){
        try {
            UserInfo userInfo = iUserService.selectUserByNameAndPwd(username, password);
            if (userInfo == null) {
                throw new MyException("当前信息未注册");
            }
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userInfo);
//        redisTemplate.opsForValue().set(username, jsonObject.toJSONString(),60 * 30, TimeUnit.SECONDS);
            putUserIntoRedis(userInfo);
        }catch (JsonProcessingException e){
            throw new MyException("Sign in failed!");
        }
        return "index";
    }

    public void putUserIntoRedis(UserInfo userInfo) throws JsonProcessingException {
        redisService.hset(KEY_USERS, userInfo.getUserName(), objectMapper.writeValueAsString(userInfo));
    }

    public void putUserIntoRedisWithExpire(UserInfo userInfo){
        redisService.settex(KEY_USER_ID,60 * 5,userInfo.getUserName() );
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
