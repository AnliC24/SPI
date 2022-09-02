package com.hitqz.sjtc.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hitqz.sjtc.constant.table.TableOrFieldNameByMysql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 这边最好将实体分为dto 和 pojo
 */
@ApiModel(value = TableOrFieldNameByMysql.SYS_USER_INFO_CN, description = "系统用户")
public class SysUserDto implements Serializable {

    @ApiModelProperty(value = TableOrFieldNameByMysql.ID_CN)
    private Integer id;

    @ApiModelProperty(TableOrFieldNameByMysql.USER_ID_CN)
    private String userId;


    @ApiModelProperty(value = TableOrFieldNameByMysql.USER_NAME_CN)
    private String userName;

    @ApiModelProperty(value = TableOrFieldNameByMysql.PASSWORD_CN)
    private String userPassword;

    @ApiModelProperty(value = TableOrFieldNameByMysql.PHONE_CN)
    private String userPhone;

    @ApiModelProperty(value = TableOrFieldNameByMysql.STATUS_CN)
    private String userStatus;

    @ApiModelProperty(value = TableOrFieldNameByMysql.EMAIL_CN)
    private String userEmail;

    @ApiModelProperty(value = TableOrFieldNameByMysql.CREATE_TIME_CN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = TableOrFieldNameByMysql.LAST_MODIFIER_TIME_CN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    @ApiModelProperty(value = TableOrFieldNameByMysql.ROLE_TYPE_CN)
    private String roleType;

    @ApiModelProperty(value = "所属上级编号")
    private String superiorUserId;

    @ApiModelProperty(value = "所属上级名称")
    private String superiorUserName;

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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getSuperiorUserId() {
        return superiorUserId;
    }

    public void setSuperiorUserId(String superiorUserId) {
        this.superiorUserId = superiorUserId;
    }

    public String getSuperiorUserName() {
        return superiorUserName;
    }

    public void setSuperiorUserName(String superiorUserName) {
        this.superiorUserName = superiorUserName;
    }


}
