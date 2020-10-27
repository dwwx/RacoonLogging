package com.racoon.backend;

import com.racoon.events.SubstituteLoggingEvent;
import com.racoon.util.BaseBuffer;
import com.racoon.util.FileUtil;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncLogging implements Runnable {
    private  static BaseBuffer baseBuffer;
    //在这里注册一个队列，去消费这里面的元素
    private LinkedBlockingQueue<SubstituteLoggingEvent> queue;
    private FileUtil fileUtil;
    public AsyncLogging(FileUtil fileUtil, BaseBuffer baseBuffer){
        this.baseBuffer = baseBuffer ;
        this.fileUtil = fileUtil;
    };
    public AsyncLogging(FileUtil fileUtil, LinkedBlockingQueue<SubstituteLoggingEvent> queue){
        this.fileUtil = fileUtil;
        this.queue = queue;
    };
    public void append() {
        //去缓冲区里面去取
        String log = baseBuffer.outBuffer();
        System.out.println(log);
        try{
            //最后再保存到文件中
            fileUtil.append(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //定时写文件
    @Override
    public void run() {
        while (true){
            while (!queue.isEmpty()){
                try {
                    SubstituteLoggingEvent event = queue.take();
                    String msg = event.getLevel()+"-"+event.getLoggerName()+"-"+event.getMessage();
                    System.out.println(msg);
                    fileUtil.append(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                fileUtil.flush();
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
