package br.com.tardelli.utils;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class FunctionsUtilsTest {

  @Test
  public void convertHoursStringToLocalTime() {
    String hour = "13:30:43.111";
    String hour2 = "30:43.111";

    LocalTime cal = FunctionsUtils.convertHoursStringToLocalTime(hour);
    LocalTime cal2 = FunctionsUtils.convertHoursStringToLocalTime(hour2);

    String ret1 = FunctionsUtils.convertLocalTimeToHoursString(cal);
    String ret2 = FunctionsUtils.convertLocalTimeToHoursString(cal2);

    assertEquals(ret1, hour);
    assertEquals(ret2, "00:" + hour2);
  }

  @Test
  public void convertLocalTimeToHoursString() {
    String pattern = "13:30:43.111";
    LocalTime hour;
    hour = FunctionsUtils.convertHoursStringToLocalTime(pattern);
    String ret = FunctionsUtils.convertLocalTimeToHoursString(hour);
    assertEquals(ret, pattern);
  }

  @Test
  public void subtractHoursInLocalTime() {
    String h1 = "13:01:12.111";
    String h2 = "14:01:12.111";

    LocalTime cal1 = FunctionsUtils.convertHoursStringToLocalTime(h1);
    LocalTime cal2 = FunctionsUtils.convertHoursStringToLocalTime(h2);

    String ret = FunctionsUtils.subtractHoursInLocalTime(cal1, cal2);

    assertEquals(ret, "01:00:00.000");
  }
}
