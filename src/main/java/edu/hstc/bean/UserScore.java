package edu.hstc.bean;

public class UserScore {

    private Integer us_id;
    private Float score;
    private Integer sj_id;
    private Integer p_id;
    private Integer u_id;
    private Integer code;

    public Integer getUs_id() {
        return us_id;
    }

    public void setUs_id(Integer us_id) {
        this.us_id = us_id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getSj_id() {
        return sj_id;
    }

    public void setSj_id(Integer sj_id) {
        this.sj_id = sj_id;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserScore{" +
                "us_id=" + us_id +
                ", score=" + score +
                ", sj_id=" + sj_id +
                ", p_id=" + p_id +
                ", u_id=" + u_id +
                ", code=" + code +
                '}';
    }
}
