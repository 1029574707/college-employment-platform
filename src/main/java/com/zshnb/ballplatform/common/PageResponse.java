package com.zshnb.ballplatform.common;

import java.util.List;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 分页返回对象
 **/
public class PageResponse<T> {

    private long totalCount;
    private List<T> results;

    public PageResponse(long totalCount, List<T> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public PageResponse() {
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "totalCount=" + totalCount +
                ", results=" + results +
                '}';
    }
}
