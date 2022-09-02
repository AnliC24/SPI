package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.Date;

@TableName("benchmark_info")
public class BenchmarkInfo implements Serializable {

    @ApiModelProperty(value = "主键编号")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "聚合基准")
    private String benchmark;

    @ApiModelProperty("对象结果映射主键")
    private String traceUuid;

    @ApiModelProperty("对象结果映射批次号")
    private String trace;

    @ApiModelProperty("是否标准")
    private String isStd;

    @ApiModelProperty("作业缺失数")
    private double lostCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getIsStd() {
        return isStd;
    }

    public void setIsStd(String isStd) {
        this.isStd = isStd;
    }

    public double getLostCount() {
        return lostCount;
    }

    public void setLostCount(double lostCount) {
        this.lostCount = lostCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTraceUuid() {
        return traceUuid;
    }

    public void setTraceUuid(String traceUuid) {
        this.traceUuid = traceUuid;
    }
}
