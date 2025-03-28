package com.sb.auto.OrangeHRMSpringBootAutomation.qa;

import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.sb.auto.OrangeHRMSpringBootAutomation.SpringBaseTestNGTest;
import com.sb.auto.OrangeHRMSpringBootAutomation.dto.RegistrationDTO;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.JsonUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.LogUtility;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.ScreenshotUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.qa.RegisterPage;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.qa.TransactionPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

//@Slf4j
//@Epic("Registration Feature")
//@Feature("Valid Registration")
public class RegisterTest extends SpringBaseTestNGTest {

    @Autowired
    private LogUtility logUtility;

    @Value("${log.level}")
    private String logLevel;

    @Autowired
    private RegisterPage registerPage;

    @Autowired
    private TransactionPage transactionPage;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private ScreenshotUtil screenshotUtil;

    @Value("classpath:data/registration.json") // file inside project
    private Resource jsonFile;

    /*private static final Logger log = LogManager.getLogger(RegisterTest.class);

    private static Launch launch;*/

    @Test(priority = 1, testName = "RegisterTest", description = "Registration form for demo site")
    public void registerTest() throws IOException {
        //ReportPortal.emitLog("Manual log test from Java!", "INFO", Calendar.getInstance().getTime());
        RegistrationDTO registrationDTO = jsonUtil.readJsonFile(RegistrationDTO.class, jsonFile);
        this.registerPage.launchApplication();
        this.registerPage.registerForm(registrationDTO);
        this.transactionPage.verifyTitle();
        this.transactionPage.verifySuccessMessage(registrationDTO);
    }

    /*@AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            screenshotUtil.captureScreenshot(result.getName());
        }
    }*/

   /* @BeforeSuite
    public void setup() {
        if (launch == null) {
            launch = ReportPortal.builder().build().newLaunch(null);
            launch.start();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void closeRP() {
        if (launch != null) {
            FinishExecutionRQ finishExecutionRQ = new FinishExecutionRQ();
            finishExecutionRQ.setEndTime(new Date());  // Set the end time for the execution
            launch.finish(finishExecutionRQ);
        }
    }*/

}
