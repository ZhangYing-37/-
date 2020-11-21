package edu.hstc.bean;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class Subject {
    private Integer sj_id;

    private Integer s_code;

    @NotEmpty(message = "题目名不能为空")
    private String s_name;

    private Integer s_type;
    private Float s_score;
    private String s_picPath;
    private Integer p_id;

    private List<Option> optionList;

    public Integer getSj_id() {
        return sj_id;
    }

    public void setSj_id(Integer sj_id) {
        this.sj_id = sj_id;
    }

    public Integer getS_code() {
        return s_code;
    }

    public void setS_code(Integer s_code) {
        this.s_code = s_code;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Integer getS_type() {
        return s_type;
    }

    public void setS_type(Integer s_type) {
        this.s_type = s_type;
    }

    public Float getS_score() {
        return s_score;
    }

    public void setS_score(Float s_score) {
        this.s_score = s_score;
    }

    public String getS_picPath() {
        return s_picPath;
    }

    public void setS_picPath(String s_picPath) {
        this.s_picPath = s_picPath;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "sj_id=" + sj_id +
                ", s_code=" + s_code +
                ", s_name='" + s_name + '\'' +
                ", s_type=" + s_type +
                ", s_score=" + s_score +
                ", s_picPath='" + s_picPath + '\'' +
                ", p_id=" + p_id +
                ", optionList=" + optionList +
                '}';
    }
}
