package com.hitqz.sjtc.dto.user;

import com.hitqz.sjtc.core.model.PageCondition;
import groovy.transform.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("系统用户--分页查询条件")
@EqualsAndHashCode(callSuper = true)
public class SysUserPageCondition extends PageCondition implements Serializable {

    @ApiModelProperty("角色类型")
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
