package cn.hsmxg1204.test.intercepter;


import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.core.utils.MyUtils;
import cn.hsmxg1204.test.constant.UserSession;
import cn.hsmxg1204.test.entity.CurrentUser;
import cn.hsmxg1204.test.service.impl.UserInfoService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 14:55
 */
@Component
@Slf4j
public class UserSecurityInterceptor implements HandlerInterceptor {

    private UserInfoService userInfoService;

    private static List<String> pageList = new ArrayList<>();
    //当前用户线程标识
    private final static String REQUEST_ID = "MDC-KEY";
    //排除验证的url
    static{
        pageList.add("/login/user");
        pageList.add("/error");
        pageList.add("/static/");
        pageList.add("/webjars/*");
        pageList.add("/swagger");
        pageList.add("/test/");
        pageList.add("/my-app/csrf");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = String.valueOf(request.getRequestURI());
        System.out.println("请求地址=====>:\t"+url);
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return false;
        }else{
            String uuid = UUID.randomUUID().toString();
            MDC.put(REQUEST_ID, uuid);
            response.setCharacterEncoding("UTF-8");
            inject(request);
            if (contains(pageList,url)){
                String token = request.getHeader("token");
                if(StringUtils.isEmpty(token)){
                    errLog(request,"token为Null，请检查请求头");
                }
                CurrentUser currentUser = userInfoService.getUserInfoByRedisKey(token);
                if(StringUtils.isEmpty(currentUser.getUserInfo())){
                    errLog(request,"token无效");
                }
                UserSession.setCurrentUser(currentUser);

            }else{
                //不需要token验证的请求
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uuid = MDC.get(REQUEST_ID);
        log.info("remove requestId ({}) from logger", uuid);
        MDC.remove(REQUEST_ID);

    }
    /**
     * 判断当前请求地址是否应该被拦截
     * @param list 排除验证的URL
     * @param url 当前URL
     * @return false 不被拦截；true 被拦截
     */
    public boolean contains(List<String> list,String url){
        if(list.size() > 0 ){
            for(String str : list){
                if(url.contains(str)){
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void errLog(HttpServletRequest request,String message){
        log.error("请求接口:{},请求参数{}",request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
        throw new MyException(message);
    }

    /**
     * 拦截器加载是在springcontext创建之前完成
     * @param request
     */
    private void inject(HttpServletRequest request) {
        if (null == userInfoService) {
            BeanFactory factory = MyUtils.inject(request);
            userInfoService = factory.getBean(UserInfoService.class);
        }
    }
}
