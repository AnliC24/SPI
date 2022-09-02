package com.hitqz.sjtc.dto.dict;

import com.hitqz.sjtc.core.model.PageCondition;

import java.io.Serializable;

public class SysDictCondition extends PageCondition implements Serializable {

    private String dictType;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }
}
