package com.gulyaich.transformationparser.service.transformationresult;

import java.util.List;

import com.gulyaich.transformationparser.model.RawTransformationData;
import com.gulyaich.transformationparser.model.TransformationResult;
import com.gulyaich.transformationparser.model.TransformedData;

public interface TransformationResultService {

    TransformationResult getResults(List<RawTransformationData> rawData, TransformedData transformedData);
}
