package com.racoon.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileUtil {
    private static FileWriter fw;
    private static BufferedWriter bw;
    private String fileName;

    public FileUtil(String fileName) throws IOException {
        this.fileName = fileName;
        fw = new FileWriter(new File(fileName));
        bw = new BufferedWriter(fw);
    }

    public void close() throws IOException {
        //注意关闭的先后顺序
        bw.close();
        fw.close();
    }
    public void flush() throws IOException {
        fw.flush();
        bw.flush();
    }
    public void append(String log)  {
        String dateTime = new Date().toString();
        log = dateTime + ":" +log;
        try{
            bw.write(log+"\t\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
