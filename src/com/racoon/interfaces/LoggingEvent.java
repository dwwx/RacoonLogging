package com.racoon.interfaces;

import com.racoon.enums.RLevel;

public interface LoggingEvent {
    RLevel getLevel();
    RMarker getMarker();
    String getLoggerName();

    String getMessage();

    String getThreadName();

    Object[] getArgumentArray();

    long getTimeStamp();

    Throwable getThrowable();
}
