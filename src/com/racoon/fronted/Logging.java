package com.racoon.fronted;

import com.racoon.util.BaseBuffer;


public class Logging {
    private  static  BaseBuffer baseBuffer;
    public Logging(BaseBuffer baseBuffer){
        this.baseBuffer = baseBuffer;
    };
    public void append(String src){
        baseBuffer.inBuffer(src);
    }
}
