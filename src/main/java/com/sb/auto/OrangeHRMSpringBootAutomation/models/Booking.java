package com.sb.auto.OrangeHRMSpringBootAutomation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Booking {

    //@NotBlank(message = "Firstname cannot be empty")
    //@Size(min = 2, max = 50, message = "Firstname should be between 2 to 50 characters")
    private String firstname;

    //@NotBlank(message = "Lastname cannot be empty")
   // @Size(min = 2, max = 50, message = "Lastname should be between 2 to 50 characters")
    private String lastname;

   // @NotBlank(message = "Totalprice cannot be empty")
   // @Size(min = 2, max = 50, message = "Totalprice should not blank")
    private int totalprice;

   // @NotBlank(message = "Depositpaid cannot be empty")
   // @Size(min = 2, max = 50, message = "Depositpaid should be boolean")
    private boolean depositpaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingdates;
    //@NotBlank(message = "Checkin date cannot be empty")
   // @Size(min = 2, max = 50, message = "Checkin date must be today or future date")
    //private Date checkin;

    //@NotBlank(message = "Checkout date cannot be empty")
   // @Size(min = 2, max = 50, message = "Checkout date must be today or future date")
    //private Date checkout;

   // @NotBlank(message = "Additionalneeds cannot be empty")
   // @Size(min = 2, max = 50, message = "Additionalneeds should be between 2 to 50 characters")
    private String additionalneeds;
}
