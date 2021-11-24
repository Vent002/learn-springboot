package cn.hsmxg1204.test.config;

import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.core.result.Result;
import cn.hsmxg1204.core.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-11-24 13:42
 */
@Slf4j
@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(MyException.class)
    public Result IncorrentCredentialException(MyException e){
        log.error("系统发生一个错误"+e.getMessage(),e);
        return ResultGenerator.genFailRes("系统发生一个错误");
    }
}
