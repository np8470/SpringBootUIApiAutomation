package com.sb.auto.OrangeHRMSpringBootAutomation.api;

import com.sb.auto.OrangeHRMSpringBootAutomation.SpringBaseTestNGTest;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.config.ApiConfig;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.RestAssuredUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;

public class ApiBase extends SpringBaseTestNGTest {

    @Autowired
    protected ApiConfig apiConfig;

    @Autowired
    protected RestAssuredUtils restAssuredUtils;

    @BeforeClass
    public void setUp()
    {
        System.out.println("API Base URL: " + apiConfig.getBaseURL());
    }
}
