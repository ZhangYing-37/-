package edu.hstc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.hstc.bean.Classes;
import edu.hstc.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClassesController {
    @Autowired
    ClassesService classesService;

    @RequestMapping("/selectAllClasses")
    @ResponseBody
    public String selectAllClasses(Integer page,Integer limit){
        if (page==null){ page=0; }else {page--;}
        List<Classes> classesList = classesService.getAllClasses(page,limit);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",classesList.size());
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<classesList.size();i++){
            jsonArray.add(classesList.get(i));
        }
        map.put("data",jsonArray);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/toEditClasses")
    public String toEditClasses(Integer c_id, Model model){
        if (c_id==0){

        }else {
            Classes classes = classesService.getClassesById(c_id);
            model.addAttribute("thisClasses",classes);
        }
        return "classesEdit";
    }

    @RequestMapping("/editClasses")
    @ResponseBody
    public String editClasses(@RequestBody Map map){
        String c_id = (String) map.get("c_id");
        if (c_id!=null&&!c_id.trim().equals("")){
            Integer c_id1 = Integer.parseInt(c_id);
            Integer c_grade = Integer.parseInt((String) map.get("c_grade"));
            String c_code = (String) map.get("c_code");
            String c_name = (String) map.get("c_name");
            classesService.updateClasses(c_code,c_grade,c_name,c_id1);
        }else {
            Integer c_grade = Integer.parseInt((String) map.get("c_grade"));
            String c_code = (String) map.get("c_code");
            String c_name = (String) map.get("c_name");
            classesService.addClasses(c_code,c_grade,c_name);
        }
        return "";
    }

    @RequestMapping("/deleteClasses")
    public String deleteClasses(Integer c_id){
        classesService.deleteClasses(c_id);
        return "adminClassList";
    }
}
