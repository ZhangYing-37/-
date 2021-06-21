package edu.hstc.mapper;

import edu.hstc.bean.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassesMapper {

    public List<Classes> selectAllClasses(Map map);

    public List<Classes> selectAllUserClasses();

    public Classes selectClassesById(Integer c_id);

    public Integer insertClasses(Classes classes);

    public Integer updateClasses(Classes classes);

    public Integer deleteClasses(Integer c_id);
}
