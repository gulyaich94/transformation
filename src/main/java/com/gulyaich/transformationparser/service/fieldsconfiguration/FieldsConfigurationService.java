package com.gulyaich.transformationparser.service.fieldsconfiguration;


import com.gulyaich.transformationparser.config.properties.BaseFieldsConfiguration;
import com.gulyaich.transformationparser.config.properties.BaseProperties;

public interface FieldsConfigurationService<T extends BaseProperties<R>, R extends BaseFieldsConfiguration> {

    R getConfiguration(String type);
}
