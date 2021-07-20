package cn.hsmxg1204.test.filter;

import cn.hsmxg1204.test.entity.dto.VerifyJwtResultDto;
import cn.hsmxg1204.test.service.JwtSpi;
import io.swagger.models.HttpMethod;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.HandlerInterceptor;
import reactor.core.publisher.Mono;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-17 16:07
 */
@Component
public class JwtGlobalFilter implements HandlerInterceptor,Ordered, EnvironmentAware {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private List<String> accessUriList;

    @Autowired
    private JwtSpi jwtSpi;

    private static final String JSON_WEB_TOKEN_KEY = "X-TOKEN";
    private static final String UID_KEY = "X-UID";
    private static final String JWT_ID_KEY = "X-JTI";

    @Override
    public void setEnvironment(Environment environment) {
        accessUriList = Arrays.asList(Objects.requireNonNull(environment.getProperty("jwt.access.uris")).split(","));
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if(Objects.nonNull(method) && Objects.equals(method, HttpMethod.OPTIONS)){
            return true;
        }
        //请求路径
        String requestPathInfo = request.getPathInfo();
        //白名单
        Boolean matchWhiteRequestPathList = Optional.ofNullable(accessUriList)
                .map(paths -> paths.stream().anyMatch(path -> pathMatcher.match(path, requestPathInfo)))
                .orElse(false);
        //白名单放行
        if(matchWhiteRequestPathList){
            return true;
        }
        String token = request.getHeader(JSON_WEB_TOKEN_KEY);
        if(!StringUtils.hasLength(token)){
            throw new Exception("   token is null  ");
        }
        VerifyJwtResultDto resultDto = jwtSpi.verify(token);
        if(Objects.equals(resultDto.getValid(),Boolean.FALSE)){
            throw new Exception("token exp");
        }
        //设置请求头token,

        return true;
    }
}
