package com.javaweb.demosb.util;

import com.javaweb.demosb.exception.AuthException;
import com.javaweb.demosb.exception.BusinessException;

public class DataUtils {
    public static boolean isNullOrZero(Integer value) {
        return (value == null || value == 0) ;
    }

    public static boolean isNullOrEmpty(String value) {
        return (value == null || value.isEmpty()) ;
    }

    public static float calculateBMI(Integer weight, Integer height) {
        Float heightInMeters = height / 100.0f;
        return weight / (heightInMeters * heightInMeters);
    }
}
