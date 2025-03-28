package com.sb.auto.OrangeHRMSpringBootAutomation.page.orangehrm;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.Page;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.SeleniumCoreUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.page.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class DashboardPage extends Base {

    @Autowired
    private SeleniumCoreUtil seleniumCoreUtil;

    @Autowired
    private WebDriver driver;

    /**
     * @return
     */
    @Override
    public boolean isLandingPageAt() {
        return false;
    }

    public void leftNavigationMenu(String menuName)
    {
        String menuElementXpath = seleniumCoreUtil.getDynamicElement("(//span[normalize-space(.)='%s'])[1]",menuName);
        WebElement menu = seleniumCoreUtil.findElement(By.xpath(menuElementXpath));
        menu.click();
    }
}
