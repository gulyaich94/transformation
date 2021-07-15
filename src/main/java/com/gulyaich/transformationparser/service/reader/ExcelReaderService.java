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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.config.excel.ExcelSheetsProperties;
import com.gulyaich.transformationparser.exception.TransformationException;
import com.gulyaich.transformationparser.model.RawTransformationData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelReaderService implements FileReaderService {

    private final ExcelSheetsProperties excelSheetsProperties;

    @Value("${reader.file.folder:}")
    private String fileFolder;

    private static final String DELIMITER = "/";

    public ExcelReaderService(final ExcelSheetsProperties excelSheetsProperties) {
        this.excelSheetsProperties = excelSheetsProperties;
    }

    @Override
    public List<RawTransformationData> read(final String fileName, final String type) {
        Objects.requireNonNull(fileName, "File name is null");

        final ExcelSheetsProperties.ExcelSheetConfig config = this.getConfig(type);
        Objects.requireNonNull(config, String.format("Reader is not configured for type %s", type));

        final String filePath = this.getFileFolder() + DELIMITER + fileName;

        if (!Files.exists(Paths.get(filePath))) {
            throw new IllegalArgumentException("The file does not exist!");
        }

        final List<RawTransformationData> data = new ArrayList<>();
        try (final FileInputStream file = new FileInputStream(filePath);
             final Workbook workbook = new XSSFWorkbook(file)) {
            final Sheet sheet = workbook.getSheetAt(0);
            for (final Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                data.add(this.getRawTransformationData(row));
            }

        } catch (final IOException ex) {
            log.error("Can't read data", ex);
            throw new TransformationException("Can't read data", ex);
        }

        log.debug("RawData {}", data);
        return data;
    }

    private RawTransformationData getRawTransformationData(Row row) {
        return RawTransformationData.builder()
                .source(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue())
                .sourceType(row.getCell(4) == null ? null : row.getCell(4).getStringCellValue())
                .target(row.getCell(8) == null ? null : row.getCell(8).getStringCellValue())
                .targetType(row.getCell(5) == null ? null : row.getCell(5).getStringCellValue())
                .nullable(row.getCell(6) == null ? null : row.getCell(6).getBooleanCellValue())
                .transform(row.getCell(7) == null ? null : row.getCell(7).getStringCellValue())
                .build();
    }

    @Override
    public String getFileFolder() {
        return fileFolder;
    }

    @Override
    public ExcelSheetsProperties.ExcelSheetConfig getConfig(final String type) {
        return excelSheetsProperties.getConfig().get(type);
    }
}
