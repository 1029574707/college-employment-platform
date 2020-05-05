package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/5 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public enum EPracticeStatus {

    STATUS_NOT_BEGIN(0, "未开始"),
    STATUS_DOING(1, "实习中"),
    STATUS_END(2, "已结束");

    public int statusCode;

    public String desc;

    EPracticeStatus(int statusCode, String desc) {
        this.statusCode = statusCode;
        this.desc = desc;
    }

    public static String getDescByCode(int statusCode) {
        EPracticeStatus[] values = EPracticeStatus.values();
        for (EPracticeStatus value : values) {
            if (value.statusCode == statusCode) {
                return value.desc;
            }
        }
        return "";
    }
}
