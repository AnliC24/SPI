package com.hitqz.sjtc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.PageResultEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.benchmark.BenchmarkInfoDto;
import com.hitqz.sjtc.dto.benchmark.BenchmarkPageCondition;
import com.hitqz.sjtc.service.BenchmarkInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "作业聚合")
@RestController
@RequestMapping("/matrix/manage")
public class BenchmarkInfoController extends BaseController implements PageTemplate{

    private static final Logger log = LoggerFactory.getLogger(BenchmarkInfoController.class);

    private final BenchmarkInfoService benchmarkInfoService;

    @Autowired
    public BenchmarkInfoController(BenchmarkInfoService benchmarkInfoService) {
        this.benchmarkInfoService = benchmarkInfoService;
    }

    @ApiOperation("聚合结果分页信息表")
    @GetMapping("/getBenchmarkInfo")
    public ResultObj getBenchmarkInfo(BenchmarkPageCondition param){
        return pageTemplate(param);
    }

    @Override
    public ResultObj executePage(PageCondition param) {
        if(!(param instanceof BenchmarkPageCondition)){
            log.error("聚合结果分页对象转换失败,{}  change to {}",param.getClass(),BenchmarkPageCondition.class);
            return ResultObj.fail("分页失败,请检查");
        }

        BenchmarkPageCondition pageCondition = MyBeanUtils.getBeanByCopyProperties(param,new BenchmarkPageCondition());

        Page<BenchmarkInfoDto> pageData = benchmarkInfoService.selectBenchmarkPage(new Page<>(param.getCurrentPage(),
                param.getPageSize()),pageCondition);

        PageResultEntity<BenchmarkInfoDto> resData = this.convertEntityPageList(pageData);

        return ResultObj.success("查询成功").withData(resData);
    }
}
