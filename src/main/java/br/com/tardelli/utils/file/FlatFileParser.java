package br.com.tardelli.utils.file;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.model.Pilot;
import br.com.tardelli.utils.file.exception.FlatFileParserException;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static br.com.tardelli.utils.file.RegexUtils.*;

public class FlatFileParser {

  private static final String DEFAULT_HOUR_MIN = "00:0";

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  public List<LogLap> fileToObject(BufferedReader bufferedReader) {
    List<LogLap> logLaps = new ArrayList<>();
    Stream<String> stream = bufferedReader.lines();

    AtomicBoolean initialLine = new AtomicBoolean(true);
    stream.forEach(s -> {

      if(!initialLine.get()){
        try {

          String hourLog = regexMatcher(s, REGEX_HOUR_LOG);
          String pilotId = regexMatcher(s, REGEX_PILOT_ID);
          String pilotName = regexMatcher(s, REGEX_PILOT_NAME);
          String lapTime = regexMatcher(s, REGEX_LAP_TIME);
          String lapNumber = regexMatcher(s, REGEX_LAP_NUMBER);
          String averageSpeed = regexMatcher(s, REGEX_AVERAGE_SPEED);

          LogLap logLap = new LogLap();
          logLap.setHourLog(LocalTime.parse(hourLog));
          logLap.setPilot(new Pilot(pilotId, pilotName));
          logLap.setLapNumber(Integer.parseInt(lapNumber));
          logLap.setAverageSpeed(new BigDecimal(averageSpeed.replace(",", ".")));
          logLap.setLapTime(LocalTime.parse(DEFAULT_HOUR_MIN + lapTime));

          logLaps.add(logLap);
        } catch (FlatFileParserException e) {
          logger.log(Level.WARNING, e.getMessage());
        }
      } else {
        initialLine.set(false);
      }
    });
    return logLaps;
  }
}
