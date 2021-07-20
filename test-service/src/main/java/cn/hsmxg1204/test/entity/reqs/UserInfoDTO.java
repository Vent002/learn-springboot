package cn.hsmxg1204.test.entity.reqs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 11:36
 */
@Data
public class UserInfoDTO {
    private String id;
    @NotNull
    @ApiModelProperty("用户名")
    private String userName;
    @NotNull
    @ApiModelProperty("登陆密码")
    private String password;
    @NotNull
    @ApiModelProperty("角色类型")
    private String roleType;
}
