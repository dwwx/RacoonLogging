package com.racoon.factory;

import com.racoon.interfaces.ILoggerFactory;
import com.racoon.interfaces.RLogger;

public class NOPLoggerFactory implements ILoggerFactory {
    @Override
    public RLogger getLogger(String name) {
        return null;
    }
}
