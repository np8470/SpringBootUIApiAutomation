package com.sb.auto.OrangeHRMSpringBootAutomation.framework.listener;

import com.epam.reportportal.service.ReportPortal;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.ConfigReaderUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TestListener implements ITestListener, ApplicationContextAware {

    /*@Autowired
    private WebDriver driver; // Inject WebDriver from Spring Boot

    @Autowired
    private ScreenshotUtil screenshotUtil;


    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed onTestFailure method: " + result.getName());
        if (this.driver == null) {
            System.out.println("webdriver is null onTestFailure method");
            screenshotUtil.captureScreenshot(result.getName());
        }
        //this.screenshotUtil.captureScreenshot(result.getName());
    }*/

    /*private final ScreenshotUtil screenshotUtil;

    // Constructor-based dependency injection
    @Autowired
    public TestListener(ScreenshotUtil screenshotUtil) {
        this.screenshotUtil = screenshotUtil;
    }*/

    private static final Map<String, Integer> testSuccessPercentageMap = new HashMap<>();

    private static ApplicationContext applicationContext;
    private ScreenshotUtil screenshotUtil;

    private static final String SCREENSHOT_DIR = "screenshots"; // Change to "target/screenshots" if needed

    private static final Logger log = LogManager.getLogger(TestListener.class);

    // Capture Spring's ApplicationContext
    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    // Manually get ScreenshotUtil from Spring context
    private ScreenshotUtil getScreenshotUtil() {
        if (screenshotUtil == null && applicationContext != null) {
            screenshotUtil = applicationContext.getBean(ScreenshotUtil.class);
        }
        return screenshotUtil;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("❌ Test failed: {}", result.getName());
        ScreenshotUtil screenshotUtil = getScreenshotUtil();
        if (screenshotUtil == null) {
            log.info("❌ ERROR: ScreenshotUtil is NULL in TestListener.");
            return;
        }

        log.info("📸 Attempting to take a screenshot...");
        screenshotUtil.captureScreenshot(result.getName());
        String testName = result.getName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = SCREENSHOT_DIR + "/" + testName + "_" + timestamp + ".png";
        ReportPortal.emitLog("Test failed: " + result.getName(), "ERROR", Calendar.getInstance().getTime(), new File(screenshotPath));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("✅ Test Passed: {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("⚠ Test Skipped: {}", result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("⏳ Test Started: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("🚀 Test Execution Started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("🏁 Test Execution Finished: {}", context.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testClassName = result.getTestClass().getName();
        int successPercentage = getSuccessPercentage(testClassName);

        log.info("⚠️ Test: {}" , result.getName() + " failed but is within {}" , successPercentage + "% success threshold.");
    }

    private int getSuccessPercentage(String testClassName) {
        return testSuccessPercentageMap.getOrDefault(
                testClassName,
                Integer.parseInt(ConfigReaderUtil.getProperty("defaultSuccessPercentage"))
        );
    }
}
