package com.sb.auto.OrangeHRMSpringBootAutomation.page.orangehrm;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.Page;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.SeleniumCoreUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.Base;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

@Page
public class LoginPage extends Base {

    @Autowired
    private SeleniumCoreUtil seleniumCoreUtil;

    @Value("${orangehrm.application.url}")
    private String applicationUrl;

   /* @Value("${default.timeout}")
    private int timeout;

    @Value("${polling.every.time}")
    private int pollingTime;*/

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginBtn;


    public void launchApplication()
    {
        this.webDriver.get(applicationUrl);
        this.seleniumCoreUtil.isDisplay(this.username);
        //this.webDriverWait.until((d) -> this.username.isDisplayed());
        /*this.webDriverWait.withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class)
                .until((d) -> this.username.isDisplayed());*/
    }

    public void loginToApplication(String userName, String pass)
    {
        this.seleniumCoreUtil.sendKeys(this.username, userName);
        this.seleniumCoreUtil.sendKeys(this.password, pass);
        /*this.username.sendKeys(userName);
        this.password.sendKeys(pass);*/
        this.seleniumCoreUtil.click(this.loginBtn);
    }

    @Override
    public boolean isLandingPageAt() {
        return this.webDriverWait.until((d) -> this.username.isDisplayed());
    }
}
