package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.entity.UserRoleInfo;

import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-22 19:53
 */
public interface IUserRoleService {
    List<UserRoleInfo> listUserRoleById(String id);
}
