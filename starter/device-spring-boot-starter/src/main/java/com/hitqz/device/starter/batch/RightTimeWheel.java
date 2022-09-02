package com.hitqz.device.starter.batch;

import com.hitqz.sjtc.core.dict.MatrixDataDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RightTimeWheel extends TimeWheelBatchNumber{

    private static final Logger log = LoggerFactory.getLogger(RightTimeWheel.class);

    private static int rightNumber = 0;

    private static int triggerNumber = 1;

    private static String rightPrefix;


    @Override
    public String createBatchNumber(String flag) {
        rightNumber++;

        int stdNumberCount = Integer.valueOf(MatrixDataDict.stdWorkNumber.getDictCode());

        //若编号超过标准作业集数,则重置number
        if(rightNumber > stdNumberCount){
            rightNumber = 1;
            triggerNumber++;
        }

        if(rightNumber == 1){
            rightPrefix = flag ;
        }

        log.info("{}  触发此次生成的批次号是: {}",flag,rightPrefix+"-"+triggerNumber+"-"+rightNumber);
        //生成批次号
        return rightPrefix+"-"+triggerNumber+"-"+rightNumber;
    }
}
