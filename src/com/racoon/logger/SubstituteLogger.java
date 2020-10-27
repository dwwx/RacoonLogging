package com.racoon.logger;

import com.racoon.backend.AsyncLogging;
import com.racoon.enums.RLevel;
import com.racoon.events.SubstituteLoggingEvent;
import com.racoon.interfaces.RLogger;
import com.racoon.util.FileUtil;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SubstituteLogger implements RLogger {
    private final String name;
    private volatile RLogger _delegate;
    private Boolean delegateEventAware;
    private EventRecodingLogger eventRecodingLogger;
    private LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue;
    private final boolean createdPostInitialization;
    //每一个Logger封装了一个FileUtil
    private FileUtil fileUtil;
    private AsyncLogging asyncLogging;
    public SubstituteLogger(String name, LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue,boolean createdPostInitialization){
        this.name = name;
        this.eventQueue = eventQueue;
        this.createdPostInitialization = createdPostInitialization;
        try {
            this.fileUtil = new FileUtil("E:/project/learning/log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.asyncLogging = new AsyncLogging(fileUtil, eventQueue);
        new Thread(asyncLogging).start();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void trace(String msg) {
        delegate().trace(msg);
    }

    @Override
    public void debug(String msg) {
        delegate().debug(msg);
    }

    @Override
    public void info(String msg) {
        delegate().info(msg);
    }

    @Override
    public void warn(String msg) {
        delegate().warn(msg);
    }

    @Override
    public void error(String msg) {
        delegate().error(msg);
    }

    RLogger delegate(){
        if(_delegate != null)
            return _delegate;
        return getEventRecordingLogger();
    }
    private RLogger getEventRecordingLogger(){
        if(eventRecodingLogger == null)
            return eventRecodingLogger = new EventRecodingLogger(this, eventQueue);
        return eventRecodingLogger;
    }
}
