package com.gulyaich.transformationparser.service.writer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
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

    private final String fileFolder;
    private final ObjectMapper mapper;

    @Autowired
    public YamlWriterService(@Value("${writer.file.folderPath:}") final String fileFolder,
                             @Qualifier("yamlObjectMapper") final ObjectMapper mapper) {
        this.fileFolder = fileFolder;
        this.mapper = mapper;
    }

    @Override
    public void write(final TransformedData obj, final String prefix) {
        try {
            final String filePath =
                    String.format("%s%s-result.yaml", fileFolder, StringUtils.lowerCase(prefix));
            final File file = new File(filePath);
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
