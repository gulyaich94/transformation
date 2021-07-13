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
import org.springframework.stereotype.Service;

import com.gulyaich.transformationparser.exception.TransformationException;
import com.gulyaich.transformationparser.model.RawTransformationData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelReaderService implements FileReaderService {

    @Override
    public List<RawTransformationData> read(final String fileName) {
        Objects.requireNonNull(fileName, "File name is null");

        final String filePath = "src/main/resources/file/" + fileName;

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

                final var rawTransformationData = RawTransformationData.builder()
                        .source(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue())
                        .sourceType(row.getCell(4) == null ? null : row.getCell(4).getStringCellValue())
                        .target(row.getCell(8) == null ? null : row.getCell(8).getStringCellValue())
                        .targetType(row.getCell(5) == null ? null : row.getCell(5).getStringCellValue())
                        .nullable(row.getCell(6) == null ? null : row.getCell(6).getBooleanCellValue())
                        .transform(row.getCell(7) == null ? null : row.getCell(7).getStringCellValue())
                        .build();

                data.add(rawTransformationData);
            }

        } catch (final IOException ex) {
            log.error("Can't read data", ex);
            throw new TransformationException("Can't read data", ex);
        }

        log.debug("RawData {}", data);
        return data;
    }
}
