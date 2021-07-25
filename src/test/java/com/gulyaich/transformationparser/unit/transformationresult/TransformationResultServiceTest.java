package com.gulyaich.transformationparser.unit.transformationresult;

import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.model.result.TransformationResult;
import com.gulyaich.transformationparser.model.target.Mapping;
import com.gulyaich.transformationparser.model.target.Transformation;
import com.gulyaich.transformationparser.model.target.TransformedData;
import com.gulyaich.transformationparser.service.transformationresult.TransformationResultService;
import com.gulyaich.transformationparser.service.transformationresult.TransformationResultServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransformationResultServiceTest {

    private static TransformationResultService transformationResultService;

    @BeforeAll
    static void setUp() {
        transformationResultService = new TransformationResultServiceImpl();
    }

    @Test
    void getTransformationResult_oneOfTwoFieldsWasNotTransformed_expectedOneOfTwoTransformedFieldsInfo() {
        final List<RawTransformationData> rawData = createRawData();
        final TransformedData transformedData = createTransformedData();

        final TransformationResult result = transformationResultService.getResults(rawData, transformedData);

        Assertions.assertEquals(2, result.getRawDataSize());
        Assertions.assertEquals(1, result.getTransformedDataSize());
        Assertions.assertEquals(List.of("amount"), result.getUnmappedFieldsTargets());
    }


    @Test
    void getTransformationResult_passedNullRawData_expectNPE() {
        final List<RawTransformationData> rawData = null;
        final TransformedData transformedData = createTransformedData();

        var expectedException =
                assertThrows(
                        NullPointerException.class,
                        () -> transformationResultService.getResults(rawData, transformedData));

        assertThat(
                "NullPointerException has (Raw data should not be null) message",
                expectedException.getMessage(),
                equalTo("Raw data list should not be null"));
    }


    @Test
    void getTransformationResult_passedNullTransformedData_expectNPE() {
        final List<RawTransformationData> rawData = createRawData();
        final TransformedData transformedData = null;

        var expectedException =
                assertThrows(
                        NullPointerException.class,
                        () -> transformationResultService.getResults(rawData, transformedData));

        assertThat(
                "NullPointerException has (Raw data should not be null) message",
                expectedException.getMessage(),
                equalTo("Transformed data should not be null"));
    }

    private static List<RawTransformationData> createRawData() {
        return Arrays.asList(
                RawTransformationData.builder()
                        .source("total_gmv")
                        .sourceType("decimal(38,10)")
                        .target("totalGmv")
                        .targetType("float")
                        .transformType("prefix")
                        .transformValue("TYPE_")
                        .build(),
                RawTransformationData.builder()
                        .source(null)
                        .sourceType(null)
                        .target("amount")
                        .targetType("int")
                        .build()
        );
    }

    private static TransformedData createTransformedData() {
        final Mapping mapping =
                Mapping.builder()
                        .source("total_gmv")
                        .sourceType("decimal(38,10)")
                        .target("totalGmv")
                        .targetType("float")
                        .transform(List.of("prefix", List.of("TYPE_")))
                        .build();

        final Transformation transformation = new Transformation();
        transformation.setMapping(Collections.singletonList(mapping));

        final TransformedData data = new TransformedData();
        data.setTransformation(transformation);

        return data;
    }
}
