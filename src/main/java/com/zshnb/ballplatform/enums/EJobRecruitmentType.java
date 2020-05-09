package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/9 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public enum EJobRecruitmentType {

    RECRUITMENT_TYPE_PRACTICE(1, "实习招聘"),
    RECRUITMENT_TYPE_JOB(2, "就业招聘");

    public int typeCode;

    public String desc;

    EJobRecruitmentType(int typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }

    public static String getDescByCode(int typeCode) {
        EJobRecruitmentType[] values = EJobRecruitmentType.values();
        for (EJobRecruitmentType value : values) {
            if (value.typeCode == typeCode) {
                return value.desc;
            }
        }
        return "";
    }
}
