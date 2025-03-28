package com.sb.auto.OrangeHRMSpringBootAutomation.framework.util;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class APIAuthUtils {

    @Value("${api.base.url.other}")
    private String baseUrl;

    @Value("${api.auth.endpoint}")
    private String authEndpoint;

    @Value("${api.auth.username}")
    private String username;

    @Value("${api.auth.password}")
    private String password;

    private static String jwtToken;

    @Value("classpath:data/api/loginapi.json") // file inside project
    private Resource loginapiJsonFile;

    public String getBasicAuthToken() {
        String credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    public String getJwtToken()
    {
        if (jwtToken == null)
        {
            jwtToken = fetchToken();
        }
        return jwtToken;
    }

    private String fetchToken()
    {
        // Creating request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        // Sending authentication request
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .body(requestBody.toString())
                .when()
                .post(authEndpoint)
                .then()
                .extract().response();

        // Extract JWT token from response
        if (response.getStatusCode() == ApiConstants.STATUS_OK) {
            return response.jsonPath().getString("token");
        } else {
            throw new RuntimeException("Failed to fetch JWT token. Status: " + response.getStatusCode());
        }
    }
}
