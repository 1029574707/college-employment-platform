package com.zshnb.ballplatform.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public class UserStudent extends Model<UserStudent> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    @TableField("collegeId")
    private Integer collegeId;

    @TableField("classId")
    private Integer classId;

    private Integer gender;

    @TableField("phoneNumber")
    private String phoneNumber;

    @TableField("teacherId")
    private String teacherId;

    private String email;

    private String password;

    @TableField(exist = false)
    private String className;

    @TableField(exist = false)
    private String collegeName;

    @TableField(exist = false)
    private String teacherName;

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserStudent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", collegeId=" + collegeId +
                ", classId=" + classId +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", className='" + className + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
