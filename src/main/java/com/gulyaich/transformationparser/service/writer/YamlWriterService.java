package com.gulyaich.transformationparser.service.writer;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulyaich.transformationparser.exception.TransformationException;
import com.gulyaich.transformationparser.model.TransformationData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class YamlWriterService implements FileWriterService<TransformationData> {

    private final ObjectMapper mapper;

    public YamlWriterService(@Qualifier("yamlObjectMapper") ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public void write(final TransformationData obj) {
        try {
            final File file = new File("src/main/resources/file/result.yaml");
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, obj);
        } catch (IOException ex) {
            log.error("Can not write object {} to yaml", obj, ex);
            throw new TransformationException("Can't read data", ex);
        }
    }
}
