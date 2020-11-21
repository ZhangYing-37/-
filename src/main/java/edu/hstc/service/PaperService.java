package edu.hstc.service;

import edu.hstc.bean.Paper;
import edu.hstc.bean.Subject;
import edu.hstc.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;

    public Paper getPaperById(Integer p_id){
        Map<String,Object> map = new HashMap<>();
        map.put("p_id",p_id);
        return paperMapper.selectPaperById(map);
    }

    public List<Paper> getPaperByCId(Integer co_id){
        return paperMapper.selectPaperByCId(co_id);
    }

    public List<Paper> getPaperAndResult(Integer co_id, Integer u_id){
        Map<String,Object> map = new HashMap<>();
        map.put("co_id",co_id);
        map.put("u_id",u_id);
        return paperMapper.selectPaperAndResult(map);
    }

    public List<Paper> getPaperByTitle(String title,Integer co_id){
        Map<String,Object> map = new HashMap<>();
        map.put("title",title);
        map.put("co_id",co_id);
        return paperMapper.selectPaperByTitle(map);
    }

    public Integer newPaper(String p_title,Integer co_id,Integer createdBy){
        Paper paper = new Paper();
        paper.setP_title(p_title);
        paper.setCo_id(co_id);
        paper.setCreatedBy(createdBy);
        paperMapper.newPaper(paper);
        return paper.getP_id();
    }

    public Integer updatePaper(String p_title, String p_desc,Integer resultCount, Integer p_id){
        Paper paper = new Paper();
        paper.setP_title(p_title);
        paper.setP_desc(p_desc);
        paper.setResultCount(resultCount);
        paper.setP_id(p_id);
        return paperMapper.updatePaper(paper);
    }

    public Integer updatePaperSetting(String startTime,String endTime,Float DXscore,Integer isRelease,Integer p_id){
        Paper paper = new Paper();
        paper.setStartTime(startTime);
        paper.setEndTime(endTime);
        paper.setDXscore(DXscore);
        paper.setP_id(p_id);
        paper.setIsRelease(isRelease);
        return paperMapper.updatePaperSetting(paper);
    }

    public float getScore(Paper paper){
        List<Subject> subjectList = paper.getSubjectList();
        float score = 0;
        if (subjectList.size()>0&&subjectList.get(0).getS_score()!=null){
            for (int i=0;i<subjectList.size();i++){
                score = score + subjectList.get(i).getS_score();
            }
        }
        return score;
    }

    public Integer deletePaper(Integer p_id){
        return paperMapper.deletePaper(p_id);
    }

    /*public List<Subject> subjectListSort(List<Subject> subjectList){
        List<Subject> subjectList1=subjectList;
        Subject subject;
        for (int i=0;i<subjectList1.size();i++){
            for (int j=i+1;j<subjectList1.size();j++){
                if (subjectList1.get(i).getS_code()>subjectList1.get(j).getS_code()){
                    subject = subjectList1.get(i);
                    subjectList1.set(i,subjectList1.get(j));
                    subjectList1.set(j,subject);
                }
            }
        }
        return subjectList1;
    }*/
}
