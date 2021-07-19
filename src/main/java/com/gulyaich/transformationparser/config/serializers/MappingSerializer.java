package com.gulyaich.transformationparser.config.serializers;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.gulyaich.transformationparser.model.target.Mapping;
import com.gulyaich.transformationparser.model.target.annotations.QuotedValue;

import lombok.SneakyThrows;

public class MappingSerializer extends JsonSerializer<Mapping> {

    private final boolean defaultMinimizeQuotes;

    public MappingSerializer(boolean defaultMinimizeQuotes) {
        this.defaultMinimizeQuotes = defaultMinimizeQuotes;
    }

    @SneakyThrows
    @Override
    public void serialize(Mapping value, JsonGenerator gen, SerializerProvider serializers) {
        final YAMLGenerator yamlGenerator = (YAMLGenerator) gen;

        yamlGenerator.writeStartObject();
        for (final Field field : value.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            final Object fieldValue = field.get(value);
            if (fieldValue != null) {
                final String serializableName = this.getSerializableName(field);

                yamlGenerator.configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, this.isMinimizeQuotes(field));
                yamlGenerator.writeObjectField(serializableName, fieldValue);
                yamlGenerator.configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, defaultMinimizeQuotes);
            }
        }

        yamlGenerator.writeEndObject();
    }

    private boolean isMinimizeQuotes(final Field field) {
        return !field.isAnnotationPresent(QuotedValue.class);
    }

    private String getSerializableName(final Field field) {
        String serializableName;

        if (field.isAnnotationPresent(JsonProperty.class)) {
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            serializableName = jsonProperty.value();
        } else {
            serializableName = field.getName();
        }

        return serializableName;
    }
}