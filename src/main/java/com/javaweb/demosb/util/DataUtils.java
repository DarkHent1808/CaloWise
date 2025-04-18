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
//    public static void weightValidation(Float weight){
//        if (weight == null || weight <= 0) {
//            throw new BusinessException("Please enter a valid weight");
//        }
//        if (weight > 455) {
//            throw new BusinessException("Weight cannot exceed 455 kg");
//        }
//        if
//    }
}
