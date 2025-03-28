package com.sb.auto.OrangeHRMSpringBootAutomation.api;

import com.sb.auto.OrangeHRMSpringBootAutomation.dto.RegistrationDTO;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.exception.ApiException;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.listener.TestListener;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.service.UserAPIService;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.JsonUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.models.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class UserAPITest extends ApiBase{

    @Autowired
    private UserAPIService userAPIService;

    @Autowired
    private User user;

    @Value("classpath:data/api/user/createuser.json") // file inside project
    private Resource createUserJsonFile;

    @Value("classpath:data/api/user/updateuser.json") // file inside project
    private Resource updateUserJsonFile;

    private static final Logger log = LogManager.getLogger(UserAPITest.class);

    @Autowired
    private JsonUtil jsonUtil;

    @Test
    public void getUserDetailsTest()
    {
        try {
            Response response = userAPIService.getUser("2");
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_OK);
            log.info("Response of GETUser details API {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

    @Test
    public void createNewUserTest() throws IOException {
        /*user.setName("Niraj Patel");
        user.setJob("Automation Architect");*/

        user = jsonUtil.readJsonFile(User.class, createUserJsonFile);

        Map<String, String> headers = Map.of(
                ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON
        );

        //Response response = userAPIService.createUser(user, headers);
        try {
            Response response = userAPIService.createUser(user);
            Assert.assertEquals(response.getStatusCode(),ApiConstants.STATUS_CREATED);
            log.info("Response of CreateUser API {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

     @Test
     public void updateUserTest() throws IOException {
         user = jsonUtil.readJsonFile(User.class, updateUserJsonFile);
         try {
             Response response = userAPIService.updateUser("2", user);
             Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_OK);
             log.info("Response of UpdateUser API {}", response.getBody().asString());
         } catch (ApiException ex) {
             log.error("API Exception Caught: {}", ex.getMessage());
             Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
         }
     }

     @Test
    public void deleteUserTest() throws IOException {
         try {
             Response response = userAPIService.deleteUser("2");
             Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_NO_CONTENT);
             log.info("Response of DeleteUser API {}", response.getBody().asString());
         } catch (ApiException ex) {
             log.error("API Exception Caught: {}", ex.getMessage());
             Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
         }
     }

}
