package com.gulyaich.transformationparser.config.excel;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import com.gulyaich.transformationparser.config.FieldsConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ConfigurationProperties(prefix = "excel")
@Getter
@Validated
public class ExcelSheetsProperties {

    @NotNull(message = "config must not be null")
    private final Map<String, ExcelSheetConfig> config;

    @ConstructorBinding
    public ExcelSheetsProperties(Map<String, ExcelSheetConfig> config) {
        this.config = config;
    }

    @Getter
    @Validated
    @AllArgsConstructor
    public static final class ExcelSheetConfig extends FieldsConfig {
        @NotNull(message = "sheet number must not be null")
        private final Integer sheet;
        @NotNull(message = "source number must not be null")
        private final Integer source;
        @NotNull(message = "sourceType number must not be null")
        private final Integer sourceType;
        @NotNull(message = "target number must not be null")
        private final Integer target;
        @NotNull(message = "targetType number must not be null")
        private final Integer targetType;
        @NotNull(message = "nullable number must not be null")
        private final Integer nullable;
        @NotNull(message = "transform number must not be null")
        private final Integer transform;
    }
}