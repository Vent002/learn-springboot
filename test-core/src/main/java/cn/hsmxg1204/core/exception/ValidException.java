package cn.hsmxg1204.core.exception;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-10 13:43
 */
public class ValidException extends IllegalArgumentException{
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidException(String message){
        super(message);
    }
    public ValidException(Integer code,String message){
        super(message);
        this.code = code;
        System.out.println("异常信息：code="+code+"--message="+message);
    }
    public ValidException(Throwable cause){
        super(cause);
    }

    public ValidException(Throwable cause,String message){
        super(message,cause);
    }
}
