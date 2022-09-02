package com.hitqz.device.starter.batch;

import com.hitqz.sjtc.core.dict.MatrixDataDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeftTimeWheel extends TimeWheelBatchNumber{

    private static final Logger log = LoggerFactory.getLogger(LeftTimeWheel.class);

    private static int leftNumber = 0;

    private static int triggerNumber = 1;

    private static String leftPrefix;

    @Override
    public String createBatchNumber(String flag) {
        leftNumber++;

        int stdNumberCount = Integer.valueOf(MatrixDataDict.stdWorkNumber.getDictCode());

        //若编号超过标准作业集数,则重置number
        if(leftNumber > stdNumberCount){
            leftNumber = 1;
            triggerNumber++;
        }

        if(leftNumber == 1){
            leftPrefix = flag ;
        }

        log.info("{}  触发此次生成的批次号是: {}",flag,leftPrefix+"-"+triggerNumber+"-"+leftNumber);
        //生成批次号
        return leftPrefix+"-"+triggerNumber+"-"+leftNumber;
    }
}
