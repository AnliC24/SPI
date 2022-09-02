package com.hitqz.sjtc.core.model;

import java.io.Serializable;

public class RequestDataException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public RequestDataException() {

    }


    public RequestDataException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public RequestDataException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public String getBusMsg() {
        return msg;
    }

    public void setBusMsg(String busMsg) {
        this.msg = busMsg;
    }

}
