package com.hitqz.visual.starter.entity;

import java.io.Serializable;

public class VisualResponse implements Serializable {

    /**
     * 200 检测成功
     * 500 检测失败
     * */
    private int code;

    /**
     * 调用消息
     * */
    private String message;

    /**
     * 检测结果
     * */
    private String result;

    private String path;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
