package com.store.mgmtAPI.utils;

import org.junit.jupiter.api.DisplayNameGenerator;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static org.springframework.data.util.ParsingUtils.splitCamelCase;

public class CamelCaseDisplayNameGenerator extends DisplayNameGenerator.Standard{

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {

        String methodName = testMethod.getName();
        StringBuilder sb = new StringBuilder();

        sb.append(String.join(" ", splitCamelCase(testMethod.getName())).toLowerCase());

        return sb.toString();
    }
}
