package edu.hstc.service;

import edu.hstc.bean.Course;
import edu.hstc.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    public List<Course> getCourse(Integer t_id){
        return courseMapper.selectCourseByTid(t_id);
    }

    public List<Course> getCourseByClass(Integer c_id){
        return courseMapper.selectCourseByCid(c_id);
    }

    public Course getCourseByCoid(Integer co_id){
        return courseMapper.selectCourseByCoid(co_id);
    }
}
