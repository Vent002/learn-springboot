package cn.hsmxg1204.core.result;

import cn.hsmxg1204.core.utils.MyUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Result<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应描述
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    /**
     * 执行时间
     */
    private long executetime;
    /**
     * 时间戳
     */
    private String timestamp = MyUtils.dateformat.format(System.currentTimeMillis());

    public Result() {

    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> List<T> priseListData(String json, Class<T> clz) {
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");
        return data.toJavaList(clz);
    }

    public static List<Object> priseListData(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        List<Object> data = jsonObject.getJSONArray("data");
        return data;
    }

    public static <T> T priseData(String json, Class<T> clz) {
        JSONObject jsonObject = JSON.parseObject(json);
        T data = jsonObject.getObject("data", clz);
        return data;
    }


    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> setMessage(String defaultSuccessMessage) {
        msg = defaultSuccessMessage;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result<T> put(String key, String value) {
        if (this.data == null) {
            this.data = (T) new HashMap();
        }
        ((Map) (this.data)).put(key, value);
        return this;
    }
}

