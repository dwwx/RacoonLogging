package com.racoon.test;

import com.racoon.backend.AsyncLogging;
import com.racoon.fronted.Logging;
import com.racoon.util.BaseBuffer;
import com.racoon.util.FileUtil;

public class sample {
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
