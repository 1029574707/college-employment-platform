package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/4 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public enum EPracticeType {

    PRACTICE_TYPE_PROFESSION(1, "专业实习"),
    PRACTICE_TYPE_JOB(2, "毕业实习");

    private int typeCode;
    private String desc;

    EPracticeType(int typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }

    public static String getDescByCode(int typeCode) {
        EPracticeType[] values = EPracticeType.values();
        for (EPracticeType value : values) {
            if (value.typeCode == typeCode) {
                return value.desc;
            }
        }
        return "";
    }
}
