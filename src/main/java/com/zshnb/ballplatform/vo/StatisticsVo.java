package com.zshnb.ballplatform.vo;

/**
 * CreateDate：2020/5/6 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public class StatisticsVo {

    private PracticeInfoStatistics practiceInfoStatistics;

    private JobInfoStatistics jobInfoStatistics;

    public PracticeInfoStatistics getPracticeInfoStatistics() {
        return practiceInfoStatistics;
    }

    public void setPracticeInfoStatistics(PracticeInfoStatistics practiceInfoStatistics) {
        this.practiceInfoStatistics = practiceInfoStatistics;
    }

    public JobInfoStatistics getJobInfoStatistics() {
        return jobInfoStatistics;
    }

    public void setJobInfoStatistics(JobInfoStatistics jobInfoStatistics) {
        this.jobInfoStatistics = jobInfoStatistics;
    }

    @Override
    public String toString() {
        return "StatisticsVo{" +
                "practiceInfoStatistics=" + practiceInfoStatistics +
                ", jobInfoStatistics=" + jobInfoStatistics +
                '}';
    }
}