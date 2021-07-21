package com.gulyaich.transformationparser.unit.transformation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.model.target.Mapping;
import com.gulyaich.transformationparser.model.target.TransformedData;
import com.gulyaich.transformationparser.service.transform.TransformationService;
import com.gulyaich.transformationparser.service.transform.TransformationServiceImpl;

class TransformationServiceTest {

    private static TransformationService transformationService;

    @BeforeAll
    static void setUp() {
        transformationService = new TransformationServiceImpl();
    }

    @Test
    void transform_mapAllFields_expectedAllFieldsMapped() {
        final List<RawTransformationData> rawDataList =
                Collections.singletonList(
                        RawTransformationData.builder()
                                .source("total_gmv")
                                .sourceType("decimal(38,10)")
                                .target("totalGmv")
                                .targetType("float")
                                .transformType("prefix")
                                .transformValue("TYPE_")
                                .build());

        final TransformedData result = transformationService.transform(rawDataList);

        Assertions.assertEquals(1, result.getTransformation().getMapping().size());

        final Mapping mapping = result.getTransformation().getMapping().get(0);
        Assertions.assertEquals("total_gmv", mapping.getSource());
        Assertions.assertEquals("decimal(38,10)", mapping.getSourceType());
        Assertions.assertEquals("totalGmv", mapping.getTarget());
        Assertions.assertTrue(
                CollectionUtils.isEqualCollection(List.of("prefix", List.of("TYPE_")), mapping.getTransform()));
    }

    @Test
    void transform_shouldNotMapWithoutTarget_expectedEmptyMapping() {
        final List<RawTransformationData> rawDataList =
                Collections.singletonList(
                        RawTransformationData.builder()
                                .source("total_gmv")
                                .sourceType("decimal(38,10)")
                                .target(null)
                                .targetType("float")
                                .transformType("prefix")
                                .transformValue("TYPE_")
                                .build());

        final TransformedData result = transformationService.transform(rawDataList);

        Assertions.assertTrue(CollectionUtils.isEmpty(result.getTransformation().getMapping()));
    }

    @Test
    void transform_throwNullIfDataIsNull_expectedNPE() {
        final List<RawTransformationData> rawDataList = null;
        final String errorMsg = "Data to transform should not be null";

        var expectedException =
                assertThrows(
                        NullPointerException.class,
                        () -> transformationService.transform(rawDataList));

        // then
        assertThat(
                "NullPointerException has (Data should not be null) message",
                expectedException.getMessage(),
                equalTo(errorMsg));
    }
}
