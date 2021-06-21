package edu.hstc.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class Teacher {
    private Integer t_id;

    @NotEmpty(message = "教师编号不能为空")
    private String t_code;

    @NotEmpty(message = "教师用户名不能为空")
    private String t_userName;

    @NotEmpty(message = "教师密码不能为空")
    private String t_password;
    private String t_sex;
    private String t_phone;

    private Integer t_userRole;
    private String t_picPath;

    private UserRole role;

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public String getT_code() {
        return t_code;
    }

    public void setT_code(String t_code) {
        this.t_code = t_code;
    }

    public String getT_userName() {
        return t_userName;
    }

    public void setT_userName(String t_userName) {
        this.t_userName = t_userName;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }

    public String getT_sex() {
        return t_sex;
    }

    public void setT_sex(String t_sex) {
        this.t_sex = t_sex;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    public Integer getT_userRole() {
        return t_userRole;
    }

    public void setT_userRole(Integer t_userRole) {
        this.t_userRole = t_userRole;
    }

    public String getT_picPath() {
        return t_picPath;
    }

    public void setT_picPath(String t_picPath) {
        this.t_picPath = t_picPath;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "t_id=" + t_id +
                ", t_code='" + t_code + '\'' +
                ", t_userName='" + t_userName + '\'' +
                ", t_password='" + t_password + '\'' +
                ", t_sex='" + t_sex + '\'' +
                ", t_phone='" + t_phone + '\'' +
                ", t_userRole=" + t_userRole +
                ", t_picPath='" + t_picPath + '\'' +
                ", role=" + role +
                '}';
    }
}
