package com.gulyaich.transformationparser.service.transformationresult;

import java.util.List;

import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.model.result.TransformationResult;
import com.gulyaich.transformationparser.model.target.TransformedData;

public interface TransformationResultService {

    TransformationResult getResults(List<RawTransformationData> rawData, TransformedData transformedData);
}
