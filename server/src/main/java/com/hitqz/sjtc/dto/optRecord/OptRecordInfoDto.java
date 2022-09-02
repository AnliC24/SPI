package com.hitqz.sjtc.dto.optRecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class OptRecordInfoDto implements Serializable {

    @ApiModelProperty(value = "主键编号")
    private Integer id;

    @ApiModelProperty("请求IP")
    private String optIp;

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("角色类型")
    private String roleType;

    @ApiModelProperty("角色类型值")
    private String roleTypeCn;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("请求危害级别")
    private String optHarmLevel;

    @ApiModelProperty("请求路径")
    private String optUrl;

    @ApiModelProperty("请求是否成功")
    private boolean isSuccess;

    @ApiModelProperty("请求结果")
    private String optResult;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptIp() {
        return optIp;
    }

    public void setOptIp(String optIp) {
        this.optIp = optIp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleTypeCn() {
        return roleTypeCn;
    }

    public void setRoleTypeCn(String roleTypeCn) {
        this.roleTypeCn = roleTypeCn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOptHarmLevel() {
        return optHarmLevel;
    }

    public void setOptHarmLevel(String optHarmLevel) {
        this.optHarmLevel = optHarmLevel;
    }

    public String getOptUrl() {
        return optUrl;
    }

    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getOptResult() {
        return optResult;
    }

    public void setOptResult(String optResult) {
        this.optResult = optResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
