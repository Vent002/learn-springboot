package cn.hsmxg1204.core.result;


import cn.hsmxg1204.core.result.ResultCode;
import cn.hsmxg1204.core.utils.MyUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "成功";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";

    public static Result genSuccessRes() {
        return new Result()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessMessage(T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    /**
     * 返回生成参数不完整的响应请求
     * @return
     */
    public static <T> Result<T> genRequireParams() {
        return new Result<T>()
                .setCode(ResultCode.REQUIRE_PARAMS.getCode())
                .setMessage(ResultCode.REQUIRE_PARAMS.getMessage());
    }
    public static <T> Result<T> genSuccessPaging(int PageCount, int currentPage, T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> Result<T> genFailRes(String message) {
        return new Result<T>()
                .setCode(ResultCode.FAILED.getCode())
                .setMessage(message);
    }

    public static <T> Result<T> genFailResult() {
        return new Result<T>()
                .setCode(ResultCode.FAILED.getCode())
                .setMessage(DEFAULT_FAIL_MESSAGE);
    }

    public static <T> Result<T> genFailResult(String msg) {
        return new Result<T>()
                .setCode(ResultCode.FAILED.getCode())
                .setMessage(msg);
    }


    public static <T> Result<T> genFailRes(String message, ResultCode code) {
        return new Result<T>()
                .setCode(code.getCode())
                .setMessage(message);
    }

    /**
     *
     * @param data
     * @param respClazz  出参接口
     * @return
     */
    public static  Result genSuccessMessage(Object data, Class respClazz) {
        Object dat;
        if(data instanceof List){
            dat = MyUtils.copyPropList((List) data, respClazz);
        }else{
            dat = MyUtils.copyProp(data,respClazz);
        }
        return genSuccessMessage(dat);
    }

    public static <T> Result<T> genSuccessMessage(String message) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(message);
    }

    public static <T> Result<T> genMessage(boolean isOk) {
        if(isOk){
            return genSuccessMessage("请求成功");
        }else {
            return genFailRes("操作失败");
        }
    }

    public static Map getData() {
        Map tokenMap=new HashMap();
        return tokenMap;
    }
}
