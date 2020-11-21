package edu.hstc.mapper;

import edu.hstc.bean.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultMapper {

    //通过试卷及用户id查询结果
    public List<Result> selectResultByUidAndPid(Result result);

    //插入试卷结果
    public Integer insertResult(Result result);
}
