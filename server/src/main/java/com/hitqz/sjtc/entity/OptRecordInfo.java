package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@TableName("opt_record_info")
public class OptRecordInfo implements Serializable {

    @ApiModelProperty(value = "主键编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("请求IP")
    @TableField("opt_ip")
    private String optIp;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("角色类型")
    @TableField("role_type")
    private String roleType;

    @ApiModelProperty("用户名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("请求危害级别")
    @TableField("opt_harm_level")
    private String optHarmLevel;

    @ApiModelProperty("请求路径")
    @TableField("opt_url")
    private String optUrl;

    @ApiModelProperty("请求是否成功")
    @TableField("is_success")
    private boolean isSuccess;

    @ApiModelProperty("请求结果")
    @TableField("opt_result")
    private String optResult;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
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
