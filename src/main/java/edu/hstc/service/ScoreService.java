package edu.hstc.service;

import edu.hstc.bean.Score;
import edu.hstc.bean.UserScore;
import edu.hstc.mapper.ScoreMapper;
import edu.hstc.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {
    @Autowired
    ScoreMapper scoreMapper;
    @Autowired
    SubjectMapper subjectMapper;

    public List<Score> getScore(Integer p_id){
        return scoreMapper.selectScoreByPid(p_id);
    }

    public List<Score> getScoreByLayui(Integer p_id,Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        map.put("p_id",p_id);
        map.put("page",page);
        map.put("limit",limit);
        return scoreMapper.selectScoreByLayui(map);
    }

    public Integer addScore(List<UserScore> userScoreList,Integer p_id,Integer u_id){
        Score score = new Score();
        Float DXscore = 0f;
        Float DX1score = 0f;
        Float PDscore = 0f;
        Float TKscore = 0f;
        Float JDscore = 0f;
        for (int i =0;i<userScoreList.size();i++){
            Integer type = subjectMapper.selectTypeById(userScoreList.get(i).getSj_id());
            switch (type){
                case 1:
                    DXscore = DXscore+userScoreList.get(i).getScore();
                    break;
                case 2:
                    DX1score = DX1score+userScoreList.get(i).getScore();
                    break;
                case 3:
                    PDscore = PDscore+userScoreList.get(i).getScore();
                    break;
                case 4:
                    TKscore = TKscore+userScoreList.get(i).getScore();
                    break;
                case 5:
                    JDscore = JDscore+userScoreList.get(i).getScore();
                    break;
            }
        }
        Float Total = DXscore+DX1score+PDscore+TKscore+JDscore;
        score.setDX_score(DXscore);
        score.setDX1_score(DX1score);
        score.setPD_score(PDscore);
        score.setTK_score(TKscore);
        score.setJD_score(JDscore);
        score.setTotalScore(Total);
        score.setIsCorrect("未批改");
        score.setP_id(p_id);
        score.setU_id(u_id);
        return scoreMapper.insertScore(score);
    }

    public Integer updateScore(List<UserScore> userScoreList,Integer p_id,Integer u_id){
        Score score = new Score();
        Float DXscore = 0f;
        Float DX1score = 0f;
        Float PDscore = 0f;
        Float TKscore = 0f;
        Float JDscore = 0f;
        for (int i =0;i<userScoreList.size();i++){
            Integer type = subjectMapper.selectTypeById(userScoreList.get(i).getSj_id());
            switch (type){
                case 1:
                    DXscore = DXscore+userScoreList.get(i).getScore();
                    break;
                case 2:
                    DX1score = DX1score+userScoreList.get(i).getScore();
                    break;
                case 3:
                    PDscore = PDscore+userScoreList.get(i).getScore();
                    break;
                case 4:
                    TKscore = TKscore+userScoreList.get(i).getScore();
                    break;
                case 5:
                    JDscore = JDscore+userScoreList.get(i).getScore();
                    break;
            }
        }
        Float Total = DXscore+DX1score+PDscore+TKscore+JDscore;
        score.setDX_score(DXscore);
        score.setDX1_score(DX1score);
        score.setPD_score(PDscore);
        score.setTK_score(TKscore);
        score.setJD_score(JDscore);
        score.setTotalScore(Total);
        score.setIsCorrect("已改");
        score.setP_id(p_id);
        score.setU_id(u_id);
        return scoreMapper.updateScore(score);

    }

}
