package com.hitqz.device.hknative;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.hitqz.device.HkCameraProperties;
import com.hitqz.device.annotation.OptLogin;
import com.hitqz.device.config.HkCameraConfig;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.nio.ByteBuffer;


@Service
public class HkServiceImpl implements HkService {

    private static final Logger log = LoggerFactory.getLogger(HkServiceImpl.class);

    @Resource
    private HkCameraProperties hkCameraProperties;

    @OptLogin
    @Override
    public void LivePreviewHk(String deviceId, int lUserID) {
        HCNetSDK hCNetSDK = HkCameraConfig.INSTANCE;

        //设置连接时间与重连时间
        hCNetSDK.NET_DVR_SetConnectTime(2000, 1);
        hCNetSDK.NET_DVR_SetReconnect(10000, true);

//        hCNetSDK.NET_DVR_SetExceptionCallBack_V30(0, NULL,g_ExceptionCallBack, NULL);
    }

    /**
     * NET_DVR_CaptureJPEGPicture_NEW  lChannel  1L 代表正常相机
     * 2l 代表红外相机
     * <p>
     * 单成像拍摄功能
     */
    @OptLogin
    @Override
    public String captureJPEGPicture(String deviceId, int lUserID) {
        String photoPath = hkCameraProperties.getSavePhotoPath();
        HCNetSDK hCNetSDK = HkCameraConfig.INSTANCE;

        //设置结构体
      //     HCNetSDK.NET_DVR_JPEGPARA jpegPara = new HCNetSDK.NET_DVR_JPEGPARA(); //---------------------------先存内存再打图---------
        HCNetSDK.LPNET_DVR_JPEGPARA jpegPara = new HCNetSDK.LPNET_DVR_JPEGPARA(); //---------------------------直接打图--------------
        jpegPara.wPicSize = 3;
        jpegPara.wPicQuality = 3;
        StringBuilder savePicturePath = new StringBuilder().append(DateUtil.current(true)).append(".jpeg");

        ByteBuffer jpegBuffer = ByteBuffer.allocate(8 * 1024 * 1024);
        IntByReference a = new IntByReference();

        log.info("调用海康设备 NET_DVR_CaptureJPEGPicture_NEW 接口,开始进行拍照");

//        //---------------------------先存内存再打图---------
//            boolean res = hCNetSDK.NET_DVR_CaptureJPEGPicture_NEW(
//                    new NativeLong(lUserID),
//                    new NativeLong(1L),
//                    jpegPara,
//                    jpegBuffer,
//                    8 * 1024 * 1024,
//                    a);
//
//        if (!res) {
//            log.error("pyd1---NET_DVR_CaptureJPEGPicture_NEW error, {}", hCNetSDK.NET_DVR_GetLastError());
//            return false;
//        }
//
//        log.info("调用海康设备 NET_DVR_CaptureJPEGPicture_NEW 接口 拍照成功");
//
//            BufferedOutputStream outputStream = null;
//            try {
//                outputStream = new BufferedOutputStream(new FileOutputStream(photoPath  +savePicturePath));
//                outputStream.write(jpegBuffer.array(), 0, a.getValue());
//                outputStream.flush();
//            } catch (Exception e) {
//                log.error("保存相机图片异常：" + e.getMessage());
//            } finally {
//                if (outputStream != null) {
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        log.error("关闭相机流数据异常：" + e.getMessage());
//                    }
//                }
//            }
//            //------------------------------------------------------------


        //--------------------------------------------直接打图---------------------------------------------

        String filePath = photoPath + savePicturePath;

        boolean res = hCNetSDK.NET_DVR_CaptureJPEGPicture(
                new NativeLong(lUserID),
                new NativeLong(1L),
                jpegPara,
                filePath
        );

        if (!res) {
            log.error("pyd1---NET_DVR_CaptureJPEGPicture error, {}", hCNetSDK.NET_DVR_GetLastError());
            return null;
        }

        log.info("调用海康设备 NET_DVR_CaptureJPEGPicture_NEW 接口 拍照成功");

        return filePath;

    }

    /**
     * 多拍,抓取流进行拍摄
     */
    @OptLogin
    @Override
    public boolean captureJPEGPictureMany(String deviceId, int lUserID) {
        String photoPath = hkCameraProperties.getSavePhotoPath();
        HCNetSDK hCNetSDK = HkCameraConfig.INSTANCE;
        StringBuilder savePicturePath = new StringBuilder().append(DateTime.now().toString("yyyy-MM-dd-ss"));

        log.info("调用海康设备 NET_DVR_RealPlay_V40");
        HCNetSDK.NET_DVR_PREVIEWINFO m_strClientInfo = new HCNetSDK.NET_DVR_PREVIEWINFO();
        m_strClientInfo.lChannel = new NativeLong(1);
        m_strClientInfo.dwStreamType = 0;   //码流类型：0-主码流，1-子码流，2-三码流，3-虚拟码流，以此类推
        m_strClientInfo.dwLinkMode = 0;
        m_strClientInfo.hPlayWnd = null;
        m_strClientInfo.bBlocked = true;
        m_strClientInfo.byPreviewMode = 0;
        m_strClientInfo.bPassbackRecord = false;
        m_strClientInfo.byProtoType = 1;
        m_strClientInfo.byVideoCodingType = 0;
        m_strClientInfo.byNPQMode = 0;
        m_strClientInfo.write();

        NativeLong realPlayId = hCNetSDK.NET_DVR_RealPlay_V40(
                new NativeLong(lUserID),
                m_strClientInfo,
                null,
                null
        );
        if (realPlayId.intValue() >= 0) {
            log.info("【海康高清摄像头播放成功】 playId:" + realPlayId.intValue());
        } else {
            log.error("【海康高清摄像头播放失败】 playId:" + hCNetSDK.NET_DVR_GetLastError());
        }

        if(hCNetSDK.NET_DVR_SetCapturePictureMode((byte) 1)){
            log.info("设置jpeg成功");
        }else{
            log.error("设置jpeg失败");
        }


//      // ---------------------CapturePictureBlock--------------------------------
        //block抓图必须先截取一段流,所以此处停5s
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<1000){
            if (hCNetSDK.NET_DVR_CapturePictureBlock(realPlayId,photoPath+savePicturePath+System.currentTimeMillis()+".jpeg", (short) 1)) {
                log.info("打图成功");
            } else {
                log.error("打图失败"+hCNetSDK.NET_DVR_GetLastError());
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//      // -------------------------------------------------------------------------

//      //  ---------------------CapturePicture-------------------------------------
//        long start = System.currentTimeMillis();
//        while(System.currentTimeMillis()-start<1000) {
//            boolean res = hCNetSDK.NET_DVR_CapturePicture(
//                    realPlayId,
//                    photoPath + savePicturePath + +System.currentTimeMillis()+".jpeg"
//            );
//            if (!res) {
//                log.error("pyd1---NET_DVR_CapturePicture error, {}", hCNetSDK.NET_DVR_GetLastError());
//                return false;
//            } else {
//                log.info("-----------------成功bmp-------");
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//      //  --------------------------------------------------------------------------------

        hCNetSDK.NET_DVR_StopRealPlay(realPlayId);

        return true;
    }

}
