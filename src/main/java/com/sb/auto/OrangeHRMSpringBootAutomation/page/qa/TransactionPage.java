package com.sb.auto.OrangeHRMSpringBootAutomation.page.qa;

import com.sb.auto.OrangeHRMSpringBootAutomation.dto.RegistrationDTO;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.Page;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.SeleniumCoreUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@Page
public class TransactionPage extends Base {

    @Autowired
    private SeleniumCoreUtil seleniumCoreUtil;

    @FindBy(xpath = "//h2[normalize-space()='Transaction Details']")
    private WebElement title;

    @FindBy(id = "messageContainer")
    private WebElement successMsg;

    private static final Logger log = LogManager.getLogger(TransactionPage.class);

    public void verifyTitle()
    {
        this.seleniumCoreUtil.isDisplay(title);
        log.info("Verify title of transaction page");
    }

    public void verifySuccessMessage(RegistrationDTO registrationDTO)
    {
        Assert.isTrue(this.seleniumCoreUtil.getText(successMsg).trim().contains(registrationDTO.getMessage()),"Registration success message");
        log.info("Verify success message");
    }

    /**
     * @return
     */
    @Override
    public boolean isLandingPageAt() {
        return false;
    }
}
