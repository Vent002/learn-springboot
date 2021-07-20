package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.test.constant.WeChatConstant;
import cn.hsmxg1204.test.entity.WeChatAccessToken;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-11 19:03
 */
@Service
@Slf4j
public class WeChatAccessTokenServiceImpl {
    @Value("${wechat.app-id:}")
    private String appId;
    @Value("${wechat.app-secret:}")
    private String appSecret;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取微信公众号 AccessToken 和 openId
     * @param code 微信公众号返回code ,用于获取授权信息
     * @return
     */
    public WeChatAccessToken getAccessTokenAndOpenId(String code){
        WeChatAccessToken accessToken = null;
        try{
            String URL = MessageFormat.format(WeChatConstant.WEB_LOGIN_TOKEN_URL, appId, appSecret, code);
            JSONObject forObject = restTemplate.getForObject(URL, JSONObject.class);
            if(forObject != null){
                if( !StringUtils.isEmpty(forObject.getString("access_token")) ){
                    accessToken = new WeChatAccessToken();
                    accessToken.setAccessToken(forObject.getString("access_token"));
                    accessToken.setOpenId(forObject.get("openid").toString());
                    accessToken.setExpiresIn(System.currentTimeMillis() + ((Integer.parseInt(forObject.get("expires_in").toString()) - 600) * 1000L));
                }else{
                    log.warn("fail:{}",forObject);
                }
            }else {
                log.warn("result is null : {},request URL is '{}'",forObject, URL);
            }
        }catch (Exception e){
            log.warn("fail:{}",e.getMessage());
            e.printStackTrace();
        }
        return accessToken;
    }

    /**
     * 获取微信用户信息
     * @param code
     * @return
     */
    public JSONObject getWeChatUserInfo(String code){
        WeChatAccessToken accessToken = this.getAccessTokenAndOpenId(code);
        if(accessToken == null){
            throw new RuntimeException("获取accessToken失败");
        }
        JSONObject forObject = null;
        String URL = MessageFormat.format(WeChatConstant.USER_INFO_URL, accessToken.getAccessToken(), accessToken.getOpenId());
        try{
            forObject = restTemplate.getForObject(URL, JSONObject.class);
            if(!StringUtils.isEmpty(forObject.get("openid"))){
                return forObject;
            }else{
                throw new RestClientException("获取微信用户信息失败");
            }
        }catch (Exception e){
            log.warn("wechat UserInfo fail : {}",e.getMessage());
            e.printStackTrace();
            forObject = null;
        }
        return forObject;
    }

    /**
     * 刷新accessToken
     * @param refreshToken
     * @return
     */
    public JSONObject refreshToken(String refreshToken){
        String URL = MessageFormat.format(WeChatConstant.REFRESH_TOKEN_URL, appId, refreshToken);
        JSONObject forObject = restTemplate.getForObject(URL, JSONObject.class);
        return forObject;
    }

}
