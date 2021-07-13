package com.gulyaich.transformationparser.service.transform;

import java.util.List;

import com.gulyaich.transformationparser.model.RawTransformationData;
import com.gulyaich.transformationparser.model.TransformationData;

public interface TransformationService {

    TransformationData transform(List<RawTransformationData> rawData);
}
