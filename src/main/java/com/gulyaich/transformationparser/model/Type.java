package com.gulyaich.transformationparser.model;

public enum Type {

    STRING, BOOLEAN, INT;

    public Type fromString(final String source) {
        for (final Type value : Type.values()) {
            if (value.toString().equalsIgnoreCase(source)) {
                return value;
            }
        }

        throw new IllegalArgumentException(String.format("No type like %s", source));
    }
}
