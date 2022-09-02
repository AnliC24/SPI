package com.hitqz.sjtc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hitqz.dict.starter.SysDictService;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.PageResultEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.dict.SysDataDictDto;
import com.hitqz.sjtc.dto.dict.SysDictCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * 只允许超级管理员配置指令
 * 可以新增一些指令,但是需要对应的设备支持,否则不予支持
 * */
@Api(tags = "指令管理")
@RestController
@RequestMapping("/opt/command/manage")
public class OptCommandController extends BaseController implements PageTemplate{

    private static final Logger log = LoggerFactory.getLogger(OptCommandController.class);

    private final SysDictService sysDictService;

    @Autowired
    public OptCommandController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    @ApiOperation("指令分页信息表")
    @GetMapping("/getOptCommandInfo")
    public ResultObj getOptCommandInfo(SysDictCondition param){
        return pageTemplate(param);
    }

    @Override
    public ResultObj executePage(PageCondition param) {
        IPage<SysDataDict> pageData = sysDictService.selectDictByPage(param);
        PageResultEntity<SysDataDict> retData = this.convertEntityPageList(pageData);
        return ResultObj.success().withData(retData);
    }

    /**
     * 新增指令 为客户级指令 需要设备支持,若设备不支持，此指令也无效果
     *
     * 新增指令时,需要判断是否存在相同编码的指令  --麻烦，暂时不做聚合了,直接裸增
     * */
    @ApiOperation("新增设备操作指令")
    @PostMapping("/addOptCommand")
    public ResultObj addOptCommand(@RequestBody SysDataDictDto sysDataDict){

        ResultObj validateRes = validateSysDataDictDto(sysDataDict);

        if(validateRes.getCode() != 200){
            return validateRes;
        }

        SysDataDict command = MyBeanUtils.getBeanByCopyProperties(sysDataDict,new SysDataDict());
        command.setDictLevel("CUST");

        int row = sysDictService.addSysDataDict(command);
        if(row > 0 ){
            return  ResultObj.success("新增成功");
        }
        return ResultObj.fail("新增失败");
    }

    @ApiOperation("编辑指令信息")
    @PutMapping("/editOptCommand")
    public ResultObj editOptCommand(@RequestBody SysDataDictDto sysDataDictDto){
        int id = sysDataDictDto.getId();

        if(!Optional.ofNullable(id).isPresent()){
            return ResultObj.fail("编辑时,id属性不允许为空");
        }

        ResultObj validateRes = validateSysDataDictDto(sysDataDictDto);

        if(validateRes.getCode() != 200){
            return validateRes;
        }
        SysDataDict command = MyBeanUtils.getBeanByCopyProperties(sysDataDictDto,new SysDataDict());
        //根据id 更新数据
        int row = sysDictService.updateSysDataDict(command);
        if(row <= 0 ){
            return ResultObj.fail("编辑失败");
        }
        return ResultObj.success("编辑成功");
    }

    /**
     * 只允许删除 自定义指令,系统指令不允许删除
     * */
    @ApiOperation("删除指令")
    @DeleteMapping("/deleteOptCommand")
    public ResultObj deleteOptCommand(int id){
        if(!Optional.ofNullable(id).isPresent()){
            return ResultObj.fail("不允许主键编号为空");
        }
        //判断是否是自定义指令
        SysDataDict command = sysDictService.getSysDataDictById(id);
        if(!Optional.ofNullable(command).isPresent()){
            return ResultObj.fail("id:"+id+"未查询到对应的字典信息");
        }
        String dictLevel = command.getDictLevel();
        if(!dictLevel.equals("CUST")){
            return ResultObj.fail("不允许删除非自定义指令");
        }
        int row = sysDictService.deleteSysDataDict(id);
        if(row <= 0){
            return ResultObj.fail("删除失败");
        }
        return ResultObj.success("删除成功");
    }


    private ResultObj validateSysDataDictDto(SysDataDictDto sysDataDictDto){

        String dictCode = sysDataDictDto.getDictCode();
        String dictValue = sysDataDictDto.getDictValue();
        String dictType = sysDataDictDto.getDictType();
        String dictDesc = sysDataDictDto.getDictDesc();

        if(!StringUtils.hasLength(dictCode)){
            return ResultObj.fail("不允许字典编码为空");
        }

        if(!StringUtils.hasLength(dictValue)){
            return ResultObj.fail("不允许字典值为空");
        }

        if(!StringUtils.hasLength(dictType)){
            return ResultObj.fail("不允许字典类型为空");
        }

        if(!StringUtils.hasLength(dictDesc)){
            return ResultObj.fail("不允许字典描述为空");
        }
        return ResultObj.success();
    }

}
