package com.hitqz.device.hknative;

public interface HkService {

    /**
     * deviceId 必填
     * lUserID 只是用来做占位符
     * */
    void LivePreviewHk(String deviceId,int lUserID);

    //抓图功能
    String captureJPEGPicture(String deviceId,int lUserID);

    boolean captureJPEGPictureMany(String deviceId,int lUserID);


}
