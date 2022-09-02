package com.hitqz.sjtc.controller;

import com.hitqz.sjtc.core.dict.VisualDataDict;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.visual.starter.VisualService;
import com.hitqz.visual.starter.entity.VisualRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "视觉检测管理")
@RestController
@RequestMapping("/visual/manage")
public class VisualController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(VisualController.class);

    private final VisualService visualService;

    @Autowired
    public VisualController(VisualService visualService) {
        this.visualService = visualService;
    }

    /**
     * 测试调用视觉
     * */
    @ApiOperation("测试车轮编号检测")
    @GetMapping("/testDetectWheelNumber")
    public ResultObj testDetectWheelNumber(){
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("H:\\2022-08-11-56.jpeg");
        VisualRequest visualRequest = new VisualRequest(imagePaths);
        return visualService.detectWheelNumber(visualRequest);
    }
}
