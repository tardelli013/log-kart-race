package br.com.tardelli.mapper;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.model.Pilot;

import java.math.BigDecimal;
import java.time.LocalTime;

import static br.com.tardelli.utils.file.RegexUtils.*;

public class LogLapMapper {

  private static final String DEFAULT_HOUR_MIN = "00:0";

  public static LogLap map(String line) {

    LogLap logLap = new LogLap();
    String hourLog = regexMatcher(line, REGEX_HOUR_LOG);
    String pilotId = regexMatcher(line, REGEX_PILOT_ID);
    String pilotName = regexMatcher(line, REGEX_PILOT_NAME);
    String lapTime = regexMatcher(line, REGEX_LAP_TIME);
    String lapNumber = regexMatcher(line, REGEX_LAP_NUMBER);
    String averageSpeed = regexMatcher(line, REGEX_AVERAGE_SPEED);

    logLap.setHourLog(LocalTime.parse(hourLog));
    logLap.setPilot(new Pilot(pilotId, pilotName));
    logLap.setLapNumber(Integer.parseInt(lapNumber));
    logLap.setAverageSpeed(new Double(averageSpeed.replace(",", ".")));
    logLap.setLapTime(LocalTime.parse(DEFAULT_HOUR_MIN + lapTime));

    return logLap;
  }
}
