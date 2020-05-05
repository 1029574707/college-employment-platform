package com.zshnb.ballplatform.vo;

import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.entity.PracticeInfo;

import java.util.List;

/**
 * CreateDate：2020/5/5 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public class StudentInfo {

    private String id;

    private String name;

    private Integer collegeId;

    private Integer classId;

    private Integer gender;

    private String phoneNumber;

    private String email;

    private String className;

    private String collegeName;

    private List<PracticeInfo> practiceInfoList;

    private List<JobInfo> jobInfoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<PracticeInfo> getPracticeInfoList() {
        return practiceInfoList;
    }

    public void setPracticeInfoList(List<PracticeInfo> practiceInfoList) {
        this.practiceInfoList = practiceInfoList;
    }

    public List<JobInfo> getJobInfoList() {
        return jobInfoList;
    }

    public void setJobInfoList(List<JobInfo> jobInfoList) {
        this.jobInfoList = jobInfoList;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", collegeId=" + collegeId +
                ", classId=" + classId +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", className='" + className + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", practiceInfoList=" + practiceInfoList +
                ", jobInfoList=" + jobInfoList +
                '}';
    }
}
