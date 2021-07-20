package cn.hsmxg1204.common.service.vilad;

import cn.hsmxg1204.core.result.ResultCode;
import cn.hsmxg1204.common.service.Validator;
import cn.hsmxg1204.core.exception.ValidException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-10 13:39
 */
@Component
@Order(1)
public class EmailValidator implements Validator {
    @Override
    public void validate(String email, String password, String name) {
        if(!email.matches("^[a-z0-9]+\\@[a-z0-9]+\\.[a-z]{2,10}$")){
            throw new ValidException(ResultCode.EMAIL_FORMAT_ERROR.getCode(),ResultCode.EMAIL_FORMAT_ERROR.getMessage());
        }
    }
}
