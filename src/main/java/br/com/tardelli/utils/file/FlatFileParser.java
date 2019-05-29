package br.com.tardelli.utils.file;

import br.com.tardelli.dto.LogLapDTO;
import br.com.tardelli.utils.file.exception.FlatFileParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FlatFileParser {

  private static Object createFromText(Class clazz, String line) throws IllegalAccessException, InstantiationException {
    Object obj = clazz.newInstance();

    if (Objects.nonNull(line)) {
      List<Field> fields = Arrays.asList(obj.getClass().getDeclaredFields());
      fields.forEach(field -> {
        if (field.isAnnotationPresent(PositionalField.class)) {
          mapField(field, obj, line);
        }
      });
    }

    return obj;
  }

  private static void mapField(Field field, Object obj, String line) {
    try {
      field.setAccessible(true);

      PositionalField ann = field.getAnnotation(PositionalField.class);
      String value = line.substring(ann.initialPosition() - 1, ann.finalPosition() - 1);

      if (String.class.isAssignableFrom(field.getType())) {
        field.set(obj, value);
      } else if (LocalTime.class.isAssignableFrom(field.getType())) {
        field.set(obj, stringToLocalTime(value.trim(), ann.pattern()));
      } else if (BigDecimal.class.isAssignableFrom(field.getType())) {
        field.set(obj, stringToBigDecimal(value.trim()));
      } else if (Integer.class.isAssignableFrom(field.getType())) {
        field.set(obj, Integer.valueOf(value.trim()));
      }

    } catch (IllegalAccessException e) {
      throw new FlatFileParserException("Error map field: " + field.getName(), e);
    }
  }

  private static BigDecimal stringToBigDecimal(String value) {
    return new BigDecimal(value.replace(",", "."));
  }

  private static LocalTime stringToLocalTime(String s, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    if (s.length() == 8) {
      s = "00:0" + s;
    }
    return LocalTime.parse(s.trim(), formatter);
  }

  public List<LogLapDTO> convertFileToObjects(BufferedReader fileLogs) {
    List<LogLapDTO> logLaps = new ArrayList<>();
    try {

      String line;
      while ((line = fileLogs.readLine()) != null) {
        logLaps.add((LogLapDTO) createFromText(LogLapDTO.class, line));
      }
      fileLogs.close();
    } catch (IllegalAccessException | InstantiationException | IOException e) {
      throw new FlatFileParserException("Error convert file to object: ", e);
    }
    return logLaps;
  }
}
