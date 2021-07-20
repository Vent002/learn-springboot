package cn.hsmxg1204.test.entity;

import lombok.Data;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-19 9:20
 */
@Data
public class VipUser extends UserInfo{
    public VipUser(){
        super();
    }
    private String vipType = "vip";
}
