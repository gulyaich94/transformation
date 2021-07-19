package com.gulyaich.transformationparser.service.transformationresult;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.model.target.Mapping;
import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.model.result.TransformationResult;
import com.gulyaich.transformationparser.model.target.TransformedData;

@Service
public class TransformationResultServiceImpl implements TransformationResultService {

    @Override
    public TransformationResult getResults(final List<RawTransformationData> rawData,
                                           final TransformedData transformedData) {
        Objects.requireNonNull(rawData, "Raw data list should not be null");
        Objects.requireNonNull(transformedData, "Transformed data should not be null");

        return TransformationResult.builder()
                .rawDataSize(rawData.size())
                .transformedDataSize(transformedData.getTransformation().getMapping().size())
                .unmappedFieldsTargets(this.getUnmappedFields(rawData, transformedData))
                .build();
    }

    private List<String> getUnmappedFields(final List<RawTransformationData> rawData,
                                           final TransformedData transformedData) {
        if (transformedData.getTransformation() == null ||
                transformedData.getTransformation().getMapping() == null) {
            return Collections.emptyList();
        }

        final List<String> mappedFields =
                transformedData.getTransformation().getMapping().stream()
                        .map(Mapping::getTarget)
                        .collect(Collectors.toList());
        return rawData.stream()
                .map(RawTransformationData::getTarget)
                .filter(f -> !mappedFields.contains(f))
                .collect(Collectors.toList());


    }
}
