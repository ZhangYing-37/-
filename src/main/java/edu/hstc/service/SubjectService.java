package edu.hstc.service;

import edu.hstc.bean.Option;
import edu.hstc.bean.Subject;
import edu.hstc.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectService {

    @Autowired
    SubjectMapper subjectMapper;

    public Subject getSubject(Integer sj_id){
        Map<String,Object> map = new HashMap<>();
        map.put("sj_id",sj_id);
        return subjectMapper.selectSO(map);
    }

    public Integer addSubject(Integer s_code,String s_name,Integer s_type,Float s_score,Integer p_id){
        Subject subject = new Subject();
        subject.setS_code(s_code);
        subject.setS_name(s_name);
        subject.setS_type(s_type);
        subject.setS_score(s_score);
        subject.setP_id(p_id);
        subjectMapper.insertSubject(subject);
        return subject.getSj_id();
    }

    public Integer updateSubject(Subject subject){
        return subjectMapper.updateSubject(subject);
    }

    public Boolean moveUpSubject(Subject moveSubject,Subject inUpSubject){
        System.out.println("moveSubject="+moveSubject.toString()+",inUpSubject="+inUpSubject.toString());
        if (moveSubject.getSj_id()!=null && moveSubject.getS_code()>1){
            if (inUpSubject.getSj_id()!=null && inUpSubject.getS_code()==moveSubject.getS_code()-1){
                moveSubject.setS_code(moveSubject.getS_code()-1);
                inUpSubject.setS_code(inUpSubject.getS_code()+1);
                subjectMapper.updateSubject(moveSubject);
                subjectMapper.updateSubject(inUpSubject);
                return true;
            }
        }
        return false;
    }
    public Boolean moveDownSubject(Subject moveSubject,Subject inDownSubject,Integer size){
        System.out.println("moveSubject="+moveSubject.toString()+",inDownSubject="+inDownSubject.toString());
        if (moveSubject.getSj_id()!=null && moveSubject.getS_code()<size){
            if (inDownSubject.getSj_id()!=null && inDownSubject.getS_code()==moveSubject.getS_code()+1){
                moveSubject.setS_code(moveSubject.getS_code()+1);
                inDownSubject.setS_code(inDownSubject.getS_code()-1);
                subjectMapper.updateSubject(moveSubject);
                subjectMapper.updateSubject(inDownSubject);
                return true;
            }
        }
        return false;
    }

    public Integer deleteSubject(Integer sj_id){
        return subjectMapper.deleteSubject(sj_id);
    }

    public Integer SubjectResult(Integer o_id, Subject subject){
        List<Option> optionList = subject.getOptionList();
        for (int i=0;i<optionList.size();i++){
            if (optionList.get(i).getO_id()==o_id){
                return optionList.get(i).getO_isTrue();
            }
        }
        return 0;
    }
}
