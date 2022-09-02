package com.hitqz.sjtc.dto.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("字典交互实体")
public class SysDataDictDto implements Serializable {

    //主键
    @ApiModelProperty("新增时,不用传此属性/编辑时,此属性必传")
    private int id;
    //字典编码
    private String dictCode;
    //字典值
    private String dictValue;
    //字典类型
    private String dictType;
    //字典描述
    private String dictDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
