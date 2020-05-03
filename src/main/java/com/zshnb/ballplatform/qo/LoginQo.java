package com.zshnb.ballplatform.qo;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 登录请求参数
 **/
public class LoginQo {

    private String id;

    private String password;

    private int userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginQo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
