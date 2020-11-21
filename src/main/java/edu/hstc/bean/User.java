package edu.hstc.bean;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class User {

    private Integer u_id;

    @NotEmpty(message = "用户编号不能为空")
    private String u_code;

    @NotEmpty(message = "用户姓名不能为空")
    private String u_userName;

    @NotEmpty(message = "用户密码不能为空")
    private String u_password;

    private Integer u_userRole;

    @NotEmpty(message = "用户真实姓名不能为空")
    private String u_realName;

    private Integer u_sex;

    @Email(message = "邮箱格式不正确")
    private String u_email;

    @NotEmpty(message = "用户电话号码不能为空")
    private String u_phone;

    private Integer c_id;
    private String u_picPath;

    private UserRole role;
    private Classes classes;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_code() {
        return u_code;
    }

    public void setU_code(String u_code) {
        this.u_code = u_code;
    }

    public String getU_userName() {
        return u_userName;
    }

    public void setU_userName(String u_userName) {
        this.u_userName = u_userName;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public Integer getU_userRole() {
        return u_userRole;
    }

    public void setU_userRole(Integer u_userRole) {
        this.u_userRole = u_userRole;
    }

    public String getU_realName() {
        return u_realName;
    }

    public void setU_realName(String u_realName) {
        this.u_realName = u_realName;
    }

    public Integer getU_sex() {
        return u_sex;
    }

    public void setU_sex(Integer u_sex) {
        this.u_sex = u_sex;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getU_picPath() {
        return u_picPath;
    }

    public void setU_picPath(String u_picPath) {
        this.u_picPath = u_picPath;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_code='" + u_code + '\'' +
                ", u_userName='" + u_userName + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_userRole=" + u_userRole +
                ", u_realName='" + u_realName + '\'' +
                ", u_sex=" + u_sex +
                ", u_email='" + u_email + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", c_id=" + c_id +
                ", u_picPath='" + u_picPath + '\'' +
                ", role=" + role +
                ", classes=" + classes +
                '}';
    }
}
