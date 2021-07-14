package com.gulyaich.transformationparser.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.gulyaich.transformationparser.config.excel.ExcelSheetsProperties;

@Configuration
@EnableConfigurationProperties(ExcelSheetsProperties.class)
public class ExcelMappingConfiguration {

}
