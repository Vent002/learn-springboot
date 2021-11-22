package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.core.utils.MyUtils;
import cn.hsmxg1204.test.entity.UserInfo;
import cn.hsmxg1204.test.entity.UserRoleInfo;
import cn.hsmxg1204.test.mapper.IUserMapper;
import cn.hsmxg1204.test.mapper.IUserRoleMapper;
import cn.hsmxg1204.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 10:45
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = true)
    IUserMapper iUserMapper;

    @Autowired
    IUserRoleMapper iUserRoleMapper;
    @Override
    public UserInfo selectUserById(String id) {
        return iUserMapper.selectUserById(id);
    }

    @Override
    public UserInfo selectUserByNameAndPwd(String userName, String password) {
        UserInfo userInfo;
        if("hsmxg".equals(userName)){
            userInfo = iUserMapper.selectUserByNameAndPwd(userName,password);
        }else {
            userInfo = iUserMapper.selectUserByName(userName);
            if(userInfo != null && !new BCryptPasswordEncoder().matches(password,userInfo.getPassword())){
                throw new MyException("密码不正确");
            }
        }

        return userInfo;
    }

    @Override
    public List<UserInfo> getAllUser() {
        return iUserMapper.getAllUser();
    }

    @Override
    public List<UserInfo> searchByName(String userName) {
        return iUserMapper.seachByName(userName);
    }

    @Override
    public int insertUser(UserInfo userInfo,String roleType) {
        userInfo.setId(MyUtils.getRandomNum(6));
        userInfo.setPassword(MyUtils.passWordEncoder("1231"));
        // 测试 1 admin 密码不加密
        if("1".equals(roleType)){
            userInfo.setPassword(userInfo.getPassword());
        }
        int result = 0;
        if (iUserMapper.insertUser(userInfo) == 1){
            //新建用户默认权限为user
            UserRoleInfo userRoleInfo = new UserRoleInfo();
            userRoleInfo.setUserId(Integer.parseInt(userInfo.getId()));
            userRoleInfo.setRoleId(Integer.parseInt(roleType));
            result = iUserRoleMapper.saveUserRoleInfo(userRoleInfo);
        }
        return result;
    }

    @Override
    public int updateUser(UserInfo userInfo) {
        return iUserMapper.updateUser(userInfo.getId(), userInfo.getUserName(), userInfo.getPassword());
    }

    @Override
    @Transactional
    public int deleteUser(String id) {
        int i = iUserMapper.deleteUser(id);
        if(i == 1){
            return 1;
        }else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new MyException("删除失败");
        }
    }

}
