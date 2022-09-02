package com.hitqz.device.config;

import com.hitqz.device.HkCameraProperties;
import com.hitqz.device.hknative.HCNetSDK;
import com.sun.jna.Native;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HkCameraConfig {

    private static final Logger log = LoggerFactory.getLogger(HkCameraConfig.class);

    public static HCNetSDK INSTANCE = null;

    @Autowired
    public HkCameraConfig(HkCameraProperties hkCameraProperties) {
        String hckNetSdkPath = hkCameraProperties.getHcNetSdkPath();
        INSTANCE  = (HCNetSDK) Native.loadLibrary(hckNetSdkPath, HCNetSDK.class);
    }

    public int Login_V40(String m_sDeviceIP, short wPort, String m_sUsername, String m_sPassword) {

        int lUserID;

        HCNetSDK hCNetSDK = HkCameraConfig.INSTANCE;

        /* 注册 */
        // 设备登录信息
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();

        // 设备信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];

        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());
        m_strLoginInfo.wPort =wPort ;
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());
        // 是否异步登录：false- 否，true- 是
        m_strLoginInfo.bUseAsynLogin = false;
        // write()调用后数据才写入到内存中
        m_strLoginInfo.write();

        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID == -1) {
            log.warn("登录失败，错误码为 {}", hCNetSDK.NET_DVR_GetLastError());
            hCNetSDK.NET_DVR_Cleanup();
            return -1;
        } else {
            log.warn("登录成功！");
            // read()后，结构体中才有对应的数据
            m_strDeviceInfo.read();
            return lUserID;
        }
    }
    //设备注销 SDK释放
    public void Logout(int lUserID) {

        HCNetSDK hCNetSDK = HkCameraConfig.INSTANCE;

        if(lUserID >= 0)
        {
            if (!hCNetSDK.NET_DVR_Logout(lUserID)) {
                log.warn("注销失败，错误码为 {}",hCNetSDK.NET_DVR_GetLastError());
            }
            log.warn("注销成功");
        }
        else{
            log.warn("设备未登录");
        }
        hCNetSDK.NET_DVR_Cleanup();
    }


}
