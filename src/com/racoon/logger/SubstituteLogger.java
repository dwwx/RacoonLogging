package com.racoon.logger;

import com.racoon.enums.RLevel;
import com.racoon.events.SubstituteLoggingEvent;
import com.racoon.interfaces.RLogger;

import java.util.Queue;

public class SubstituteLogger implements RLogger {
    private final String name;
    private volatile RLogger _delegate;
    private Boolean delegateEventAware;
    private EventRecodingLogger eventRecodingLogger;
    private Queue<SubstituteLoggingEvent> eventQueue;
    private final boolean createdPostInitialization;
    public SubstituteLogger(String name, Queue<SubstituteLoggingEvent> eventQueue,boolean createdPostInitialization){
        this.name = name;
        this.eventQueue = eventQueue;
        this.createdPostInitialization = createdPostInitialization;
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
