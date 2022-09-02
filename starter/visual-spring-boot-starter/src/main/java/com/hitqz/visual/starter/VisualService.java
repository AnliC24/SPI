package com.hitqz.visual.starter;

import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.visual.starter.annotation.DetectException;
import com.hitqz.visual.starter.callable.DetectWheelNumberTask;
import com.hitqz.visual.starter.components.VisualHttpClient;
import com.hitqz.visual.starter.entity.VisualRequest;
import com.hitqz.visual.starter.service.VisualDetectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

/**
 * @author windc
 * 视觉调用服务
 * 动态注入视觉调用参数
 *
 * 既然是starter了，那就封装一批对应接口的服务
 *
 * 1.轮子编号识别
 * 2.轮子转动异常检测
 * 3.车轮串动检测
 * 4.车轮磨损检测
 * 5.轮圆表面伤痕检测
 * 6.车轮轴与轨道高度检测
 * 7.车轮温度检测
 * 8.篦条掉落，变形检测
 * 9.注油点油嘴损坏检测
 *
 * 有一些检测是耗时操作，所以直接用线程池异步，不要同步阻塞
 *
 *
 * */
public class VisualService implements VisualDetectService {

    @Autowired
    private VisualHttpClient visualHttpClient;

    //链表阻塞队列
    private final static BlockingQueue linkBlockQueue = new LinkedBlockingQueue<>();

    //用来处理所有耗时的视觉调用服务 核心线程 和 最大线程数 根据对应的视觉服务是否为计算密集型 来配置
    private final static ThreadPoolExecutor visualPool =  new ThreadPoolExecutor(
            10,
            20,
            1000L, TimeUnit.SECONDS, linkBlockQueue);

    /**
     * 轮子编号识别
     * 如果调用视觉服务无需返回值，可以直接创建一个Runnable 的任务
     * 然后提供内部接口给视觉服务 返回检测结果，需要重新定义入参结构，用来对结果进行映射
     * 例如，哪个批次号的结果  批次号 -> 检测结果
     * */
    @DetectException
    @Override
    public ResultObj detectWheelNumber(VisualRequest request) {
        //生成一个任务，然后把任务提交给线程池，然后等待执行完
        DetectWheelNumberTask task = new DetectWheelNumberTask(visualHttpClient,request);
        Future future = visualPool.submit(task);
        //future.get 会阻塞
        return ResultObj.success("调用成功");
    }
}
