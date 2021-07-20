package cn.hsmxg1204.common.service.vilad;

import cn.hsmxg1204.common.service.Validator;
import cn.hsmxg1204.core.exception.ValidException;
import cn.hsmxg1204.core.result.ResultCode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-10 13:55
 */
@Component
@Order(3)
public class PasswordValidator implements Validator {
    @Override
    public void validate(String email, String password, String name) {

        // 1. 解密验证

        // 2. 格式验证
        if(password.matches("^.{6,20}$")){
            throw new ValidException(ResultCode.PASSWORD_FORMAT_ERROR.getCode(),
                    ResultCode.PASSWORD_AUTHENTICATION_ERROR.getMessage());
        }

    }
}
