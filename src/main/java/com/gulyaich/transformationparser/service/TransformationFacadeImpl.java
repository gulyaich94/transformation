package com.gulyaich.transformationparser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.model.RawTransformationData;
import com.gulyaich.transformationparser.model.TransformationData;
import com.gulyaich.transformationparser.service.reader.FileReaderService;
import com.gulyaich.transformationparser.service.transform.TransformationService;
import com.gulyaich.transformationparser.service.writer.FileWriterService;

@Service
public class TransformationFacadeImpl implements TransformationFacade {

    private final FileReaderService fileReader;
    private final FileWriterService fileWriter;
    private final TransformationService transformationService;

    public TransformationFacadeImpl(final FileReaderService fileReader,
                                    @Qualifier("yamlWriterService") final FileWriterService fileWriter,
                                    final TransformationService transformationService) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.transformationService = transformationService;
    }

    @Override
    public void doTransformation(final String fileName, final String type) {
        final List<RawTransformationData> rawData = fileReader.read(fileName, type);
        final TransformationData transformationData = transformationService.transform(rawData);
        fileWriter.write(transformationData);
    }
}
