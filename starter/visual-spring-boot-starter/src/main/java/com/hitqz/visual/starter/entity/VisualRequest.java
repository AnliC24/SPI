package com.hitqz.visual.starter.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * @author windc
 * 视觉请求参数配置
 * */
public class VisualRequest implements Serializable {

    @ApiModelProperty("调用路径")
    private String url;

    @ApiModelProperty("图片路径")
    private List<String> pathImages;

    @ApiModelProperty("调用类型")
    private String type;

    @ApiModelProperty("机器人点位")
    private String point;

    @ApiModelProperty("检测后图片保存路径")
    private String imageSavePath;

    public VisualRequest(List<String> pathImages) {
        this.pathImages = pathImages;
    }

    public VisualRequest(String url, List<String> pathImages, String type, String point) {
        this.url = url;
        this.pathImages = pathImages;
        this.type = type;
        this.point = point;
    }

    public VisualRequest(String url, String type, List<String> pathImages) {
        this.url = url;
        this.type = type;
        this.pathImages = pathImages;
    }

    public VisualRequest(String url, List<String> pathImages, String type, String point, String imageSavePath) {
        this.url = url;
        this.pathImages = pathImages;
        this.type = type;
        this.point = point;
        this.imageSavePath = imageSavePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public List<String> getPathImages() {
        return pathImages;
    }

    public void setPathImages(List<String> pathImages) {
        this.pathImages = pathImages;
    }

    public String getImageSavePath() {
        return imageSavePath;
    }

    public void setImageSavePath(String imageSavePath) {
        this.imageSavePath = imageSavePath;
    }
}
