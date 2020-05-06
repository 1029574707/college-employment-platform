package com.zshnb.ballplatform.vo;

/**
 * CreateDate：2020/5/6 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public class JobInfoStatistics {

    private int hasJobCount;

    private int noJobCount;

    public int getHasJobCount() {
        return hasJobCount;
    }

    public void setHasJobCount(int hasJobCount) {
        this.hasJobCount = hasJobCount;
    }

    public int getNoJobCount() {
        return noJobCount;
    }

    public void setNoJobCount(int noJobCount) {
        this.noJobCount = noJobCount;
    }

    @Override
    public String toString() {
        return "JobInfoStatistics{" +
                "hasJobCount=" + hasJobCount +
                ", noJobCount=" + noJobCount +
                '}';
    }
}