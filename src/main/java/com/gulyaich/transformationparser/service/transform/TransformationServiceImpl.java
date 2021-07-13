package com.gulyaich.transformationparser.service.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.model.Mapping;
import com.gulyaich.transformationparser.model.RawTransformationData;
import com.gulyaich.transformationparser.model.Transformation;
import com.gulyaich.transformationparser.model.TransformationData;
import com.gulyaich.transformationparser.utils.TransformationUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransformationServiceImpl implements TransformationService {

    @Override
    public TransformationData transform(final List<RawTransformationData> rawData) {
        Objects.requireNonNull(rawData, "Data to transform should not be null");
        log.info("Raw data size: {}", rawData.size());

        final TransformationData transformationData = new TransformationData();
        final Transformation transformation = new Transformation();
        final List<Mapping> mappings = new ArrayList<>();
        transformation.setMapping(mappings);
        transformationData.setTransformation(transformation);

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
                    .build();

            mappings.add(mapping);
        }
        log.info("Transformed data size: {}", transformationData.getTransformation().getMapping().size());
        return transformationData;
    }
}
