package com.hitqz.sjtc.core.dict;

import java.io.Serializable;

/**
 * @author windC
 */
public class SysDataDict implements Serializable {

    public SysDataDict(String dictCode, String dictValue, String dictType, String desc) {
        this.dictCode = dictCode;
        this.dictValue = dictValue;
        this.dictType = dictType;
        this.dictDesc = desc;
    }

    public SysDataDict() {
    }

    //主键编号
    private Integer id;
    //字典编码
    private String dictCode;
    //字典值
    private String dictValue;
    //字典类型
    private String dictType;
    //字典描述
    private String dictDesc;
    //上级字典编号
    private String dictPid;
    //字典级别
    private String dictLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public String getDictPid() {
        return dictPid;
    }

    public void setDictPid(String dictPid) {
        this.dictPid = dictPid;
    }

    public String getDictLevel() {
        return dictLevel;
    }

    public void setDictLevel(String dictLevel) {
        this.dictLevel = dictLevel;
    }
}
