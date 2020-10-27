package com.racoon.test;

import com.racoon.interfaces.RLogger;
import com.racoon.interfaces.RLoggerFactory;

public class TestLogger {
    private static final RLogger logger = RLoggerFactory.getLogger(sample.class);
    public static void main(String[] args) {
        while (true){
            logger.info("aaaaa");
            logger.warn("bbbbb");
            logger.debug("ccccc");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
