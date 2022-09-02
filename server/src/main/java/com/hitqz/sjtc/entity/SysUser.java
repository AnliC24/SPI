package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hitqz.sjtc.constant.table.TableOrFieldNameByMysql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = TableOrFieldNameByMysql.SYS_USER_INFO_CN, description = "系统用户")
@TableName(TableOrFieldNameByMysql.SYS_USER_INFO)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 8638277884175934887L;

    @ApiModelProperty(value = TableOrFieldNameByMysql.ID_CN)
    @TableId(value = TableOrFieldNameByMysql.ID, type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(TableOrFieldNameByMysql.USER_ID_CN)
    @TableField(value = TableOrFieldNameByMysql.USER_ID, fill = FieldFill.INSERT)
    private String userId;

    @ApiModelProperty(value = TableOrFieldNameByMysql.USER_NAME_CN)
    @TableField(TableOrFieldNameByMysql.USER_NAME)
    private String userName;

    @ApiModelProperty(value = TableOrFieldNameByMysql.PASSWORD_CN)
    @TableField(TableOrFieldNameByMysql.PASSWORD)
    private String userPassword;

    @ApiModelProperty(value = TableOrFieldNameByMysql.PHONE_CN)
    @TableField(TableOrFieldNameByMysql.PHONE)
    private String userPhone;

    @ApiModelProperty(value = TableOrFieldNameByMysql.STATUS_CN)
    @TableField(TableOrFieldNameByMysql.STATUS)
    private String userStatus;

    @ApiModelProperty(value = TableOrFieldNameByMysql.EMAIL_CN)
    @TableField(TableOrFieldNameByMysql.EMAIL)
    private String userEmail;

    @ApiModelProperty(value = TableOrFieldNameByMysql.CREATE_TIME_CN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = TableOrFieldNameByMysql.CREATE_TIME, fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = TableOrFieldNameByMysql.LAST_MODIFIER_TIME_CN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = TableOrFieldNameByMysql.MODIFIER_TIME, fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    @ApiModelProperty(value = TableOrFieldNameByMysql.ROLE_TYPE_CN)
    @TableField(value = TableOrFieldNameByMysql.ROLE_TYPE, fill = FieldFill.INSERT)
    private String roleType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
