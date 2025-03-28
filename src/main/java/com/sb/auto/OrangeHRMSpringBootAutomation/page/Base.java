package com.sb.auto.OrangeHRMSpringBootAutomation.page;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


public abstract class Base {

    @Lazy
    @Autowired
    public WebDriver webDriver;

    @Autowired
    public WebDriverWait webDriverWait;

    @PostConstruct
    public void init()
    {
        PageFactory.initElements(this.webDriver,this);
    }

    public abstract boolean isLandingPageAt();

}
