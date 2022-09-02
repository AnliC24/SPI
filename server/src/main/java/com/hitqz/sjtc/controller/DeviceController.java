package com.hitqz.sjtc.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.device.starter.DeviceNotRegisterException;
import com.hitqz.device.starter.DeviceService;
import com.hitqz.device.starter.dto.SendCommandsDto;
import com.hitqz.sjtc.annotation.OptRecord;
import com.hitqz.sjtc.core.dict.DeviceDataDict;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.*;
import com.hitqz.sjtc.core.sdk.BaseDevice;
import com.hitqz.sjtc.core.sdk.VariousDeviceDto;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.device.*;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.service.BaseDeviceInfoService;
import com.hitqz.sjtc.service.DeviceGroupMapService;
import com.hitqz.sjtc.task.MatrixTask;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author windc
 * */
@Api(tags = "设备管理")
@RestController
@RequestMapping("/device/manage")
public class DeviceController extends BaseController implements PageTemplate {

    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

    private static final String batchNumberUnUse = "00000000000";

    private final DeviceService deviceService;

    private final BaseDeviceInfoService baseDeviceInfoService;

    private final DeviceGroupMapService deviceGroupMapService;

    @Resource
    private MatrixTask matrixTask;

    @Resource
    private ApplicationContext ctx;

    @Autowired
    public DeviceController(BaseDeviceInfoService baseDeviceInfoService,
                            DeviceGroupMapService deviceGroupMapService,
                            DeviceService deviceService) {
        this.baseDeviceInfoService = baseDeviceInfoService;
        this.deviceGroupMapService = deviceGroupMapService;
        this.deviceService = deviceService;
    }

    @ApiOperation("设备基础信息分页接口")
    @GetMapping("/getDevicePage")
    public ResultObj getDevicePage(DevicePageCondition param){
        return pageTemplate(param);
    }


    @Override
    public ResultObj executePage(PageCondition param) {

        if(!(param instanceof DevicePageCondition)){
            log.error("设备基础信息管理分页对象转换失败,{}  change to {}",param.getClass(),DevicePageCondition.class);
            return ResultObj.fail("分页失败,请检查");
        }

        DevicePageCondition devicePageCondition = MyBeanUtils.getBeanByCopyProperties(param,new DevicePageCondition());

        Page<BaseDeviceInfoDto> pageData = baseDeviceInfoService.selectDevicePage(new Page<>(param.getCurrentPage(),
                param.getPageSize()),devicePageCondition);

        PageResultEntity<BaseDeviceInfoDto> resData = this.convertEntityPageList(pageData);

        return ResultObj.success("查询成功").withData(resData);

    }

    @PutMapping("/bindDeviceGroup")
    @ApiOperation("绑定设备组")
    public ResultObj bindDeviceGroup(@RequestBody BindDeviceGroupDto bindDeviceGroupDto){
        String deviceId = bindDeviceGroupDto.getDeviceId();
        String deviceGroupId = bindDeviceGroupDto.getDeviceGroupId();
        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("设备编号不能为空");
        }
        if(!StringUtils.hasLength(deviceGroupId)){
            return ResultObj.fail("设备组编号不允许为空");
        }
        List<String> deviceIds = new ArrayList<>();
        deviceIds.add(deviceId);
        int row = deviceGroupMapService.batchBindDeviceGroup(deviceIds,deviceGroupId);
        if(row == 0){
            return ResultObj.fail("绑定失败");
        }
        return ResultObj.success("绑定成功");
    }

    /**
     * 解除设备组绑定
     * */
    @DeleteMapping("/unBindDeviceGroup")
    @ApiOperation("解除设备组绑定")
    public ResultObj unBindDeviceGroup(String deviceId){
        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("不允许设备编号为空");
        }
        int row  = deviceGroupMapService.unBindDeviceGroup(deviceId);
        if(row == 0){
            return ResultObj.fail("无设备绑定");
        }
        return ResultObj.success("解绑设备成功");
    }

    /**
     * 设备故障由人工判定和设置
     * */
    @ApiOperation("变更设备状态")
    @PutMapping("/updateDeviceLoginStatus")
    public ResultObj updateDeviceLoginStatus(@RequestBody UpdateDeviceStatusDto updateDeviceStatusDto){
        String deviceId = updateDeviceStatusDto.getDeviceId();
        String loginStatus = updateDeviceStatusDto.getLoginStatus();

        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("不允许设备编号为空");
        }

        if(!StringUtils.hasLength(loginStatus)){
            return ResultObj.fail("不允许登录状态为空");
        }

        int row = baseDeviceInfoService.updateDeviceLoginStatus(deviceId,loginStatus,new Date());
        if(row == 0){
            return ResultObj.fail("无效变更");
        }
        return ResultObj.success("变更成功");
    }

    @ApiOperation("获取设备信息详情")
    @GetMapping(value = "/getDeviceDetail")
    public ResultObj getDeviceDetail(@ApiParam(name = "deviceId",value = "设备编号") String deviceId,
                                     @ApiParam(name = "deviceType",value = "设备类型 01 02 03 04") String deviceType) {
        try{
            BaseDevice baseDevice = deviceService.getDeviceDetail(deviceId,deviceType);
            return ResultObj.success("查询成功").withData(baseDevice);
        }catch (DeviceNotRegisterException e){
            return ResultObj.fail(e.getMessage());
        }
    }

    @ApiOperation("设备信息编辑")
    @PutMapping("/editDeviceInfo")
    public ResultObj editDeviceInfo(@RequestBody BaseDeviceInfoDto deviceInfoDto){
        if(!Optional.ofNullable(deviceInfoDto).isPresent()){
            return ResultObj.fail("设备基础信息为空,无法编辑,请检查");
        }

        String deviceId = deviceInfoDto.getDeviceId();
        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("设备编号不允许为空");
        }

        String deviceType = deviceInfoDto.getDeviceType();
        if(!StringUtils.hasLength(deviceType)){
            return ResultObj.fail("设备类型不允许为空");
        }

        BaseDevice deviceInfo = MyBeanUtils.getBeanByCopyProperties(deviceInfoDto,new BaseDevice());
        deviceInfo.setModifyTime(new Date());
        BaseDeviceInfo baseDeviceInfo = MyBeanUtils.getBeanByCopyProperties(deviceInfoDto,new BaseDeviceInfo());
        baseDeviceInfo.setModifyTime(new Date());
        try {
            deviceService.editDevice(deviceType,deviceInfoDto);
        }catch (Exception e){
            return ResultObj.fail("编辑失败");
        }
        return ResultObj.success("编辑成功");
    }

    @OptRecord
    @ApiOperation("注销设备信息--物理删除")
    @DeleteMapping("/deleteDevice")
    public ResultObj deleteDevice(@RequestBody DeviceDto deviceDto){

        String deviceType = deviceDto.getDeviceType();
        String deviceId = deviceDto.getDeviceId();

        if(!StringUtils.hasLength(deviceType)){
            return ResultObj.fail("设备类型不允许为空");
        }

        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("设备编号不允许为空");
        }
        //若存在设备组映射关系,则不能删除,先去解绑
        List<String> isBind = deviceGroupMapService.isBindDeviceGroup(deviceId);
        if(isBind.size() > 0) {
            return ResultObj.fail("该设备存在设备组绑定,请先去解绑");
        }

        int res = baseDeviceInfoService.deleteDeviceInfo(deviceId,deviceType);

        if(res == 0){
            return ResultObj.fail("无效删除,请检查");
        }

        return ResultObj.success("注销成功");
    }

    /**
     * 某类型设备需要主动注册设备信息
     * 通过此接口注册的设备,设备编号全部为uuid
     * 例如海康设备
     * 设备注册
     * 1.注册设备基础信息表
     * 2.注册设备详情信息表
     * */
    @ApiOperation("注册设备")
    @PostMapping("/addDeviceInfo")
    public ResultObj addDeviceInfo(@RequestBody VariousDeviceDto variousDeviceDto){

        String deviceName = variousDeviceDto.getDeviceName();
        String deviceType = variousDeviceDto.getDeviceType();

        if(!StringUtils.hasLength(deviceType)){
            return ResultObj.fail("不允许设备类型为空,请配置");
        }
        if(!StringUtils.hasLength(deviceName)){
            return ResultObj.fail("不允许设备名称为空,请配置");
        }
        variousDeviceDto.setDeviceId(IdUtil.simpleUUID());
        variousDeviceDto.setCreateTime(new Date());
        variousDeviceDto.setModifyTime(new Date());
        variousDeviceDto.setLoginStatus(DeviceDataDict.deviceLoginStatusByUnderline.getDictCode());
        baseDeviceInfoService.registerDeviceInfo(variousDeviceDto);
        return ResultObj.success("注册设备成功");
    }

    /**
     * 调用设备，下发指令功能
     * 可批量发送指令
     * */
    @ApiOperation("批量发送指令")
    @PostMapping("/sendCommands")
    public ResultObj sendCommands(@RequestBody BatchSendCommands batchSendCommands){
        String deviceId = batchSendCommands.getDeviceId();
        String deviceType = batchSendCommands.getDeviceType();
        List<SysDataDict> commands = batchSendCommands.getCommands();

        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("不允许设备编号为空");
        }

        if(!StringUtils.hasLength(deviceType)){
            return ResultObj.fail("不允许设备类型为空");
        }

        if(commands.size() == 0){
            return ResultObj.fail("未配置指令");
        }

        SendCommandsDto sendCommandsDto = new SendCommandsDto();
        sendCommandsDto.setCommands(commands);
        //随便配置一个作业批次号,单步测试设备无要求
        sendCommandsDto.setBatchNumber(batchNumberUnUse);

        return deviceService.sendCommands(deviceType,deviceId,sendCommandsDto);
    }

    /**
     * 测试光电触发
     * */
    @ApiOperation("测试光电触发")
    @GetMapping("/gdTrigger")
    public ResultObj gdTrigger(){
        BaseGdTrigger baseGdTrigger = new BaseGdTrigger();
        //左边
        String left = DeviceDataDict.groupAddressByLeft.getDictCode();
        baseGdTrigger.setFlag(left);
        ctx.publishEvent(baseGdTrigger);
        return ResultObj.success("测试成功");
    }
	
	 /**
     * 服务主动对设备进行ping功能
     * */
    @ApiOperation("获取设备当前网络状态")
    @PutMapping("/testDeviceNetConnect")
    public ResultObj testDeviceNetConnect(@RequestBody BatchSendCommands batchSendCommands){

        String deviceType = batchSendCommands.getDeviceType();
        String deviceId = batchSendCommands.getDeviceId();

        if(!StringUtils.hasLength(deviceType)){
            return ResultObj.fail("不允许设备类型为空");
        }

        if(!StringUtils.hasLength(deviceId)){
            return ResultObj.fail("不允许设备编号为空");
        }

        //默认为ping指令
        SysDataDict ping = DeviceDataDict.pingOpt;
        List<SysDataDict> commands = new ArrayList<>();
        commands.add(ping);
        SendCommandsDto sendCommandsDto = new SendCommandsDto();
        sendCommandsDto.setCommands(commands);
        ResultObj res = deviceService.sendCommands(deviceType,deviceId,sendCommandsDto);

        PingEntity pingEntity = MyBeanUtils.getBeanByCopyProperties(res.getData(),new PingEntity());
        String delay;
        //如果ping通了,则更新设备表
        if(pingEntity.isState()){
            delay = pingEntity.getDelay();
        }else{
            delay = "∞ms";
        }
        int row = baseDeviceInfoService.updateDeviceNetWork(delay,deviceId);
        return res;
    }

    /**
     * 测试矩阵算法
     * */
    @ApiOperation("测试矩阵算法")
    @GetMapping("/testMatrixTask")
    public ResultObj testMatrixTask(){
        matrixTask.buildMatrix();
        return ResultObj.success("测试成功");
    }

}
