package com.gulyaich.transformationparser.service.writer;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulyaich.transformationparser.exception.TransformationException;
import com.gulyaich.transformationparser.model.target.TransformedData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class YamlWriterService implements FileWriterService<TransformedData> {

    @Value("${writer.file.folder:}")
    private String fileFolder;

    private ObjectMapper mapper;

    @Override
    public void write(final TransformedData obj) {
        try {
            final File file = new File(getFileFolder() + "/result.yaml");
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, obj);
        } catch (IOException ex) {
            log.error("Can not write object {} to yaml", obj, ex);
            throw new TransformationException("Can't read data", ex);
        }
    }

    @Override
    public String getFileFolder() {
        return fileFolder;
    }

    @Override
    @Autowired
    @Qualifier("yamlObjectMapper")
    public void setMapper(final ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
