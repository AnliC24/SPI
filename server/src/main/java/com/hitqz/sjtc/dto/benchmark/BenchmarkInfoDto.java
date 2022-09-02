package com.hitqz.sjtc.dto.benchmark;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("作业聚合信息")
public class BenchmarkInfoDto implements Serializable {

    @ApiModelProperty("主键编号")
    private int id;

    @ApiModelProperty("基准批次号")
    private String batchOperationId;

    @ApiModelProperty("聚合作业批次号")
    private String trace;

    @ApiModelProperty("是否标准")
    private String isStd;

    @ApiModelProperty("是否标准值")
    private String isStdCn;

    @ApiModelProperty("缺失数")
    private double lostCount;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchOperationId() {
        return batchOperationId;
    }

    public void setBatchOperationId(String batchOperationId) {
        this.batchOperationId = batchOperationId;
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

    public String getIsStdCn() {
        return isStdCn;
    }

    public void setIsStdCn(String isStdCn) {
        this.isStdCn = isStdCn;
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
}
