package cn.hsmxg1204.common.service.vilad;

import cn.hsmxg1204.common.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-10 14:07
 */
@Component
public class Validators {
    @Autowired
    List<Validator> validators;

    public void validate(String email,String password,String name){
        for ( Validator validator : validators){
            validator.validate(email,password,name);
        }
    }
}
