package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 用户类型
 **/
public enum EUserType {

    USER_TYPE_ADMIN(0, "管理员"),
    USER_TYPE_TEACHER(1, "导师"),
    USER_TYPE_STUDENT(2, "学生");

    public int typeCode;
    public String desc;


    EUserType(int typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }
}
