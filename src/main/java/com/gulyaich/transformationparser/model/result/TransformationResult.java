package com.gulyaich.transformationparser.model.result;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class TransformationResult {

    private final List<String> unmappedFieldsTargets;
    private final Integer rawDataSize;
    private final Integer transformedDataSize;
}
