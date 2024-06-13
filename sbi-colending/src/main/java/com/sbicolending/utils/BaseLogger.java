package com.sbicolending.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BaseLogger {
    public BaseLogger() {
    }
    public static Logger getLogger(Class<?> clazz) {
        Logger logger = null;
        if (logger == null){
            logger = LoggerFactory.getLogger(clazz);
        }
        return logger;
    }
}
