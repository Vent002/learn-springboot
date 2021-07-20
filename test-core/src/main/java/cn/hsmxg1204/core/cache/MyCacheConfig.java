package cn.hsmxg1204.core.cache;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 张琳琅
 * @Description: 缓存配置
 * @Date: 2020-04-02 14:43
 * @Version: 1.0
 */
@Component
@Data
public class MyCacheConfig {

    DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
    SpelExpressionParser parser = new SpelExpressionParser();

    public static Map<String,CacheBean> syncKeys = new HashMap<>();

    public static CacheBean getSyncKey(String key){
        CacheBean cacheBean = MyCacheConfig.syncKeys.get(key);
        if(cacheBean==null){
            cacheBean = new CacheBean();
            MyCacheConfig.syncKeys.put(key,cacheBean);
        }
        return cacheBean;
    }

    public String generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(method);
        // 解析过后的Spring表达式对象
        Expression expression = parser.parseExpression(spELString);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 通过joinPoint获取被注解方法的形参
        Object[] args = joinPoint.getArgs();
        // 给上下文赋值
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        Object value = expression.getValue(context);
        if(value==null){
            return null;
        }
        return value.toString();
    }
}
