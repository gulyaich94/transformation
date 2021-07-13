package com.gulyaich.transformationparser.utils;

public class TransformationUtils {

    private TransformationUtils() {
    }

    public static String getNonEmptyStringOrNull(final String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        return value.trim();
    }

}
