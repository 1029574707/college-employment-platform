package com.zshnb.ballplatform.qo;

/**
 * CreateDate：2020/5/11 <br/>
 * Author：WangHao <br/>
 * Description: 招聘信息查询qo
 **/
public class RecruitmentQo extends PageQo{

    private Integer type;

    private Integer minSalary;

    private Integer maxSalary;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "RecruitmentQo{" +
                "type=" + type +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
