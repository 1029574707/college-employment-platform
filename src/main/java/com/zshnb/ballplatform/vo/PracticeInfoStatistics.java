package com.zshnb.ballplatform.vo;

/**
 * CreateDate：2020/5/6 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public class PracticeInfoStatistics {

    private int notPracticeCount;

    private int practicingCount;

    private int donePracticeCount;

    // private int undocumentedCount;

    public int getNotPracticeCount() {
        return notPracticeCount;
    }

    public void setNotPracticeCount(int notPracticeCount) {
        this.notPracticeCount = notPracticeCount;
    }

    public int getPracticingCount() {
        return practicingCount;
    }

    public void setPracticingCount(int practicingCount) {
        this.practicingCount = practicingCount;
    }

    public int getDonePracticeCount() {
        return donePracticeCount;
    }

    public void setDonePracticeCount(int donePracticeCount) {
        this.donePracticeCount = donePracticeCount;
    }

    // public int getUndocumentedCount() {
    //     return undocumentedCount;
    // }
    //
    // public void setUndocumentedCount(int undocumentedCount) {
    //     this.undocumentedCount = undocumentedCount;
    // }

    @Override
    public String toString() {
        return "PracticeInfoStatistics{" +
                "notPracticeCount=" + notPracticeCount +
                ", practicingCount=" + practicingCount +
                ", donePracticeCount=" + donePracticeCount +
                // ", undocumentedCount=" + undocumentedCount +
                '}';
    }
}