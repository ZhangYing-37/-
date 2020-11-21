package edu.hstc.service;

import edu.hstc.bean.UserScore;
import edu.hstc.mapper.UserScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserScoreService {

    @Autowired
    UserScoreMapper userScoreMapper;

    public List<UserScore> getUserScore(Integer p_id,Integer u_id){
        UserScore userScore = new UserScore();
        userScore.setP_id(p_id);
        userScore.setU_id(u_id);
        return userScoreMapper.selectScoreByPU(userScore);
    }

    public Integer insertUserScore(Float score,Integer sj_id,Integer p_id,Integer u_id){
        UserScore userScore = new UserScore();
        userScore.setScore(score);
        userScore.setSj_id(sj_id);
        userScore.setP_id(p_id);
        userScore.setU_id(u_id);
        return userScoreMapper.insertScore(userScore);
    }

    public Integer updateUserScore(Float score,Integer us_id){
        UserScore userScore = new UserScore();
        userScore.setUs_id(us_id);
        userScore.setScore(score);
        return userScoreMapper.updateScore(userScore);
    }
}
