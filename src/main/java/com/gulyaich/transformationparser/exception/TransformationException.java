package com.gulyaich.transformationparser.exception;

import java.io.IOException;

public class TransformationException extends RuntimeException {

    public TransformationException(String message, IOException ex) {
        super(message, ex);
    }
}
