package cn.hsmxg1204.test.constant;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-11 17:28
 */
public class WeChatConstant {
    /**
     * @Fields WEB_LOGIN_TOKEN_URL :  网页登录授权时，使用code换取openid的URL
     */
    public static final String WEB_LOGIN_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
    /**
     * @Fields ACCESS_TOKEN_URL :  获取公众号的ACCESS_TOKEN
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
    /**
     * @Fields ACCESS_TOKEN_URL :  调起微信登录的地址
     */
    public static final String WECHAT_LOGIN = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    public static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";

    public static final String REFRESH_TOKEN_URL = " https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={0}&grant_type=refresh_token&refresh_token={1}";

}
