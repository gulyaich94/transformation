package com.gulyaich.transformationparser.service.transform;

import java.util.List;

import com.gulyaich.transformationparser.model.RawTransformationData;
import com.gulyaich.transformationparser.model.TransformedData;

public interface TransformationService {

    TransformedData transform(List<RawTransformationData> rawData);
}
