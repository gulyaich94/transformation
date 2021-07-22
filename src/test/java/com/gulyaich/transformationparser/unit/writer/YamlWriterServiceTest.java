package com.gulyaich.transformationparser.unit.writer;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.gulyaich.transformationparser.config.serializers.MappingSerializer;
import com.gulyaich.transformationparser.model.target.Mapping;
import com.gulyaich.transformationparser.model.target.Transformation;
import com.gulyaich.transformationparser.model.target.TransformedData;
import com.gulyaich.transformationparser.service.writer.FileWriterService;
import com.gulyaich.transformationparser.service.writer.YamlWriterService;

class YamlWriterServiceTest {

    private static FileWriterService fileWriterService;
    private static final String FILE_FOLDER = "src/test/resources/file/";
    private static final String PREFIX = "test";

    @BeforeAll
    static void setUp() {
        fileWriterService = new YamlWriterService(FILE_FOLDER, yamlObjectMapper());
    }

    @AfterAll
    static void deleteFile() {
        getFile().delete();
    }

    @Test
    void writing_writeObject_fileExists() {
        final Mapping mapping =
                Mapping.builder()
                        .source("total_gmv")
                        .sourceType("decimal(38,10)")
                        .target(null)
                        .targetType("float")
                        .transform(List.of("prefix", List.of("TYPE_")))
                        .build();

        final Transformation transformation = new Transformation();
        transformation.setMapping(Collections.singletonList(mapping));

        final TransformedData data = new TransformedData();
        data.setTransformation(transformation);

        fileWriterService.write(data, PREFIX);

        final File file = getFile();
        if (!file.exists()) {
            Assertions.fail("File has not been created");
        }
    }

    private static File getFile() {
        final String filePath =
                String.format("%s%s-result.yaml", FILE_FOLDER, StringUtils.lowerCase(PREFIX));
        return new File(filePath);
    }

    private static ObjectMapper yamlObjectMapper() {
        final YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false);
        yamlFactory.configure(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR, true);
        final ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final SimpleModule module = new SimpleModule();
        module.addSerializer(Mapping.class, new MappingSerializer(true));
        mapper.registerModule(module);

        return mapper;
    }
}
