package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.test.entity.RoleInfo;
import cn.hsmxg1204.test.entity.UserInfo;
import cn.hsmxg1204.test.entity.UserRoleInfo;
import cn.hsmxg1204.test.service.IRoleService;
import cn.hsmxg1204.test.service.IUserRoleService;
import cn.hsmxg1204.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-22 18:47
 */
@Service
public class LightSwordUserDetailServiceImpl implements UserDetailsService {

    private static String userRoleStr = "admin";

    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    IUserRoleService iUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        List<UserInfo> infos = iUserService.searchByName(userName);
        if(infos.size() != 1){
            throw new MyException("查询用户信息错误,username = {}",userName);
        }
        List<UserRoleInfo> userRoleInfo = iUserRoleService.listUserRoleById(infos.get(0).getId());

        for(UserRoleInfo uRoleInfo : userRoleInfo){
            RoleInfo roleInfo = iRoleService.getRoleInfo(uRoleInfo.getRoleId());

            if(!StringUtils.isEmpty(roleInfo.getRole())){
                authorities.add(new SimpleGrantedAuthority(roleInfo.getRole()));
            }
        }
        //在进行用户注册时注入
//        auth.userRegisterService(userService).passwordEncoder(new BCryptPasswordEncoder());
        System.out.println(authorities+"\t,密码："+infos.get(0).getPassword());
        if(contain(authorities)){
            return new User(userName, new BCryptPasswordEncoder().encode(infos.get(0).getPassword()),authorities);
        }else {
            return new User(userName, infos.get(0).getPassword(), authorities);
        }
    }

    public boolean contain(List<SimpleGrantedAuthority> roles){
        if(roles.size()>1) {
            for (int i = 0; i < roles.size(); i++) {
                if(userRoleStr.contains(roles.get(i).toString())){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}
