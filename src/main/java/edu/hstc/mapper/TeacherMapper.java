package edu.hstc.mapper;

import edu.hstc.bean.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TeacherMapper {

    //根据教师编号和密码查询
    public Teacher selectTeacherByCP(Map map);
}
