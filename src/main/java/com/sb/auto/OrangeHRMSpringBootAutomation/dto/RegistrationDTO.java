package com.sb.auto.OrangeHRMSpringBootAutomation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO
{

    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private String email;
    private String date;
    private String hour;
    private String minute;
    private String mobile;
    private String query;
    private String message;

}
