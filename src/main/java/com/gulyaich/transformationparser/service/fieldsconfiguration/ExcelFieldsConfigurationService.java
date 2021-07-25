package com.gulyaich.transformationparser.service.fieldsconfiguration;

import com.gulyaich.transformationparser.config.properties.excel.ExcelFieldsConfiguration;
import com.gulyaich.transformationparser.config.properties.excel.ExcelProperties;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExcelFieldsConfigurationService
        implements FieldsConfigurationService<ExcelProperties, ExcelFieldsConfiguration> {

    private final ExcelProperties properties;

    public ExcelFieldsConfigurationService(final ExcelProperties properties) {
        this.properties = properties;
    }

    @Override
    public ExcelFieldsConfiguration getConfiguration(final String type) {
        Objects.requireNonNull(type, "Configuration type can not be null");

        return properties.getConfig().get(type.toLowerCase());
    }
}
