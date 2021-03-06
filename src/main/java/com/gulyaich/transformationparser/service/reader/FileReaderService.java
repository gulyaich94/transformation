package com.gulyaich.transformationparser.service.reader;

import java.util.List;

import com.gulyaich.transformationparser.config.properties.BaseFieldsConfiguration;
import com.gulyaich.transformationparser.model.raw.RawTransformationData;


public interface FileReaderService<T extends BaseFieldsConfiguration> {

    List<RawTransformationData> read(String fileName, T fieldsConfiguration);
}
