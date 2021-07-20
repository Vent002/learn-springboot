package cn.hsmxg1204.core.cache;

import cn.hsmxg1204.core.exception.MyException;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-15 14:13
 */
public class CacheException extends MyException {
    public CacheException(String message) {
        super(message);
    }

    public CacheException(String code, String message) {
        super(code, message);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

    public CacheException(Throwable cause, String message) {
        super(cause, message);
    }
}
