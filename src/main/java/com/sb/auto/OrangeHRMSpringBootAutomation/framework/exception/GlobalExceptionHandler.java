package com.sb.auto.OrangeHRMSpringBootAutomation.framework.exception;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex)
    {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", ex.getStatusCode());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("response", ex.getResponseBody());
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex)
    {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", ApiConstants.STATUS_INTERNAL_SERVER_ERROR);
        errorDetails.put("message", "An unexpected error occurred");
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
