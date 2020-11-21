package edu.hstc.mapper;

import edu.hstc.bean.Score;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoreMapper {

    public List<Score> selectScoreByPid(Integer p_id);

    public List<Score> selectScoreByLayui(Map map);

    public Integer insertScore(Score score);

    public Integer updateScore(Score score);
}
