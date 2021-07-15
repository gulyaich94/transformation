package com.gulyaich.transformationparser.service.fieldsconfiguration;


import com.gulyaich.transformationparser.config.properties.BaseFieldsConfiguration;
import com.gulyaich.transformationparser.config.properties.BaseProperties;

public interface FieldsConfigurationService<T extends BaseProperties<E>, E extends BaseFieldsConfiguration> {

    E getConfiguration(String type);

    void setProperties(T properties);
}
