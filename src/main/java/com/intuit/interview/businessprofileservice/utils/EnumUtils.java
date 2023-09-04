package com.intuit.interview.businessprofileservice.utils;

import com.intuit.interview.businessprofileservice.exceptions.AppException;

import java.util.HashSet;
import java.util.function.Function;

public final class EnumUtils {

    private EnumUtils() {
    }

    public static <T extends Enum<T>> void validateEnumValueUniqueness(Class<T> tClass, Function<T, String> mapper) {
        if (!tClass.isEnum()) {
            throw new AppException(MessageConstants.THIS_METHOD_MUST_BE_INVOKED_WITH_AN_ENUM_CLASS);
        }

        T[] enumConstants = tClass.getEnumConstants();
        HashSet<String> hashSet = new HashSet<>();
        for (T t : enumConstants) {
            String value = mapper.apply(t);
            if (!hashSet.add(value)) {
                throw new AppException(
                        String.format(MessageConstants.DUPLICATE_VALUE_FOUND_IN_ENUM, value, tClass.getSimpleName())
                );
            }
        }
    }
}
