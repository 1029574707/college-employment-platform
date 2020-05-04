package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/4 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public enum EEvaluationType {

    EVALUATION_TYPE_PLAN(1, "实习计划评价"),
    EVALUATION_TYPE_DIARY(2, "实习日记评价"),
    EVALUATION_TYPE_REPORT(3, "实习报告评价");

    private int typeCode;

    private String desc;

    EEvaluationType(int typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }
}
