package com.sb.auto.OrangeHRMSpringBootAutomation.framework.config;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.LazyConfiguration;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

// @Lazy : commented because we have created custom annotation LazyConfiguration and adding on that.
// @Configuration : commented because  we have created  custom annotation LazyConfiguration and adding on that.
@LazyConfiguration
@Profile("!remote")
public class WebDriverConfig {

    WebDriver driver;

    //@Primary // it will execute this method as a primary
    //@Scope("browserscope")
    @Scope
    @Bean
    //@ThreadScopeBean  // if we use our custom annotation then no need to add @Bean and @Scope("browserscope") as we have already add on custom annotation
    //@ConditionalOnMissingBean // if no bean is found for parameter pass to run using mvn command then by default it execute this method
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    //@Bean
    //@Scope("singleton")
    public WebDriver chromeDriver()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    //@Scope("browserscope")
    @Scope
    @Bean
    //@ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public WebDriver edgeDriver()
    {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
        //return new EdgeDriver();
    }

    //@ThreadScopeBean
    //@Scope("thread")
    //@Scope("browserscope")
    @Scope
    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
