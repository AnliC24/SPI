package com.hitqz.sjtc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.PageResultEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.dto.dict.SysDictCondition;
import com.hitqz.dict.starter.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author windc
 * */

@Api(tags = "字典系统")
@RestController
@RequestMapping("/dict/manage")
public class DictController extends BaseController implements PageTemplate{

    private final SysDictService sysDictService;

    @Autowired
    public DictController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 获取字典分页
     */
    @GetMapping("/getDictPage")
    @ApiOperation(value = "获取字典分页")
    public ResultObj getDictPage(SysDictCondition param){
        return pageTemplate(param);
    }

    /**
     * 根据dict_type 获取sysDict对象
     * */
    @ApiOperation("根据字典类型获取sysDict对象")
    @GetMapping("/getSysDictByDictType")
    public ResultObj getSysDict(String dictType){
        List<SysDataDict> sysDataDictList = sysDictService.getDictValueByDictType(dictType);
        return ResultObj.success().withData(sysDataDictList);
    }

    @ApiOperation("获取所有系统字典对象")
    @GetMapping("/getAllSysDict")
    public ResultObj getAllSysDict(){
        List<SysDataDict> allSysDict = sysDictService.getDictValueByDictType(null);
        return ResultObj.success().withData(allSysDict);
    }

    @Override
    public ResultObj executePage(PageCondition param) {
        IPage<SysDataDict> pageData = sysDictService.selectDictByPage(param);
        PageResultEntity<SysDataDict> retData = this.convertEntityPageList(pageData);
        return ResultObj.success().withData(retData);
    }
}
