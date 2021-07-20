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
 * @date 2021-04-13 10:39
 */
@Data
public class UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userName;
    private String password;
    private String email;
//    private String vipType = "normal";
    public UserInfo(){

    }
    public UserInfo(String id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
