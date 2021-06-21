package edu.hstc.service;

import edu.hstc.bean.Teacher;
import edu.hstc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    public List<Teacher> getAllTeacher(Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        return teacherMapper.selectAllTeacher(map);
    }

    public Teacher getTeacherById(Integer t_id){
        return teacherMapper.selectTeacherById(t_id);
    }

    public Integer addTeacher(String t_code,String t_userName,String t_password,String t_sex,String t_phone){
        Teacher teacher = new Teacher();
        teacher.setT_code(t_code);
        teacher.setT_userName(t_userName);
        teacher.setT_password(t_password);
        teacher.setT_sex(t_sex);
        teacher.setT_phone(t_phone);
        return teacherMapper.insertTeacher(teacher);
    }

    public Integer updateTeacher(String t_code,String t_userName,String t_password,String t_sex,String t_phone,Integer t_id) {
        Teacher teacher = new Teacher();
        teacher.setT_code(t_code);
        teacher.setT_userName(t_userName);
        teacher.setT_password(t_password);
        teacher.setT_sex(t_sex);
        teacher.setT_phone(t_phone);
        teacher.setT_id(t_id);
        return teacherMapper.updateTeacher(teacher);
    }

    public Integer updateTeacherPassword(String password,Integer t_id){
        Teacher teacher = new Teacher();
        teacher.setT_id(t_id);
        teacher.setT_password(password);
        return teacherMapper.updateTeacherPassword(teacher);
    }

    public Integer deleteTeacher(Integer t_id){
        return teacherMapper.deleteTeacher(t_id);
    }
}
