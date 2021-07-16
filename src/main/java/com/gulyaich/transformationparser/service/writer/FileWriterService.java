package com.gulyaich.transformationparser.service.writer;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface FileWriterService<T> {

    void write(T obj);

    String getFileFolder();

    void setMapper(ObjectMapper mapper);
}
