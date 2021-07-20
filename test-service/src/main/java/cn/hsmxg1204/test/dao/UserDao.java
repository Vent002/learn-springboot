package cn.hsmxg1204.test.dao;

import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.core.utils.MyUtils;
import cn.hsmxg1204.test.dao.JDBC.AbstractDao;
import cn.hsmxg1204.test.entity.UserInfo;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 14:53
 */
@Component
@Transactional
public class UserDao extends AbstractDao<UserInfo> {

    public UserInfo getUserByEmail(String email){
        List<UserInfo> infos = getJdbcTemplate().query("SELECT * FROM user_info WHERE email = ?", new Object[]{email},
                (ResultSet rs, int rowNum) -> {
                    return new UserInfo(
                            rs.getString("id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email")
                    );
                });
        return infos.isEmpty()? null : infos.get(0);
    }

    public UserInfo login(String email,String password){
        UserInfo userInfo = getUserByEmail(email);
        if(userInfo.getPassword().equals(password)){
            return userInfo;
        }
        throw new MyException("login fail");
    }

    public UserInfo creatUserInfo(String email,String userName,String password){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(1 != getJdbcTemplate().update((conn) -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_info(id,email,password,name) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, MyUtils.generateId());
            ps.setObject(2,userName);
            ps.setObject(3,password);
            ps.setObject(4,email);
            return ps;
        },keyHolder)){
            throw new MyException("Insert failed.");
        }
        return new UserInfo(keyHolder.getKey().toString(),userName,password,email);
    }

    public void updateUserInfo(UserInfo userInfo){
        if(1 != getJdbcTemplate().update("UPDATE USER_INFO SET user_name = ? where id = ?",userInfo.getUserName(),userInfo.getId())){
            throw new MyException("User not found by id");
        }
    }
}
