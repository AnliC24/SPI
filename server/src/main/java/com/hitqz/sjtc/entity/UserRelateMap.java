package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user_relate_map")
public class UserRelateMap implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "super_relate_id")
    private String superRelateId;

    @TableField(value = "super_relate_name")
    private String superRelateName;

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

    public String getSuperRelateId() {
        return superRelateId;
    }

    public void setSuperRelateId(String superRelateId) {
        this.superRelateId = superRelateId;
    }

    public String getSuperRelateName() {
        return superRelateName;
    }

    public void setSuperRelateName(String superRelateName) {
        this.superRelateName = superRelateName;
    }
}
