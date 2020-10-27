package com.racoon.factory;

import com.racoon.events.SubstituteLoggingEvent;
import com.racoon.interfaces.ILoggerFactory;
import com.racoon.interfaces.RLogger;
import com.racoon.logger.SubstituteLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class SubstituteLoggerFactory implements ILoggerFactory {
    boolean postInitialization = false;
    final Map<String, SubstituteLogger> loggers = new HashMap<>();
    //每次都用eventQueue去初始化一个logger,这个eventQueue在多个logger中是单独存在的还是共享的
    //每一个logger都在共享这个eventQueue,所以只需要去消费这个eventQueue里面的内容即可
    final LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue = new LinkedBlockingQueue<SubstituteLoggingEvent>();

    @Override
    public RLogger getLogger(String name) {
        SubstituteLogger logger = loggers.get(name);
        if(logger == null){
            logger = new SubstituteLogger(name, eventQueue, postInitialization);
            loggers.put(name, logger);
        }
        return logger;
    }
    public List<String> getLoggerNames() {
        return new ArrayList<String>(loggers.keySet());
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList<SubstituteLogger>(loggers.values());
    }

    public LinkedBlockingQueue<SubstituteLoggingEvent> getEventQueue() {
        return eventQueue;
    }

    public void postInitialization() {
        postInitialization = true;
    }

    public void clear() {
        loggers.clear();
        eventQueue.clear();
    }
}
