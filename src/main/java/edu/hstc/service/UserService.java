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

    public List<User> getAllUser(){
        return userMapper.selectAllUser();
    }

    public User selectUserById(Integer u_id){
        return userMapper.selectUserById(u_id);
    }
}
