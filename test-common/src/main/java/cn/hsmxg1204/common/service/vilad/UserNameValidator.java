package cn.hsmxg1204.common.service.vilad;

import cn.hsmxg1204.common.service.Validator;
import cn.hsmxg1204.core.exception.ValidException;
import cn.hsmxg1204.core.result.ResultCode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-10 13:55
 */
@Component
@Order(2)
public class UserNameValidator implements Validator {
    @Override
    public void validate(String email, String password, String name) {
        if(StringUtils.isEmpty(name)){
            throw new ValidException(ResultCode.USER_NAME_EMPTY_ERROR.getCode(),
                    ResultCode.USER_NAME_EMPTY_ERROR.getMessage());
        }
        if(name.length() > 20){
            throw new ValidException(ResultCode.USER_NAME_LENGTH_ERROR.getCode(),
                    ResultCode.USER_NAME_LENGTH_ERROR.getMessage());
        }
    }
}
