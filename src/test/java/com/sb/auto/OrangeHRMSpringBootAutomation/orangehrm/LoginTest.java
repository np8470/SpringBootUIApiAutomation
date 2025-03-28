package com.sb.auto.OrangeHRMSpringBootAutomation.orangehrm;

import com.sb.auto.OrangeHRMSpringBootAutomation.SpringBaseTestNGTest;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.orangehrm.DashboardPage;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.orangehrm.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class LoginTest extends SpringBaseTestNGTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashboardPage dashboardPage;

    @Test
    public void loginToApplication() throws InterruptedException {
        this.loginPage.launchApplication();
        this.loginPage.loginToApplication("Admin","admin123");

        this.dashboardPage.leftNavigationMenu("Admin");
        Thread.sleep(5000);
    }
}
