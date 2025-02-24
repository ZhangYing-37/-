package edu.hstc.mapper;

import edu.hstc.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    //检查登录，根据用户编号和密码查询数据库
    public User selectUserByCP(Map map);

    //查询所有用户
    public List<User> selectAllUser(Map map);

    public User selectUserById(Integer u_id);

    public Integer insertUser(User user);

    public Integer updateUser(User user);

    public Integer updateUserPassword(User user);

    public Integer deleteUser(Integer u_id);
}
