package com.racoon.logger;

import com.racoon.interfaces.RLogger;

public class NOPLogger implements RLogger {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void trace(String msg) {

    }

    @Override
    public void debug(String msg) {

    }

    @Override
    public void info(String mag) {

    }

    @Override
    public void warn(String msg) {

    }

    @Override
    public void error(String msg) {

    }
}
