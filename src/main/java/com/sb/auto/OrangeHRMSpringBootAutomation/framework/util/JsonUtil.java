package com.sb.auto.OrangeHRMSpringBootAutomation.framework.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();
    public <T> T readJsonFile(Class<T> tClass, Resource jsonFile) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(jsonFile.getInputStream(), tClass);
    }
}
