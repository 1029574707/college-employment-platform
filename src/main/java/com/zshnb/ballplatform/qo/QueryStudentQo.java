package com.zshnb.ballplatform.qo;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 查询学生列表请求类
 **/
public class QueryStudentQo extends PageQo{

    private Integer classId;

    private String studentId;

    private Integer practiceStatus;

    private Integer collegeId;

    private Integer tripartite;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getPracticeStatus() {
        return practiceStatus;
    }

    public void setPracticeStatus(Integer practiceStatus) {
        this.practiceStatus = practiceStatus;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getTripartite() {
        return tripartite;
    }

    public void setTripartite(Integer tripartite) {
        this.tripartite = tripartite;
    }

    @Override
    public String toString() {
        return "AdminQueryStudentQo{" +
                "classId=" + classId +
                ", studentId='" + studentId + '\'' +
                ", practiceStatus=" + practiceStatus +
                ", collegeId=" + collegeId +
                ", tripartite=" + tripartite +
                '}';
    }
}
