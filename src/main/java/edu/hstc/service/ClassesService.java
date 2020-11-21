package edu.hstc.service;

import edu.hstc.bean.Classes;
import edu.hstc.mapper.ClassesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassesService {

    @Autowired
    ClassesMapper classesMapper;

    public List<Classes> getAllClasses(Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        return classesMapper.selectAllClasses(map);
    }

    public Classes getClassesById(Integer c_id){
        return classesMapper.selectClassesById(c_id);
    }

    public Integer addClasses(String c_code,Integer c_grade,String c_name){
        Classes classes = new Classes();
        classes.setC_code(c_code);
        classes.setC_grade(c_grade);
        classes.setC_name(c_name);
        return classesMapper.insertClasses(classes);
    }

    public Integer updateClasses(String c_code,Integer c_grade,String c_name,Integer c_id){
        Classes classes = new Classes();
        classes.setC_code(c_code);
        classes.setC_grade(c_grade);
        classes.setC_name(c_name);
        classes.setC_id(c_id);
        return classesMapper.updateClasses(classes);
    }

    public Integer deleteClasses(Integer c_id){
        return classesMapper.deleteClasses(c_id);
    }
}
