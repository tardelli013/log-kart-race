package br.com.tardelli.utils.file;

import org.junit.Test;

import static br.com.tardelli.utils.file.RegexUtils.*;
import static org.junit.Assert.assertEquals;

public class RegexUtilsTest {

  @Test
  public void regexMatcherTest(){
    String s = "23:53:06.741     015 - F.ALONSO  4     1:20.050       34,763";

    String hourLog = regexMatcher(s, REGEX_HOUR_LOG);
    String pilotId = regexMatcher(s, REGEX_PILOT_ID);
    String pilotName = regexMatcher(s, REGEX_PILOT_NAME);
    String lapTime = regexMatcher(s, REGEX_LAP_TIME);
    String lapNumber = regexMatcher(s, REGEX_LAP_NUMBER);
    String averageSpeed = regexMatcher(s, REGEX_AVERAGE_SPEED);

    assertEquals(hourLog, "23:53:06.741");
    assertEquals(pilotId, "015");
    assertEquals(pilotName, "F.ALONSO");
    assertEquals(lapNumber, "4");
    assertEquals(lapTime, "1:20.050");
    assertEquals(averageSpeed, "34,763");
  }
}
