package com.zshnb.ballplatform.qo;

/**
 * CreateDate：2020/5/10 <br/>
 * Author：WangHao <br/>
 * Description: 教师端查看实习计划、日记、报告的请求类
 **/
public class PracticeInfoQo extends PageQo {

    private String studentName;

    private Integer type;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PracticeInfoQo{" +
                "studentName='" + studentName + '\'' +
                ", type=" + type +
                '}';
    }
}
