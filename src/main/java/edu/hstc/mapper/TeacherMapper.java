package edu.hstc.mapper;

import edu.hstc.bean.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeacherMapper {

    //根据教师编号和密码查询
    public Teacher selectTeacherByCP(Map map);

    public List<Teacher> selectAllTeacher(Map map);

    public Teacher selectTeacherById(Integer t_id);

    public Integer insertTeacher(Teacher teacher);

    public Integer updateTeacher(Teacher teacher);

    public Integer updateTeacherPassword(Teacher teacher);

    public Integer deleteTeacher(Integer t_id);
}
