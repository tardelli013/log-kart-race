package br.com.tardelli.mapper;

import br.com.tardelli.model.LogLap;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class LogMapperTest {

  @Test
  public void map(){
    LogLap logLap = LogLapMapper.map("23:52:22.586      033 – R.BARRICHELLO\t\t          4\t\t1:04.010                        43,474");

    Assert.assertEquals(logLap.getPilot().getId(), "033");
    Assert.assertEquals(logLap.getPilot().getName(), "R.BARRICHELLO");
    Assert.assertEquals(logLap.getLapTime(), LocalTime.parse("00:01:04.010"));
    Assert.assertEquals(logLap.getLapNumber(), new Integer(4));
    Assert.assertEquals(logLap.getHourLog(), LocalTime.parse("23:52:22.586"));
    Assert.assertEquals(logLap.getAverageSpeed(), new Double("43.474"));
  }
}
