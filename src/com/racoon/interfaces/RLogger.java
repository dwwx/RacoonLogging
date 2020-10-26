package com.racoon.interfaces;

public interface RLogger {
    final public String ROOT_LOGGER_NAME = "ROOT";

    public String getName();

    public void trace(String msg);

    public void debug(String msg);

    public void info(String mag);

    public void warn(String msg);

    public void error(String msg);
}
