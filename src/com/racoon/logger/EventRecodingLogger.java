package com.racoon.logger;

import com.racoon.enums.RLevel;
import com.racoon.events.SubstituteLoggingEvent;
import com.racoon.interfaces.RLogger;
import com.racoon.interfaces.RMarker;

import java.util.Queue;

public class EventRecodingLogger implements RLogger {
    String name;
    SubstituteLogger logger;
    Queue<SubstituteLoggingEvent> eventQueue;
    public EventRecodingLogger(SubstituteLogger logger, Queue<SubstituteLoggingEvent> eventQueue){
        this.logger =logger;
        this.name = logger.getName();
        this.eventQueue = eventQueue;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void trace(String msg) {
        recordEvent_0Args(RLevel.TRACE, null, msg, null);
    }

    @Override
    public void debug(String msg) {
        recordEvent_0Args(RLevel.DEBUG, null, msg, null);
    }

    @Override
    public void info(String msg) {
        recordEvent_0Args(RLevel.INFO, null, msg, null);
    }

    @Override
    public void warn(String msg) {
        recordEvent_0Args(RLevel.WARN, null, msg, null);
    }

    @Override
    public void error(String msg) {
        recordEvent_0Args(RLevel.ERROR, null, msg, null);
    }

    private void recordEvent_0Args(RLevel level, RMarker marker, String msg, Throwable t) {
        recordEvent(level, marker, msg, null, t);
    }
    private void recordEvent(RLevel level, RMarker marker, String msg, Object[] args, Throwable throwable) {
        SubstituteLoggingEvent loggingEvent = new SubstituteLoggingEvent();
        loggingEvent.setTimeStamp(System.currentTimeMillis());
        loggingEvent.setLevel(level);
        loggingEvent.setLogger(logger);
        loggingEvent.setLoggerName(name);
        loggingEvent.setMarker(marker);
        loggingEvent.setMessage(msg);
        loggingEvent.setThreadName(Thread.currentThread().getName());

        loggingEvent.setArgArray(args);
        loggingEvent.setThrowable(throwable);

        eventQueue.add(loggingEvent);
    }
}
