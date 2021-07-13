package com.gulyaich.transformationparser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RawTransformationData {

    private String source;
    private String sourceType;
    private String target;
    private String targetType;
    private Boolean nullable;
    private String transform;
}
