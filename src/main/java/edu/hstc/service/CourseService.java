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

    public Integer addCourse(String co_name,String c_desc){
        Course course = new Course();
        course.setCo_name(co_name);
        course.setC_desc(c_desc);
        courseMapper.insertCourse(course);
        return course.getCo_id();
    }

    public Integer updateCourse(String co_name,String c_desc,Integer co_id){
        Course course = new Course();
        course.setCo_name(co_name);
        course.setC_desc(c_desc);
        course.setCo_id(co_id);
        return courseMapper.updateCourse(course);
    }

    public Integer deleteCourse(Integer co_id){
        return courseMapper.deleteCourse(co_id);
    }
}
