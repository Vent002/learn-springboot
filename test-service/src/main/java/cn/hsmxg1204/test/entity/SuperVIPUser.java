package cn.hsmxg1204.test.entity;

import lombok.Data;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-19 9:18
 */
@Data
public class SuperVIPUser extends UserInfo{
    public SuperVIPUser(){
        super();
    }
    private String vipType = "super";
}
