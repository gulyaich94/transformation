package com.gulyaich.transformationparser.service;

import com.gulyaich.transformationparser.model.TransformationResult;

public interface TransformationFacade {

    TransformationResult doTransformation(String fileName, String type);
}
