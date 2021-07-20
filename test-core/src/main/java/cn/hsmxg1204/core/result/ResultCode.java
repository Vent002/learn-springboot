package cn.hsmxg1204.core.result;

/**
 * 统一响应代码
 */
public enum ResultCode {


    //200系列： 代表 接收收据成功  可以继续执行下一步操作，  有附带消息需要处理。
    //300系列： 如权限问题，
    //400系列： 参数类型错误。
    //450系列： 微信问题
    //500系列： 跳过不是用
    //600系列： 支付问题
    FAIL(400,"失败"),//失败
    UNAUTHORIZED(401,"未认证（签名错误）"),//未认证（签名错误）
    TOKEN_NULL(402,"token异常"),//token异常
    PWDERROR(450,"支付密码错误"),//支付密码错误
    NOT_FOUND(404,"接口不存在"),//
    NOT_DATA(406,"没有该数据"),//
    AUTH_ERROR(301,"权限不够异常"),//
    BINDING_NULL(421,"绑定为空"),//
    AUDIT_AUTHENTICATION(411,"验证身份失败"),//
    UNION_ID(414,"用户名为空"),
    WX_NO_REGISTER(415,""),
    INTERNAL_SERVER_ERROR(500,""),//服务器内部错误
    PWD_ERROR(450, "支付密码错误"),//支付密码错误
    EMAIL_FORMAT_ERROR(413,"邮箱格式错误"),
    USER_NAME_EMPTY_ERROR(416,"用户名传入为空"),
    USER_NAME_LENGTH_ERROR(418,"用户名长度错误"),
    PASSWORD_FORMAT_ERROR(408,"密码格式错误"),
    PASSWORD_AUTHENTICATION_ERROR(409,"密码验证失败"),

    /**
     * 基本返回类型
     */
    SUCCESS(200, "操作成功"),
    FAILED(401, "操作失败"),
    REQUIRE_PARAMS(402, "参数不完整"),
    NOT_FOUND_404(404, "路径不存在，请检查路径是否正确"),
    INTERNAL_SERVER_ERROR_500(500, "系统内部错误"),


    /**
     * 数据库相关响应码,范围【2000,3000）
     */
    DB_DATA_EXIST_2000(2000, "数据库中已存在该记录"),
    DB_FILED_LENGTH_LIMIT_2001(2001, "字段太长,超出数据库字段的长度"),
    /**
     * Redis相关响应码
     */
    REDIS_ERROR_3000(3000, "Redis连接异常!");



    private final Integer CODE;
    private final String MESSAGE;

    ResultCode(Integer code, String message) {
        this.CODE = code;
        this.MESSAGE = message;
    }

    public Integer getCode() {
        return this.CODE;
    }

    public String getMessage() {
        return this.MESSAGE;
    }

    /**
     *  通过code获取枚举
     *
     */
    public static ResultCode getByCode(Integer index) {
        for (ResultCode tEnum : values()) {
            if (tEnum.getCode().equals(index)) {
                return tEnum;
            }
        }
        return null;
    }
}
