package com.hitqz.sjtc.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页登录请求对象")
public class UserLoginDto {

    @ApiModelProperty(value = "登陆用户名")
    private String userName;

    @ApiModelProperty(value = "登陆密码")
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
