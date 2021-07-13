package com.gulyaich.transformationparser.service.writer;

public interface FileWriterService<T> {

    void write(T obj);
}
