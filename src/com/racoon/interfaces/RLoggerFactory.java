package com.racoon.interfaces;

import com.racoon.factory.NOPLoggerFactory;
import com.racoon.factory.SubstituteLoggerFactory;


public final class RLoggerFactory {
    static final int UNINITIALIZED = 0;
    static final int ONGOING_INITIALIZATION = 1;
    static final int FAILED_INITIALIZATION = 2;
    static final int SUCCESSFUL_INITIALIZATION = 3;
    static final int NOP_FALLBACK_INITIALIZATION = 4;

    static volatile int INITIALIZATION_STATE = UNINITIALIZED;

    static final SubstituteLoggerFactory SUBST_FACTORY = new SubstituteLoggerFactory();
    static final NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();

    private RLoggerFactory(){}
    public static RLogger getLogger(Class<?> clazz){
        RLogger logger = getLogger(clazz.getName());
        return logger;
    }
    public static RLogger getLogger(String name){
        ILoggerFactory iLoggerFactory = getILoggerFactory();
        return iLoggerFactory.getLogger(name);
    }
    //获取factory的对象
    public static ILoggerFactory getILoggerFactory(){
        if(INITIALIZATION_STATE == UNINITIALIZED){
            synchronized (RLoggerFactory.class){
                if(INITIALIZATION_STATE == UNINITIALIZED){
                    INITIALIZATION_STATE = ONGOING_INITIALIZATION;
                }
            }
        }
        switch (INITIALIZATION_STATE){
            case SUCCESSFUL_INITIALIZATION:
                return null;
            case NOP_FALLBACK_INITIALIZATION:
                return NOP_FALLBACK_FACTORY;
            case ONGOING_INITIALIZATION:
                return SUBST_FACTORY;
        }
        throw new IllegalStateException("Unreachable code");
    }
}
