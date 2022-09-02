package com.hitqz.sjtc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.core.dict.UserDataDict;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.PageResultEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.optRecord.OptRecordInfoDto;
import com.hitqz.sjtc.dto.optRecord.OptRecordPageCondition;
import com.hitqz.sjtc.entity.SysUser;
import com.hitqz.sjtc.service.OptRecordInfoService;
import com.hitqz.sjtc.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = "操作记录管理")
@RestController
@RequestMapping("/opt/record/manage")
public class OptRecordController extends BaseController implements PageTemplate {

    private static final Logger log = LoggerFactory.getLogger(OptRecordController.class);

    private OptRecordInfoService optRecordInfoService;

    @Autowired
    public OptRecordController(OptRecordInfoService optRecordInfoService) {
        this.optRecordInfoService = optRecordInfoService;
    }

    @ApiOperation("获取操作日志-分页")
    @GetMapping("/getOptRecordPage")
    public ResultObj getOptRecordPage(OptRecordPageCondition param){
        return pageTemplate(param);
    }

    @Override
    public ResultObj executePage(PageCondition param) {

        if(!(param instanceof OptRecordPageCondition)){
            log.error("操作记录管理分页对象转换失败,{}  change to {}",param.getClass(), OptRecordPageCondition.class);
            return ResultObj.fail("分页失败,请检查");
        }

        OptRecordPageCondition pageCondition = MyBeanUtils.getBeanByCopyProperties(param,new OptRecordPageCondition());

        Page<OptRecordInfoDto> pageData = optRecordInfoService.selectOptRecordPage(new Page<>(param.getCurrentPage(),
                param.getPageSize()),pageCondition);

        PageResultEntity<OptRecordInfoDto> resData = this.convertEntityPageList(pageData);

        return ResultObj.success("查询成功").withData(resData);
    }

    /**
     * 2022-07-28 只允许超级管理员删除操作日志记录
     * */
    @ApiOperation("批量删除操作日志")
    @DeleteMapping("/batchDeleteOptRecord")
    public ResultObj batchDeleteOptRecord(String ids){
        if(!StringUtils.hasLength(ids)){
            return ResultObj.fail("不允许ids 为空,请检查");
        }
        String[] idStr = ids.split(",");
        SysUser user = ShiroUtils.getUserEntity();
        String roleType = user.getRoleType();
        if(!roleType.equals(UserDataDict.superAdmin.getDictCode())){
            return ResultObj.fail("只允许超级管理员使用该功能");
        }
        List<Integer> list = new ArrayList<>();
        for(String id : idStr){
            Integer idI = Integer.valueOf(id);
            list.add(idI);
        }
        int row = optRecordInfoService.batchDeleteOptRecord(list);
        if(row == 0){
            return ResultObj.fail("无数据可删除,查看是否为脏数据");
        }
        return ResultObj.success("删除成功");
    }

    @ApiOperation("删除操作日志")
    @DeleteMapping("/deleteOptRecord")
    public ResultObj deleteOptRecord(Integer id){
        if(!Optional.ofNullable(id).isPresent()){
            return ResultObj.fail("不允许ids 为空,请检查");
        }
        SysUser user = ShiroUtils.getUserEntity();
        String roleType = user.getRoleType();
        if(!roleType.equals(UserDataDict.superAdmin.getDictCode())){
            return ResultObj.fail("只允许超级管理员使用该功能");
        }
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        int row = optRecordInfoService.batchDeleteOptRecord(ids);
        if(row == 0){
            return ResultObj.fail("无数据可删除,查看是否为脏数据");
        }
        return ResultObj.success("删除成功");
    }
}
