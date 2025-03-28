package com.sb.auto.OrangeHRMSpringBootAutomation.framework.service;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.APIAuthUtils;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.RestAssuredUtils;
import com.sb.auto.OrangeHRMSpringBootAutomation.models.Booking;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookingAPIService {

    @Autowired
    private RestAssuredUtils restAssuredUtils;

    @Autowired
    private APIAuthUtils apiAuthUtils;

    Map<String, String> headers = new HashMap<>();

    public Response healthCheckService()
    {
        return restAssuredUtils.sendGetRequest(ApiConstants.FORWARD_SLASH + ApiConstants.PING, headers);
        //return restAssuredUtils.sendGetRequest("/ping", headers);
    }

    public Response getBookingIDsService()
    {
        //return restAssuredUtils.sendGetRequest("/booking", headers);
        return restAssuredUtils.sendGetRequest(ApiConstants.FORWARD_SLASH + ApiConstants.BOOKING, headers);
    }

    public Response getBookingService(String bookingId)
    {
        //headers.put("Accept","application/json");
        headers.put(ApiConstants.ACCEPT, ApiConstants.APPLICATION_JSON);
        //return restAssuredUtils.sendGetRequest("/booking/" + bookingId, headers);
        return restAssuredUtils.sendGetRequest(ApiConstants.FORWARD_SLASH + ApiConstants.BOOKING + ApiConstants.FORWARD_SLASH + bookingId, headers);
    }

    public Response createBookingService(Booking booking)
    {
        headers.put(ApiConstants.AUTHORIZATION, ApiConstants.BASIC + " " + apiAuthUtils.getJwtToken());
        headers.put(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON);
        headers.put(ApiConstants.ACCEPT,ApiConstants.APPLICATION_JSON);
        //return restAssuredUtils.sendPostRequest("/booking", booking, headers);
        return restAssuredUtils.sendPostRequest(ApiConstants.FORWARD_SLASH + ApiConstants.BOOKING, booking, headers);
    }

    public Response updateBookingService(String bookingId, Booking booking)
    {
        //headers.put("Authorization", "Basic " + apiAuthUtils.getBasicAuthToken());
        headers.put(ApiConstants.AUTHORIZATION, ApiConstants.BASIC + " " + apiAuthUtils.getBasicAuthToken());
        //headers.put("Content-Type", "application/json");
        headers.put(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON);
        //return restAssuredUtils.sendPutRequest("/booking/" + bookingId, booking, headers);
        return restAssuredUtils.sendPutRequest(ApiConstants.FORWARD_SLASH + ApiConstants.BOOKING + ApiConstants.FORWARD_SLASH + bookingId, booking, headers);
    }

    public Response deleteBookingService(String bookingId)
    {
        //headers.put("Authorization", "Basic " + apiAuthUtils.getBasicAuthToken());
        headers.put(ApiConstants.AUTHORIZATION, ApiConstants.BASIC + " " + apiAuthUtils.getBasicAuthToken());
        //headers.put("Content-Type", "application/json");
        headers.put(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON);
        //return restAssuredUtils.sendDeleteRequest("/booking/" + bookingId, headers);
        return restAssuredUtils.sendDeleteRequest(ApiConstants.FORWARD_SLASH + ApiConstants.BOOKING + ApiConstants.FORWARD_SLASH + bookingId, headers);
    }
}
