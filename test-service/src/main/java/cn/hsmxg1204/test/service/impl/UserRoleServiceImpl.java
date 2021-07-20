package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.test.mapper.IUserRoleMapper;
import cn.hsmxg1204.test.service.IUserRoleService;
import cn.hsmxg1204.test.entity.UserRoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-23 8:59
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    IUserRoleMapper iUserRoleMapper;
    @Override
    public List<UserRoleInfo> listUserRoleById(String id) {
        return iUserRoleMapper.listUserRoleById(id);
    }
}
