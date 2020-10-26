package com.racoon.interfaces;

public interface RLocationAwareLogger extends RLogger {
    // these constants should be in EventContants. However, in order to preserve binary backward compatibility
    // we keep these constants here
    final public int TRACE_INT = 00;
    final public int DEBUG_INT = 10;
    final public int INFO_INT = 20;
    final public int WARN_INT = 30;
    final public int ERROR_INT = 40;

    /**
     * Printing method with support for location information.
     *
     * @param marker The marker to be used for this event, may be null.
     * @param fqcn The fully qualified class name of the <b>logger instance</b>,
     * typically the logger class, logger bridge or a logger wrapper.
     * @param level One of the level integers defined in this interface
     * @param message The message for the log event
     * @param t Throwable associated with the log event, may be null.
     */
    public void log(RMarker marker, String fqcn, int level, String message, Object[] argArray, Throwable t);
}
