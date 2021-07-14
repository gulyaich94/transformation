package com.gulyaich.transformationparser.config.excel;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ConfigurationProperties(prefix = "excel")
@Getter
public class ExcelSheetsProperties {

    private final Map<String, ExcelSheetConfig> config;

    @ConstructorBinding
    public ExcelSheetsProperties(Map<String, ExcelSheetConfig> config) {
        this.config = config;
    }

    @Getter
    @Validated
    @AllArgsConstructor
    public static final class ExcelSheetConfig {
        @NotNull
        private final Integer sheet;
        @NotNull
        private final Integer source;
        @NotNull
        private final Integer sourceType;
        @NotNull
        private final Integer target;
        @NotNull
        private final Integer targetType;
        @NotNull
        private final Integer nullable;
        @NotNull
        private final Integer transform;
    }
}