package edu.hstc.controller;

import edu.hstc.bean.Option;
import edu.hstc.bean.Paper;
import edu.hstc.bean.Subject;
import edu.hstc.service.OptionService;
import edu.hstc.service.PaperService;
import edu.hstc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SubjectController {

    @Autowired
    PaperService paperService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    OptionService optionService;

    @RequestMapping("/updateSubjectDX1")
    @ResponseBody
    public String addORUpdateSubjectDX1(@RequestBody Map map, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        System.out.println("map="+map.toString());
        Integer s_code = Integer.parseInt((String) map.get("s_code"));
        String s_name = (String) map.get("s_name");
        Float s_score = Float.parseFloat((String) map.get("s_score"));
        String mapKey = "o_name";
        int trueId = Integer.parseInt((String)map.get("isTrue"));
        String o_name = "";

        if (map.get("sj_id").equals("")){
            Integer sj_id = subjectService.addSubject(s_code,s_name,1,s_score,paper.getP_id());
            int i = 1;
            while (true){
                String mapKeyCopy = mapKey+i;
                if (map.containsKey(mapKeyCopy)){
                    o_name = (String) map.get(mapKeyCopy);
                    if (i==trueId){
                        optionService.addOption(o_name,1,sj_id);
                    }else {
                        optionService.addOption(o_name,2,sj_id);
                    }
                    i++;
                }else { break; }
            }

            return "addSubjectDX success";

        }else {
            Integer sj_id = Integer.parseInt((String)map.get("sj_id"));
            Subject subject = (Subject) session.getAttribute("thisSubject");
            subject.setSj_id(sj_id);
            subject.setS_code(s_code);
            subject.setS_name(s_name);
            subject.setS_type(1);
            subject.setS_score(s_score);
            subjectService.updateSubject(subject);

            int i = 0;

            while (true){
                i++;
                String mapKeyCopy = mapKey+i;
                if (!map.containsKey(mapKeyCopy)){
                    i--;
                    break;
                }
            }

            int size = subject.getOptionList().size();
            int difference = size-i;
            int numAbs = Math.abs(difference);

            if (difference>0){
                size = size - numAbs;
            }
            Option option = new Option();
            for (int j=1;j<=size;j++){
                option = subject.getOptionList().get(j-1);
                String mapKeyCopy = mapKey+j;
                o_name = (String) map.get(mapKeyCopy);

                if (j==trueId){
                    optionService.updateOption(o_name,1,option.getO_id());
                }else {
                    optionService.updateOption(o_name,2,option.getO_id());
                }

            }
            if (difference>0){
                for (int j=size;j<subject.getOptionList().size();j++){
                    option = subject.getOptionList().get(j);
                    optionService.deleteOption(option.getO_id());
                }
            }else if (difference<0){
                for (int j=size+1;j<=size+numAbs;j++){
                    String mapKeyCopy = mapKey+j;
                    o_name = (String) map.get(mapKeyCopy);
                    if (j==trueId){
                        optionService.addOption(o_name,1,sj_id);
                    }else {
                        optionService.addOption(o_name,2,sj_id);
                    }
                }
            }

            return "updateSubjectDX success";
        }
    }

    @RequestMapping("/updateSubjectDX")
    @ResponseBody
    public String addORUpdateSubjectDX(@RequestBody Map map, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        System.out.println("map="+map.toString());
        Integer s_code = Integer.parseInt((String) map.get("s_code"));
        String s_name = (String) map.get("s_name");
        Float s_score = Float.parseFloat((String) map.get("s_score"));
        String mapKey = "o_name";
        String mapKey1 = "isTrue";
        String o_name = "";
        Integer o_isTrue = 2;

        if (map.get("sj_id").equals("")){
            Integer sj_id = subjectService.addSubject(s_code,s_name,2,s_score,paper.getP_id());
            int i = 1;
            while (true){
                String mapKeyCopy = mapKey+i;
                String mapKey1Copy = mapKey1+i;
                if (map.containsKey(mapKeyCopy)){
                    o_name = (String) map.get(mapKeyCopy);
                    if (map.containsKey(mapKey1Copy)){
                        o_isTrue = 1;
                    }else {
                        o_isTrue = 2;
                    }
                    optionService.addOption(o_name,o_isTrue,sj_id);
                    i++;
                }else { break; }
            }

            return "addSubjectDX success";

        }else {
            Integer sj_id = Integer.parseInt((String)map.get("sj_id"));
            Subject subject = (Subject) session.getAttribute("thisSubject");
            subject.setSj_id(sj_id);
            subject.setS_code(s_code);
            subject.setS_name(s_name);
            subject.setS_type(2);
            subject.setS_score(s_score);
            subjectService.updateSubject(subject);

            int i = 0;

            while (true){
                i++;
                String mapKeyCopy = mapKey+i;
                System.out.println("hasKey?="+map.containsKey(mapKeyCopy));
                System.out.println("i="+i);
                if (!map.containsKey(mapKeyCopy)){
                    i--;
                    break;
                }
            }

            int size = subject.getOptionList().size();
            int difference = size-i;
            int numAbs = Math.abs(difference);

            System.out.println("size="+size+",difference="+difference+",numAbs"+numAbs);

            if (difference>0){
                size = size - numAbs;
            }
            Option option = new Option();
            for (int j=1;j<=size;j++){
                option = subject.getOptionList().get(j-1);
                String mapKeyCopy = mapKey+j;
                String mapKey1Copy = mapKey1+j;

                o_name = (String) map.get(mapKeyCopy);
                if (map.containsKey(mapKey1Copy)){
                    o_isTrue = 1;
                }else {
                    o_isTrue = 2;
                }
                optionService.updateOption(o_name,o_isTrue,option.getO_id());
            }
            if (difference>0){
                for (int j=size;j<subject.getOptionList().size();j++){
                    option = subject.getOptionList().get(j);
                    optionService.deleteOption(option.getO_id());
                }
            }else if (difference<0){
                for (int j=size+1;j<=size+numAbs;j++){
                    String mapKeyCopy = mapKey+j;
                    String mapKey1Copy = mapKey1+j;

                    o_name = (String) map.get(mapKeyCopy);
                    if (map.containsKey(mapKey1Copy)){
                        o_isTrue = 1;
                    }else {
                        o_isTrue = 2;
                    }
                    optionService.addOption(o_name,o_isTrue,sj_id);
                }
            }

            return "updateSubjectDX success";

        }
    }



    @RequestMapping("/updateSubjectPD")
    @ResponseBody
    public String addORUpdateSubjectPD(@RequestBody Map map, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        System.out.println("map="+map.toString());
        Integer s_code = Integer.parseInt((String) map.get("s_code"));
        String s_name = (String) map.get("s_name");
        Float s_score = Float.parseFloat((String) map.get("s_score"));
        String mapKey = "o_name";
        int trueId = Integer.parseInt((String)map.get("isTrue"));
        String o_name = "";

        if (map.get("sj_id").equals("")){
            Integer sj_id = subjectService.addSubject(s_code,s_name,3,s_score,paper.getP_id());
            int i = 1;
            while (true){
                String mapKeyCopy = mapKey+i;
                if (map.containsKey(mapKeyCopy)){
                    o_name = (String) map.get(mapKeyCopy);
                    if (i==trueId){
                        optionService.addOption(o_name,1,sj_id);
                    }else {
                        optionService.addOption(o_name,2,sj_id);
                    }
                    i++;
                }else { break; }
            }

            return "addSubjectDX success";

        }else {
            Integer sj_id = Integer.parseInt((String)map.get("sj_id"));
            Subject subject = (Subject) session.getAttribute("thisSubject");
            subject.setSj_id(sj_id);
            subject.setS_code(s_code);
            subject.setS_name(s_name);
            subject.setS_type(3);
            subject.setS_score(s_score);
            subjectService.updateSubject(subject);

            int i = 0;

            while (true){
                i++;
                String mapKeyCopy = mapKey+i;
                if (!map.containsKey(mapKeyCopy)){
                    i--;
                    break;
                }
            }

            int size = subject.getOptionList().size();
            int difference = size-i;
            int numAbs = Math.abs(difference);

            if (difference>0){
                size = size - numAbs;
            }
            Option option = new Option();
            for (int j=1;j<=size;j++){
                option = subject.getOptionList().get(j-1);
                String mapKeyCopy = mapKey+j;
                o_name = (String) map.get(mapKeyCopy);

                if (j==trueId){
                    optionService.updateOption(o_name,1,option.getO_id());
                }else {
                    optionService.updateOption(o_name,2,option.getO_id());
                }

            }
            if (difference>0){
                for (int j=size;j<subject.getOptionList().size();j++){
                    option = subject.getOptionList().get(j);
                    optionService.deleteOption(option.getO_id());
                }
            }else if (difference<0){
                for (int j=size+1;j<=size+numAbs;j++){
                    String mapKeyCopy = mapKey+j;
                    o_name = (String) map.get(mapKeyCopy);
                    if (j==trueId){
                        optionService.addOption(o_name,1,sj_id);
                    }else {
                        optionService.addOption(o_name,2,sj_id);
                    }
                }
            }

            return "updateSubjectDX success";
        }
    }

    @RequestMapping("/updateSubjectTK")
    @ResponseBody
    public String addORUpdateSubjectTK(Integer sj_id, Integer s_code, String s_name, Float s_score, Integer s_type, String o_name, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        if (sj_id==null || sj_id==0){
            Integer id = subjectService.addSubject(s_code,s_name,s_type,s_score,paper.getP_id());
            optionService.addOption(o_name,1,id);
            return "addSubjectTK success";
        }else {
            Subject subject = (Subject) session.getAttribute("thisSubject");
            subject.setSj_id(sj_id);
            subject.setS_code(s_code);
            subject.setS_name(s_name);
            subject.setS_type(s_type);
            subject.setS_score(s_score);
            subjectService.updateSubject(subject);
            optionService.updateOption(o_name,1,subject.getOptionList().get(0).getO_id());
            return "updateSubjectTK success";
        }
    }

    @RequestMapping("/updateSubjectJD")
    @ResponseBody
    public String addORUpdateSubjectJD(Integer sj_id, Integer s_code, String s_name, Float s_score, Integer s_type, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");
        if (sj_id==null || sj_id==0){
            Integer id = subjectService.addSubject(s_code,s_name,5,s_score,paper.getP_id());
            return "addSubjectJD success";
        }else {
            Subject subject = (Subject) session.getAttribute("thisSubject");
            subject.setSj_id(sj_id);
            subject.setS_code(s_code);
            subject.setS_name(s_name);
            subject.setS_type(s_type);
            subject.setS_score(s_score);
            subjectService.updateSubject(subject);
            return "updateSubjectJD success";
        }
    }

    @RequestMapping("/moveUpSubject")
    @ResponseBody
    public Boolean moveUpSubject(Integer s_code,HttpSession session){
        System.out.println("s_code="+s_code);
        Paper paper = (Paper) session.getAttribute("thisPaper");
        Boolean flag = subjectService.moveUpSubject(paper.getSubjectList().get(s_code-1),paper.getSubjectList().get(s_code-2));
        paper = paperService.getPaperById(paper.getP_id());
        session.setAttribute("thisPaper",paper);
        return flag;
    }

    @RequestMapping("/moveDownSubject")
    @ResponseBody
    public Boolean moveDownSubject(Integer s_code,HttpSession session){
        System.out.println("s_code="+s_code);
        Paper paper = (Paper) session.getAttribute("thisPaper");
        Boolean flag = subjectService.moveDownSubject(paper.getSubjectList().get(s_code-1),paper.getSubjectList().get(s_code),paper.getSubjectList().size());
        paper = paperService.getPaperById(paper.getP_id());
        session.setAttribute("thisPaper",paper);
        return flag;
    }

    @RequestMapping("/deleteSubject")
    @ResponseBody
    public String deleteSubject(Integer sj_id, Integer s_code, HttpSession session){
        Paper paper = (Paper) session.getAttribute("thisPaper");

        subjectService.deleteSubject(sj_id);
        for (int i=s_code;i<=paper.getSubjectList().size();i++){
            Subject subject = paper.getSubjectList().get(i);
            subject.setS_code(i);
            subjectService.updateSubject(subject);
        }

        paper = paperService.getPaperById(paper.getP_id());
        session.setAttribute("thisPaper",paper);
        return "";
    }
}
