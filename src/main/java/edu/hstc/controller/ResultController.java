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
        String mapName = "subject";
        String mapName1 = "option";
        Integer p_id = paper.getP_id();
        Integer sj_id = 0;
        Integer u_id = user.getU_id();
        for (int i=0;i<subjectList.size();i++){              //循环遍历该试卷的所有题目列表
            int index = i+1;
            String resultContent = "";
            Float r_score;
            Subject subject = subjectList.get(i);
            sj_id = subject.getSj_id();
            if (subject.getS_type()==1){                                        //单选题自动批改
                resultContent = (String) map.get(mapName+index);
                Integer o_id = Integer.parseInt(resultContent);
                Integer isTrue = subjectService.SubjectResult(o_id,subject);   //遍历题目的所有选项，返回该o_id选项是否为正确选项
                if (isTrue==1){
                    r_score = subject.getS_score();                //该选项正确，获取题目得分
                }else {
                    r_score = 0f;                                  //该选项错误，得分为0
                }
                resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                userScoreService.insertUserScore(r_score,sj_id,p_id,u_id);

            } else if (subject.getS_type()==2){                               //多选题自动批改
                List<Option> optionList = subject.getOptionList();
                int trueSubjectCount = 0;
                for (int j=0;j<optionList.size();j++){
                    Integer isTrue = optionList.get(j).getO_isTrue();
                    if (isTrue==1){
                        trueSubjectCount++;                 //计算多选题正确选项总数
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
                            trueSubjectCount--;          //每答对一题，正确选项总数减一
                        }else {
                            r_score = 0f;
                            trueSubjectCount = -1;        //答错一题，正确选项总数置为负一
                        }
                        resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                    }
                }
                float score = 0;
                if (trueSubjectCount>0){
                    score = paper.getDXscore();         //如果最后正确选项总数大于0，则该题得分为教师设置的不完全正确得分
                }else if (trueSubjectCount==0){
                    score = subject.getS_score();         //正确选项总数等于0，则得分为该题的总分
                }else if (trueSubjectCount<0){
                    score = 0;                          //正确选项总数小于0，则得分为0
                }
                userScoreService.insertUserScore(score,sj_id,p_id,u_id);

            }else if (subject.getS_type()==3){              //判断题自动批改
                resultContent = (String) map.get(mapName+index);
                System.out.println("subject"+index+":"+resultContent);
                Integer o_id = Integer.parseInt(resultContent);
                Integer isTrue = subjectService.SubjectResult(o_id,subject);
                if (isTrue==1){
                    r_score = subject.getS_score();             //选项正确，获得题目总分
                }else {
                    r_score = 0f;                 //选项错误，获得0分
                }
                resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                userScoreService.insertUserScore(r_score,sj_id,p_id,u_id);

            }else if (subject.getS_type()==4){                    //填空题自动批改
                resultContent = (String) map.get(mapName+index);
                System.out.println("subject"+index+":"+resultContent);
                String answer = subject.getOptionList().get(0).getO_name();
                System.out.println("answer="+answer);
                if (answer.trim().equals(resultContent.trim())){           //将学生答案和正确答案都除去头尾空格字符然后比较，相同则获得满分，不同则获得0分
                    r_score = subject.getS_score();
                }else {
                    r_score = 0f;
                }
                resultService.addResult(resultContent,r_score,p_id,sj_id,u_id);
                userScoreService.insertUserScore(r_score,sj_id,p_id,u_id);

            }else if (subject.getS_type()==5){                    //简答题将内容保存由教师批改
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
