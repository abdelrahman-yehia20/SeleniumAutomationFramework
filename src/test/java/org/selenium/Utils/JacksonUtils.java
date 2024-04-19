package org.selenium.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static <T> T deserializeJson(String filePath, Class<T> T ) throws IOException {
        InputStream Is = JacksonUtils.class.getClassLoader().getResourceAsStream(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Is, T);
    }


    // the not generic method
    /*
    public static BillingAddress deserializeJson(InputStream filePath, BillingAddress billingAddress) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(filePath, billingAddress.getClass());
    }
    */
}
