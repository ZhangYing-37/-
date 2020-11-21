package edu.hstc.mapper;

import edu.hstc.bean.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {

    //通过tid查询所有课程
    public List<Course> selectCourseByTid(Integer t_id);

    //通过班级id查询
    public List<Course> selectCourseByCid(Integer c_id);

    //通过co_id查询课程
    public Course selectCourseByCoid(Integer co_id);


}
