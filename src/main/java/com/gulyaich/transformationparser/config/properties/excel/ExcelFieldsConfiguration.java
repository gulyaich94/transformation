package com.gulyaich.transformationparser.config.properties.excel;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.gulyaich.transformationparser.config.properties.BaseFieldsConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Validated
@AllArgsConstructor
@ToString
@Builder
public final class ExcelFieldsConfiguration implements BaseFieldsConfiguration {
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
    @NotNull(message = "transformType number must not be null")
    private final Integer transformType;
    @NotNull(message = "transformValue number must not be null")
    private final Integer transformValue;
}
