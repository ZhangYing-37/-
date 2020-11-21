package edu.hstc.mapper;

import edu.hstc.bean.UserScore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreMapper {

    public List<UserScore> selectScoreByPU(UserScore userScore);

    public Integer insertScore(UserScore userScore);

    public Integer updateScore(UserScore userScore);
}
