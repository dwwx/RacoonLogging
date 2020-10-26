package com.racoon.events;

import com.racoon.enums.RLevel;
import com.racoon.interfaces.LoggingEvent;
import com.racoon.interfaces.RMarker;
import com.racoon.logger.SubstituteLogger;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class SubstituteLoggingEvent implements LoggingEvent {
    RLevel level;
    RMarker marker;
    String loggerName;
    SubstituteLogger logger;
    String threadName;
    String message;
    Object[] argArray;
    long timeStamp;
    Throwable throwable;
    @Override
    public RLevel getLevel() {
        return level;
    }

    public void setLevel(RLevel level) {
        this.level = level;
    }

    public void setMarker(RMarker marker) {
        this.marker = marker;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public void setLogger(SubstituteLogger logger) {
        this.logger = logger;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setArgArray(Object[] argArray) {
        this.argArray = argArray;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public RMarker getMarker() {
        return marker;
    }

    @Override
    public String getLoggerName() {
        return loggerName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getThreadName() {
        return threadName;
    }

    @Override
    public Object[] getArgumentArray() {
        return argArray;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public Throwable getThrowable() {
        return throwable;
    }
}
