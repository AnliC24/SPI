package com.hitqz.sjtc.core.model;


import java.io.Serializable;

public class ResultObj implements Serializable {
    private static final long serialVersionUID = -8707210463471547178L;
    private long code;
    private String msg;
    private Object data;

    public ResultObj() {
        this.code = 200;
        this.msg = "DEFAULT-MSG-000";
    }

    public ResultObj(long code) {
        this.code = code;
    }

    public ResultObj(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultObj success() {
        return success("操作成功");
    }

    public static ResultObj success(String msg) {
        return new ResultObj(200, msg);
    }

    public static ResultObj fail(String msg) {
        return new ResultObj(400, msg);
    }

    public ResultObj withData(Object data) {
        this.data = data;
        return this;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
