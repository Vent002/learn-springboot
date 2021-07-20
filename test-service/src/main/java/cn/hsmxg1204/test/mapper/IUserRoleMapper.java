package cn.hsmxg1204.test.mapper;

import cn.hsmxg1204.test.entity.UserRoleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 10:39
 */
@Mapper
public interface IUserRoleMapper {
    @Select("select * from user_role_info where user_id = #{id}")
    List<UserRoleInfo> listUserRoleById(String id);

    @Insert("insert into user_role_info(role_id,user_id) values(#{roleId},#{userId})")
    int saveUserRoleInfo(UserRoleInfo roleInfo);
}
