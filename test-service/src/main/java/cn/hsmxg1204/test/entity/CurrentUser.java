package cn.hsmxg1204.test.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-16 14:31
 */
@Data
public class CurrentUser implements Serializable {
    private UserInfo userInfo;
}
