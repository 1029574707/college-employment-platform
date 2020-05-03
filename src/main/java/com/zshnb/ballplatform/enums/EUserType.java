package com.zshnb.ballplatform.enums;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 用户类型
 **/
public enum EUserType {

    USER_TYPE_ADMIN(0, "admin"),
    USER_TYPE_TEACHER(0, "admin"),
    USER_TYPE_STUDENT(0, "admin");

    public int typeCode;
    public String desc;



    EUserType(int typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }
}
