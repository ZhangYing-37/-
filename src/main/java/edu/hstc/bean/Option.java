package edu.hstc.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class Option {

    private Integer o_id;

    @NotEmpty(message = "选项内容不能为空")
    private String o_name;

    private Integer o_isTrue;
    private String o_picPath;
    private Integer o_count;
    private Integer sj_id;

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public Integer getO_isTrue() {
        return o_isTrue;
    }

    public void setO_isTrue(Integer o_isTrue) {
        this.o_isTrue = o_isTrue;
    }

    public String getO_picPath() {
        return o_picPath;
    }

    public void setO_picPath(String o_picPath) {
        this.o_picPath = o_picPath;
    }

    public Integer getO_count() {
        return o_count;
    }

    public void setO_count(Integer o_count) {
        this.o_count = o_count;
    }

    public Integer getSj_id() {
        return sj_id;
    }

    public void setSj_id(Integer sj_id) {
        this.sj_id = sj_id;
    }

    @Override
    public String toString() {
        return "Option{" +
                "o_id=" + o_id +
                ", o_name='" + o_name + '\'' +
                ", o_isTrue=" + o_isTrue +
                ", o_picPath='" + o_picPath + '\'' +
                ", o_count=" + o_count +
                ", sj_id=" + sj_id +
                '}';
    }
}
