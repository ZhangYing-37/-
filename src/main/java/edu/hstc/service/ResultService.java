package edu.hstc.service;

import edu.hstc.bean.Result;
import edu.hstc.mapper.ResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    ResultMapper resultMapper;

    public List<Result> getResult(Integer u_id,Integer p_id){
        Result result = new Result();
        result.setP_id(p_id);
        result.setU_id(u_id);
        return resultMapper.selectResultByUidAndPid(result);
    }

    public Integer getResultCount(Integer o_id){
        List<Result> resultList = resultMapper.selectResultByOid(o_id);
        return resultList.size();
    }

    public Integer addResult(String result,Float r_score,Integer p_id,Integer sj_id,Integer u_id){
        Result result1 = new Result();
        result1.setResult(result);
        result1.setR_score(r_score);
        result1.setP_id(p_id);
        result1.setSj_id(sj_id);
        result1.setU_id(u_id);
        return resultMapper.insertResult(result1);
    }

}
