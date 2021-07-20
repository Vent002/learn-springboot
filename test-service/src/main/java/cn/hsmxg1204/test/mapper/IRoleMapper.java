package cn.hsmxg1204.test.mapper;

import cn.hsmxg1204.test.entity.RoleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 10:39
 */
@Mapper
public interface IRoleMapper {
    @Select("select * from role_info where id = #{id}")
    RoleInfo getRoleInfo(int id);

    @Insert("insert into role_info(role) values(#{role})")
    int saveRoleInfo(RoleInfo roleInfo);
}
