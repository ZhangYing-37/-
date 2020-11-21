package edu.hstc.bean;


public class Result {
    private Integer re_id;
    private String result;
    private Float r_score;
    private Integer p_id;
    private Integer sj_id;
    private Integer u_id;

    private Subject subject;

    public Integer getRe_id() {
        return re_id;
    }

    public void setRe_id(Integer re_id) {
        this.re_id = re_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Float getR_score() {
        return r_score;
    }

    public void setR_score(Float r_score) {
        this.r_score = r_score;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getSj_id() {
        return sj_id;
    }

    public void setSj_id(Integer sj_id) {
        this.sj_id = sj_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Result{" +
                "re_id=" + re_id +
                ", result='" + result + '\'' +
                ", r_score=" + r_score +
                ", p_id=" + p_id +
                ", sj_id=" + sj_id +
                ", u_id=" + u_id +
                ", subject=" + subject +
                '}';
    }
}
