package com.gulyaich.transformationparser.model.target;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("Source")
    private String source;

    @JsonProperty("SourceType")
    private String sourceType;

    @JsonProperty("Target")
    private String target;

    @JsonProperty("TargetType")
    private String targetType;

    @JsonProperty("Nullable")
    private Boolean nullable;

    @JsonProperty("Transform")
    private List<Object> transform;
}
