package com.gulyaich.transformationparser.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@JsonPropertyOrder({ "source", "sourceType", "target", "targetType", "nullable", "transform" })
public class Mapping {

    private String source;
    private String sourceType;
    private String target;
    private String targetType;
    private Boolean nullable;
    private String transform;
}
