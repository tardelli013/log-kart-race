package br.com.tardelli.utils;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FunctionsUtils implements Serializable {

  private static final long serialVersionUID = 1L;

  public static LocalTime convertHoursStringToLocalTime(String hour) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    if (hour.length() == 9) {
      hour = "00:" + hour;
    } else if (hour.length() == 8) {
      hour = "00:0" + hour;
    } else if (hour.length() == 7) {
      hour = "00:00" + hour;
    } else if (hour.length() == 6) {
      hour = "00:00:" + hour;
    }

    return LocalTime.parse(hour, formatter);
  }

  public static String convertLocalTimeToHoursString(LocalTime hour) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    return hour.format(formatter);
  }

  public static String subtractHoursInLocalTime(LocalTime cal1, LocalTime cal2) {
    long nano = ChronoUnit.NANOS.between(cal1, cal2);

    LocalTime ts = LocalTime.ofNanoOfDay(nano);

    return convertLocalTimeToHoursString(ts);
  }

}
