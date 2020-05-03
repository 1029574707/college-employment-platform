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
public class JobInfo extends Model<JobInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String company;

    private String position;

    private String address;

    private Integer tripartite;

    @TableField("contactName")
    private String contactName;

    @TableField("contactPhone")
    private String contactPhone;

    @TableField("contactEmail")
    private String contactEmail;

    private String note;

    @TableField("studentId")
    private String studentId;

    @TableField("createTime")
    private String createTime;

    @TableField("updateTime")
    private String updateTime;

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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getTripartite() {
        return tripartite;
    }

    public void setTripartite(Integer tripartite) {
        this.tripartite = tripartite;
    }
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
            "id=" + id +
            ", company=" + company +
            ", position=" + position +
            ", address=" + address +
            ", tripartite=" + tripartite +
            ", contactName=" + contactName +
            ", contactPhone=" + contactPhone +
            ", contactEmail=" + contactEmail +
            ", note=" + note +
            ", studentId=" + studentId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
