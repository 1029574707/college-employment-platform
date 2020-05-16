package com.zshnb.ballplatform.vo;

/**
 * CreateDate：2020/5/15 <br/>
 * Author：WangHao <br/>
 * Description: 学院统计vo类
 **/
public class CollegeStatisticsVo {

    private String collegeName;

    private int classCount;

    private int studentCount;

    private int jobStudentCount;

    private String jobPercent;

    private int practiceStudentCount;

    private String practicePercent;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getJobStudentCount() {
        return jobStudentCount;
    }

    public void setJobStudentCount(int jobStudentCount) {
        this.jobStudentCount = jobStudentCount;
    }

    public String getJobPercent() {
        return jobPercent;
    }

    public void setJobPercent(String jobPercent) {
        this.jobPercent = jobPercent;
    }

    public int getPracticeStudentCount() {
        return practiceStudentCount;
    }

    public void setPracticeStudentCount(int practiceStudentCount) {
        this.practiceStudentCount = practiceStudentCount;
    }

    public String getPracticePercent() {
        return practicePercent;
    }

    public void setPracticePercent(String practicePercent) {
        this.practicePercent = practicePercent;
    }

    @Override
    public String toString() {
        return "CollegeStatisticsVo{" +
                "collegeName='" + collegeName + '\'' +
                ", classCount=" + classCount +
                ", studentCount=" + studentCount +
                ", jobStudentCount=" + jobStudentCount +
                ", jobPercent='" + jobPercent + '\'' +
                ", practiceStudentCount=" + practiceStudentCount +
                ", practicePercent='" + practicePercent + '\'' +
                '}';
    }
}
