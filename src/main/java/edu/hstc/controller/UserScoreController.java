package edu.hstc.controller;

import edu.hstc.bean.Paper;
import edu.hstc.bean.UserScore;
import edu.hstc.service.ScoreService;
import edu.hstc.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserScoreController {
    @Autowired
    UserScoreService userScoreService;
    @Autowired
    ScoreService scoreService;

    @RequestMapping("/correctPaperByTeacher")
    @ResponseBody
    public String correctPaper(@RequestBody Map map, HttpSession session){
        Paper paper = (Paper) session.getAttribute("correctPaper");
        List<UserScore> userScoreList = (List<UserScore>) session.getAttribute("userScore");
        String mapName = "subject";
        for (int i=0;i<userScoreList.size();i++){
            int index = i+1;
            String score = (String) map.get(mapName+index);
            if (score!=""&&score!=null){
                float score1 = Float.parseFloat(score);
                userScoreList.get(i).setScore(score1);
                userScoreService.updateUserScore(score1,userScoreList.get(i).getUs_id());
            }
        }
        scoreService.updateScore(userScoreList,paper.getP_id(),userScoreList.get(0).getU_id());
        return ""+paper.getP_id();
    }
}
