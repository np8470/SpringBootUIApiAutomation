package com.sb.auto.OrangeHRMSpringBootAutomation.page.orangehrm;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.SeleniumCoreUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminPage extends Base {

    @Autowired
    private SeleniumCoreUtil seleniumCoreUtil;

    @FindBy(xpath = "//label[contains(normalize-space(.),'Username')]//parent::div//following-sibling::div//input")
    private WebElement userNameInput;


    public void enterUserName(String username)
    {
        seleniumCoreUtil.sendKeys(userNameInput,username);
    }

    public void selectUserRole()
    {

    }

    /**
     * @return
     */
    @Override
    public boolean isLandingPageAt() {
        return false;
    }


}
