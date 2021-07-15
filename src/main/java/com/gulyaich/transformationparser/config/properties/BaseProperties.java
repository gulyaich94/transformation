package com.gulyaich.transformationparser.config.properties;

import java.util.Map;

public interface BaseProperties<T extends BaseFieldsConfiguration> {

    Map<String, T> getConfig();
}
