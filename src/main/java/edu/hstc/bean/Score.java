package edu.hstc.bean;

public class Score {
    private Integer sc_id;
    private Float DX_score;
    private Float DX1_score;
    private Float PD_score;
    private Float TK_score;
    private Float JD_score;
    private Float totalScore;
    private String isCorrect;
    private Integer p_id;
    private Integer u_id;

    private Paper paper;
    private User user;
    private String userCode;
    private String userName;
    private String title;

    public Integer getSc_id() {
        return sc_id;
    }

    public void setSc_id(Integer sc_id) {
        this.sc_id = sc_id;
    }

    public Float getDX_score() {
        return DX_score;
    }

    public void setDX_score(Float DX_score) {
        this.DX_score = DX_score;
    }

    public Float getDX1_score() {
        return DX1_score;
    }

    public void setDX1_score(Float DX1_score) {
        this.DX1_score = DX1_score;
    }

    public Float getPD_score() {
        return PD_score;
    }

    public void setPD_score(Float PD_score) {
        this.PD_score = PD_score;
    }

    public Float getTK_score() {
        return TK_score;
    }

    public void setTK_score(Float TK_score) {
        this.TK_score = TK_score;
    }

    public Float getJD_score() {
        return JD_score;
    }

    public void setJD_score(Float JD_score) {
        this.JD_score = JD_score;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Score{" +
                "sc_id=" + sc_id +
                ", DX_score=" + DX_score +
                ", DX1_score=" + DX1_score +
                ", PD_score=" + PD_score +
                ", TK_score=" + TK_score +
                ", JD_score=" + JD_score +
                ", totalScore=" + totalScore +
                ", isCorrect='" + isCorrect + '\'' +
                ", p_id=" + p_id +
                ", u_id=" + u_id +
                ", paper=" + paper +
                ", user=" + user +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
