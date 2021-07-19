package com.gulyaich.transformationparser.service.transform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.model.target.Mapping;
import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.model.target.Transformation;
import com.gulyaich.transformationparser.model.target.TransformedData;
import com.gulyaich.transformationparser.utils.TransformationUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransformationServiceImpl implements TransformationService {

    @Override
    public TransformedData transform(final List<RawTransformationData> rawData) {
        Objects.requireNonNull(rawData, "Data to transform should not be null");

        final TransformedData transformedData = new TransformedData();
        final Transformation transformation = new Transformation();
        final List<Mapping> mappings = new ArrayList<>();
        transformation.setMapping(mappings);
        transformedData.setTransformation(transformation);

        for (final RawTransformationData item : rawData) {
            final String source = TransformationUtils.getNonEmptyStringOrNull(item.getSource());
            final String sourceType = TransformationUtils.getNonEmptyStringOrNull(item.getSourceType());
            final String target = TransformationUtils.getNonEmptyStringOrNull(item.getTarget());
            final String targetType = TransformationUtils.getNonEmptyStringOrNull(item.getTargetType());

            if (source == null || sourceType == null || target == null || targetType == null) {
                continue;
            }

            final Mapping mapping = Mapping.builder()
                    .source(source)
                    .sourceType(sourceType)
                    .target(target)
                    .targetType(targetType)
                    .nullable(item.getNullable())
                    .transform(this.createTransformValue(item))
                    .build();

            mappings.add(mapping);
        }

        return transformedData;
    }

    private List<Object> createTransformValue(final RawTransformationData item) {
        final String transformType = item.getTransformType();
        final String transformValue = item.getTransformValue();

        if (StringUtils.isAnyEmpty(transformType, transformValue)) {
            return null;
        }

        final List<Object> valuesList = Arrays.asList(transformValue.split(" "));
        return Arrays.asList(transformType, valuesList);
    }
}
