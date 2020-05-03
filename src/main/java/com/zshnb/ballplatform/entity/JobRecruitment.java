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
public class JobRecruitment extends Model<JobRecruitment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("companyName")
    private String companyName;

    @TableField("companyProperty")
    private String companyProperty;

    private String position;

    @TableField("personCount")
    private Integer personCount;

    @TableField("imgUrl")
    private String imgUrl;

    private Integer type;

    private String require;

    private Integer salary;

    private String address;

    private String deadline;

    @TableField("concatInfo")
    private String concatInfo;

    @TableField("publisherId")
    private String publisherId;

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
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyProperty() {
        return companyProperty;
    }

    public void setCompanyProperty(String companyProperty) {
        this.companyProperty = companyProperty;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public String getConcatInfo() {
        return concatInfo;
    }

    public void setConcatInfo(String concatInfo) {
        this.concatInfo = concatInfo;
    }
    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
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
        return "JobRecruitment{" +
            "id=" + id +
            ", companyName=" + companyName +
            ", companyProperty=" + companyProperty +
            ", position=" + position +
            ", personCount=" + personCount +
            ", imgUrl=" + imgUrl +
            ", type=" + type +
            ", require=" + require +
            ", salary=" + salary +
            ", address=" + address +
            ", deadline=" + deadline +
            ", concatInfo=" + concatInfo +
            ", publisherId=" + publisherId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
