package com.gulyaich.transformationparser.service.fieldsconfiguration;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.config.properties.excel.ExcelFieldsConfiguration;
import com.gulyaich.transformationparser.config.properties.excel.ExcelProperties;

@Service
public class ExcelFieldsConfigurationService
        implements FieldsConfigurationService<ExcelProperties, ExcelFieldsConfiguration> {

    private ExcelProperties properties;

    @Override
    public ExcelFieldsConfiguration getConfiguration(final String type) {
        Objects.requireNonNull(type, "Configuration type can not be null");

        return properties.getConfig().get(type.toLowerCase());
    }

    @Override
    @Autowired
    public void setProperties(final ExcelProperties properties) {
        this.properties = properties;
    }
}
