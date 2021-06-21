package edu.hstc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.hstc.bean.*;
import edu.hstc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GuideController {

    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    PaperService paperService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    ScoreService scoreService;
    @Autowired
    UserScoreService userScoreService;
    @Autowired
    ResultService resultService;


    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(@RequestBody Map map, HttpSession session){
        Integer userId;
        String userCode = (String) map.get("userCode");
        String password = (String) map.get("password");
        Integer role = Integer.parseInt((String) map.get("role"));
        User user = new User();
        if (role==3){
            user = userService.checkLogin(userCode,password,role,session);
            if (user!=null){
                userId= user.getU_id();
            }else {
                userId = null;
            }

        }else if (role<3&&role>0){
            userId = teacherService.checkLogin(userCode,password,role,session);
        }else {
            userId = null;
        }

        if (userId!=null&&role==1){
            return "ok";
        }
        else if(userId!=null&&role==2) {
            List<Course> courseList = courseService.getCourse(userId);
            session.setAttribute("courseList",courseList);
            return "ok";
        }else if (userId!=null&&role==3){
            List<Course> courseList = courseService.getCourseByClass(user.getC_id());
            session.setAttribute("courseList",courseList);
            return "ok";
        }
        else {
            return "error";
        }
    }

    @RequestMapping("/loginGo")
    public String loginGo(Integer role){
        System.out.println("role="+role);
        if (role==1){
            return "adminMain";
        }else if (role==2){
            return "teacherMain";
        }else if (role==3){
            return "userMain";
        }else {
            return "login";
        }
    }

    @RequestMapping("/toUserPassword")
    public String toUserPassword(){
        return "userPassword";
    }

    @RequestMapping("/toTeacherPassword")
    public String toTeacherPassword(){
        return "teacherPassword";
    }

    @RequestMapping("/toPaperList")
    public String toPaperList(Integer co_id, Model model){
        List<Paper> paperList = paperService.getPaperByCId(co_id);
        Course course = courseService.getCourseByCoid(co_id);
        model.addAttribute("thisCourse",course);
        model.addAttribute("paperList",paperList);
        return "teacherPaperList";
    }

    @RequestMapping("/toPaperListUser")
    public String toPaperListUser(Integer co_id, Model model,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        List<Paper> paperList = paperService.getPaperAndResult(co_id,user.getU_id());
        Course course = courseService.getCourseByCoid(co_id);
        System.out.println("paperList="+paperList);
        model.addAttribute("thisCourse",course);
        model.addAttribute("paperList",paperList);
        return "userPaperList";
    }



    @RequestMapping("/toUpdatePaper")
    public String toUpdatePaper(Integer type, Integer sj_id, HttpSession session, Model model){
        Subject subject = subjectService.getSubject(sj_id);
        String toPage = "login";
        session.setAttribute("thisSubject",subject);
        if (type==null || type==0){
            toPage = "updatePaperName";
        }else if (type==1) {
            toPage = "updateSubjectDX1";
        }else if (type==2) {
            toPage = "updateSubjectDX";
        }else if (type==3) {
            toPage = "updateSubjectPD";
        }else if (type==4){
            toPage = "updateSubjectTK";
        }else if (type==5){
            toPage = "updateSubjectJD";
        }
        return toPage;
    }

    @RequestMapping("/toAnalysis")
    public String toAnalysis(Integer p_id,HttpSession session){
        Paper paper = paperService.getPaperById(p_id);
        session.setAttribute("thisPaper",paper);
        return "teacherAnalysis";
    }

    @RequestMapping("/getAnalysis")
    @ResponseBody
    public String getAnalysis(Integer page,Integer limit,HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        if (page==null){ page=0; }else {page--;}
        List<Score> scoreList = scoreService.getScoreByLayui(paper.getP_id(),page,limit);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",scoreList.size());
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<scoreList.size();i++){
            scoreList.get(i).setUserCode(scoreList.get(i).getUser().getU_code());
            scoreList.get(i).setUserName(scoreList.get(i).getUser().getU_realName());
            scoreList.get(i).setTitle(scoreList.get(i).getPaper().getP_title());
            jsonArray.add(scoreList.get(i));
        }
        map.put("data",jsonArray);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/toClassesList")
    public String toClassesList(){
        return "adminClassList";
    }

    @RequestMapping("/toTeacherList")
    public String toTeacherList(){
        return "adminTeacherList";
    }

    @RequestMapping("/toUserList")
    public String toUserList(){
        return "adminUserList";
    }

    @RequestMapping("/toCourseList")
    public String toCourseList(){
        return "adminCourseList";
    }

    @RequestMapping("/correctPaper")
    public String correctPaper(Integer u_id, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        paper.getResultList().clear();
        List<UserScore> userScoreList = userScoreService.getUserScore(paper.getP_id(),u_id);
        List<Result> resultList = resultService.getResult(u_id,paper.getP_id());

        if (paper.getSubjectList().size()==userScoreList.size()){
            for (int i=0;i<resultList.size();i++){
                if (resultList.get(i).getSubject().getS_type()<4){

                    Integer o_id = Integer.parseInt(resultList.get(i).getResult());
                    for (int j=0;j<paper.getSubjectList().size();j++){
                        if (paper.getSubjectList().get(j).getS_type()>3){
                            break;
                        }
                        for (int k=0;k<paper.getSubjectList().get(j).getOptionList().size();k++){

                            if (paper.getSubjectList().get(j).getOptionList().get(k).getO_id()==o_id){
                                Integer isTrue = paper.getSubjectList().get(j).getOptionList().get(k).getO_isTrue();
                                if (isTrue>20){
                                    paper.getSubjectList().get(j).getOptionList().get(k).setO_isTrue(paper.getSubjectList().get(j).getOptionList().get(k).getO_isTrue()-10);
                                }else {
                                    paper.getSubjectList().get(j).getOptionList().get(k).setO_isTrue(paper.getSubjectList().get(j).getOptionList().get(k).getO_isTrue()+10);
                                }

                            }else {
                                Integer isTrue = paper.getSubjectList().get(j).getOptionList().get(k).getO_isTrue();
                                if (isTrue>10){

                                }else {
                                    paper.getSubjectList().get(j).getOptionList().get(k).setO_isTrue(paper.getSubjectList().get(j).getOptionList().get(k).getO_isTrue()+20);
                                }
                            }
                        }
                    }
                }else if (resultList.get(i).getSubject().getS_type()==4){
                    for (int j=0;j<paper.getSubjectList().size();j++){
                        if (resultList.get(i).getSj_id()==paper.getSubjectList().get(j).getSj_id()){
                            paper.getSubjectList().get(j).getOptionList().get(0).setO_name(resultList.get(i).getResult());
                        }
                    }
                }else {
                    paper.getResultList().add(resultList.get(i));
                }
            }
            float totalScore = 0;
            for (int i=0;i<userScoreList.size();i++){
                totalScore = totalScore+userScoreList.get(i).getScore();
            }

            User user = userService.selectUserById(u_id);
            session.setAttribute("thisPaperUser",user);
            session.setAttribute("totalScore",totalScore);
            session.setAttribute("correctPaper",paper);
            session.setAttribute("userScore",userScoreList);
            return "correctPaper";

        }else {
            return "teacherMain";
        }


    }

    @RequestMapping("/exitLogin")
    public String exitLogin(HttpSession session){
        session.invalidate();
        return "login";
    }


}

