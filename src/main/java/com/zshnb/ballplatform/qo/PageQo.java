package com.zshnb.ballplatform.qo;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 分页请求参数
 **/
public class PageQo {

    private int pageSize;

    private int pageNo;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "PageQo{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
