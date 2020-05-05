package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/5 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public enum EPlanType {

    PLAN_TYPE_WEEK(1, "周计划"),
    PLAN_TYPE_TOTAL(2, "总计划");

    public int typeCode;

    public String desc;

    EPlanType(int typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }

    public static String getDescByCode(int typeCode) {
        EPlanType[] values = EPlanType.values();
        for (EPlanType value : values) {
            if (value.typeCode == typeCode) {
                return value.desc;
            }
        }
        return "";
    }
}
