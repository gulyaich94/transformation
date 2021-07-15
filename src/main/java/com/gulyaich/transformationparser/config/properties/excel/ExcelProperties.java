package com.gulyaich.transformationparser.config.properties.excel;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import com.gulyaich.transformationparser.config.properties.BaseProperties;

@ConfigurationProperties(prefix = "excel")
@Validated
public class ExcelProperties implements BaseProperties<ExcelFieldsConfiguration> {

    @NotNull(message = "config must not be null")
    private final Map<String, ExcelFieldsConfiguration> config;

    @ConstructorBinding
    public ExcelProperties(Map<String, ExcelFieldsConfiguration> config) {
        this.config = config;
    }

    public Map<String, ExcelFieldsConfiguration> getConfig() {
        return this.config;
    }
}