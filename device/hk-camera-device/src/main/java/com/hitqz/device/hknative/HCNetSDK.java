package com.hitqz.device.hknative;

import com.sun.jna.*;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public interface HCNetSDK extends StdCallLibrary {


    /**
     * 宏定义
     **/
    //常量
    int SERIALNO_LEN = 48;   //序列号长度
    int MAX_IP_DEVICE = 32;    //允许接入的最大IP设备数
    int MAX_ANALOG_CHANNUM = 32;    //最大32个模拟通道
    int MAX_IP_CHANNEL = 32;   //允许加入的最多IP通道数
    int NAME_LEN = 32;    //用户名长度
    int PASSWD_LEN = 16;    //密码长度
    int MACADDR_LEN = 6;      //mac地址长度
    //IP接入配置参数 （NET_DVR_IPPARACFG结构）
    int NET_DVR_GET_IPPARACFG = 1048;    //获取IP接入配置信息
    int NET_DVR_SET_IPPARACFG = 1049;    //设置IP接入配置信息
    int NET_DVR_GET_REALTIME_THERMOMETRY = 3629; //实时温度检测
    int NET_DVR_GET_FOCUSMODECFG=3305; //获取快球聚焦模式信息
    int NET_DVR_SET_FOCUSMODECFG=3306; //设置快球聚焦模式信息
    int NET_DVR_GET_AEMODECFG= 3309; //获取快球其他参数信息:曝光/增益
    int NET_DVR_SET_AEMODECFG= 3310; //设置快球其他参数信息:曝光/增益
    int NET_DVR_GET_CCDPARAMCFG=1067; //获取前端参数
    int NET_DVR_SET_CCDPARAMCFG=1068; //设置前端参数
    int NET_DVR_GET_CCDPARAMCFG_EX=3368; //获取前端参数(扩展)
    int NET_DVR_SET_CCDPARAMCFG_EX=3369; //设置前端参数(扩展)
    int NET_DVR_SET_THERMOMETRY_MODE = 6766; // 设置测温模式参数
    int NET_DVR_SET_SUPPLEMENTLIGHT=3729;  // 设置内置补光灯配置 4字节(DWORD)通道号（补光灯编号）
    int NET_DVR_SET_PTZLOCKCFG=3288;  // 设置云台锁定

    int NET_DVR_GET_PTZABSOLUTEEX= 6696; // 获取高精度PTZ绝对位置配置
    int NET_DVR_SET_PTZABSOLUTEEX= 6697; // 设置高精度PTZ绝对位置配置

    int ZOOM_IN = 11;	/* 焦距以速度SS变大(倍率变大) */
    int ZOOM_OUT = 12;	/* 焦距以速度SS变小(倍率变小) */
    int FOCUS_NEAR = 13; /* 焦点以速度SS前调 */
    int FOCUS_FAR = 14; /* 焦点以速度SS后调 */
    int IRIS_OPEN = 15; /* 光圈以速度SS扩大 */
    int IRIS_CLOSE = 16; /* 光圈以速度SS缩小 */
    int TILT_UP = 21;	/* 云台以SS的速度上仰 */
    int TILT_DOWN = 22;	/* 云台以SS的速度下俯 */
    int PAN_LEFT = 23;	/* 云台以SS的速度左转 */
    int PAN_RIGHT = 24;	/* 云台以SS的速度右转 */
    int UP_LEFT = 25;	/* 云台以SS的速度上仰和左转 */
    int UP_RIGHT = 26;	/* 云台以SS的速度上仰和右转 */
    int DOWN_LEFT = 27;	/* 云台以SS的速度下俯和左转 */
    int DOWN_RIGHT = 28;	/* 云台以SS的速度下俯和右转 */
    int PAN_AUTO = 29;	/* 云台以SS的速度左右自动扫描 */
    /********************预览回调函数*********************/
    int NET_DVR_SYSHEAD = 1;//系统头数据
    int NET_DVR_STREAMDATA = 2;//视频流数据（包括复合流和音视频分开的视频流数据）
    int NET_DVR_AUDIOSTREAMDATA = 3;//音频流数据
    int NET_DVR_STD_VIDEODATA = 4;//标准视频流数据
    int NET_DVR_STD_AUDIODATA = 5;//标准音频流数据
    /*******************ISAPI透传URL**********************************/
    /**
     * 设置焦距值
     */
    String SET_FOCUS_URL="PUT /ISAPI/PTZCtrl/channels/channelValue/absolute";
    /**
     * 获取焦距报文
     */
    String GET_FOCUS_URL="GET /ISAPI/PTZCtrl/channels/channelValue/status";
    /**
     * 控制补光灯/雨刷
     */
    String SET_LIGHT_WIPER_URL="PUT /ISAPI/PTZCtrl/channels/channelValue/auxcontrols/1";
    //关闭补光灯请求XML
    String SET_LIGHT_OFF="<PTZAux><id>1</id><type>LIGHT</type><status>off</status></PTZAux>";
    //打开补光灯请求XML
    String SET_LIGHT_ON="<PTZAux><id>1</id><type>LIGHT</type><status>on</status></PTZAux>";
    //关闭雨刷请求XML
    String SET_WIPER_OFF="<PTZAux><id>1</id><type>WIPER</type><status>off</status></PTZAux>";
    //打开雨刷请求XML
    String SET_WIPER_ON="<PTZAux><id>1</id><type>WIPER</type><status>on</status></PTZAux>";

    /*********************
     * 回调函数类型 begin
     ************************/
    int COMM_ALARM = 0x1100;    //8000报警信息主动上传
    int COMM_TRADEINFO = 0x1500;  //ATMDVR主动上传交易信息
    int COMM_ALARM_V30 = 0x4000;//9000报警信息主动上传
    int COMM_ALARM_V40 = 0x4007;
    int COMM_ALARM_RULE = 0x1102;//行为分析信息上传
    int COMM_ALARM_PDC = 0x1103;//客流量统计报警上传
    int COMM_UPLOAD_PLATE_RESULT = 0x2800;//交通抓拍结果上传
    int COMM_ITS_PLATE_RESULT = 0x3050;//交通抓拍的终端图片上传
    int COMM_IPCCFG = 0x4001;//9000设备IPC接入配置改变报警信息主动上传
    int COMM_ITS_PARK_VEHICLE = 0x3056;//停车场数据上传
    int COMM_VEHICLE_CONTROL_ALARM = 0x3059;//车辆报警上传
    int COMM_ALARM_TFS = 0x1113; //交通取证报警信息
    int COMM_ALARM_TPS_V41 = 0x1114; //交通事件报警信息扩展
    int COMM_ALARM_AID_V41 = 0x1115; //交通事件报警信息扩展
    int COMM_UPLOAD_FACESNAP_RESULT = 0x1112;  //人脸识别结果上传
    int COMM_SNAP_MATCH_ALARM = 0x2902;  //人脸比对结果上传
    int COMM_ALARM_ACS = 0x5002; //门禁主机报警信息
    int COMM_ID_INFO_ALARM = 0x5200; //门禁身份证刷卡信息
    int COMM_VCA_ALARM = 0x4993; //智能检测通用报警
    int COMM_ISAPI_ALARM = 0x6009;//ISAPI协议报警信息
    int COMM_ALARM_TPS_STATISTICS = 0x3082; //TPS统计过车数据上传
    int COMM_ALARM_TPS_REAL_TIME = 0x3081; //TPS实时过车数据上传
    int COMM_ALARMHOST_CID_ALARM = 0x1127;  //报告报警上传
    int COMM_SENSOR_VALUE_UPLOAD = 0x1120;  //模拟量数据实时上传
    int COMM_SENSOR_ALARM = 0x1121;  //模拟量报警上传
    int COMM_SWITCH_ALARM = 0x1122;     //开关量报警
    int COMM_ALARMHOST_EXCEPTION = 0x1123;  //报警主机故障报警
    int COMM_ALARMHOST_OPERATEEVENT_ALARM = 0x1124;  //操作事件报警上传
    int COMM_ALARMHOST_SAFETYCABINSTATE = 0x1125;    //防护舱状态
    int COMM_ALARMHOST_ALARMOUTSTATUS = 0x1126;     //报警输出口/警号状态
    int COMM_ALARMHOST_DATA_UPLOAD = 0x1129;     //报警数据上传

    int COMM_UPLOAD_VIDEO_INTERCOM_EVENT = 0x1132;  //可视对讲事件记录上传
    int COMM_ALARM_VIDEO_INTERCOM = 0x1133;  //可视对讲报警上传
    int COMM_THERMOMETRY_ALARM = 0x5212;  //温度报警上传
    int COMM_FIREDETECTION_ALARM = 0x4991;  //火点报警上传
    int COMM_THERMOMETRY_DIFF_ALARM = 0x5111; //温差报警
    int COMM_ALARM_SHIPSDETECTION = 0x4521; //船只检测报警
    int COMM_UPLOAD_AIOP_VIDEO = 0x4021; //设备支持AI开放平台接入，上传视频检测数据
    int COMM_UPLOAD_AIOP_PICTURE = 0x4022; //设备支持AI开放平台接入，上传图片检测数据
    int COMM_UPLOAD_AIOP_POLLING_SNAP = 0x4023; //设备支持AI开放平台接入，上传轮巡抓图图片检测数据 对应的结构体(NET_AIOP_POLLING_PICTURE_HEAD)
    int COMM_UPLOAD_AIOP_POLLING_VIDEO = 0x4024; //设备支持AI开放平台接入，上传轮巡视频检测数据 对应的结构体(NET_AIOP_POLLING_VIDEO_HEAD)
    int COMM_IPC_AUXALARM_RESULT = 0x2820; //PIR报警、无线报警、呼救报警信息

    //实时测温回调函数接口
    interface FRemoteConfigCallback extends StdCallCallback {
        void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) throws UnsupportedEncodingException;
    }

    //NET_DVR_Login_V30()参数结构
    class NET_DVR_DEVICEINFO_V30 extends Structure {
        public byte[] sSerialNumber = new byte[SERIALNO_LEN];  //序列号
        public byte byAlarmInPortNum;                //报警输入个数
        public byte byAlarmOutPortNum;                //报警输出个数
        public byte byDiskNum;                    //硬盘个数
        public byte byDVRType;                    //设备类型, 1:DVR 2:ATM DVR 3:DVS ......
        public byte byChanNum;                    //模拟通道个数
        public byte byStartChan;                    //起始通道号,例如DVS-1,DVR - 1
        public byte byAudioChanNum;                //语音通道数
        public byte byIPChanNum;                    //最大数字通道个数
        public byte[] byRes1 = new byte[24];                    //保留
    }

    class NET_DVR_IPPARACFG extends Structure {/* IP接入配置结构 */
        public int dwSize;                                        /* 结构大小 */
        public NET_DVR_IPDEVINFO[] struIPDevInfo = new NET_DVR_IPDEVINFO[MAX_IP_DEVICE];    /* IP设备 */
        public byte[] byAnalogChanEnable = new byte[MAX_ANALOG_CHANNUM];        /* 模拟通道是否启用，从低到高表示1-32通道，0表示无效 1有效 */
        public NET_DVR_IPCHANINFO[] struIPChanInfo = new NET_DVR_IPCHANINFO[MAX_IP_CHANNEL];    /* IP通道 */
    }

    //IPC接入参数配置
    class NET_DVR_IPDEVINFO extends Structure {/* IP设备结构 */
        public int dwEnable;                    /* 该IP设备是否启用 */
        public byte[] sUserName = new byte[NAME_LEN];        /* 用户名 */
        public byte[] sPassword = new byte[PASSWD_LEN];        /* 密码 */
        public NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();            /* IP地址 */
        public short wDVRPort;                    /* 端口号 */
        public byte[] byres = new byte[34];                /* 保留 */
    }

   class NET_DVR_IPADDR extends Structure {
        public byte[] sIpV4 = new byte[16];
        public byte[] byRes = new byte[128];
    }

    //配置输入输出参数结构体
    class NET_DVR_IPCHANINFO extends Structure {/* IP通道匹配参数 */
        public byte lpCondBuffer;
        public byte byEnable;                    /* 该通道是否启用 */
        public byte byIPID;                    /* IP设备ID 取值1- MAX_IP_DEVICE */
        public byte byChannel;                    /* 通道号 */
        public byte[] byres = new byte[33];                    /* 保留 */
    }

    class NET_DVR_REALTIME_THERMOMETRY_COND extends Structure {
        public int dwSize;
        public int dwChan;
        public byte byRuleID;
        public byte byMode;
        public byte[] byRes = new byte[62];
    }


    enum NET_SDK_CALLBACK_TYPE {
        NET_SDK_CALLBACK_TYPE_STATUS(0),
        NET_SDK_CALLBACK_TYPE_PROGRESS(1),
        NET_SDK_CALLBACK_TYPE_DATA(2);
        private final int value;

        NET_SDK_CALLBACK_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum NET_SDK_CALLBACK_STATUS {
        NET_SDK_CALLBACK_STATUS_SUCCESS(1000),
        NET_SDK_CALLBACK_STATUS_PROCESSING(1001),
        NET_SDK_CALLBACK_STATUS_FAILED(1002);
        private final int value;

        NET_SDK_CALLBACK_STATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    //校时结构参数
    class NET_DVR_TIME extends Structure {//校时结构参数
        public int dwYear;        //年
        public int dwMonth;        //月
        public int dwDay;        //日
        public int dwHour;        //时
        public int dwMinute;        //分
        public int dwSecond;        //秒

        public String toString() {
            return "NET_DVR_TIME.dwYear: " + dwYear + "\n" + "NET_DVR_TIME.dwMonth: \n" + dwMonth + "\n" + "NET_DVR_TIME.dwDay: \n" + dwDay + "\n" + "NET_DVR_TIME.dwHour: \n" + dwHour + "\n" + "NET_DVR_TIME.dwMinute: \n" + dwMinute + "\n" + "NET_DVR_TIME.dwSecond: \n" + dwSecond;
        }

        //用于列表中显示
        public String toStringTime() {
            return String.format("%02d/%02d/%02d%02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        //存储文件名使用
        public String toStringTitle() {
            return String.format("Time%02d%02d%02d%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }

    class NET_DVR_THERMOMETRY_UPLOAD extends Structure {
        // public static class ByReference extends NET_DVR_TEMP_HUMI_INFO implements Structure.ByReference{}
        public int dwSize;
        public int dwRelativeTime;
        public int dwAbsTime;
        public byte[] szRuleName = new byte[32];
        public byte byRuleID;
        public byte byRuleCalibType;
        public short wPresetNo;
        public NET_DVR_POINT_THERM_CFG struPointThermCfg;
        public NET_DVR_LINEPOLYGON_THERM_CFG struLinePolygonThermCfg;
        public byte byThermometryUnit;
        public byte byDataType;
        public byte byRes1;
        public byte bySpecialPointThermType;
        public byte fCenterPointTemperature;
        public byte fHighestPointTemperature;
        public byte fLowestPointTemperature;
        public byte[] byRes = new byte[112];
    }

    class NET_DVR_POINT_THERM_CFG extends Structure {
        // public static class ByReference extends NET_DVR_TEMP_HUMI_INFO implements Structure.ByReference{}
        public float fTemperature;
        public NET_VCA_POINT struPoint;
        public byte[] byRes = new byte[120];
    }

    //JPEG图像信息结构体。
    class NET_DVR_JPEGPARA extends Structure {
        public short wPicSize;
        public short wPicQuality;
    }

    //wPicQuality   0-最好，1-较好，2-一般   wPicSize   0-CIF(352*288/352*240)
    class LPNET_DVR_JPEGPARA extends Structure {
        public short wPicSize;
        public short wPicQuality;
    }

    class NET_VCA_POINT extends Structure {
        // public static class ByReference extends NET_DVR_TEMP_HUMI_INFO implements Structure.ByReference{}
        public float fX;
        public float fY;
    }

    class NET_DVR_LINEPOLYGON_THERM_CFG extends Structure {
        // public static class ByReference extends NET_DVR_TEMP_HUMI_INFO implements Structure.ByReference{}
        public float fMaxTemperature;
        public float fMinTemperature;
        public float fAverageTemperature;
        public float fTemperatureDiff;
        public NET_VCA_POLYGON struRegion;
        public byte[] byRes = new byte[32];
    }

    class NET_VCA_POLYGON extends Structure {
        public int dwPointNum;
        public NET_VCA_POINT[] struPos = new NET_VCA_POINT[10];
    }

    NativeLong NET_DVR_Login_V30(String sDVRIP, short wDVRPort, String sUserName, String sPassword, NET_DVR_DEVICEINFO_V30 lpDeviceInfo);

    boolean NET_DVR_Logout_V30(NativeLong lUserID);

    boolean NET_DVR_GetDVRConfig(NativeLong lUserID, int dwCommand, NativeLong lChannel, Pointer lpOutBuffer, int dwOutBufferSize, IntByReference lpBytesReturned);

    boolean NET_DVR_Init();

    boolean NET_DVR_SetConnectTime(int dwWaitTime, int dwTryTimes);

    boolean NET_DVR_SetReconnect(int dwInterval, boolean bEnableRecon);

    //定时抓图
    boolean NET_DVR_CaptureJPEGPicture_NEW(NativeLong lUserID, NativeLong lChannel, NET_DVR_JPEGPARA lpJpegPara, ByteBuffer sJpegPicBuffer, int dwPicSize, IntByReference lpSizeReturned);

    //抓图
    boolean NET_DVR_CaptureJPEGPicture(NativeLong lUserID,NativeLong lChannel,LPNET_DVR_JPEGPARA lpJpegPara,String sPicFileName);

    //预览抓图
    boolean NET_DVR_CapturePicture(NativeLong lRealHandle,String sPicFileName);

    boolean NET_DVR_CapturePictureBlock(NativeLong iRealHandle,String sPicFileName,short dwTimeOut);

    //设置抓图模式
    boolean NET_DVR_SetCapturePictureMode(short dwCaptureMode);

    //实时测温
    NativeLong NET_DVR_StartRemoteConfig(NativeLong lUserID, int dwCommand, Pointer lpInBuffer, int dwInBufferLen, FRemoteConfigCallback cbStateCallback, Pointer pUserData);

    //获取错误信息
    int NET_DVR_GetLastError();

    boolean NET_DVR_StopRemoteConfig(NativeLong lHandle);

    //释放SDK资源
    boolean NET_DVR_Cleanup();

    boolean NET_DVR_Logout(int lUserID);

    int NET_DVR_DEV_ADDRESS_MAX_LEN = 129;
    int NET_DVR_LOGIN_USERNAME_MAX_LEN = 64;
    int NET_DVR_LOGIN_PASSWD_MAX_LEN = 64;

    //NET_DVR_Login_V40()参数
    class
    NET_DVR_USER_LOGIN_INFO extends Structure {
        public byte[] sDeviceAddress = new byte[NET_DVR_DEV_ADDRESS_MAX_LEN];
        public byte byUseTransport;
        public short wPort;
        public byte[] sUserName = new byte[NET_DVR_LOGIN_USERNAME_MAX_LEN];
        public byte[] sPassword = new byte[NET_DVR_LOGIN_PASSWD_MAX_LEN];
        public FLoginResultCallBack cbLoginResult;
        public Pointer pUser;
        public boolean bUseAsynLogin;
        public byte[] byRes2 = new byte[128];
    }

    //NET_DVR_Login_V40()参数
    class NET_DVR_DEVICEINFO_V40 extends Structure {
        public NET_DVR_DEVICEINFO_V30 struDeviceV30 = new NET_DVR_DEVICEINFO_V30();
        public byte bySupportLock;
        public byte byRetryLoginTime;
        public byte byPasswordLevel;
        public byte byRes1;
        public int dwSurplusLockTime;
        public byte[] byRes2 = new byte[256];

    }

    interface FLoginResultCallBack extends StdCallCallback {
         int invoke(int lUserID, int dwResult, NET_DVR_DEVICEINFO_V30 lpDeviceinfo, Pointer pUser);
    }

    int NET_DVR_Login_V40(NET_DVR_USER_LOGIN_INFO pLoginInfo, NET_DVR_DEVICEINFO_V40 lpDeviceInfo);

    class LPNET_DVR_STD_CONFIG_GET extends Structure {
        public Pointer lpCondBuffer;
        public int dwCondSize;
        public Pointer lpInBuffer;
        public int dwInSize;
        public Pointer lpOutBuffer;
        public int dwOutSize;
        public Pointer lpStatusBuffer;
        public int dwStatusSize;
        public Pointer lpXmlBuffer;
        public int dwXmlSize;
        public byte byDataType;
        public byte[] byRes = new byte[23];

    }

    class NET_PTZ_INFO extends Structure {
        public float fPan;
        public float fTilt;
        public float fZoom;
        public int dwFocus;// 聚焦参数，聚焦范围：归一化0-100000
        public byte[] byRes = new byte[4];
    }

    class NET_DVR_PTZABSOLUTEEX_CFG extends Structure {
        public int dwSize;//结构体大小
        public NET_PTZ_INFO struPTZCtrl = new NET_PTZ_INFO();//设备PTZF信息
        public int dwFocalLen;//焦距范围：0-100000MM
        public float fHorizontalSpeed;//水平转动速度：0.01-1000.00度/S
        public float fVerticalSpeed;//垂直转动速度：0.01-1000.00度/S
        /*镜头变倍配置类型；absoluteZoom:通过变倍参数进行配置，选择为该类型时struPTZCtrl中的fZoom参数生效。focalLen:通过焦距参数进行配置，选择为该类型时，dwFocalLen参数生效。*/
        public byte byZoomType;// 镜头变倍配置类型0~ absoluteZoom，1~ focalLen
        public byte[] byRes = new byte[123];
    }

    class LPNET_DVR_STD_CONFIG_SET extends Structure {
        public Pointer lpCondBuffer;
        public int dwCondSize;
        public Pointer lpInBuffer;
        public int dwInSize;
        public Pointer lpOutBuffer;
        public int dwOutSize;
        public Pointer lpStatusBuffer;
        public int dwStatusSize;
        public String lpXmlBuffer;
        public int dwXmlSize;
        public byte byDataType;
        public byte[] byRes = new byte[23];
    }


    class NET_DVR_THERMOMETRY_MODE extends Structure {
        public int dwSize;
        public byte byMode;
        public byte byThermometryROIEnabled;
        public byte[] byResnew = new byte[62];

    }

    class NET_SDK_MANUALTHERM_BASICPARAM extends Structure {
        public int dwSize;
        public short wDistance;
        public byte byDistanceUnit;
        public byte[] byRes1 = new byte[1];
        public float fEmissivity;
        public byte[] byRes = new byte[64];

    }

    class NET_DVR_STD_ABILITY extends Structure {
        public Pointer lpCondBuffer;
        public int dwCondSize;
        public Pointer lpOutBuffer;
        public int dwOutSize;
        public Pointer lpStatusBuffer;
        public int dwStatusSize;
        public int dwRetSize;
        public byte[] byRes = new byte[32];

    }

    class NET_DVR_THERMOMETRY_COND extends Structure {
        public int dwSize;
        public int dwChannel;
        public short wPresetNo;
        public byte[] byRes = new byte[62];
    }

    class NET_DVR_THERMOMETRY_BASICPARAM extends Structure {
        public int dwSize;
        public byte byEnabled;
        public byte byStreamOverlay;
        public byte byPictureOverlay;
        public byte byThermometryRange;
        public byte byThermometryUnit;
        public byte byThermometryCurve;
        public byte byFireImageModea;
        public byte byShowTempStripEnable;
        public float fEmissivity;
        public byte byDistanceUnit;
        public byte byEnviroHumidity;
        public byte[] byRes2 = new byte[2];
        public NET_DVR_TEMPERATURE_COLOR struTempColor = new NET_DVR_TEMPERATURE_COLOR();
        public int iEnviroTemperature;
        public int iCorrectionVolume;
        public byte bySpecialPointThermType;
        public byte byReflectiveEnabled;
        public short wDistance;
        public float fReflectiveTemperature;
        public float fAlert;
        public float fAlarm;
        public float fThermalOpticalTransmittance;
        public float fExternalOpticsWindowCorrection;
        public byte byDisplayMaxTemperatureEnabled;
        public byte byDisplayMinTemperatureEnabled;
        public byte byDisplayAverageTemperatureEnabled;
        public byte byThermometryInfoDisplayposition;
        public int dwAlertFilteringTime;
        public int dwAlarmFilteringTime;
        public byte byemissivityMode;
        public byte bydisplayTemperatureInOpticalChannelEnabled;
        public byte[] byRes = new byte[50];
    }


    class NET_DVR_TEMPERATURE_COLOR extends Structure {
        public byte byType;
        public byte[] byRes1 = new byte[3];
        public int iHighTemperature;
        public int iLowTemperature;
        public byte[] byRes = new byte[8];

    }

    class NET_DVR_THERMOMETRY_PRESETINFO extends Structure {
        public int dwSize;
        public int wPresetNo;
        public byte[] byRes = new byte[2];
        public NET_DVR_THERMOMETRY_PRESETINFO_PARAM[] struPresetInfo = new NET_DVR_THERMOMETRY_PRESETINFO_PARAM[40];
    }

    class NET_DVR_THERMOMETRY_PRESETINFO_PARAM extends Structure {
        public byte byEnabled;
        public byte byRuleID;
        public int wDistance;
        public float fEmissivity;
        public byte byDistanceUnit;
        public byte[] byRes = new byte[2];
        public byte byReflectiveEnabled;
        public float fReflectiveTemperature;
        public byte[] szRuleName = new byte[NAME_LEN];
        public byte[] byRes1 = new byte[63];
        public byte byRuleCalibType;
        public NET_VCA_POINT struPoint;
        public NET_VCA_POLYGON struRegion;


    }

    class NET_DVR_CLIENTINFO extends Structure {
        public NativeLong lChannel;
        public NativeLong lLinkMode;
//        public HWND hPlayWnd;
        public String sMultiCastIP;
    }

    class NET_DVR_PREVIEWINFO extends Structure {
        public NativeLong lChannel;
        public int    dwStreamType; //码流类型：0-主码流，1-子码流，2-三码流，3-虚拟码流，以此类推
        public int        dwLinkMode; //连接方式：0- TCP方式，1- UDP方式，2- 多播方式，3- RTP方式，4- RTP/RTSP，5- RTP/HTTP，6- HRUDP（可靠传输） ，7- RTSP/HTTPS，8- NPQ
        public Integer     hPlayWnd;
        public boolean bBlocked;
        public boolean  bPassbackRecord;
        public byte     byPreviewMode;
        public byte[]        byStreamID=new byte[216];
        public byte        byProtoType;
        public byte        byRes1;
        public byte        byVideoCodingType;
        public int        dwDisplayBufNum;
        public byte        byNPQMode;
        public byte[]       byRes=new byte[215];
    }

    interface FRealDataCallBack_V30 extends StdCallCallback {
         void invoke(NativeLong lRealHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, Pointer pUser);
    }

    interface fDrawFun extends StdCallCallback {
         void invoke(NativeLong lRealHandle, int hDc,int dwUser);
    }

    boolean NET_DVR_GetSTDConfig(NativeLong lUserID, int dwCommand, LPNET_DVR_STD_CONFIG_GET lpConfigParam);

    boolean NET_DVR_SetSTDConfig(NativeLong lUserID, int dwCommand, LPNET_DVR_STD_CONFIG_GET lpConfigParam);

    boolean NET_DVR_GetSTDAbility(NativeLong lUserID, int dwAbilityType, NET_DVR_STD_ABILITY net_dvr_std_ability);

    boolean NET_DVR_SetSTDConfig(NativeLong lUserID, int dwCommand, LPNET_DVR_STD_CONFIG_SET lpConfigParam);

    NativeLong NET_DVR_RealPlay_V30(NativeLong lUserID, NET_DVR_CLIENTINFO lpClientInfo, FRealDataCallBack_V30 fRealDataCallBack_V30, Pointer pUser, boolean bBlocked);


    NativeLong NET_DVR_RealPlay_V40(NativeLong lUserID, NET_DVR_PREVIEWINFO lpPreviewInfo, FRealDataCallBack_V30 fRealDataCallBack_V30, Pointer pUser);

    /**
     *     停止预览。
     * @param lRealHandle
     * @return
     */
    boolean NET_DVR_StopRealPlay(NativeLong lRealHandle);



    // 云台接口
    boolean NET_DVR_PTZControl(NativeLong lRealHandle, int dwPTZCommand, int dwStop);

    //快球聚焦模式信息。
    class NET_DVR_FOCUSMODE_CFG extends Structure {
        public int dwSize;          //        dwSize  结构体大小
        public byte byFocusMode;   //        byFocusMode 聚焦模式：0-自动，1-手动，2-半自动
        public byte byAutoFocusMode; //                byAutoFocusMode 自动聚焦模式：0-关，1-模式A，2-模式B，3-模式AB，4-模式C
        public short wMinFocusDistance;//                wMinFocusDistance 最小聚焦距离，单位CM：0-自动，0xffff-无穷远
        public byte byZoomSpeedLevel;//                byZoomSpeedLevel 变倍速度，为实际取值，取值范围：1~3
        public byte byFocusSpeedLevel; //        byFocusSpeedLevel 聚焦速度，为实际取值，取值范围：1~3
        public byte byOpticalZoom;//        byOpticalZoom 光学变倍，取值范围：0~255
        public byte byDigtitalZoom;//        byDigtitalZoom 数字变倍，取值范围：0~255
        public float fOpticalZoomLevel;//        fOpticalZoomLevel 光学变倍(倍率值)，取值范围：[1,32]，最小间隔0.5 ，内部设备交互的时候*1000
        public int dwFocusPos;//        dwFocusPos  focus值（聚焦值），取值范围：[0x1000,0xC000]，这个值是sony坐标值，使用这个值是为了对外统一，保证不同的镜头对外focus值都转换在这个范围内 (手动聚焦模式下下应用)
        public byte byFocusDefinitionDisplay;//        byFocusDefinitionDisplay  聚焦清晰度显示，0~不显示，1~显示, 开启会在码流上显示当前镜头目标的清晰度值，用于帮助客户调焦使相机抓拍能够达到最清晰的效果，该清晰度越大代表着越清晰，清晰度范围为：0~100.0000
        public byte byFocusSensitivity;//        byFocusSensitivity 聚焦灵敏度，范围[0,2]，聚焦模式为自动、半自动时生效
        public byte[] byRes1 = new byte[2]; //保留
        public int dwRelativeFocusPos;   //相对focus值，其低16位表示聚焦值，0~4000；高16位代表当前聚焦值获取时的温度值
        public byte[] byRes = new byte[48];  //保留
    }

    //曝光、快门等其他参数配置结构体。
    class NET_DVR_AEMODECFG extends Structure {
        public int    dwSize;               //        dwSize 结构体大小
        public int      iIrisSet;           //        iIrisSet 光圈，为实际取值*100的值，0表示关闭光圈
        public int      iGainSet;           //                iGainSet 快球增益（曝光补偿，用于手动曝光模式下调节增益），为实际取值*100的值，有可能是负值
        public int      iGainLimit;         //                iGainLimit 增益限制（曝光补偿，用于手动曝光模式下调节增益限制），为实际取值*100的值，有可能是负值
        public int      iExposureCompensate;//                iExposureCompensate 曝光补偿，为实际取值*100的值，比如1050代表10.5dB, -750代表-7.5dB
        public byte     byExposureModeSet;  //                byExposureModeSet 球机的曝光模式：0-手动模式，1-自动曝光，2-光圈优先，3-快门优先，4-增益优先
        public byte     byShutterSet;       //                byShutterSet 快门等级：0-关，1-自动x1，2-自动x2，3-自动x4，4-自动x8，5-自动x16，6-自动x32，7-自动x64，8-自动x128，9-1/1，10-1/2，11-1/3，12-1/4，13-1/6，14-1/8，15-1/12，16-1/15，17-1/25，18-1/30，19-1/50，20-1/60，21-1/75，22-1/90，23-1/100，24-1/120，25-1/125，26-1/150，27-1/180，28-1/200，29-1/215，30-1/250，31-1/300，32-1/350，33-1/425，34-1/500，35-1/600，36-1/725，37-1/1000，38-1/1250，39-1500,40-1/1750，41-1/2000，42-1/2500，43-3000,44-1/3500，45-1/4000，46-1/6000，47-1/10000，48-1/30000，49-1/100000，50-1/175，51-1/195，52-1/225，53-1/230
        public byte     byImageStabilizeLevel;//        byImageStabilizeLevel 防抖动等级，取值范围: 0~3
        public byte     byCameraIrCorrect;//        byCameraIrCorrect 红外校正: 0-关，1-开，2-自动
        public byte     byHighSensitivity;//                byHighSensitivity 高灵敏度设置: 0-关，1-开
        public byte     byInitializeLens;//                byInitializeLens  初始化镜头: 0-关，1-开
        public byte     byChromaSuppress;//                byChromaSuppress 色彩抑制，取值范围: 0~255
        public byte     byMaxShutterSet;//        byMaxShutterSet 最大快门值，索引值与byShutterSet相同(在自动曝光模式下生效)
        public byte     byMinShutterSet;//        byMinShutterSet 最小快门值，索引值与byShutterSet相同(在自动曝光模式下生效)
        public byte     byMaxIrisSet;//        byMaxIrisSet 最大光圈限制值(在自动曝光模式下生效)，取值范围：[0,100]
        public byte     byMinIrisSet;//        byMinIrisSet 最小光圈限制值(在自动曝光模式下生效)，取值范围：[0,100]
        public byte     byExposureLevel;//        byExposureLevel  曝光等级（曝光模式为自动、光圈优先、快门优先情况有效），取值范围：等级1~5，默认为4（为了兼容老版本，该字节为0时默认为曝光等级1）
        public byte[]    byRes2=new byte[60];//        byRes2 保留
    }

    //获取设备的配置信息。
    boolean NET_DVR_GetDVRConfig(NativeLong lUserID, int dwCommand, long lChannel, Pointer lpOutBuffer, int dwOutBufferSize, IntByReference lpBytesReturned);

    //设置设备的配置信息。
    boolean NET_DVR_SetDVRConfig(NativeLong lUserID, int dwCommand, long lChannel, Pointer lpInBuffer, int dwInBufferSize);

    //重启设备
    boolean NET_DVR_RebootDVR(NativeLong lUserID);
    //获取预览视频显示参数。
    //    lUserID
    //[in] NET_DVR_Login或者NET_DVR_Login_V30的返回值
    //            lChannel
    //[in] 通道号，起始通道号为1
    //            pBrightValue
    //[out] 亮度指针，取值范围[1,10]
    //    pContrastValue
    //[out] 对比度指针，取值范围[1,10]
    //    pSaturationValue
    //[out] 饱和度指针，取值范围[1,10]
    //    pHueValue
    //[out] 色度指针，取值范围[1,10]
    boolean NET_DVR_GetVideoEffect(NativeLong    lUserID, long  lChannel,
                                   Pointer pBrightValue, Pointer pContrastValue,
                                   Pointer pSaturationValue, Pointer pHueValue);
    //设置预览视频显示参数。
    //    lUserID  [in] NET_DVR_Login或者NET_DVR_Login_V30的返回值
    //    lChannel  [in] 通道号，起始通道号为1
    //    dwBrightValue  [in] 亮度，取值范围[1,10]，小于1的值默认为1，大于10的值默认为10
    //    dwContrastValue  [in] 对比度，取值范围[1,10]，小于1的值默认为1，大于10的值默认为10
    //    dwSaturationValue  [in] 饱和度，取值范围[1,10]，小于1的值默认为1，大于10的值默认为10
    //    dwHueValue  in] 色度，取值范围[1,10]，小于1的值默认为1，大于10的值默认为10
    boolean NET_DVR_SetVideoEffect( NativeLong    lUserID,  long  lChannel,
                                    int     dwBrightValue,
                                    int     dwContrastValue,
                                    int     dwSaturationValue,
                                    int     dwHueValue);


//    dwSize
//            结构体大小
//    struVideoEffect
//            视频效果参数
//    struGain
//            增益参数
//    struWhiteBalance
//            白平衡参数
//    struExposure
//            曝光参数
//    struGammaCorrect
//            Gamma校正参数
//    struWdr
//            宽动态参数
//    struDayNight
//            日夜转换功能参数
//    struBackLight
//            背光补偿参数
//    struNoiseRemove
//            数字降噪参数
//    byPowerLineFrequencyMode
//0-50HZ; 1-60HZ
//            byIrisMode
//    光圈模式：
//            0- 自动光圈
//1- 手动光圈
//2- P-Iris1
//3- Union 3-9mm F1.6-2.7 (T5280-PQ1)
//            4- Union 2.8-12mm F1.6-2.7 (T5289-PQ1)
//            5- HIK 3.8-16mm F1.5（HV3816P-8MPIR）
//            6- HIK 11-40mm F1.7 (HV1140P-8MPIR)
//            7- HIK 2.7-12mm F1.2（TV2712P-MPIR）
//            8- MZ5721D-12MPIR
//9- MZ1555D-12MPIR
//10- MZ5721D-12MPIR(RS485)
//11- MZ1555D-12MPIR(RS485)
//    当byIrisMode==2时，可以配置红外光圈大小等级，即参数struPIrisParam有效。
//    byMirror
//    镜像：0 关闭;1 左右;2 上下;3 中间
//            byDigitalZoom
//    数字缩放：0- 不启用，1- 启用。对于热成像仪，表示数字变倍倍率：0- 关闭，1-×2，2-×4，3-×8，4-×16，5-×32
//    byDeadPixelDetect
//    坏点检测是否启用，0-不启用，1-启用
//            byBlackPwl
//    黑电平补偿，0-255
//    byEptzGate
//    EPTZ开关变量：0-关闭电子云台；1-开启电子云台
//            byLocalOutputGate
//    本地输出开关变量：
//            byCoderOutputMode
//    编码器fpga输出模式：0-直通；3-像素搬家
//            byLineCoding
//    是否开启行编码：0- 否，1- 是
//            byDimmerMode
//    调光模式：0- 半自动，1- 自动，适用于热成像仪
//            byPaletteMode
//    调色板：0- 白热，1- 黑热，2- 调色板2，…，8- 调色板8，9-融合1，10-彩虹，11-融合2，12-铁红1，13-铁红2，14-深褐色，15-色彩1，16-色彩2，17-冰火，18-雨，19-红热，20-绿热，21-深蓝，适用于热成像仪
//            byEnhancedMode
//    增强方式（探测物体周边）：0- 不增强，1- 1，2- 2，3- 3，4- 4，适用于热成像仪
//            byDynamicContrastEN
//    动态对比度增强：0-不增强；1-增强
//            byDynamicContrast
//    动态对比度：0~100
//    byJPEGQuality
//    JPEG图像质量：0~100。对于V3.5或以上版本的智能交通摄像机，该参数无效，相关功能通过使能参数（NET_DVR_SNAPENABLECFG）的wJpegPicSize实现。
//    struCmosModeCfg
//    CMOS模式下前端参数配置，镜头模式从能力集获取
//            byFilterSwitch
//    滤波开关：0- 不启用，1- 启用，适用于热成像仪
//            byFocusSpeed
//    镜头调焦速度，取值范围0~10，适用于热成像仪
//            byAutoCompensationInterval
//    定时自动快门补偿，取值范围1~120，单位：分钟，适用于热成像仪
//            bySceneMode
//    场景模式：0- 室外，1- 室内，2- 默认，3- 弱光
//            struDefogCfg
//    透雾参数
//            struElectronicStabilization
//    电子防抖
//            struCorridorMode
//    旋转功能
//            byExposureSegmentEnable
//    曝光时间和增益呈阶梯状调整：0- 不启用，1- 启用，比如曝光往上调整时，先提高曝光时间到中间值，然后提高增益到中间值，再提高曝光到最大值，最后提高增益到最大值
//            byBrightCompensate
//    夜晚亮度增强，取值范围：[0,100]
//    byCaptureModeN
//    视频输入模式（N制）:
//            byCaptureModeP
//    视频输入模式（P制），取值同byCaptureModeN
//            struSmartIRParam
//    红外过爆配置信息
//            struPIrisParam
//    P-Iris红外光圈大小等级配置信息
//            struLaserParam
//    激光参数
//            struFFCParam
//    FFC参数
//            struDDEParam
//    DDE参数
//            struAGCParam
//    AGC参数
//            byLensDistortionCorrection
//    镜头畸变校正：0- 关闭，1- 开启
//            byDistortionCorrectionLevel
//    畸变校正等级：0- 保留，1- 等级一，2- 等级二，3- 等级三，255-自定义
//            byCalibrationAccurateLevel
//    畸变校正强度，取值范围：[0,100]
//    byZoomedInDistantViewLevel
//    远端放大等级（畸变校正等级关闭的时候不能配置远端放大），取值范围：[0,100]
//    struSnapCCD
//    抓拍机CCD参数，只用于智能交通摄像机(抓拍机)
//    struOpticalDehaze
//            光学透雾参数
//    struThermAGC
//            测温AGC配置
//    byFusionMode
//    双光谱视频融合模式：0- 热成像模式，1- 融合模式，2- 画中画模式
//            byHorizontalFOV
//    水平视场角，取值范围：[0,100]
//    byVerticalFOV
//    垂直视场角，取值范围：[0,100]
//    byBrightnessSuddenChangeSuppression
//    亮度突变抑制：0- 关闭，1- 开启
//            byRes2
//    保留，置为0

    //前端参数结构体
    class NET_DVR_CAMERAPARAMCFG_EX extends Structure {
        public int                              dwSize;
        public NET_DVR_VIDEOEFFECT                struVideoEffect=new NET_DVR_VIDEOEFFECT();
        public NET_DVR_GAIN                       struGain=new NET_DVR_GAIN();
        public NET_DVR_WHITEBALANCE               struWhiteBalance=new NET_DVR_WHITEBALANCE();
        public NET_DVR_EXPOSURE                   struExposure=new NET_DVR_EXPOSURE();
        public NET_DVR_GAMMACORRECT               struGammaCorrect=new NET_DVR_GAMMACORRECT();
        public NET_DVR_WDR                        struWdr=new NET_DVR_WDR();
        public NET_DVR_DAYNIGHT                   struDayNight=new NET_DVR_DAYNIGHT();
        public NET_DVR_BACKLIGHT                  struBackLight=new NET_DVR_BACKLIGHT();
        public NET_DVR_NOISEREMOVE                struNoiseRemove=new NET_DVR_NOISEREMOVE();
        public byte                               byIrisMode;
        public byte                               byMirror;
        public byte                               byDigitalZoom;
        public byte                               byDeadPixelDetect;
        public byte                               byBlackPwl;
        public byte                               byEptzGate;
        public byte                               byLocalOutputGate;
        public byte                               byCoderOutputMode;
        public byte                               byLineCoding;
        public byte                               byDimmerMode;
        public byte                               byPaletteMode;
        public byte                               byEnhancedMode;
        public byte                               byDynamicContrastEN;
        public byte                               byDynamicContrast;
        public byte                               byJPEGQuality;
        public NET_DVR_CMOSMODECFG                struCmosModeCfg=new NET_DVR_CMOSMODECFG();
        public byte                               byFilterSwitch;
        public byte                               byFocusSpeed;
        public byte                               byAutoCompensationInterval;
        public byte                               bySceneMode;
        public NET_DVR_DEFOGCFG                   struDefogCfg=new NET_DVR_DEFOGCFG();
        public NET_DVR_ELECTRONICSTABILIZATION    struElectronicStabilization=new NET_DVR_ELECTRONICSTABILIZATION();
        public NET_DVR_CORRIDOR_MODE_CCD          struCorridorMode= new NET_DVR_CORRIDOR_MODE_CCD();
        public byte                               byExposureSegmentEnable;
        public byte                               byBrightCompensate;
        public byte                               byCaptureModeN;
        public byte                               byCaptureModeP;
        public NET_DVR_SMARTIR_PARAM              struSmartIRParam=new NET_DVR_SMARTIR_PARAM();
        public NET_DVR_PIRIS_PARAM                struPIrisParam=new NET_DVR_PIRIS_PARAM();
        public NET_DVR_LASER_PARAM_CFG            struLaserParam=new NET_DVR_LASER_PARAM_CFG();
        public NET_DVR_FFC_PARAM                  struFFCParam=new NET_DVR_FFC_PARAM();
        public NET_DVR_DDE_PARAM                  struDDEParam=new NET_DVR_DDE_PARAM();
        public NET_DVR_AGC_PARAM                  struAGCParam=new NET_DVR_AGC_PARAM();
        public byte                               byLensDistortionCorrection;
        public byte                               byDistortionCorrectionLevel;
        public byte                               byCalibrationAccurateLevel;
        public byte                               byZoomedInDistantViewLevel;
        public NET_DVR_SNAP_CAMERAPARAMCFG        struSnapCCD=new NET_DVR_SNAP_CAMERAPARAMCFG();
        public NET_DVR_OPTICAL_DEHAZE             struOpticalDehaze=new NET_DVR_OPTICAL_DEHAZE();
        public NET_DVR_THERMOMETRY_AGC            struThermAGC=new NET_DVR_THERMOMETRY_AGC();
        public byte                               byFusionMode;
        public byte                               byHorizontalFOV;
        public byte                               byVerticalFOV;
        public byte                               byBrightnessSuddenChangeSuppression;
        public byte[] byRes2=new byte[156];
    }
    //视频参数结构体。
    class NET_DVR_VIDEOEFFECT extends Structure {
        public byte    byBrightnessLevel; //亮度，取值范围[0,100]byBrightnessLevel; //亮度，取值范围[0,100]
        public byte    byContrastLevel;   //对比度，取值范围[0,100]
        public byte    bySharpnessLevel;  //锐度，取值范围[0,100]
        public byte    bySaturationLevel; //饱和度，取值范围[0,100]
        public byte    byHueLevel;        //色度，取值范围[0,100]，保留
        public byte    byEnableFunc;      //使能，按位表示。bit0-SMART IR(防过曝)，bit1-低照度，bit2-强光抑制使能，值：0-否，1-是，例如byEnableFunc&0x2==1表示使能低照度功能； bit3-锐度类型，值：0-自动，1-手动。
        public byte    byLightInhibitLevel; //强光抑制等级，取值范围：[1,3]
        public byte    byGrayLevel;         //灰度值域:0-[0,255]，1-[16,235]
    }
    //增益参数结构体。
    class NET_DVR_GAIN extends Structure {
        public byte      byGainLevel;   //增益，单位dB，取值范围[0,100]
        public byte      byGainUserSet;  //用户自定义增益，单位dB，取值范围[0,100]，对于智能交通摄像机，是CCD模式下的抓拍增益
        public byte[] byRes=new byte[2];
        public int     dwMaxGainValue;
    }
    //白平衡参数结构体。
    class NET_DVR_WHITEBALANCE extends Structure {
        public byte       byWhiteBalanceMode;   //0-手动白平衡（MWB），1-自动白平衡1（AWB1，范围小），
                                                // 2-自动白平衡2（AWB2，范围宽，2200K-15000K），3- 锁定白平衡（Locked WB），4-室外，5-室内，6-日光灯，
                                                // 7-钠灯，8-自动跟踪（Auto-Track），9-一次白平衡（One Push），10-室外自动（Auto-Outdoor），
                                                // 11-钠灯自动(Auto-Sodiumlight)，12-水银灯模式(Mercury Lamp)，13-自动白平衡(Auto)，14-白炽灯 (IncandescentLamp)，
                                                // 15-暖光灯(Warm Light Lamp)，16-自然光(Natural Light)
        public byte       byWhiteBalanceModeRGain; //手动白平衡时有效，手动白平衡R增益
        public byte       byWhiteBalanceModeBGain;   //手动白平衡时有效，手动白平衡B增益
        public byte[]     byRes=new byte[5];
    }
    //CCD曝光控制参数结构体。
    class NET_DVR_EXPOSURE extends Structure {
        public byte       byExposureMode;    //0-手动曝光，1-自动曝光
        public byte       byAutoApertureLevel; //自动光圈灵敏度，取值范围：0~10
        public byte[]       byRes=new byte[2];
        public int      dwVideoExposureSet;    //自定义视频曝光时间（单位us），自动曝光时该值为曝光最慢值
        public int      dwExposureUserSet;  //自定义曝光时间。在智能交通摄像机上应用及CCD模式时，是指抓拍快门速度，（单位us）
        public int      dwRes;
    }
    //Gamma校正配置参数结构体。
    class NET_DVR_GAMMACORRECT extends Structure {
        public byte       byGammaCorrectionEnabled; //Gamma校正是否启用，0-不启用，1-启用
        public byte       byGammaCorrectionLevel;   //取值范围0~100
        public byte[]       byRes=new byte[6];
    }
    //宽动态参数结构体
    class NET_DVR_WDR extends Structure {
        public byte    byWDREnabled;  //宽动态是否启用，0-不启用，1-启用，2-自动
        public byte    byWDRLevel1;
        public byte    byWDRLevel2;
        public byte    byWDRContrastLevel;
        public byte[]    byRes=new byte[16];
    }
    //日夜转换功能参数结构体。
    class NET_DVR_DAYNIGHT extends Structure {
        public byte       byDayNightFilterType;  //日夜切换：0-白天，1-夜晚，2-自动（光敏电阻模式），3-定时，4-报警输入触发，5-自动模式2（无光敏，即通过视频亮度来判断日夜模式而不是光敏电阻）
        public byte       bySwitchScheduleEnabled;//0- 启动， 1- 禁用。（保留）
        public byte       byBeginTime;              //定时模式开始时间（小时），取值范围：0~23
        public byte       byEndTime;                //定时模式结束时间（小时），取值范围：0~23
        public byte       byDayToNightFilterLevel;  //网络摄像机取值范围：0~7，球机取值范围：1~3
        public byte       byNightToDayFilterLevel;  //网络摄像机取值范围：0~7，球机取值范围：1~3
        public byte       byDayNightFilterTime;
        public byte       byBeginTimeMin;  //定时模式开始时间（分），取值范围：0~59
        public byte       byBeginTimeSec;//定时模式开始时间（秒），取值范围：0~59
        public byte       byEndTimeMin;//定时模式结束时间（分），取值范围：0~59
        public byte       byEndTimeSec;//定时模式结束时间（秒），取值范围：0~59
        public byte       byAlarmTrigState;          //报警输入触发状态：0-白天，1-夜晚
    }
    //智能交通摄像机背光补偿参数结构体
    class NET_DVR_BACKLIGHT extends Structure {
        public byte       byBacklightMode;
        public byte       byBacklightLevel;
        public byte[]       byRes1=new byte[2];
        public int      dwPositionX1;
        public int      dwPositionY1;
        public int      dwPositionX2;
        public int      dwPositionY2;
        public byte[]       byRes2=new byte[4];
    }
    //数字降噪功能参数结构体
    class NET_DVR_NOISEREMOVE extends Structure {
        public byte    byDigitalNoiseRemoveEnable;
        public byte    byDigitalNoiseRemoveLevel;
        public byte    bySpectralLevel;
        public byte    byTemporalLevel;
        public byte    byDigitalNoiseRemove2DEnable;
        public byte    byDigitalNoiseRemove2DLevel;
        public byte[]    byRes=new byte[2];
    }
    //CMOS模式下前端镜头配置。
    //byCaptureMod
    //抓拍模式：0-抓拍模式1；1-抓拍模式2
    //byBrightnessGate
    //亮度阈值
    //byCaptureGain1
    //抓拍增益1，0-100
    //byCaptureGain2
    //抓拍增益2，0-100
    //dwCaptureShutterSpeed1
    //抓拍快门速度1
    //dwCaptureShutterSpeed2
    //抓拍快门速度2
    //byRes
    //保留
    class NET_DVR_CMOSMODECFG extends Structure {
        public byte    byCaptureMod;
        public byte    byBrightnessGate;
        public byte    byCaptureGain1;
        public byte    byCaptureGain2;
        public int   dwCaptureShutterSpeed1;
        public int   dwCaptureShutterSpeed2;
        public byte[]    byRes=new byte[4];
    }

    //透雾参数结构体。
    class NET_DVR_DEFOGCFG extends Structure {
        public byte    byMode;
        public byte    byLevel;
        public byte[]    byRes=new byte[6];
    }
    //电子防抖参数结构体。
    class NET_DVR_ELECTRONICSTABILIZATION extends Structure {
        public byte    byEnable;  //电子防抖使能：0- 不启用，1- 启用
        public byte    byLevel;  //电子防抖等级，取值范围：0~100
        public byte[]    byRes=new byte[6];
    }
    //旋转功能参数结构体。
    class NET_DVR_CORRIDOR_MODE_CCD extends Structure {
        public byte    byEnableCorridorMode; //是否启用旋转功能：0- 不启用，1- 启用
        public byte[]    byRes=new byte[11];
    }
    //SMART IR(防过曝)配置参数结构体。
    class NET_DVR_SMARTIR_PARAM extends Structure {
        public byte    byMode;  //SMART IR模式：0- 自动，1- 手动
        public byte    byIRDistance; //红外距离等级(等级越高，红外距离越远)：1~100，默认:50，手动模式下可修改
        public byte    byShortIRDistance; //近光灯距离等级，取值范围：1~100
        public byte    byLongIRDistance;  //远光灯距离等级，取值范围：1~100
    }
    //P-Iris红外光圈大小等级配置结构体。
    class NET_DVR_PIRIS_PARAM extends Structure {
        public byte    byMode;  //-Iris模式：0- 自动，1- 手动
        public byte    byPIrisAperture;
        public byte[]    byRes=new byte[6];
    }
    //激光参数配置结构体。
    class NET_DVR_LASER_PARAM_CFG extends Structure{
        public byte    byControlMode;
        public byte    bySensitivity;
        public byte    byTriggerMode;
        public byte    byBrightness;
        public byte    byAngle;
        public byte    byLimitBrightness;
        public byte    byEnabled;
        public byte    byIllumination;
        public byte    byLightAngle;
        public byte[]    byRes=new byte[7];
    }
    //FFC参数结构体。
    class NET_DVR_FFC_PARAM extends Structure{
        public byte    byMode;
        public byte    byRes1;
        public short    wCompensateTime;
        public byte[]    byRes2=new byte[4];
    }
    //DDE参数结构体。
    class NET_DVR_DDE_PARAM extends Structure{
        public byte    byMode;
        public byte    byNormalLevel;
        public byte    byExpertLevel;
        public byte[]    byRes=new byte[5];
    }
    //AGC参数结构体。
    class NET_DVR_AGC_PARAM extends Structure{
        public byte    bySceneType;
        public byte    byLightLevel;
        public byte    byGainLevel;
        public byte[]    byRes=new byte[5];
    }
    //CCD参数结构体。
    class NET_DVR_SNAP_CAMERAPARAMCFG extends Structure{
        public byte               byWDRMode;
        public byte               byWDRType;
        public byte               byWDRLevel;
        public byte               byRes1;
        public NET_DVR_TIME_EX    struStartTime=new NET_DVR_TIME_EX();
        public NET_DVR_TIME_EX    struEndTime=new NET_DVR_TIME_EX();
        public byte               byDayNightBrightness;
        public byte               byMCEEnabled;
        public byte               byMCELevel;
        public byte               byAutoContrastEnabled;
        public byte               byAutoContrastLevel;
        public byte               byLSEDetailEnabled;
        public byte               byLSEDetailLevel;
        public byte               byLPDEEnabled;
        public byte               byLPDELevel;
        public byte[]               byRes=new byte[35];
    }
    //时间参数结构体。
    class NET_DVR_TIME_EX extends Structure{
        public int    wYear;
        public byte    byMonth;
        public byte    byDay;
        public byte    byHour;
        public byte    byMinute;
        public byte    bySecond;
        public byte    byRes;
    }
    //光学透雾参数结构体。
    class NET_DVR_OPTICAL_DEHAZE extends Structure{
        public byte    byEnable;
        public byte[]    byRes=new byte[7];
    }
    //测温AGC参数结构体。
    class NET_DVR_THERMOMETRY_AGC extends Structure{
        public byte    byMode;
        public byte[]  byRes1=new byte[3];
        public int     iHighTemperature;
        public int     iLowTemperature;
        public byte[]  byRes=new byte[8];
    }

    //前端参数配置结构体。
    class NET_DVR_CAMERAPARAMCFG extends Structure {
        public int                   dwSize;
        public NET_DVR_VIDEOEFFECT     struVideoEffect=new NET_DVR_VIDEOEFFECT();
        public NET_DVR_GAIN            struGain=new NET_DVR_GAIN();
        public NET_DVR_WHITEBALANCE    struWhiteBalance=new NET_DVR_WHITEBALANCE();
        public NET_DVR_EXPOSURE        struExposure=new NET_DVR_EXPOSURE();
        public NET_DVR_GAMMACORRECT    struGammaCorrect= new NET_DVR_GAMMACORRECT();
        public NET_DVR_WDR             struWdr=new NET_DVR_WDR();
        public NET_DVR_DAYNIGHT        struDayNight=new NET_DVR_DAYNIGHT();
        public NET_DVR_BACKLIGHT       struBackLight=new NET_DVR_BACKLIGHT();
        public NET_DVR_NOISEREMOVE     struNoiseRemove=new NET_DVR_NOISEREMOVE();
        public byte                    byPowerLineFrequencyMode;
        public byte                    byIrisMode;
        public byte                    byMirror;
        public byte                    byDigitalZoom;
        public byte                    byDeadPixelDetect;
        public byte                    byBlackPwl;
        public byte                    byEptzGate;
        public byte                    byLocalOutputGate;
        public byte                    byCoderOutputMode;
        public byte                    byLineCoding;
        public byte                    byDimmerMode;
        public byte                    byPaletteMode;
        public byte                    byEnhancedMode;
        public byte                    byDynamicContrastEN;
        public byte                    byDynamicContrast;
        public byte                    byJPEGQuality;
        public NET_DVR_CMOSMODECFG     struCmosModeCfg=new NET_DVR_CMOSMODECFG();
        public byte                    byFilterSwitch;
        public byte                    byFocusSpeed;
        public byte                    byAutoCompensationInterval;
        public byte                    bySceneMode;
    }

    class NET_DVR_LOCAL_SDK_PATH extends Structure {
        public byte[] sPath=new byte[256];
        public byte[] byRes=new byte[128];
    }

    //透传接口输入参数结构体。
    class NET_DVR_XML_CONFIG_INPUT extends Structure{
        public int  dwSize; //[in] 结构体大小
        public Pointer lpRequestUrl; //请求信令，字符串格式
        public int  dwRequestUrlLen; //请求信令长度，字符串长度
        public Pointer lpInBuffer;  //输入参数缓冲区，XML/JSON格式
        public int  dwInBufferSize; //输入参数缓冲区大小
        public int  dwRecvTimeOut; //接收超时时间，单位：ms，填0则使用默认超时5s
        public byte  byForceEncrpt;
        public byte   byNumOfMultiPart; //报文分段个数：0- 无效；其他- lpInBuffer传入NET_DVR_MIME_UNIT结构体指针，该值即代表结构体个数
        public byte[] byRes=new byte[30];
    }

    //透传接口输出参数结构体。
    class NET_DVR_XML_CONFIG_OUTPUT extends Structure{
        public int  dwSize; //[in] 结构体大小
        public Pointer lpOutBuffer; //[out]输出参数缓冲区，XML/JSON格式，请求信令为“GET”类型时应用层需要事先分配足够大的内存
        public int  dwOutBufferSize; //[in] 输出参数缓冲区大小(内存大小)
        public int  dwReturnedXMLSize; //[in] 实际输出的XML/JSON内容大小
        public Pointer lpStatusBuffer; //[out] 返回的状态参数(XML/JSON格式：ResponseStatus)，获取命令成功时不会赋值，如果不需要，可以置NULL
        public int  dwStatusSize; //[in] 状态缓冲区大小(内存大小)
        public byte[] byRes=new byte[32];
    }

    //补光灯配置结构体。
    class NET_DVR_BUILTIN_SUPPLEMENTLIGHT extends Structure{
        public int  dwSize;   //结构体大小
        public byte  byMode; //补光灯模式：0- 定时，1- 开启，2- 关闭，3- 自动（非光敏，算法画面识别）
        public byte  byBrightnessLimit; //亮度限制，取值范围：[0,100]
        public byte[]  byRes=new byte[6];
        public NET_DVR_SCHEDULE_DAYTIME struSchedTime=new NET_DVR_SCHEDULE_DAYTIME(); //定时时间段，byMode为1时有效，在时间段内自动开启补光灯，在时间段外自动关闭补光灯
        public byte[]  byRes1=new byte[256];
    }

    class NET_DVR_SCHEDULE_DAYTIME extends Structure{
        public NET_DVR_DAYTIME    struStartTime;
        public NET_DVR_DAYTIME    struStopTime;
    }
    //时间参数结构体
    class NET_DVR_DAYTIME extends Structure{
        public byte    byHour; //时，取值范围：0~24
        public byte    byMinute; //分，取值范围：0~60
        public byte    bySecond; //秒，取值范围：0~60
        public byte    byRes;   //
        public byte    wMilliSecond; // 毫秒，取值范围：0~1000
        public byte[]    byRes1=new byte[2];
    }

    //  用户自定义结构体
    class NET_DVR_USER_DATA extends Structure {
        public byte[] robotSn = new byte[20];
    }

    //  云台锁定配置结构体
    class NET_DVR_PTZ_LOCKCFG extends Structure {
        public int  dwSize;   //结构体大小
        public byte  byWorkMode; //云台锁定控制：0- 解锁，1- 锁定
        public byte[]  byRes=new byte[123]; //保留，置为0
    }


    //    通过该接口保存录像，保存的录像文件数据超过文件最大限制字节数（默认为1024MB），SDK会自动切片，即新建文件进行保存，文件名命名规则为“在接口传入的文件名基础上增加数字标识(例如：*_1.mp4、*_2.mp4)”。
    //    lRealHandle
    //[in] NET_DVR_RealPlay或NET_DVR_RealPlay_V30的返回值
    //            sFileName
    //[in] 文件路径指针，包括文件名，例如："D:\\test.mp4"
    //    Return Values
    boolean NET_DVR_SaveRealData(NativeLong  lRealHandle, String  sFileName);

    /**
     * 停止录制视频
     * @param lRealHandle
     * @return
     */
    boolean NET_DVR_StopSaveRealData(NativeLong  lRealHandle);

    /**
     * 按指定的目标封装格式捕获数据并存放到指定的文件中。
     * @param lRealHandle
     * @param dwTransType
     * @param sFileName
     * @return
     */
    boolean NET_DVR_SaveRealData_V30(NativeLong   lRealHandle, int  dwTransType, String  sFileName);


    /**
     *
     * @param lRealHandle
     * @param cbDrawFun
     * @param dwUser
     * @return
     */
    boolean NET_DVR_RigisterDrawFun(NativeLong  lRealHandle, fDrawFun  cbDrawFun,int dwUser);

    /**
     * 开启日志 SDK初始化之后增加调用NET_DVR_SetLogToFile启动写日志，nLogLevel日志等级设成3，bAutoDel设置为FALSE。
     * @param nLogLevel
     * @param strLogDir
     * @param bAutoDel
     * @return
     */
    boolean NET_DVR_SetLogToFile(int  nLogLevel, String strLogDir, boolean bAutoDel);

    boolean NET_DVR_SetSDKInitCfg(int  enumType, Pointer  lpInBuff);

    /**
     * ISAPI协议命令透传。
     * @param lUserID  NET_DVR_Login_V40等登录接口的返回值
     * @param lpInputParam  输入参数
     * @param lpOutputParam
     * @return
     */
    boolean NET_DVR_STDXMLConfig( NativeLong  lUserID, Pointer lpInputParam, Pointer lpOutputParam );

    /**
     * //启动语音对讲(Linux版本暂不支持)。
     * @param lUserID  [in] NET_DVR_Login_V40等登录接口的返回值
     * @param dwVoiceChan  [in] 语音通道号。对于设备本身的语音对讲通道，从1开始；对于设备的IP通道，为登录返回的起始对讲通道号(byStartDTalkChan) + IP通道索引 - 1，例如客户端通过NVR跟其IP Channel02所接前端IPC进行对讲，则dwVoiceChan=byStartDTalkChan + 1
     * @param bNeedCBNoEncData  [in] 需要回调的语音数据类型：0- 编码后的语音数据，1- 编码前的PCM原始数据
     * @param cbVoiceDataCallBack  [in] 音频数据回调函数
     * @param pUser  [in] 用户数据指针
     * @return
     */
    NativeLong NET_DVR_StartVoiceCom_V30(NativeLong lUserID,int  dwVoiceChan,boolean  bNeedCBNoEncData, FVoiceDataCallBack cbVoiceDataCallBack,Pointer pUser);


    /**
     * 停止语音对讲或者语音转发。
     * @param lVoiceComHandle
     * @return
     */
    boolean NET_DVR_StopVoiceCom(NativeLong  lVoiceComHandle);

    //语音对讲回调函数接口
    public static interface FVoiceDataCallBack extends StdCallCallback {
        public void invoke(NativeLong   lVoiceComHandle, Pointer pRecvDataBuffer,int dwBufSize, byte byAudioFlag,Pointer pUser);
    }

    /**
     * 调用预置点
     * @param userId
     * @param lChannel
     * @param dwPTZPresetCmd
     * @param dwPresetIndex
     * @return
     */
    boolean NET_DVR_PTZPreset_Other(NativeLong userId, NativeLong lChannel, NativeLong dwPTZPresetCmd, NativeLong dwPresetIndex);


    /**  ↓ ↓ ↓报警布防相关接口 ↓ ↓ ↓  **/

    class NET_DVR_ALARM_ISAPI_INFO extends Structure {
        public Pointer pAlarmData;           // 报警数据（参见下表）
        public int dwAlarmDataLen;   // 报警数据长度
        public byte byDataType;        // 0-invalid,1-xml,2-json
        public byte byPicturesNumber;  // 图片数量
        public byte[] byRes = new byte[2];
        public Pointer pPicPackData;         // 图片变长部分
        //（byPicturesNumber个{NET_DVR_ALARM_ISAPI_PICDATA}；）
        public byte[] byRes1 = new byte[32];
    }

    int MAX_CHANNUM_V30 = 64;//64
    int MAX_ALARMOUT_V30 = 96;//96
    int MAX_ALARMIN_V30 = 160;//160
    int MAX_DISKNUM_V30 = 33;        //9000设备最大硬盘数/* 最多33个硬盘(包括16个内置SATA硬盘、1个eSATA硬盘和16个NFS盘) */

    class NET_DVR_ALARMINFO_V30 extends Structure {//上传报警信息(9000扩展)
        public int dwAlarmType;/*0-信号量报警,1-硬盘满,2-信号丢失,3－移动侦测,4－硬盘未格式化,5-读写硬盘出错,6-遮挡报警,7-制式不匹配, 8-非法访问, 0xa-GPS定位信息(车载定制)*/
        public int dwAlarmInputNumber;/*报警输入端口*/
        public byte[] byAlarmOutputNumber = new byte[MAX_ALARMOUT_V30];/*触发的输出端口，为1表示对应输出*/
        public byte[] byAlarmRelateChannel = new byte[MAX_CHANNUM_V30];/*触发的录像通道，为1表示对应录像, dwAlarmRelateChannel[0]对应第1个通道*/
        public byte[] byChannel = new byte[MAX_CHANNUM_V30];/*dwAlarmType为2或3,6时，表示哪个通道，dwChannel[0]对应第1个通道*/
        public byte[] byDiskNumber = new byte[MAX_DISKNUM_V30];/*dwAlarmType为1,4,5时,表示哪个硬盘, dwDiskNumber[0]对应第1个硬盘*/

    }

    boolean NET_DVR_SetSDKLocalCfg(int enumType, Pointer lpInBuff);

    class NET_DVR_LOCAL_GENERAL_CFG extends Structure {
        public byte byExceptionCbDirectly;    //0-通过线程池异常回调，1-直接异常回调给上层
        public byte byNotSplitRecordFile;     //回放和预览中保存到本地录像文件不切片 0-默认切片，1-不切片
        public byte byResumeUpgradeEnable;    //断网续传升级使能，0-关闭（默认），1-开启
        public byte byAlarmJsonPictureSeparate;   //控制JSON透传报警数据和图片是否分离，0-不分离，1-分离（分离后走COMM_ISAPI_ALARM回调返回）
        public byte[] byRes = new byte[4];      //保留
        public long i64FileSize;      //单位：Byte
        public int dwResumeUpgradeTimeout;       //断网续传重连超时时间，单位毫秒
        public byte[] byRes1 = new byte[236];    //预留

    }

    int MAX_FILE_PATH_LEN = 256; //文件路径长度

    class NET_DVR_ALARM_ISAPI_PICDATA extends Structure {
        public int dwPicLen;
        public byte byPicType;  //图片格式: 1- jpg
        public byte[] byRes = new byte[3];
        public byte[] szFilename = new byte[MAX_FILE_PATH_LEN];
        public Pointer pPicData; // 图片数据
    }

    boolean NET_DVR_SetDVRMessageCallBack_V31(FMSGCallBack_V31 fMessageCallBack, Pointer pUser);

    interface FMSGCallBack_V31 extends Callback {
         boolean invoke(int lCommand, NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser);
    }

    //报警设备信息
    class NET_DVR_ALARMER extends Structure {
        public byte byUserIDValid;                 /* userid是否有效 0-无效，1-有效 */
        public byte bySerialValid;                 /* 序列号是否有效 0-无效，1-有效 */
        public byte byVersionValid;                /* 版本号是否有效 0-无效，1-有效 */
        public byte byDeviceNameValid;             /* 设备名字是否有效 0-无效，1-有效 */
        public byte byMacAddrValid;                /* MAC地址是否有效 0-无效，1-有效 */
        public byte byLinkPortValid;               /* login端口是否有效 0-无效，1-有效 */
        public byte byDeviceIPValid;               /* 设备IP是否有效 0-无效，1-有效 */
        public byte bySocketIPValid;               /* socket ip是否有效 0-无效，1-有效 */
        public int lUserID;                       /* NET_DVR_Login()返回值, 布防时有效 */
        public byte[] sSerialNumber = new byte[SERIALNO_LEN];	/* 序列号 */
        public int dwDeviceVersion;			    /* 版本信息 高16位表示主版本，低16位表示次版本*/
        public byte[] sDeviceName = new byte[NAME_LEN];		    /* 设备名字 */
        public byte[] byMacAddr = new byte[MACADDR_LEN];		/* MAC地址 */
        public short wLinkPort;                     /* link port */
        public byte[] sDeviceIP = new byte[128];    			/* IP地址 */
        public byte[] sSocketIP = new byte[128];    			/* 报警主动上传时的socket IP地址 */
        public byte byIpProtocol;                  /* Ip协议 0-IPV4, 1-IPV6 */
        public byte[] byRes2 = new byte[11];
    }

    int NET_DVR_SetupAlarmChan_V41(int lUserID, NET_DVR_SETUPALARM_PARAM lpSetupParam);

    //布防参数
    class NET_DVR_SETUPALARM_PARAM extends Structure {
        public int dwSize;
        public byte byLevel; //布防优先级，0-一等级（高），1-二等级（中），2-三等级（低）
        public byte byAlarmInfoType; //上传报警信息类型（抓拍机支持），0-老报警信息（NET_DVR_PLATE_RESULT），1-新报警信息(NET_ITS_PLATE_RESULT)2012-9-28
        public byte byRetAlarmTypeV40; //0--返回NET_DVR_ALARMINFO_V30或NET_DVR_ALARMINFO, 1--设备支持NET_DVR_ALARMINFO_V40则返回NET_DVR_ALARMINFO_V40，不支持则返回NET_DVR_ALARMINFO_V30或NET_DVR_ALARMINFO
        public byte byRetDevInfoVersion; //CVR上传报警信息回调结构体版本号 0-COMM_ALARM_DEVICE， 1-COMM_ALARM_DEVICE_V40
        public byte byRetVQDAlarmType; //VQD报警上传类型，0-上传报报警NET_DVR_VQD_DIAGNOSE_INFO，1-上传报警NET_DVR_VQD_ALARM
        public byte byFaceAlarmDetection;
        public byte bySupport;
        public byte byBrokenNetHttp;
        public short wTaskNo;    //任务处理号 和 (上传数据NET_DVR_VEHICLE_RECOG_RESULT中的字段dwTaskNo对应 同时 下发任务结构 NET_DVR_VEHICLE_RECOG_COND中的字段dwTaskNo对应)
        public byte byDeployType;    //布防类型：0-客户端布防，1-实时布防
        public byte[] byRes1 = new byte[3];
        public byte byAlarmTypeURL;//bit0-表示人脸抓拍报警上传（INTER_FACESNAP_RESULT）；0-表示二进制传输，1-表示URL传输（设备支持的情况下，设备支持能力根据具体报警能力集判断,同时设备需要支持URL的相关服务，当前是”云存储“）
        public byte byCustomCtrl;//Bit0- 表示支持副驾驶人脸子图上传: 0-不上传,1-上传,(注：只在公司内部8600/8200等平台开放)
    }

    boolean NET_DVR_CloseAlarmChan_V30(int lAlarmHandle);

    //行为分析报警
    class NET_VCA_RULE_ALARM extends Structure {
        public int dwSize;
        public int dwRelativeTime;
        public int dwAbsTime;
        public NET_VCA_RULE_INFO struRuleInfo;
        public NET_VCA_TARGET_INFO struTargetInfo;
        public NET_VCA_DEV_INFO struDevInfo;
        public int dwPicDataLen;
        public byte byPicType;
        public byte byRelAlarmPicNum; //关联通道报警图片数量
        public byte bySmart;//IDS设备返回0(默认值)，Smart Functiom Return 1
        public byte byPicTransType;        //图片数据传输方式: 0-二进制；1-url
        public int dwAlarmID;     //报警ID，用以标识通道间关联产生的组合报警，0表示无效
        public short wDevInfoIvmsChannelEx;     //与NET_VCA_DEV_INFO里的byIvmsChannel含义相同，能表示更大的值。老客户端用byIvmsChannel能继续兼容，但是最大到255。新客户端版本请使用wDevInfoIvmsChannelEx。
        public byte byRelativeTimeFlag;      //dwRelativeTime字段是否有效  0-无效， 1-有效，dwRelativeTime表示UTC时间
        public byte byAppendInfoUploadEnabled; //附加信息上传使能 0-不上传 1-上传
        public Pointer pAppendInfo;     //指向附加信息NET_VCA_APPEND_INFO的指针，byAppendInfoUploadEnabled为1时或者byTimeDiffFlag为1时有效
        public Pointer pImage;
    }

    //事件规则信息
    class NET_VCA_RULE_INFO extends Structure {
        public byte byRuleID;
        public byte byRes;
        public short wEventTypeEx;
        public byte[] byRuleName = new byte[NAME_LEN];
        public int dwEventType;
        public NET_VCA_EVENT_UNION uEventParam;

        public void read() {
            super.read();
            switch (wEventTypeEx) {
                case 1:
                    uEventParam.setType(NET_VCA_TRAVERSE_PLANE.class);
                    break;
                case 2:
                case 3:
                    uEventParam.setType(NET_VCA_AREA.class);
                    break;
                default:
                    break;
            }
            uEventParam.read();
        }

        public void write() {
            super.write();
            uEventParam.write();
        }

    }

    //撤防
    boolean NET_DVR_CloseAlarmChan(int lAlarmHandle);

    //报警目标信息
    class NET_VCA_TARGET_INFO extends Structure {
        public int dwID;
        public NET_VCA_RECT struRect;
        public byte[] byRes = new byte[4];
    }

    //前端设备信息
    class NET_VCA_DEV_INFO extends Structure {
        public NET_DVR_IPADDR struDevIP;
        public short wPort;
        public byte byChannel;
        public byte byIvmsChannel;
    }

    class NET_DVR_EVENT_TRIGGER extends Structure {
        public int dwSize;//结构体大小
        public NET_DVR_HANDLEEXCEPTION_V41 struHandleException;     //异常处理方式
        public int[] dwRelRecordChan = new int[512]; //实际触发录像通道，按值表示，采用紧凑型排列，从下标0开始顺序读取，中间遇到0xffffffff则后续无效。
        public NET_DVR_PRESETCHAN_INFO[] struPresetChanInfo = new NET_DVR_PRESETCHAN_INFO[512]; //启用的预置点信息
        public NET_DVR_CRUISECHAN_INFO[] struCruiseChanInfo = new NET_DVR_CRUISECHAN_INFO[512]; //启用巡航功能通道的信息
        public NET_DVR_PTZTRACKCHAN_INFO[] struPtzTrackInfo = new NET_DVR_PTZTRACKCHAN_INFO[512]; //调用云台轨迹的通道信息
        public byte byDirection;//触发方向：0-保留；1-全部；2-正向；3-反向
        public byte[] byRes2 = new byte[255];
    }

    class NET_DVR_PRESETCHAN_INFO extends Structure {
        public int dwEnablePresetChan;    /*启用预置点的通道, 0xfffffff表示不调用预置点*/
        public int dwPresetPointNo;        /*调用预置点通道对应的预置点序号, 0xfffffff表示不调用预置点。*/
    }

    class NET_DVR_CRUISECHAN_INFO extends Structure {
        public int dwEnableCruiseChan;    /*启用巡航的通道*/
        public int dwCruiseNo;        /*巡航通道对应的巡航编号, 0xfffffff表示无效*/
    }

    class NET_DVR_PTZTRACKCHAN_INFO extends Structure {
        public int dwEnablePtzTrackChan;    /*启用云台轨迹的通道*/
        public int dwPtzTrackNo;        /*云台轨迹通道对应的编号, 0xfffffff表示无效*/
    }

    class NET_DVR_HANDLEEXCEPTION_V41 extends Structure {
        public int dwHandleType;        //异常处理,异常处理方式的"或"结果
        /*0x00: 无响应*/
        /*0x01: 监视器上警告*/
        /*0x02: 声音警告*/
        /*0x04: 上传中心*/
        /*0x08: 触发报警输出*/
        /*0x10: 触发JPRG抓图并上传Email*/
        /*0x20: 无线声光报警器联动*/
        /*0x40: 联动电子地图(目前只有PCNVR支持)*/
        /*0x200: 抓图并上传FTP*/
        /*0x400: 虚交侦测 联动 聚焦模式（提供可配置项，原先设备自动完成）IPC5.1.0*/
        /*0x800: PTZ联动跟踪(球机跟踪目标)*/
        /*0x4000:白光灯报警*/
        /*0x10000:短信报警*/
        public int dwMaxRelAlarmOutChanNum; //触发的报警输出通道数（只读）最大支持数
        public int[] dwRelAlarmOut = new int[4128]; //触发报警通道
        public byte[] byRes = new byte[64];           //保留
    }

    //警戒规则参数联合体
    class NET_VCA_EVENT_UNION extends Union {
        public int[] uLen = new int[23];
        public NET_VCA_TRAVERSE_PLANE struTraversePlane;
        public NET_VCA_AREA struArea;
    }


    //穿越警戒面参数
    class NET_VCA_TRAVERSE_PLANE extends Structure {
        public NET_VCA_LINE struPlaneBottom;
        public int dwCrossDirection;
        public byte bySensitivity;
        public byte byPlaneHeight;
        public byte byDetectionTarget;/*检测目标：0- 所有目标，1- 人，2- 车   */
        public byte[] byRes2 = new byte[37];
    }

    //线结构参数
    class NET_VCA_LINE extends Structure {
        public NET_VCA_POINT struStart;
        public NET_VCA_POINT struEnd;
    }

    //区域框参数
    class NET_VCA_RECT extends Structure {
        public float fX;
        public float fY;
        public float fWidth;
        public float fHeight;
    }

    //进入/离开区域参数
    class NET_VCA_AREA extends Structure {
        public NET_VCA_POLYGON struRegion;
        public byte[] byRes = new byte[8];
    }

    /**  ↑ ↑ ↑报警布防相关接口 ↑ ↑ ↑  **/

}

