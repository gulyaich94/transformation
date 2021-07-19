package com.gulyaich.transformationparser.service.transform;

import java.util.List;

import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.model.target.TransformedData;

public interface TransformationService {

    TransformedData transform(List<RawTransformationData> rawData);
}
