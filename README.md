# Excel to yaml transformation for ETLite

## Overview

This application can help you to generate ETLite transformation files parsed from Excel files.

## Settings

You need to define some settings in application.yaml file

1. `reader.file.folderPath` - path to folder with the source file
2. `writer.file.folderPath` - path to folder where the result file will be stored
3. Some properties for Excel reader. You should define you object type and specify sheet number and number of columns
   with fields.

### Settings example:

You have transformation objects named Customer and Reseller.

```yaml
excel:
  config:
    customer: # object type name
      sheet: 0 # sheet number
      source: 0 # column number
      sourceType: 1 # column number
      target: 2 # column number
      targetType: 3 # column number
      nullable: 4 # column number
      transformType: 5 # column number
      transformValue: 6 # column number

    reseller: # object type name
      sheet: 1 # sheet number
      source: 0 # column number
      sourceType: 1 # column number
      target: 2 # column number
      targetType: 3 # column number
      nullable: 4 # column number
      transformType: 5 # column number
      transformValue: 6 # column number
```

## Notes
1. Null values are not printed
2. If any of columns source, source type, target, target type is null, this node is not printed in result file
3. Transform parameter is printed in result file only if we have both not null transform type and transform value
4. All keys are printed with the first uppercase word
5. Transformation value column may contain a list of objects. You can put several values into the column delimited by whitespace
6. Only transformation values are printed with quotes

## Run an application

To run an application just copy this project, run it and call uri from `api/api.http` file. You need to redefine the
first parameter (type of your object from config) and the second (name of the source file).

As a result you will receive an object with information:
1. `rawDataSize` - how many rows with info you have (some of them may have null values in some columns)
2. `unmappedFieldsTargets` - names of unmapped fields (targets). See which rows will not be mapped in Notes section
3. `transformedDataSize` - how many rows are mapped and written in the result file

