package com.gulyaich.transformationparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"com.gulyaich.transformationparser.config.properties"})
public class TransformationParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransformationParserApplication.class, args);
    }

}
