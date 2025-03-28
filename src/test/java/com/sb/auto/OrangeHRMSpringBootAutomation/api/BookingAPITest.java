package com.sb.auto.OrangeHRMSpringBootAutomation.api;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.exception.ApiException;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.service.BookingAPIService;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.JsonUtil;
import com.sb.auto.OrangeHRMSpringBootAutomation.models.Booking;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BookingAPITest extends ApiBase{

    @Autowired
    private BookingAPIService bookingAPIService;

    @Autowired
    private Booking booking;

    @Autowired
    private JsonUtil jsonUtil;

    @Value("classpath:data/api/booking/createbooking.json")
    private Resource createBookingJsonFile;

    @Value("classpath:data/api/booking/updatebooking.json")
    private Resource updateBookingJsonFile;

    private static final Logger log = LogManager.getLogger(BookingAPITest.class);

    @Test(priority = 1)
    public void healthCheckTest()
    {
        try {
            Response response = bookingAPIService.healthCheckService();
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_CREATED);
            log.info("A simple health check endpoint to confirm whether the API is up and running. {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

    @Test(priority = 2)
    public void getBookingIDsTest()
    {
        try {
            Response response = bookingAPIService.getBookingIDsService();
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_OK);
            log.info("Response of Get Booking IDs API {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

    @Test(priority = 3)
    public void getBookingTest()
    {
        try {
            Response response = bookingAPIService.getBookingService("1");
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_OK);
            log.info("Response of Get Booking for particular ID API {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

    @Test(priority = 4)
    public void createBookingTest() throws IOException {
        booking = jsonUtil.readJsonFile(Booking.class, createBookingJsonFile);
        try {
            Response response = bookingAPIService.createBookingService(booking);
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_OK);
            Assert.assertNotNull(response.jsonPath().get("bookingid"), "Booking ID should not be null");
            log.info("Response of Create Booking API {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }

    }

    @Test(priority = 5)
    public void updateBookingTest() throws IOException {
        booking = jsonUtil.readJsonFile(Booking.class, updateBookingJsonFile);
        try {
            Response response = bookingAPIService.updateBookingService("1", booking);
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_OK);
            log.info("Response of Update Booking API for particular ID {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

    @Test(priority = 6)
    public void deleteBookingTest()
    {
        try {
            Response response = bookingAPIService.deleteBookingService("1");
            Assert.assertEquals(response.getStatusCode(), ApiConstants.STATUS_CREATED);
            log.info("Response of Delete Booking API for particular ID {}", response.getBody().asString());
        } catch (ApiException ex) {
            log.error("API Exception Caught: {}", ex.getMessage());
            Assert.fail("Test failed due to API exception: " + ex.getResponseBody());
        }
    }

}
