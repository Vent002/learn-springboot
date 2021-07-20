package cn.hsmxg1204.test.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-22 18:33
 */
@Data
public class UserRoleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int roleId;
}
