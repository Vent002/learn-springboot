package cn.hsmxg1204.core.utils;

import cn.hsmxg1204.core.exception.MyException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-03-14 10:35
 */
public class MyUtils {
    public MyUtils() {
    }

    /**
     * 通过UUID生成Token
     *
     * @return
     */
    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据时间戳以及随机数生成id
     *
     * @return
     */
    public static synchronized String generateId() {
        return System.currentTimeMillis() + getRandomNum(6);
    }

    /**
     * 获取传入长度随机数
     *
     * @param length
     * @return
     */
    public static synchronized String getRandomNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 将Map转化为
     *
     * @param results
     * @param clz
     * @param <T>
     * @return
     */
//    public static <T> List<T> MapToObj(List<Map<String, Object>> results, Class<T> clz) {
//        List<T> list = new ArrayList<>();
//        TableHumpTUnderline annotation = clz.getAnnotation(TableHumpTUnderline.class);
//        try {
//            Map<String, Field> fieldMap = new HashMap<>();
//            for (Field field : clz.getDeclaredFields()) {
//                String key = null;
//                TableField annotations = field.getAnnotation(TableField.class);
//                if (annotations != null) {
//                    key = annotations.value();
//                } else if (key == null) {
//                    TableId annotation1 = field.getAnnotation(TableId.class);
//                    if (annotation1 != null) key = annotation1.value();
//                } else if (key == null || key.length() == 0) {
//                    if (annotation != null) {
//                        key = handleToUnderline(field.getName());
//                    } else {
//                        key = field.getName();
//                    }
//                }
//                fieldMap.put(key.toLowerCase(), field);
//            }
//
//            for (Map<String, Object> map : results) {
//                T t = clz.newInstance();
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
//                    Field field1 = fieldMap.get(entry.getKey().toLowerCase());
//                    if (field1 != null) {
//                        field1.setAccessible(true);
//                        //获取字段的属性
//                        if (field1.getType().equals(String.class)) {
//                            field1.set(t, entry.getValue().toString());
//                        } else if (field1.getType().equals(Integer.class)) {
//                            field1.set(t, Integer.parseInt(entry.getValue().toString()));
//                        } else {
//                            field1.set(t, entry.getValue());
//                        }
//                    }
//                }
//                list.add(t);
//            }
//        } catch (Exception e) {
//            throw new MyException(e);
//        }
//        return list;
//    }

    /**
     * 将驼峰转化为下划线命名
     *
     * @param param
     * @return
     */
    public static String handleToUnderline(String param) {
        StringBuffer buffer = new StringBuffer();
        int temp = 0;
        if (!param.contains("_")) {
            for (int i = 0; i < param.length(); i++) {
                if (Character.isUpperCase(param.charAt(temp))) {
                    buffer.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return buffer.toString().toLowerCase();
    }

    /**
     * 注入Bean
     * @param request
     * @return
     */
    public static BeanFactory inject(HttpServletRequest request){
        return WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
    }

    /**
     * 加密密码
     * @param password
     * @return
     */
    public static String passWordEncoder(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


    public static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取指定过期时间
     * @return
     */
    public static String getExpireTime() {
        return getExpireTime(null,0,0,0,0,0,0);
    }

    /**
     *
     * @param startTime 指定开始时间
     * @param diffYear  相差年数
     * @param diffMonth
     * @param diffDay
     * @param diffHour
     * @param diffMinute
     * @param diffSecond
     * @return
     */
    public static String getExpireTime(Date startTime, int diffYear, int diffMonth, int diffDay, int diffHour, int diffMinute, int diffSecond) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        if (null != startTime) {
            c.setTime(startTime);
        }
        if (diffYear != 0) {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR) + diffYear);
        }
        if (diffMonth != 0) {
            c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1 + diffMonth);
        }
        if (diffDay != 0) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + diffDay);
        }

        if (diffHour != 0) {
            c.set(Calendar.HOUR, c.get(Calendar.HOUR) + diffHour);
        }
        if (diffMinute != 0) {
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + diffMinute);
        }
        if (diffSecond != 0) {
            c.set(Calendar.SECOND, c.get(Calendar.SECOND) + diffSecond);
        }

        return dateformat.format(c.getTime());
    }

    public static <T> List<T> copyPropList(List obj, Class<T> clz) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            T dataResp = MyUtils.copyProp(obj.get(i), clz);
            list.add(dataResp);
        }
        return list;
    }

    /**
     * 转换对象，忽略大小写
     * @param obj
     * @param newObj
     */
    public static void copyProp(Object obj,  Object newObj)  {
        Class clz = newObj.getClass();
        Map<String, Field> destPropertyMap = new HashMap<>();
        for (Field curField : clz.getDeclaredFields()) {
            destPropertyMap.put(curField.getName().toLowerCase(), curField);
        }
        //拷贝属性
        try {
            for (Field curField : obj.getClass().getDeclaredFields()) {
                Field targetField = destPropertyMap.get(curField.getName().toLowerCase());
                if (targetField != null) {
                    targetField.setAccessible(true);
                    curField.setAccessible(true);
                    targetField.set(newObj, curField.get(obj));
                }
            }
        } catch (Exception e1) {
            throw new MyException("属性复制失败");
        }
    }

    public static <T> T copyProp(Object obj, Class<T> clz) {
        T result = null;
        try {
            if (obj != null && !obj.equals("")) {
                result = (T) clz.newInstance();
                copyProp(obj,result);
            }
        } catch (Exception e1) {
            throw new MyException("属性复制失败");
        }
        return result;
    }

    private static SecureRandom random = new SecureRandom();

    /**
     * 获取6 位随机验证码
     * @return string
     */
    public static String getCode(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
