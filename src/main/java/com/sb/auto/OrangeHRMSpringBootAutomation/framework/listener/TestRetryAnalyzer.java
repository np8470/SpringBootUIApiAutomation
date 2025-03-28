package com.sb.auto.OrangeHRMSpringBootAutomation.framework.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

@Component
public class TestRetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxTry = 2;
    private static final Logger log = LogManager.getLogger(TestRetryAnalyzer.class);

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxTry) {
            count++;
            Reporter.log("🔁 Retrying test: " + iTestResult.getName() + " (" + count + "/" + maxTry + ")", true);
            log.info("🔁 Retrying test: " + iTestResult.getName() + " (" + count + "/" + maxTry + ")");
            return true;
        }
        return false;
    }
}
