package com.gulyaich.transformationparser.config.excel;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ExcelSheetsProperties.class)
public class ExcelMappingConfiguration {

}
