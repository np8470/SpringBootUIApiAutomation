package com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.lang.annotation.*;

@Lazy
@Autowired
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD}) // TYPE - class level, field - field level, method -method level OR we can set array any of the combination
public @interface LazyAutowired {
}
