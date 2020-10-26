package com.racoon.backend;

import com.racoon.util.BaseBuffer;
import com.racoon.util.FileUtil;

public class AsyncLogging {
    private  static BaseBuffer baseBuffer;
    private FileUtil fileUtil;
    public AsyncLogging(FileUtil fileUtil, BaseBuffer baseBuffer){
        this.baseBuffer = baseBuffer ;
        this.fileUtil = fileUtil;
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
}
