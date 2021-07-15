package com.gulyaich.transformationparser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.config.properties.BaseFieldsConfiguration;
import com.gulyaich.transformationparser.model.RawTransformationData;
import com.gulyaich.transformationparser.model.TransformedData;
import com.gulyaich.transformationparser.service.fieldsconfiguration.FieldsConfigurationService;
import com.gulyaich.transformationparser.service.reader.FileReaderService;
import com.gulyaich.transformationparser.service.transform.TransformationService;
import com.gulyaich.transformationparser.service.writer.FileWriterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransformationFacadeImpl implements TransformationFacade {

    private final FileReaderService fileReader;
    private final FileWriterService fileWriter;
    private final TransformationService transformationService;
    private final FieldsConfigurationService fieldsConfigurationService;

    public TransformationFacadeImpl(final FileReaderService fileReader,
                                    @Qualifier("yamlWriterService") final FileWriterService fileWriter,
                                    final TransformationService transformationService,
                                    @Qualifier("excelFieldsConfigurationService")
                                    final FieldsConfigurationService fieldsConfigurationService) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.transformationService = transformationService;
        this.fieldsConfigurationService = fieldsConfigurationService;
    }

    @Override
    public void doTransformation(final String fileName, final String type) {
        final BaseFieldsConfiguration fieldsConfiguration = fieldsConfigurationService.getConfiguration(type);
        log.info("fieldsConfiguration " + fieldsConfiguration);

        final List<RawTransformationData> rawData = fileReader.read(fileName, fieldsConfiguration);
        log.info("rawData " + rawData);

        final TransformedData transformedData = transformationService.transform(rawData);
        log.info("transformationData " + transformedData);

        fileWriter.write(transformedData);
    }
}
