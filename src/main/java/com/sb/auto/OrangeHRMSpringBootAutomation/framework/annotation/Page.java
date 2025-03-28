package com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // @Scope("prototype") and this both are similar
@Documented
@Retention(RetentionPolicy.RUNTIME) //annotation available at runtime
@Target({ElementType.TYPE}) // TYPE - class level, field - field level, method -method level OR we can set array any of the combination
public @interface Page {
}
