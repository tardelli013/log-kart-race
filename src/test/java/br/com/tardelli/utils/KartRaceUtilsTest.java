package br.com.tardelli.utils;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.model.Pilot;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class KartRaceUtilsTest {

  @Test
  public void getLapsByPilotOrderByLapNumberAscTest() {
    Map<String, SortedSet<LogLap>> lapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(getLogLapsMock());

    lapsByPilot.forEach((s, logLaps) -> {
      Assert.assertEquals(logLaps.first().getLapNumber(), new Integer(1));
      Assert.assertEquals(logLaps.last().getLapNumber(), new Integer(2));
    });
  }

  @Test
  public void getAllFinalLapsOrderByHourLogTest(){
    Map<String, SortedSet<LogLap>> lapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(getLogLapsMock());

    SortedSet<LogLap> finalLaps = KartRaceUtils.getAllFinalLapsOrderByHourLogAsc(lapsByPilot);

    Assert.assertEquals(finalLaps.first().getHourLog(), LocalTime.parse("23:53:06.741"));
    Assert.assertEquals(finalLaps.last().getHourLog(), LocalTime.parse("23:55:06.741"));
  }

  @Test
  public void calcArrivalDifferenceTest(){
    Map<String, SortedSet<LogLap>> lapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(getLogLapsMock());
    SortedSet<LogLap> finalLaps = KartRaceUtils.getAllFinalLapsOrderByHourLogAsc(lapsByPilot);

    Map<String, String> arrivalDifference = KartRaceUtils.calcArrivalDifference(finalLaps);

    Assert.assertEquals(arrivalDifference.get("001"),"  23:53:06.741");
    Assert.assertEquals(arrivalDifference.get("002"),"+ 00:02:00.000");
  }

  @Test
  public void findBestLapByPilotTest(){
    Map<String, SortedSet<LogLap>> lapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(getLogLapsMock());

    Map<String, LogLap> bestLapByPilot = KartRaceUtils.findBestLapByPilot(lapsByPilot);

    Assert.assertEquals(bestLapByPilot.get("001").getLapTime(), LocalTime.parse("00:01:06.241"));
    Assert.assertEquals(bestLapByPilot.get("002").getLapTime(), LocalTime.parse("00:01:01.241"));
  }

  @Test
  public void findBestLapRaceTest(){
    Map<String, SortedSet<LogLap>> lapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(getLogLapsMock());

    Map<String, LogLap> bestLapByPilot = KartRaceUtils.findBestLapByPilot(lapsByPilot);

    LogLap bestLapRace = KartRaceUtils.findBestLapRace(bestLapByPilot);

    Assert.assertEquals(bestLapRace.getLapTime(),LocalTime.parse("00:01:01.241"));
  }

  @Test
  public void calcAverageSpeedyInRace(){
    Double averageSpeedyInRace = KartRaceUtils.calcAverageSpeedyInRace(getLogLapsMock());

    Assert.assertEquals(averageSpeedyInRace, new Double("60.05"));
  }

  private List<LogLap> getLogLapsMock() {
    List<LogLap> list = new ArrayList<>();
    Pilot pilot1 = new Pilot("001", "Pilot 1"); //Pilot 1 is champion
    Pilot pilot2 = new Pilot("002", "Pilot 2");

    LogLap logLap2 = new LogLap();
    logLap2.setPilot(pilot1);
    logLap2.setLapNumber(2);
    logLap2.setHourLog(LocalTime.parse("23:53:06.741"));
    logLap2.setLapTime(LocalTime.parse("00:01:06.741"));
    logLap2.setAverageSpeed(new Double("60.05"));
    list.add(logLap2);

    LogLap logLap = new LogLap();
    logLap.setPilot(pilot1);
    logLap.setLapNumber(1);
    logLap.setHourLog(LocalTime.parse("23:50:06.741"));
    logLap.setLapTime(LocalTime.parse("00:01:06.241"));
    logLap.setAverageSpeed(new Double("60.05"));
    list.add(logLap);

    LogLap logLap4 = new LogLap();
    logLap4.setPilot(pilot2);
    logLap4.setLapNumber(2);
    logLap4.setHourLog(LocalTime.parse("23:55:06.741"));
    logLap4.setLapTime(LocalTime.parse("00:01:07.241"));
    logLap4.setAverageSpeed(new Double("60.05"));
    list.add(logLap4);

    LogLap logLap3 = new LogLap();
    logLap3.setPilot(pilot2);
    logLap3.setLapNumber(1);
    logLap3.setHourLog(LocalTime.parse("23:50:06.741"));
    logLap3.setLapTime(LocalTime.parse("00:01:01.241"));
    logLap3.setAverageSpeed(new Double("60.05"));
    list.add(logLap3);

    return list;
  }
}
