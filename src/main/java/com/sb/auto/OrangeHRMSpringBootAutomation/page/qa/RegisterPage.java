package com.sb.auto.OrangeHRMSpringBootAutomation.page.qa;

import com.sb.auto.OrangeHRMSpringBootAutomation.dto.RegistrationDTO;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.Page;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.JsonUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.SeleniumCoreUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.testng.Assert;

import java.io.IOException;

@Page
public class RegisterPage extends Base {

    @Lazy
    @Autowired
    private SeleniumCoreUtil seleniumCoreUtil;

    @Value("${demo.url}")
    private String applicationUrl;

    @FindBy(id = "vfb-5")
    private WebElement firstName;

    @FindBy(id = "vfb-7")
    private WebElement lastName;

    @FindBy(id = "vfb-31-1")
    private WebElement maleGender;

    @FindBy(id = "vfb-31-2")
    private WebElement femaleGender;

    @FindBy(id = "vfb-20-0")
    private WebElement seleniumCourse;

    @FindBy(id="vfb-20-1")
    private WebElement javaCourse;

    @FindBy(id="vfb-20-3")
    private WebElement devOpsCourse;

    @FindBy(id = "vfb-13-address")
    private WebElement street;

    @FindBy(id = "vfb-13-city")
    private WebElement city;

    @FindBy(id = "vfb-13-country")
    private WebElement country;

    @FindBy(id = "vfb-13-zip")
    private WebElement zipCode;

    @FindBy(id = "vfb-14")
    private WebElement email;

    @FindBy(id = "vfb-18")
    private WebElement dateOfDemo;

    @FindBy(id = "vfb-16-hour")
    private WebElement convenientTimeHH;

    @FindBy(id = "vfb-16-min")
    private WebElement getConvenientTimeMM;

    @FindBy(id = "vfb-19")
    private WebElement mobileNumber;

    @FindBy(id = "vfb-23")
    private WebElement query;

    @FindBy(id = "vfb-3")
    private WebElement code;

    @FindBy(xpath = "//input[@id='vfb-3']//parent::span//label")
    private WebElement codeText;

    @FindBy(id = "vfb-4")
    private WebElement submitBtn;

    @FindBy(xpath = "//h3[normalize-space()='Register For Demo']")
    private WebElement title;

    private static final Logger log = LogManager.getLogger(RegisterPage.class);

    public void launchApplication()
    {
        this.webDriver.get(applicationUrl);
        this.seleniumCoreUtil.isDisplay(this.title);
        log.info("Launch the Application");
    }

    public void verifyTitle()
    {
        seleniumCoreUtil.isDisplay(title);
    }

    public void registerForm(RegistrationDTO registrationDTO) throws IOException {
        //Assert.assertFalse(true);
        seleniumCoreUtil.sendKeys(this.firstName, registrationDTO.getFirstname());
        seleniumCoreUtil.sendKeys(this.lastName, registrationDTO.getLastname());
        seleniumCoreUtil.selectRadioButton(this.maleGender);
        seleniumCoreUtil.selectCheckBox(this.seleniumCourse, true);
        seleniumCoreUtil.selectCheckBox(this.javaCourse, true);
        seleniumCoreUtil.selectCheckBox(this.devOpsCourse, false);
        seleniumCoreUtil.sendKeys(street, registrationDTO.getStreet());
        seleniumCoreUtil.sendKeys(city, registrationDTO.getCity());
        seleniumCoreUtil.selectDropdownByVisibleText(country, registrationDTO.getCountry());
        seleniumCoreUtil.sendKeys(zipCode, registrationDTO.getPostcode());
        seleniumCoreUtil.sendKeys(email, registrationDTO.getEmail());
        seleniumCoreUtil.enterTextUsingJS(dateOfDemo, registrationDTO.getDate());
        seleniumCoreUtil.selectDropdownByValue(convenientTimeHH, registrationDTO.getHour());
        seleniumCoreUtil.selectDropdownByVisibleText(getConvenientTimeMM,registrationDTO.getMinute());
        seleniumCoreUtil.sendKeys(mobileNumber, registrationDTO.getMobile());
        seleniumCoreUtil.sendKeys(query, registrationDTO.getQuery());
        String verificationCodeText = seleniumCoreUtil.getText(codeText);
        String verificationCode = verificationCodeText.substring(9,11);
        System.out.println("Verification code:: "+verificationCode);
        seleniumCoreUtil.sendKeys(code,verificationCode);
        seleniumCoreUtil.click(submitBtn);
        log.info("Registration completed");
    }

    /**
     * @return
     */
    @Override
    public boolean isLandingPageAt() {
        return false;
    }
}
