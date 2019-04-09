package com.agro.wallet.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtils {

    private static final Logger logger = LoggerFactory
        .getLogger(ObjectUtils.class);
    private static ObjectMapper mapper = _getMapper();

    private ObjectUtils() {
        // Prevent reinitialization
    }

    private static ObjectMapper _getMapper() {
        return new ObjectMapper();
    }

    private static ObjectMapper _getMapper2() {
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper;
    }

    /**
     * Returns instance of object mapper
     *
     * @return ObjectMapper instance
     */
    public static ObjectMapper getOMInstance() {
        return mapper;
    }

    /**
     * Give a java class serialize to string
     *
     * @return string serialized object
     */
    public static String getStringJSON(Object data) {
        try {
            ObjectMapper mapper = getOMInstance();
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            // Logger issue
            logger.error("Object Mapper Exceptions: " + e.getMessage());
        }
        return null;
    }

    public static String getStringJSONWithoutNullValues(Object data) {
        try {
            ObjectMapper mapper = _getMapper2();
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            // Logger issue
            logger.error("Object Mapper Exceptions: " + e.getMessage());
        }
        return null;
    }

}
