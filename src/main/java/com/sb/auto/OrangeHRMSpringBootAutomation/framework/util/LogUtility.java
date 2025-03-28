package com.sb.auto.OrangeHRMSpringBootAutomation.framework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LogUtility {

    private static final Logger logger = LogManager.getLogger(LogUtility.class);

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message) {
        logger.error(message);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }
}
