package com.gulyaich.transformationparser.unit.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gulyaich.transformationparser.config.properties.excel.ExcelFieldsConfiguration;
import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.service.reader.ExcelReaderService;
import com.gulyaich.transformationparser.service.reader.FileReaderService;

class ExcelReaderServiceTest {

    private static FileReaderService fileReaderService;
    private static final String FILE_FOLDER = "src/test/resources/file/";

    @BeforeAll
    static void setUp() {
        fileReaderService = new ExcelReaderService(FILE_FOLDER);
    }

    @Test
    void read_passedNullFileName_expectNPE() {
        final String fileName = null;
        final ExcelFieldsConfiguration excelFieldsConfiguration = createConfig();
        final String errorMsg = "File name is null";

        var expectedException =
                assertThrows(
                        NullPointerException.class,
                        () -> fileReaderService.read(fileName, excelFieldsConfiguration));

        assertThat(
                "NullPointerException has (File name should not be null) message",
                expectedException.getMessage(),
                equalTo(errorMsg));
    }

    @Test
    void read_readObjectFromFile_expectedCorrectObject() {
        final String fileName = "test.xlsx";
        final ExcelFieldsConfiguration excelFieldsConfiguration = createConfig();

        final List<RawTransformationData> result = fileReaderService.read(fileName, excelFieldsConfiguration);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("first_name", result.get(0).getSource());
        Assertions.assertEquals("string", result.get(0).getSourceType());
        Assertions.assertEquals("name", result.get(0).getTarget());
        Assertions.assertEquals("string", result.get(0).getTargetType());
        Assertions.assertEquals(Boolean.FALSE, result.get(0).getNullable());
        Assertions.assertEquals("prefix", result.get(0).getTransformType());
        Assertions.assertEquals("type_", result.get(0).getTransformValue());
    }

    @Test
    void read_passedNullConfig_expectNPE() {
        final String fileName = "test.xlsx";
        final ExcelFieldsConfiguration excelFieldsConfiguration = null;
        final String errorMsg = "Fields configuration is null";

        var expectedException =
                assertThrows(
                        NullPointerException.class,
                        () -> fileReaderService.read(fileName, excelFieldsConfiguration));

        assertThat(
                "NullPointerException has (Fields configuration should not be null) message",
                expectedException.getMessage(),
                equalTo(errorMsg));
    }

    private static ExcelFieldsConfiguration createConfig() {
        return ExcelFieldsConfiguration.builder()
                .sheet(0)
                .source(0)
                .sourceType(1)
                .target(2)
                .targetType(3)
                .nullable(4)
                .transformType(5)
                .transformValue(6)
                .build();
    }
}
