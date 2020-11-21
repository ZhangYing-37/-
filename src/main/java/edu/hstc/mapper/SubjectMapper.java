package edu.hstc.mapper;

import edu.hstc.bean.Subject;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import java.util.Map;

@Repository
public interface SubjectMapper {

    public Subject selectSO(Map map);

    public Integer selectTypeById(Integer sj_id);

    //添加题目
    public Integer insertSubject(Subject subject);

    //更新题目
    public Integer updateSubject(Subject subject);

    //删除题目
    public Integer deleteSubject(Integer sj_id);

}
