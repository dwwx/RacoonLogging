package com.racoon.util;

import netscape.security.UserTarget;

import java.nio.CharBuffer;
import java.util.concurrent.LinkedBlockingQueue;

public class BaseBuffer {
    private volatile static BaseBuffer baseBuffer;
    private static LinkedBlockingQueue<String> lbq;
    private BaseBuffer(int size){
        lbq = new LinkedBlockingQueue<>(size);
    }
    public static BaseBuffer getBaseBufferInstance(int size){
        if(baseBuffer == null){
            synchronized (BaseBuffer.class){
                if(baseBuffer == null){
                    return  new BaseBuffer(size);
                }
            }
        }
        return baseBuffer;
    }
    public void inBuffer(String src){
        try {
            lbq.put(src);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String outBuffer(){
        try {
            return lbq.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
