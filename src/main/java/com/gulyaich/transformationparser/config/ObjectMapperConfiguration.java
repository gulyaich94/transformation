package com.gulyaich.transformationparser.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    @Qualifier("yamlObjectMapper")
    public ObjectMapper yamlObjectMapper() {
        final YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false);
        yamlFactory.configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true);
        final ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper;
    }
}
