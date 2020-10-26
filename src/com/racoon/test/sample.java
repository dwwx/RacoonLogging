package com.racoon.test;

import com.racoon.backend.AsyncLogging;
import com.racoon.events.SubstituteLoggingEvent;
import com.racoon.fronted.Logging;
import com.racoon.interfaces.RLogger;
import com.racoon.interfaces.RLoggerFactory;
import com.racoon.util.BaseBuffer;
import com.racoon.util.FileUtil;

import java.util.concurrent.LinkedBlockingQueue;

public class sample {
    private static final  RLogger logger = RLoggerFactory.getLogger(sample.class);
    public static void main(String[] args) {
        String fileName = "E:/project/learning/log.txt";
        try{
            FileUtil fileUtil = new FileUtil(fileName);
            BaseBuffer baseBuffer = BaseBuffer.getBaseBufferInstance(20);
            Logging logging = new Logging(baseBuffer);
            String log = "hello world";
            AsyncLogging asyncLogging = new AsyncLogging(fileUtil, baseBuffer);
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<10; i++){
                        logging.append(log);
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<10; i++){
                        asyncLogging.append();
                    }
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            //当两个线程执行完毕之后就关闭文件描述符
            fileUtil.close();
            logger.debug("aaaa");
            System.out.println(logger.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
