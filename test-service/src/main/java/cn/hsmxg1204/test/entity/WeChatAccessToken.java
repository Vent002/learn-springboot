package cn.hsmxg1204.test.entity;

import lombok.Data;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-11 19:08
 */
@Data
public class WeChatAccessToken {
    private String openId;
    private String accessToken;
    // 过期时间
    private long expiresIn;
}
