package br.com.tardelli.utils.file;

import br.com.tardelli.mapper.LogLapMapper;
import br.com.tardelli.model.LogLap;
import br.com.tardelli.utils.file.exception.FlatFileParserException;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FlatFileParser {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  public List<LogLap> fileToObject(BufferedReader bufferedReader) {
    List<LogLap> logLaps = new ArrayList<>();
    Stream<String> stream = bufferedReader.lines();

    AtomicBoolean initialLine = new AtomicBoolean(true);
    stream.forEach(line -> {
      if(!initialLine.get()){
        try {
          logLaps.add(LogLapMapper.map(line));
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
