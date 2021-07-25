package com.gulyaich.transformationparser.model.result;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransformationResult {

    private List<String> unmappedFieldsTargets;
    private Integer rawDataSize;
    private Integer transformedDataSize;
}
