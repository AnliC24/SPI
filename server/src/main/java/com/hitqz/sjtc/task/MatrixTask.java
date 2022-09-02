package com.hitqz.sjtc.task;

import cn.hutool.core.util.IdUtil;
import com.hitqz.sjtc.core.dict.MatrixDataDict;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.entity.BenchmarkInfo;
import com.hitqz.sjtc.entity.OriginWorkRecordInfo;
import com.hitqz.sjtc.entity.WorkRecordInfo;
import com.hitqz.sjtc.service.BenchmarkInfoService;
import com.hitqz.sjtc.service.OriginWorkRecordInfoService;
import com.hitqz.sjtc.service.WorkRecordInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.ujmp.core.treematrix.DefaultTreeMatrix;
import org.ujmp.core.treematrix.TreeMatrix;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 矩阵算法
 * */
@Component
public class MatrixTask {

    private static final Logger log = LoggerFactory.getLogger(MatrixTask.class);

    @Resource
    private OriginWorkRecordInfoService originWorkRecordInfoService;

    @Resource
    private BenchmarkInfoService benchmarkInfoService;

    @Resource
    private WorkRecordInfoService workRecordInfoService;


    /**
     *  构建动态矩阵
     *  1.先去 origin_work_record_info 取出  未检测的批次号,选其中一条作为基准，然后以此基准算出 连续的8个批次作业号
     *  2.根据这8个连续的批次作业号，取出对应的 8*8 = 64 条数据，构建矩阵，取这个矩阵的对角线，并将此
     *
     *  当origin_work_record_info表数据量过大时，为了避免查询过慢，所以将第一次查询的所有作业批次号，
     *  缓存进redis内，每次先去redis内获取批次号，以redis内的批次号进行连续批次号的构建
     *
     *  由于单侧设备数未确定，所以以2条例
     *s
     *  批次号 left-1-1
     *
     *  为了减少算法开销，可以使用redis缓存查询出的批次号，等每一批的批次号都检测完成后，再去取新的一批批次号
     * */
//    @XxlJob("buildMatrix")
    public void buildMatrix(){

        int stdNumber = Integer.parseInt(MatrixDataDict.stdWorkNumber.getDictCode());

        //先取其8条作业批次号
        List<String> batchNumbers = originWorkRecordInfoService.getTenBatchNumberByOld();

        //取其第一条，然后以此基准构建矩阵
        if(batchNumbers.size() == 0){
            log.warn("未查询到有效批次号");
            return;
        }
        //取矩阵基准
        String benchmark = batchNumbers.get(0);
        //计算出连续的批次号
        List<String> continuousBatchNumber = computeStdBatchNumber(benchmark);
        log.info("以 {} 为基准,计算其连续批次号为 {}",benchmark,continuousBatchNumber);
        //根据连续的批次号,取出其对应的数据流,拉到栈内
        List<OriginWorkRecordInfo> records = originWorkRecordInfoService.getWorkRecordByBatchNumber(continuousBatchNumber);
        if(records.size() == 0){
            log.info("未查询到对应数据流,请检查");
            return;
        }
        if(records.size() < stdNumber*stdNumber){
            log.info("无法构建标准矩阵,某一批次号下的作业数量异常,请检查");
            //非标准矩阵,需将此信息记录
            BenchmarkInfo benchmarkInfo = new BenchmarkInfo();
            benchmarkInfo.setBenchmark(benchmark);
            benchmarkInfo.setCreateTime(new Date());
            benchmarkInfo.setIsStd(MatrixDataDict.unStdMatrix.getDictCode());
            benchmarkInfo.setTrace("--");
            benchmarkInfo.setLostCount(stdNumber*stdNumber-records.size());

            benchmarkInfoService.save(benchmarkInfo);
            return;
        }
        int i = 0;
        List<Integer> ids = new ArrayList<>();
        String detected = MatrixDataDict.handleStatusByDetected.getDictCode();
        //如果匹配到标准的矩阵集，那么就构建标准作业集 -- 矩阵库不好用，暂时放弃
        List<String> stdWork = new ArrayList<>();
        List<String> stdWorkUuid = new ArrayList<>();
        List<WorkRecordInfo> workRecordInfos = new ArrayList<>();
        for(String str : continuousBatchNumber){
            stdWork.add(str + "+" + ++i);
            for(OriginWorkRecordInfo recordInfo : records){
                //如果当前的作业记录批次号和工序号都匹配到了，那么取得其id
                //取出当前迹对应的record，将其存进work_record_info
                if(recordInfo.getBatchOperationId().equals(str)&&recordInfo.getOptProcess() == i){
                    ids.add(recordInfo.getId());
                    WorkRecordInfo workRecordInfo = MyBeanUtils.getBeanByCopyProperties(recordInfo,new WorkRecordInfo());
                    workRecordInfo.setHandleStatus(detected);
                    String uuid = IdUtil.randomUUID();
                    workRecordInfo.setId(uuid);
                    stdWorkUuid.add(uuid);
                    workRecordInfos.add(workRecordInfo);
                    break;
                }
            }
        }
        log.info("标准作业集对应批次号：{}",stdWork);
        //迹 所标记的批次号都改成已处理
        benchmarkInfoService.save(createStdBenchmarkInfo(benchmark,stdWork,stdWorkUuid));
        //取出迹的所有ids，然后把这些ids 在Origin_work_record_info 内的数据 全部删除,origin_work_record_info 内只保存未聚合的记录
        workRecordInfoService.saveWorkRecordBatch(workRecordInfos);
        originWorkRecordInfoService.deleteOriginRecordByIds(ids);
//        originWorkRecordInfoService.updateOriginRecordById(ids,detected);

    }

    //根据标准批次号格式，进行批次号分解，然后计算出连续的标准批次号数
    private List<String> computeStdBatchNumber(String benchmark){
        List<String> realRes = new ArrayList<>();
        //先分解
        String[] val1 = benchmark.split("-");
        //取其倒二位
        String val2 = val1[1];
        int res2 = Integer.parseInt(val2);
        //取其最后一位数
        String val3 = val1[2];
        int res3 = Integer.parseInt(val3);

        realRes.add(benchmark);

        int stdNumber = Integer.parseInt(MatrixDataDict.stdWorkNumber.getDictCode());

        //计算连续的标准数
        for(int i = 0;i < stdNumber-1;i++){
            int a = ++res3;
            String res = String.valueOf(a);
            if(a > stdNumber){
                res2++;
                res3 = 1;
                res = String.valueOf(res3);
            }
            String test1 = val1[0]+"-"+res2+"-"+res;
            realRes.add(test1);
        }
        return realRes;
    }

    //标准矩阵计算
    private BenchmarkInfo createStdBenchmarkInfo(String benchmark,List<String> trace,List<String> traceUuid){
        BenchmarkInfo benchmarkInfo = new BenchmarkInfo();
        benchmarkInfo.setBenchmark(benchmark);
        benchmarkInfo.setCreateTime(new Date());
        benchmarkInfo.setIsStd(MatrixDataDict.stdMatrix.getDictCode());
        benchmarkInfo.setTrace(String.join(",",trace));
        benchmarkInfo.setTraceUuid(String.join(",",traceUuid));
        return benchmarkInfo;
    }


    /**
     * 矩阵测试
     * */
    public static void main(String[] args) {
//        test.setObject();



//
//        // set entry at row 2 and column 3 to the value 5.0
//        dense.setAsDouble(5.0, 2, 3);
//
//        // set some other values
//        dense.setAsDouble(1.0, 0, 0);
//        dense.setAsDouble(3.0, 1, 1);
//        dense.setAsDouble(4.0, 2, 2);
//        dense.setAsDouble(-2.0, 3, 3);
//        dense.setAsDouble(-2.0, 1, 3);
//
//        // print the final matrix on the console
//        System.out.println(dense);

        TreeMatrix<String> treeMatrix = new DefaultTreeMatrix<>();

        // create data
        treeMatrix.setRoot("root");
        treeMatrix.addChild("root", "child1");
        treeMatrix.addChild("root", "child2");
        treeMatrix.addChild("root", "child3");
        treeMatrix.addChild("child1", "subChild11");
        treeMatrix.addChild("child1", "subChild12");
        treeMatrix.addChild("child1", "subChild13");
        treeMatrix.addChild("child2", "subChild21");
        treeMatrix.addChild("child3", "subChild31");
        treeMatrix.addChild("child3", "subChild32");
        treeMatrix.addChild("subChild12", "subSubChild121");
        treeMatrix.addChild("subChild12", "subSubChild122");
        treeMatrix.addChild("subSubChild122", "subSubSubChild1221");

        treeMatrix.showGUI();
    }

}
