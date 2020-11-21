package edu.hstc.controller;

import edu.hstc.bean.*;
import edu.hstc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ResultController {

    @Autowired
    ResultService resultService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    UserScoreService userScoreService;
    @Autowired
    ScoreService scoreService;
    @Autowired
    PaperService paperService;

    @RequestMapping("/addResult")
    @ResponseBody
    public String addResult(@RequestBody Map map, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        User user = (User) session.getAttribute("loginUser");
        List<Subject> subjectList = paper.getSubjectList();
        System.out.println("map="+map.toString());
        String mapName = "subject";
        String mapName1 = "option";
        Integer p_id = paper.getP_id();
        Integer sj_id = 0;
        Integer u_id = user.getU_id();
        for (int i=0;i<subjectList.size();i++){
            int index = i+1;
            String resultContent = "";
            Float r_score;
            Subject subject = subjectList.get(i);
            sj_id = subject.getSj_id();
            if (subject.getS_type()==1){
                resultContent = (String) map.get(mapName+index);
                System.out.println("subject"+index+":"+resultContent);
                Integer o_id = Integer.parseInt(resultContent);
                Integer isTrue = subjectService.SubjectResult(o_id,subject);
                if (isTrue==1){
                    r_score = subject.getS_score();
                }else {
                    r_score = 0f;
                }
                resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                userScoreService.insertUserScore(r_score,sj_id,p_id,u_id);
            }else if (subject.getS_type()==2){
                List<Option> optionList = subject.getOptionList();
                int trueSubjectCount = 0;
                for (int j=0;j<optionList.size();j++){
                    Integer isTrue = optionList.get(j).getO_isTrue();
                    if (isTrue==1){
                        trueSubjectCount++;
                    }
                }

                for (int j=0;j<optionList.size();j++){
                    resultContent = (String) map.get(mapName+index+mapName1+(j+1));
                    System.out.println("subject"+index+"option"+(j+1)+":"+resultContent);
                    if (resultContent!=null){
                        Integer o_id = Integer.parseInt(resultContent);
                        Integer isTrue = subjectService.SubjectResult(o_id,subject);
                        if (isTrue==1){
                            r_score = 1f;
                            trueSubjectCount--;
                        }else {
                            r_score = 0f;
                            trueSubjectCount = -1;
                        }
                        resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                    }
                }
                float score = 0;
                if (trueSubjectCount>0){
                    score = paper.getDXscore();
                }else if (trueSubjectCount==0){
                    score = subject.getS_score();
                }else if (trueSubjectCount<0){
                    score = 0;
                }
                userScoreService.insertUserScore(score,sj_id,p_id,u_id);
            }else if (subject.getS_type()==3){
                resultContent = (String) map.get(mapName+index);
                System.out.println("subject"+index+":"+resultContent);
                Integer o_id = Integer.parseInt(resultContent);
                Integer isTrue = subjectService.SubjectResult(o_id,subject);
                if (isTrue==1){
                    r_score = subject.getS_score();
                }else {
                    r_score = 0f;
                }
                resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                userScoreService.insertUserScore(r_score,sj_id,p_id,u_id);
            }else if (subject.getS_type()==4){
                resultContent = (String) map.get(mapName+index);
                System.out.println("subject"+index+":"+resultContent);
                String answer = subject.getOptionList().get(0).getO_name();
                System.out.println("answer="+answer);
                if (answer.trim().equals(resultContent.trim())){
                    r_score = subject.getS_score();
                }else {
                    r_score = 0f;
                }
                resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                userScoreService.insertUserScore(r_score,sj_id,p_id,u_id);
            }else if (subject.getS_type()==5){
                resultContent = (String) map.get(mapName+index);
                System.out.println("subject"+index+":"+resultContent);
                resultService.addResult(resultContent,0f,p_id,sj_id,u_id);
                userScoreService.insertUserScore(0f,sj_id,p_id,u_id);
            }
        }
        paperService.updatePaper(paper.getP_title(),paper.getP_desc(),paper.getResultCount()+1,p_id);
        List<UserScore> userScoreList = userScoreService.getUserScore(p_id,u_id);
        scoreService.addScore(userScoreList,p_id,u_id);
        return ""+paper.getCo_id();
    }
}
