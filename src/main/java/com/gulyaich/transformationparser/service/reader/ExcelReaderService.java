package com.gulyaich.transformationparser.service.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.config.properties.excel.ExcelFieldsConfiguration;
import com.gulyaich.transformationparser.exception.TransformationException;
import com.gulyaich.transformationparser.model.raw.RawTransformationData;
import com.gulyaich.transformationparser.utils.ExcelUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelReaderService implements FileReaderService<ExcelFieldsConfiguration> {

    private final String fileFolder;

    @Autowired
    public ExcelReaderService(@Value("${reader.file.folderPath:}") final String fileFolder) {
        this.fileFolder = fileFolder;
    }

    @Override
    public List<RawTransformationData> read(final String fileName, final ExcelFieldsConfiguration fieldsConfiguration) {
        Objects.requireNonNull(fileName, "File name is null");
        Objects.requireNonNull(fieldsConfiguration, "Fields configuration is null");

        final String filePath = String.format("%s%s", fileFolder, fileName);

        if (!Files.exists(Paths.get(filePath))) {
            throw new IllegalArgumentException("The file does not exist!");
        }

        final int sheetNumber = fieldsConfiguration.getSheet();
        final List<RawTransformationData> data = new ArrayList<>();
        try (final FileInputStream file = new FileInputStream(filePath);
             final Workbook workbook = new XSSFWorkbook(file)) {
            final Sheet sheet = workbook.getSheetAt(sheetNumber);
            for (final Row row : sheet) {
                if (row.getRowNum() == 0 || ExcelUtils.isRowEmpty(row)) {
                    continue;
                }

                data.add(this.getRawTransformationData(row, fieldsConfiguration));
            }

        } catch (final IOException ex) {
            log.error("Can't read data", ex);
            throw new TransformationException("Can't read data", ex);
        }

        log.debug("RawData {}", data);
        return data;
    }

    private RawTransformationData getRawTransformationData(final Row row,
                                                           final ExcelFieldsConfiguration fieldsConfiguration) {
        return RawTransformationData.builder()
                .source(getStringValue(row, fieldsConfiguration.getSource()))
                .sourceType(getStringValue(row, fieldsConfiguration.getSourceType()))
                .target(getStringValue(row, fieldsConfiguration.getTarget()))
                .targetType(getStringValue(row, fieldsConfiguration.getTargetType()))
                .nullable(getBooleanValue(row, fieldsConfiguration.getNullable()))
                .transformType(getStringValue(row, fieldsConfiguration.getTransformType()))
                .transformValue(getStringValue(row, fieldsConfiguration.getTransformValue()))
                .build();
    }

    private Boolean getBooleanValue(final Row row, final int nullable) {
        return row.getCell(nullable) == null ? null : row.getCell(nullable).getBooleanCellValue();
    }

    private String getStringValue(final Row row, final int i) {
        return row.getCell(i) == null ? null : row.getCell(i).getStringCellValue();
    }
}
