package com.sb.auto.OrangeHRMSpringBootAutomation.framework.annotation;


import java.lang.annotation.*;
//@Lazy
//@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Page // above 3 included in custom Page annotation
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Window {
}
