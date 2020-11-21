package edu.hstc.service;

import edu.hstc.bean.Teacher;
import edu.hstc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public Integer checkLogin(String userCode, String password, Integer userRole, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        map.put("userCode",userCode);
        map.put("password",password);
        map.put("userRole",userRole);
        Teacher teacher = teacherMapper.selectTeacherByCP(map);
        if (teacher!=null){
            session.setAttribute("loginTeacher",teacher);
            return teacher.getT_id();
        }else {
            return null;
        }
    }
}
