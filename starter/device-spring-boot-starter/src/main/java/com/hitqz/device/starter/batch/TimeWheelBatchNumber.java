package com.hitqz.device.starter.batch;

/**
 * 时间轮 批次号生成器
 * 由于设备组存在两侧，分为左右侧，所以为了避免两侧光电竞争一个时间轮，将时间轮设计为左轮，右轮
 * */
public abstract class TimeWheelBatchNumber implements BatchNumber {


    public static void main(String[] args) {
        //创建一个线程去模拟 生成批次号
        Thread left = new MyThread();
        Thread right = new MyThread1();

        left.start();
        right.start();
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            while(true){
                try {
                    BatchNumber createBatchNumber = new LeftTimeWheel();
                    createBatchNumber.createBatchNumber("left");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread1 extends  Thread {
        @Override
        public void run() {
            while(true){
                try {
                    BatchNumber createBatchNumber = new RightTimeWheel();
                    createBatchNumber.createBatchNumber("right");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
