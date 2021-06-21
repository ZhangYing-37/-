package edu.hstc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.hstc.bean.Classes;
import edu.hstc.bean.Teacher;
import edu.hstc.bean.User;
import edu.hstc.service.ClassesService;
import edu.hstc.service.UserService;
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
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ClassesService classesService;

    @RequestMapping("/selectAllUser")
    @ResponseBody
    public String selectAllUser(Integer page,Integer limit){
        if (page==null){ page=0; }else {page--;}
        List<User> userList = userService.getAllUser(page, limit);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",userList.size());
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<userList.size();i++){
            userList.get(i).setUserClass(userList.get(i).getClasses().getC_grade()+"级"+userList.get(i).getClasses().getC_code()+"班");
            jsonArray.add(userList.get(i));
        }
        map.put("data",jsonArray);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/toEditUser")
    public String toEditUser(Integer u_id, Model model){
        List<Classes> classesList = classesService.selectAllUserClasses();
        if (u_id==0){

        }else {
            User user = userService.selectUserById(u_id);
            model.addAttribute("thisUser",user);
        }
        model.addAttribute("classesList",classesList);
        return "UserEdit";
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(@RequestBody Map map){
        String u_id = (String) map.get("u_id");
        if (u_id!=null&&!u_id.trim().equals("")){
            Integer u_id1 = Integer.parseInt(u_id);
            String u_code = (String) map.get("u_code");
            String u_password = (String) map.get("u_password");
            String u_realName = (String) map.get("u_realName");
            String u_sex = (String) map.get("u_sex");
            String u_phone = (String) map.get("u_phone");
            String u_email = (String) map.get("u_email");
            Integer c_id = Integer.parseInt((String) map.get("c_id")) ;
            userService.updateUser(u_code,u_realName,u_password,u_realName,u_sex,u_email,u_phone,c_id,u_id1);
        }else {
            String u_code = (String) map.get("u_code");
            String u_password = (String) map.get("u_password");
            String u_realName = (String) map.get("u_realName");
            String u_sex = (String) map.get("u_sex");
            String u_phone = (String) map.get("u_phone");
            String u_email = (String) map.get("u_email");
            Integer c_id = Integer.parseInt((String) map.get("c_id")) ;
           userService.addUser(u_code,u_realName,u_password,u_realName,u_sex,u_email,u_phone,c_id);
        }
        return "";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Integer u_id){
        userService.deleteUser(u_id);
        return "adminUserList";
    }

    @RequestMapping("/toUserMessage")
    public String toUserMessage(HttpSession session,Model model){
        User user = (User) session.getAttribute("loginUser");
        Classes classes = classesService.getClassesById(user.getC_id());
        model.addAttribute("thisClasses",classes);
        return "userMessage";
    }

    @RequestMapping("/userMessageEdit")
    @ResponseBody
    public String userMessageEdit(@RequestBody Map map,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        Integer u_id = user.getU_id();
        String u_code = user.getU_code();
        String u_password = user.getU_password();
        String u_realName = (String) map.get("u_realName");
        String u_sex = (String) map.get("u_sex");
        String u_phone = (String) map.get("u_phone");
        String u_email = (String) map.get("u_email");
        Integer c_id = user.getC_id() ;
        user.setU_realName(u_realName);
        user.setU_sex(u_sex);
        user.setU_phone(u_phone);
        user.setU_email(u_email);
        session.setAttribute("loginUser",user);
        userService.updateUser(u_code,u_realName,u_password,u_realName,u_sex,u_email,u_phone,c_id,u_id);
        return "";
    }


    @RequestMapping("/userPasswordEdit")
    @ResponseBody
    public String userPasswordEdit(@RequestBody Map map,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        String u_password = user.getU_password();
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        String rePassword = (String) map.get("rePassword");
        if (u_password.equals(oldPassword)){
            if (newPassword.equals(rePassword)){
                userService.updateUserPassword(newPassword,user.getU_id());
                user.setU_password(newPassword);
                session.setAttribute("loginUser",user);
                return "ok";
            }else {
                return "error2";
            }
        }else {
            return "error1";
        }
    }
}
