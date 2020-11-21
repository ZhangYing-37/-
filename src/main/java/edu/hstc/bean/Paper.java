package edu.hstc.bean;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class Paper {

    private Integer p_id;

    @NotEmpty(message = "试卷标题不能为空")
    private String p_title;

    private String p_desc;
    private Integer co_id;
    private Integer createdBy;
    private String createdTime;
    private String startTime;
    private String endTime;
    private Integer resultCount;
    private Integer isRelease;
    private Float DXscore;

    private Course course;
    private List<Result> resultList;
    private List<Subject> subjectList;

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Integer getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    public Float getDXscore() {
        return DXscore;
    }

    public void setDXscore(Float DXscore) {
        this.DXscore = DXscore;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "p_id=" + p_id +
                ", p_title='" + p_title + '\'' +
                ", p_desc='" + p_desc + '\'' +
                ", co_id=" + co_id +
                ", createdBy=" + createdBy +
                ", createdTime='" + createdTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", resultCount=" + resultCount +
                ", isRelease=" + isRelease +
                ", DXscore=" + DXscore +
                ", course=" + course +
                ", resultList=" + resultList +
                ", subjectList=" + subjectList +
                '}';
    }
}
