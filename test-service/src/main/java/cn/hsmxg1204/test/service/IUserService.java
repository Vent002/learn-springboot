package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.entity.UserInfo;

import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 10:44
 */
public interface IUserService {
    UserInfo selectUserById(String id);
    UserInfo selectUserByNameAndPwd(String userName,String password);

    List<UserInfo> getAllUser();
    List<UserInfo> searchByName(String userName);

    int insertUser(UserInfo userInfo,String roleType);

    int updateUser(UserInfo userInfo);

    int deleteUser(String id);
}
