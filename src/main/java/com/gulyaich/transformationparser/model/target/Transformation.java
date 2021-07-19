package com.gulyaich.transformationparser.model.target;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Transformation {

    private List<Mapping> mapping;
}
