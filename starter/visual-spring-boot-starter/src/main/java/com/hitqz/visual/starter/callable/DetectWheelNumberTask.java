package com.hitqz.visual.starter.callable;

import com.hitqz.sjtc.core.dict.VisualDataDict;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.visual.starter.components.VisualHttpClient;
import com.hitqz.visual.starter.entity.VisualRequest;


import java.util.concurrent.Callable;

public class DetectWheelNumberTask implements Callable<ResultObj> {

    private VisualHttpClient visualHttpClient;

    private VisualRequest request;

    public DetectWheelNumberTask(VisualHttpClient visualHttpClient,VisualRequest request) {
        this.visualHttpClient = visualHttpClient;
        this.request = request;
    }

    //视觉服务调用路径
    private final static String URL = VisualDataDict.visualRequest.getDictValue();

    @Override
    public ResultObj call() throws Exception {
        request.setUrl(URL);
        String type = VisualDataDict.detectWheelNumber.getDictCode();
        request.setType(type);
        return visualHttpClient.execute(request);
    }
}
