package com.itenlee.search.analysis.help;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.SpecialPermission;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * @author tenlee
 * @date 2020/6/4
 */
public class JSONUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T parseJSON(String json, Class<T> valueType) throws PrivilegedActionException {
        SpecialPermission.check();
        return AccessController.doPrivileged((PrivilegedExceptionAction<T>) () -> mapper.readValue(json, valueType));
    }

    public static <T> T parseJSON(String json, TypeReference<T> valueTypeRef) throws PrivilegedActionException {
        SpecialPermission.check();
        return AccessController.doPrivileged((PrivilegedExceptionAction<T>) () -> mapper.readValue(json, valueTypeRef));
    }
}

