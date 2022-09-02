package com.hitqz.visual.starter.components;

import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.visual.starter.entity.VisualRequest;
import com.hitqz.visual.starter.entity.VisualResponse;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author windc
 *
 * 1.检查入参是否正常 可选
 *
 * */
public interface VisualHttpClient {

    //检查入参
    default VisualRequest checkConfig(VisualRequest http){

        if(http == null){
            throw new NullPointerException("未配置VisualRequest,请检查");
        }

        String url = http.getUrl();
        checkUrl(url);

        String type = http.getType();
        checkType(type);

        String point;

        if(needConfigurePoint()){
            point = http.getPoint();
        }else{
            point = "";
        }

        String savePath;
        if(needConfigureSavePath()){
            savePath = http.getImageSavePath();
            checkImagePath(savePath);
        }else{
            savePath = "";
        }

        List<String> pathImages = http.getPathImages();
        checkPathImages(pathImages);

        return new VisualRequest(url,pathImages,type,point);
    }


    default ResultObj execute(VisualRequest request){

        //配置参数
        VisualRequest http = checkConfig(request);

        //各种不同类型的调用
        String result = request(http);

        //结果处理
        VisualResponse response = mapResponse(result);

        return ResultObj.success("调用成功").withData(response);
    }


    default boolean needConfigurePoint(){
        return false;
    }

    default boolean needConfigureSavePath(){return false;}

    default void checkUrl(String url){
        if(!StringUtils.hasLength(url)){
            System.out.println("不允许调用路径为空,请检查");
            throw new NullPointerException("不允许调用路径为空,请检查");
        }
    }

    default void checkType(String type){
        if(!StringUtils.hasLength(type)){
            System.out.println("不允许视觉检测类型为空,请检查");
            throw new NullPointerException("不允许视觉检测类型为空,请检查");
        }
    }

    default void checkImagePath(String path){
        if(!StringUtils.hasLength(path)){
            System.out.println("不允许图片路径为空,请检查");
            throw new NullPointerException("不允许图片路径为空,请检查");
        }
    }

    default void checkPathImages(List<String> pathImages){
        if(pathImages.size() <= 0 ){
            System.out.println("不允许检测图片路径为空,请检查");
            throw new NullPointerException("不允许检测图片路径为空,请检查");
        }
    }

    String request(VisualRequest http);

    VisualResponse mapResponse(String result);

}
