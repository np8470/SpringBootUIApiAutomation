package com.sb.auto.OrangeHRMSpringBootAutomation.framework.util;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.exception.ApiException;
import io.restassured.RestAssured;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestAssuredUtils {

    @Value("${api.base.url}")
    private String baseURL;

    @Value("${api.base.url.other}")
    private String baseUrl;

    @Autowired
    private APIAuthUtils apiAuthUtils;

    private void validateResponse(Response response)
    {
        int statusCode = response.getStatusCode();
        if (statusCode >= 400) {
            throw new ApiException(statusCode, "API Request Failed", response.getBody().asString());
        }
    }

    public Response getRequest(String endpoint)
    {
        Response response =  RestAssured.given()
                .baseUri(baseURL)
                .headers(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .log().all()                    // Logs the request details
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();       // Logs the response details

        validateResponse(response);
        return response;
    }

    public Response getRequest(String endpoint, Map<String, String> headers)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .headers(headers)
                .log().all()                    // Logs the request details
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();         // Logs the response details

        validateResponse(response);
        return response;
    }

    public Response postRequest(String endpoint, Object body)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .headers(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    public Response postRequest(String endpoint, Object body, Map<String, String> headers)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    public Response putRequest(String endpoint, Object body)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    public Response putRequest(String endpoint, Object body, Map<String, String> headers)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    public Response deleteRequest(String endpoint)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    public Response deleteRequest(String endpoint, Map<String, String> headers)
    {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .headers(headers)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    // GET request with JWT Authentication
    public Response sendGetRequest(String endpoint, Map<String, String> headers) {
        /*Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiAuthUtils.getJwtToken());
        headers.put("Accept","application/json");*/

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .headers(headers)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    // POST request with JWT Authentication
    public Response sendPostRequest(String endpoint, Object body, Map<String, String> headers) {
        /*Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiAuthUtils.getJwtToken());
        headers.put("Content-Type", "application/json");
        headers.put("Accept","application/json");*/

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    // PUT request with JWT Authentication
    public Response sendPutRequest(String endpoint, Object body, Map<String, String> headers) {
        /*Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiAuthUtils.getJwtToken());
        headers.put("Content-Type", "application/json");
        headers.put("Accept","application/json");*/

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }

    // DELETE request with JWT Authentication
    public Response sendDeleteRequest(String endpoint, Map<String, String> headers) {
        /*Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiAuthUtils.getJwtToken());*/

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .headers(headers)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();

        validateResponse(response);
        return response;
    }
}
