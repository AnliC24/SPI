package com.hitqz.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.device.annotation.OptLogin;
import com.hitqz.device.entity.HkCameraDeviceInfo;
import com.hitqz.device.hknative.HkService;
import com.hitqz.device.mapper.HkCameraDeviceInfoMapper;
import com.hitqz.device.service.HkCameraService;
import com.hitqz.sjtc.core.dict.DeviceDataDict;
import com.hitqz.sjtc.core.model.BaseWorkRecord;
import com.hitqz.sjtc.core.model.PingEntity;
import com.hitqz.sjtc.core.sdk.BaseDevice;
import com.hitqz.sjtc.core.sdk.VariousDeviceDto;
import com.hitqz.sjtc.core.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class HkCameraServiceImpl extends ServiceImpl<HkCameraDeviceInfoMapper,HkCameraDeviceInfo> implements HkCameraService {

    private static final Logger log = LoggerFactory.getLogger(HkCameraServiceImpl.class);

    @Resource
    private ApplicationContext ctx;

    @Resource
    private HkService hkService;

    @Override
    public HkCameraDeviceInfo getHkCameraDeviceInfo(String deviceId) {
        return baseMapper.getHkCameraDeviceInfo(deviceId);
    }

    @OptLogin
    @Override
    public HkCameraDeviceInfo test(String deviceId) {
        log.info("测试测试");
        return new HkCameraDeviceInfo();
    }

    @Override
    public BaseDevice getDeviceInfoByDeviceId(String deviceId){
        return baseMapper.getHkCameraDeviceInfo(deviceId);
    }

    @Override
    public int addHkCameraDeviceInfo(VariousDeviceDto variousDeviceDto) {
        return baseMapper.addHkCameraDeviceInfo(variousDeviceDto);
    }

    @Override
    public int deleteHkCameraDevice(String deviceId) {
        return baseMapper.deleteHkCameraDevice(deviceId);
    }

    public int editDeviceInfo(BaseDevice baseDevice){
        return baseMapper.editDeviceInfo(baseDevice);
    }

    @Override
    public boolean getPicture(String deviceId, int lUserId,String batchNumber) {
        //首先调用海康设备进行拍摄,如果调用成功,则存储照片记录,进原始记录表，失败，也要存一条操作记录进作业记录表
        String realRecord = hkService.captureJPEGPicture(deviceId,lUserId);
        //发布作业记录
        BaseWorkRecord res = new BaseWorkRecord();
        String resType = DeviceDataDict.cameraWorkResType.getDictCode();

        res.setResType(resType);
        res.setResValue(realRecord);
        res.setBatchOperationId(batchNumber);
        res.setDeviceId(deviceId);

        ctx.publishEvent(res);

        return StringUtils.hasLength(realRecord);
    }

    @Override
    public boolean getPicturePlay(String deviceId, int lUserId, String batchNumber) {
        return hkService.captureJPEGPictureMany(deviceId,lUserId);
    }
	
	@Override
    public PingEntity ping(String deviceId) {
        //先获取设备信息
        HkCameraDeviceInfo hkCameraDeviceInfo = baseMapper.getHkCameraDeviceInfo(deviceId);
        String ipAddress = hkCameraDeviceInfo.getIpAddress();
        if(!StringUtils.hasLength(ipAddress)){
            log.error("ipAddress 为空,请检查");
        }
        PingEntity res;
        try {
            res = NetUtil.ping2(ipAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        log.info(" {} 当前联通状态 {} ",deviceId,res.isState());
        return res;
    }
}
