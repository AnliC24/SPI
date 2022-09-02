package com.hitqz.sjtc.core.service.user;


import java.io.Serializable;

/**
 * @author windc
 * 基础用户模型
 * */

public class BaseUser implements Serializable {

    //主键
    private int id;

    //用户编号
    private String userId;

    //用户登录名
    private String userName;

    //用户登录密码
    private String password;

    //用户上级编号
    private String parentId;

    //用户角色
    private String roleType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
