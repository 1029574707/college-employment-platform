package com.zshnb.ballplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public class PracticeDiary extends Model<PracticeDiary> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    @TableField("studentId")
    private String studentId;

    @TableField(exist = false)
    private String evaluation;

    @TableField("createTime")
    private String createTime;

    @TableField("updateTime")
    private String updateTime;

    @TableField("practiceId")
    private Integer practiceId;

    @TableField(exist = false)
    private String companyName;

    @TableField("evaluationId")
    private Integer evaluationId;

    @TableField(exist = false)
    private String studentName;

    private String date;

    @TableField(exist = false)
    private String practiceInfoType;

    public String getPracticeInfoType() {
        return practiceInfoType;
    }

    public void setPracticeInfoType(String practiceInfoType) {
        this.practiceInfoType = practiceInfoType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Integer practiceId) {
        this.practiceId = practiceId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PracticeDiary{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", studentId='" + studentId + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", practiceId=" + practiceId +
                ", companyName='" + companyName + '\'' +
                ", evaluationId=" + evaluationId +
                ", studentName='" + studentName + '\'' +
                ", date='" + date + '\'' +
                ", practiceInfoType='" + practiceInfoType + '\'' +
                '}';
    }
}
