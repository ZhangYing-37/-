package edu.hstc.controller;

import edu.hstc.bean.Course;
import edu.hstc.bean.Paper;
import edu.hstc.bean.Teacher;
import edu.hstc.service.CourseService;
import edu.hstc.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class PaperController {

    @Autowired
    PaperService paperService;
    @Autowired
    CourseService courseService;

    @RequestMapping("/selectPaper")
    public String selectPaper(String title, Integer co_id, Model model){
        List<Paper> paperList = paperService.getPaperByTitle(title,co_id);
        Course course = courseService.getCourseByCoid(co_id);
        model.addAttribute("thisCourse",course);
        model.addAttribute("paperList",paperList);
        model.addAttribute("searchTitle",title);
        return "teacherPaperList";
    }

    @RequestMapping("/newPaper")
    public String newPaper(String t_title, Integer co_id, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
        Integer p_id = paperService.newPaper(t_title,co_id,teacher.getT_id());
        Paper paper = paperService.getPaperById(p_id);
        session.setAttribute("totalScore",0);
        session.setAttribute("thisPaper",paper);
        return "redirect:toEditPaper";
    }

    @RequestMapping("/toEditPaper")
    public String toEditPaper(Integer p_id, HttpSession session){
        if (p_id==null||p_id==0){
            Paper paper= (Paper) session.getAttribute("thisPaper");
            p_id=paper.getP_id();
        }
        Paper paper = paperService.getPaperById(p_id);
        /*if (paper.getSubjectList().size()>0){
            paper.setSubjectList(paperService.subjectListSort(paper.getSubjectList()));
        }*/
        float totalScore = paperService.getScore(paper);
        session.setAttribute("totalScore",totalScore);
        session.setAttribute("thisPaper",paper);
        return "paperEdit";
    }

    @RequestMapping("/toPaperSetting")
    public String toPaperSetting(Integer p_id, HttpSession session){
        Paper paper = paperService.getPaperById(p_id);
        session.setAttribute("thisPaper",paper);
        return "paperSetting";
    }

    @RequestMapping("/toWritePaper")
    public String toWritePaper(Integer p_id, HttpSession session){
        Paper paper = paperService.getPaperById(p_id);
        session.setAttribute("thisPaper",paper);
        return "writePaper";
    }

    @RequestMapping("/updatePaperND")
    @ResponseBody
    public String updatePaperND(String paperTitle, String paperDesc, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        int count = paperService.updatePaper(paperTitle,paperDesc,paper.getResultCount(),paper.getP_id());
        if (count>0){
            paper = paperService.getPaperById(paper.getP_id());
            /*if (paper.getSubjectList().size()>0){
                paper.setSubjectList(paperService.subjectListSort(paper.getSubjectList()));
            }*/
            float totalScore = paperService.getScore(paper);
            session.setAttribute("totalScore",totalScore);
            session.setAttribute("thisPaper",paper);
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @RequestMapping("/updatePaperSetting")
    @ResponseBody
    public String updatePaperSetting(@RequestBody Map map,HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        String startTime = (String) map.get("startTime");
        String endTime = (String) map.get("endTime");
        float DXscore = Float.parseFloat((String)map.get("DXscore"));
        paperService.updatePaperSetting(startTime,endTime,DXscore,1,paper.getP_id());
        return "";
    }

    @RequestMapping("/toStopPaper")
    public String toStopPaper(Integer p_id, HttpSession session){
        Paper paper = paperService.getPaperById(p_id);
        paperService.updatePaperSetting(paper.getStartTime(),paper.getEndTime(),paper.getDXscore(),0,p_id);
        return "redirect:/toPaperList?co_id="+paper.getCo_id();
    }

    @RequestMapping("/deletePaper")
    public String deletePaper(Integer p_id,Integer co_id, Model model){
        paperService.deletePaper(p_id);
        List<Paper> paperList = paperService.getPaperByCId(co_id);
        Course course = courseService.getCourseByCoid(co_id);
        model.addAttribute("thisCourse",course);
        model.addAttribute("paperList",paperList);
        return "teacherPaperList";
    }

}
