package com.sb.auto.OrangeHRMSpringBootAutomation.framework.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;

@Component
public class TestMethodListener implements IInvokedMethodListener {

    private static final Logger log = LogManager.getLogger(TestMethodListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        log.info("▶ Before Method: {}", method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        log.info("◀ After Method: {}", method.getTestMethod().getMethodName());
    }
}
