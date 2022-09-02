package com.hitqz.dict.starter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.PageCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDataDictMapper extends BaseMapper<SysDataDict> {

    Page selectDictPage(@Param("page") Page page, PageCondition param);

    int addSysDataDict(@Param("param") SysDataDict sysDataDict);

    int updateSysDataDict(@Param("param")SysDataDict sysDataDict);

    SysDataDict getDataDictById(int id);

    int deleteDataDict(int id);

}
