package com.sb.auto.OrangeHRMSpringBootAutomation.framework.config;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.LazyConfiguration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Clock;
import java.time.Duration;

@LazyConfiguration
public class WebDriverWaitConfig {

    @Value("${default.timeout}")
    private int timeout;

    /*@Value("${polling.every.time}")
    private int pollingTime;*/

   /* @Autowired
    protected WebDriverWait webDriverWait;*/

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait webDriverWait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(this.timeout));
    }

    /*@Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait fluentWait(WebDriver driver)
    {
        return  new WebDriverWait(driver,Duration.ofSeconds(this.timeout)
                , Duration.ofSeconds(this.pollingTime)
                , Clock.systemDefaultZone(),
                Sleeper.SYSTEM_SLEEPER);
    }*/

}
