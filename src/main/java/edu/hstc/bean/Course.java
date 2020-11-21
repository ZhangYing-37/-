package edu.hstc.bean;

public class Course {

    Integer co_id;
    String c_name;
    String c_desc;


    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    @Override
    public String toString() {
        return "Course{" +
                "co_id=" + co_id +
                ", c_name='" + c_name + '\'' +
                ", c_desc='" + c_desc + '\'' +
                '}';
    }
}
