package com.hitqz.sjtc.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("查询用户信息过滤条件")
public class QueryUserParam implements Serializable {

    @ApiModelProperty("用户角色")
    private String userRole;

    @ApiModelProperty("用户编号")
    private String userId;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
