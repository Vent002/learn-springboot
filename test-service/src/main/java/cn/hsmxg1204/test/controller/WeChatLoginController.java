package cn.hsmxg1204.test.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-11 17:19
 */
@RestController
@RequestMapping("/wx/login")
@Api(tags = "获取微信公众号授权数据")
@Slf4j
public class WeChatLoginController {

    @RequestMapping(value = "/wechatCode",method = RequestMethod.GET)
    public void getWeChatCode(HttpServletRequest request){

    }

}
