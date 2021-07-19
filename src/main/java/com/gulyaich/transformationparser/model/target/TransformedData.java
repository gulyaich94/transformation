package com.gulyaich.transformationparser.model.target;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TransformedData {

    @JsonProperty("Transformation")
    private Transformation transformation;
}
