package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.test.mapper.IRoleMapper;
import cn.hsmxg1204.test.service.IRoleService;
import cn.hsmxg1204.test.entity.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-23 8:59
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired(required = true)
    IRoleMapper iRoleMapper;
    @Override
    public RoleInfo getRoleInfo(int id) {
        return iRoleMapper.getRoleInfo(id);
    }
}
