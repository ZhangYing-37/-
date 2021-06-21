package edu.hstc.bean;


import org.hibernate.validator.constraints.NotEmpty;

public class Classes {

    private Integer c_id;

    @NotEmpty(message = "班级编号不能为空")
    private String c_code;
    private Integer c_grade;

    @NotEmpty(message = "班级名称不能为空")
    private String c_name;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public Integer getC_grade() {
        return c_grade;
    }

    public void setC_grade(Integer c_grade) {
        this.c_grade = c_grade;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "c_id=" + c_id +
                ", c_code='" + c_code + '\'' +
                ", c_grade='" + c_grade + '\'' +
                ", c_name='" + c_name + '\'' +
                '}';
    }
}
