package edu.hstc.service;

import edu.hstc.bean.User;
import edu.hstc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User checkLogin(String userCode, String password, Integer userRole, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        map.put("userCode",userCode);
        map.put("password",password);
        map.put("userRole",userRole);
        User user = userMapper.selectUserByCP(map);
        if (user!=null){
            session.setAttribute("loginUser",user);
            return user;
        }else {
            return null;
        }
    }

    public List<User> getAllUser(Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        return userMapper.selectAllUser(map);
    }

    public User selectUserById(Integer u_id){
        return userMapper.selectUserById(u_id);
    }

    public Integer addUser(String u_code,String u_userName,String u_password,String u_realName,String u_sex,String u_email,String u_phone,Integer c_id){
        User user = new User();
        user.setU_code(u_code);
        user.setU_userName(u_userName);
        user.setU_password(u_password);
        user.setU_realName(u_realName);
        user.setU_sex(u_sex);
        user.setU_email(u_email);
        user.setU_phone(u_phone);
        user.setC_id(c_id);
        return userMapper.insertUser(user);
    }

    public Integer updateUser(String u_code,String u_userName,String u_password,String u_realName,String u_sex,String u_email,String u_phone,Integer c_id,Integer u_id){
        User user = new User();
        user.setU_code(u_code);
        user.setU_userName(u_userName);
        user.setU_password(u_password);
        user.setU_realName(u_realName);
        user.setU_sex(u_sex);
        user.setU_email(u_email);
        user.setU_phone(u_phone);
        user.setC_id(c_id);
        user.setU_id(u_id);
        return userMapper.updateUser(user);
    }

    public Integer updateUserPassword(String password,Integer u_id){
        User user = new User();
        user.setU_id(u_id);
        user.setU_password(password);
        return userMapper.updateUserPassword(user);
    }

    public Integer deleteUser(Integer u_id){
        return userMapper.deleteUser(u_id);
    }
}
