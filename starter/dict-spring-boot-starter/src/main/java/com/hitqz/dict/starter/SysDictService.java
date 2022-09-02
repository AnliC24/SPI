package com.hitqz.dict.starter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.dict.starter.entity.TableOrFieldNameByMysql;
import com.hitqz.dict.starter.mapper.SysDataDictMapper;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.PageCondition;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author windc
 * 系统字典表服务
 * */
public class SysDictService {

    @Resource
    private SysDataDictMapper sysDataDictMapper;

    @ApiOperation("通过字典类型获取字典值")
    public List<SysDataDict> getDictValueByDictType(String dictType) {
        return getDictValueByDictCodeAndDictType(null, dictType);
    }

    @ApiOperation("分页获取系统字典")
    public IPage selectDictByPage(PageCondition pageCondition){
        return sysDataDictMapper.selectDictPage(new Page<>(pageCondition.getCurrentPage(),pageCondition.getPageSize()),pageCondition);
    }

    @ApiOperation("新增字典信息")
    public int addSysDataDict(SysDataDict sysDataDict){
        return sysDataDictMapper.addSysDataDict(sysDataDict);
    }

    @ApiOperation("通过主键编号获取对应的字典信息")
    public SysDataDict getSysDataDictById(int id){
        return sysDataDictMapper.getDataDictById(id);
    }

    /**
     * 系统级字典不允许删除
     * */
    @ApiOperation("删除自定义字典")
    public int deleteSysDataDict(int id){
        return sysDataDictMapper.deleteDataDict(id);
    }

    @ApiOperation("更新字典信息")
    public int updateSysDataDict(SysDataDict sysDataDict){
        return sysDataDictMapper.updateSysDataDict(sysDataDict);
    }

    public List<SysDataDict> getDictValueByDictCodeAndDictType(String dictCode, String dictType) {
        QueryWrapper<SysDataDict> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasLength(dictCode)) {
            queryWrapper.in(TableOrFieldNameByMysql.DICT_CODE, dictCode);
        }
        if (StringUtils.hasLength(dictType)) {
            queryWrapper.in(TableOrFieldNameByMysql.DICT_TYPE, dictType);
        }
        return sysDataDictMapper.selectList(queryWrapper);
    }

}
