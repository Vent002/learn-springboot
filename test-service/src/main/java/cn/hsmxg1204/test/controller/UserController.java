package cn.hsmxg1204.test.controller;

import cn.hsmxg1204.common.service.vilad.Validators;
import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.test.config.dbconfig.RoutingDataSourceContext;
import cn.hsmxg1204.test.config.dbconfig.RoutingWithSlave;
import cn.hsmxg1204.test.constant.UserSession;
import cn.hsmxg1204.test.entity.reqs.UserInfoDTO;
import cn.hsmxg1204.test.entity.UserInfo;
import cn.hsmxg1204.test.service.impl.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.hsmxg1204.test.constant.RedisConstant.KEY_USERS;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 10:47
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseService {

    @GetMapping("/simple/{id}")
    public UserInfo findUserById(@PathVariable String id){
        UserInfo user = null;
        try {
            log.info("读取缓存");
            user = getUserFromRedis(UserSession.getCurrentUser().getUserInfo().getUserName());
            System.out.println(UserSession.getCurrentUser());
            if(user == null){
                user = iUserService.selectUserById(id);
            }
        }catch (JsonProcessingException e){
            user = iUserService.selectUserById(id);
        }
        return user;
    }

    @GetMapping("/simple/users")
    public List<UserInfo> getAllUser(){
        log.info("{}",RoutingDataSourceContext.getDataSourceKey());
        return iUserService.getAllUser();
    }

    @PutMapping("/simple/user")
    public UserInfo insertUser(@RequestParam(value = "id")String id,
                               @RequestParam(value = "userName")String userName,
                               @RequestParam(value = "password")String password){
        validators.validate(id,userName,password);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        int i = iUserService.updateUser(userInfo);
        log.info("{}",i);
        if(i > 0){
            return userInfo;
        }else{
            return null;
        }
    }
    @PostMapping("/simple/user")
    public UserInfo addUser(@RequestBody UserInfoDTO userInfoDTO){
        List<UserInfo> userInfos = iUserService.searchByName(userInfoDTO.getUserName());
        if(userInfos.size()>0){
            throw new MyException("用户名已存在");
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        int i = iUserService.insertUser(userInfo,userInfoDTO.getRoleType());
        log.info("{}",i);
        if(i > 0){
            return userInfo;
        }else{
            return null;
        }
    }

    @DeleteMapping("/simple/{id}")
    public int deleteUser(@PathVariable String id){
        return iUserService.deleteUser(id);
    }

    @GetMapping("/simple/profile")
    @RoutingWithSlave
    public UserInfo profile(){
        log.info("database: {}",RoutingDataSourceContext.getDataSourceKey());
//        UserDao userDao = (UserDao) ProxyUtils.doCheckForDao(UserDao.class).create();
//        userDao.getUserByEmail("hsmxg1204@qq.com");
        UserInfo user = userService.getUserByEmail("hsmxg1204@qq.com");
        if(user == null){
            throw new MyException("please sign in");
        }
        return user;
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
