package cn.hsmxg1204.core.exception;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-03-14 11:00
 */
public class MyException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyException(String message){
        super(message);
    }

    public MyException(String code,String message){
        super(message);
        this.code = code;
    }
    public MyException(Throwable cause){
        super(cause);
    }

    public MyException(Throwable cause,String message){
        super(message,cause);
    }
}
