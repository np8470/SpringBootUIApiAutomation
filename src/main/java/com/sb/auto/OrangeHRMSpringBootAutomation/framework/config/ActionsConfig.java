package com.sb.auto.OrangeHRMSpringBootAutomation.framework.config;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation.LazyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@LazyConfiguration
public class ActionsConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Actions actions(WebDriver driver) {
        return new Actions(driver);
    }
}
