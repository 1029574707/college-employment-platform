package com.zshnb.ballplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class PracticeInfo extends Model<PracticeInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String company;

    private String position;

    @TableField("beginTime")
    private LocalDateTime beginTime;

    @TableField("endTime")
    private LocalDateTime endTime;

    private String address;

    @TableField("principalName")
    private String principalName;

    @TableField("principalPhone")
    private String principalPhone;

    @TableField("practiceStatus")
    private Integer practiceStatus;

    @TableField("jobContent")
    private String jobContent;

    @TableField("studentId")
    private String studentId;

    private Integer fraction;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }
    public Integer getPracticeStatus() {
        return practiceStatus;
    }

    public void setPracticeStatus(Integer practiceStatus) {
        this.practiceStatus = practiceStatus;
    }
    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public Integer getFraction() {
        return fraction;
    }

    public void setFraction(Integer fraction) {
        this.fraction = fraction;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PracticeInfo{" +
            "id=" + id +
            ", company=" + company +
            ", position=" + position +
            ", beginTime=" + beginTime +
            ", endTime=" + endTime +
            ", address=" + address +
            ", principalName=" + principalName +
            ", principalPhone=" + principalPhone +
            ", practiceStatus=" + practiceStatus +
            ", jobContent=" + jobContent +
            ", studentId=" + studentId +
            ", fraction=" + fraction +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", type=" + type +
        "}";
    }
}
