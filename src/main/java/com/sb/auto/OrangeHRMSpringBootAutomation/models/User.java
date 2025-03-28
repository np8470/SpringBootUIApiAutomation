package com.sb.auto.OrangeHRMSpringBootAutomation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 to 50 characters")
    private String name;

    @NotBlank(message = "Job cannot be empty")
    @Size(min = 3, max = 30, message = "Job should be between 3 to 30 characters")
    private String job;
}
