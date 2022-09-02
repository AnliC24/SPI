package com.hitqz.sjtc.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.annotation.OptRecord;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.PageResultEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupInfoDetail;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupInfoDto;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupPageCondition;
import com.hitqz.sjtc.entity.DeviceGroupInfo;
import com.hitqz.sjtc.service.DeviceGroupInfoService;
import com.hitqz.sjtc.service.DeviceGroupMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "设备组管理")
@RestController
@RequestMapping("/device/group/manage")
public class DeviceGroupController extends BaseController implements PageTemplate {

    private static final Logger log = LoggerFactory.getLogger(DeviceGroupController.class);

    private final DeviceGroupInfoService deviceGroupInfoService;

    private final DeviceGroupMapService deviceGroupMapService;

    @Autowired
    public DeviceGroupController(DeviceGroupInfoService deviceGroupInfoService,DeviceGroupMapService deviceGroupMapService) {
        this.deviceGroupInfoService = deviceGroupInfoService;
        this.deviceGroupMapService = deviceGroupMapService;
    }

    @ApiOperation("设备组分页接口")
    @GetMapping("/getDeviceGroupPage")
    public ResultObj getDeviceGroupPage(DeviceGroupPageCondition param){
        return pageTemplate(param);
    }


    @Override
    public ResultObj executePage(PageCondition param) {
        if(!(param instanceof DeviceGroupPageCondition)){
            log.error("设备组信息管理分页对象转换失败,{}  change to {}",param.getClass(),DeviceGroupPageCondition.class);
            return ResultObj.fail("分页失败,请检查");
        }

        DeviceGroupPageCondition deviceGroupPageCondition = MyBeanUtils.getBeanByCopyProperties(param,new DeviceGroupPageCondition());

        Page<DeviceGroupInfoDetail> pageData = deviceGroupInfoService.selectDeviceGroupPage(new Page<>(param.getCurrentPage(),
                param.getPageSize()),deviceGroupPageCondition);

        PageResultEntity<DeviceGroupInfoDetail> resData = this.convertEntityPageList(pageData);

        return ResultObj.success("查询成功").withData(resData);
    }

    //设备组增删改查
    @ApiOperation("添加设备组")
    @PostMapping("/addDeviceGroup")
    public ResultObj addDeviceGroup(@RequestBody DeviceGroupInfoDto deviceGroupInfoDto){
        if(!Optional.ofNullable(deviceGroupInfoDto).isPresent()){
            return ResultObj.fail("不允许新增设备组信息为空");
        }
        String deviceGroupName = deviceGroupInfoDto.getDeviceGroupName();
        String deviceGroupDesc = deviceGroupInfoDto.getDeviceGroupDesc();
        if(!StringUtils.hasLength(deviceGroupName)){
            return ResultObj.fail("不允许设备名称为空");
        }
        if(!StringUtils.hasLength(deviceGroupDesc)){
            return ResultObj.fail("不允许设备组描述为空");
        }
        DeviceGroupInfo deviceGroupInfo = MyBeanUtils.getBeanByCopyProperties(deviceGroupInfoDto,new DeviceGroupInfo());
        deviceGroupInfo.setDeviceGroupId(IdUtil.simpleUUID());
        boolean res = deviceGroupInfoService.save(deviceGroupInfo);
        if(!res){
            return ResultObj.fail("添加设备组失败");
        }
        return ResultObj.success("添加设备组成功");
    }

    /**
     * 如果设备组绑定者设备,则不允许删除
     * */
    @OptRecord
    @ApiOperation("删除设备组")
    @DeleteMapping("/deleteDeviceGroup")
    public ResultObj deleteDeviceGroup(String deviceGroupId){
        if(!StringUtils.hasLength(deviceGroupId)){
            return ResultObj.fail("不允许设备组编号为空");
        }
        //先查询一下设备组是否绑定了设备
        List<String> deviceIds = deviceGroupMapService.isBindDevice(deviceGroupId);
        //没有再删除
        if(deviceIds.size() > 0){
            return ResultObj.fail("设备组编号:"+deviceGroupId+" 存在绑定的设备信息,请先去解除设备绑定,在进行设备组的删除");
        }
        int res = deviceGroupInfoService.deleteDeviceGroup(deviceGroupId);
        if(res == 0){
            log.warn("未发生任何删除");
        }
        return ResultObj.success("删除成功");
    }
    /**
     * 如果此设备组未存在绑定设备关系，那么可以编辑，若存在组内设备
     * 则不可以进行变动,需先删除所有设备绑定关系
     * */
    @OptRecord
    @ApiOperation("编辑设备组信息")
    @PutMapping("/editDeviceGroup")
    public ResultObj editDeviceGroup(@RequestBody DeviceGroupInfoDto deviceGroupInfoDto){
        String deviceGroupName = deviceGroupInfoDto.getDeviceGroupName();
        String deviceGroupDesc = deviceGroupInfoDto.getDeviceGroupDesc();
        String deviceGroupId = deviceGroupInfoDto.getDeviceGroupId();
        if(!StringUtils.hasLength(deviceGroupId)){
            return ResultObj.fail("不允许设备组编号为空");
        }
        if(!StringUtils.hasLength(deviceGroupName)){
            return ResultObj.fail("不允许设备组名为空");
        }
        if(!StringUtils.hasLength(deviceGroupDesc)){
            return ResultObj.fail("不允许设备组描述为空");
        }
        //先判断设备组是否已经绑定了设备
        List<String> deviceIds = deviceGroupMapService.isBindDevice(deviceGroupId);
        if(deviceIds.size() > 0){
            return ResultObj.fail("该设备组已绑定了设备,若是修改名称或描述,可能会引起业务歧义,请先去除设备绑定,在进行编辑");
        }
        DeviceGroupInfo deviceGroupInfo = MyBeanUtils.getBeanByCopyProperties(deviceGroupInfoDto,new DeviceGroupInfo());
        boolean res = deviceGroupInfoService.saveOrUpdate(deviceGroupInfo);
        if(res){
            return ResultObj.success("编辑成功");
        }
        return ResultObj.fail("编辑失败");
    }

    @ApiOperation("获取设备组所有信息")
    @GetMapping("/getDeviceGroupInfoAll")
    public ResultObj getDeviceGroupInfoAll(){
        List<DeviceGroupInfoDetail> deviceGroupInfoDetail = deviceGroupInfoService.getDeviceGroupInfoAll();
        return ResultObj.success("查询成功").withData(deviceGroupInfoDetail);
    }

}
