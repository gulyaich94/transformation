package com.gulyaich.transformationparser.model.target;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Transformation {

    @JsonProperty("Mapping")
    private List<Mapping> mapping;
}
