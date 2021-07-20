package cn.hsmxg1204.test.mapper;

import cn.hsmxg1204.test.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-13 10:39
 */
@Mapper
public interface IUserMapper {
    @Select("select * from user_info where id = #{id}")
    UserInfo selectUserById(String id);

    @Select("select * from user_info")
    List<UserInfo> getAllUser();

    @Insert("insert into user_info(id,user_name,password) VALUES(#{id},#{userName},#{password})")
    int insertUser(UserInfo userInfo);

    @Update("update user_info set user_name = #{userName} ,password = #{password} where id = #{id}")
    int updateUser(String id,String userName,String password);

    @Select("select * from user_info where user_name like #{useName}")
    List<UserInfo> seachByName(String userName);

    @Select("select * from user_info where user_name = #{userName} and password = #{password}")
    UserInfo selectUserByNameAndPwd(String userName,String password);

    @Select("select * from user_info where user_name = #{userName}")
    UserInfo selectUserByName(String userName);

    @Delete("delete from user_info where id = #{id}")
    int deleteUser(String id);
}
