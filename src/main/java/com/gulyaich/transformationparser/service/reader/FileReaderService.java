package com.gulyaich.transformationparser.service.reader;

import java.util.List;

import com.gulyaich.transformationparser.config.FieldsConfig;
import com.gulyaich.transformationparser.model.RawTransformationData;


public interface FileReaderService {

    List<RawTransformationData> read(String fileName, String type);

    String getFileFolder();

    FieldsConfig getConfig(String type);
}
