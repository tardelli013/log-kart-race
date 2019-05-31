package br.com.tardelli.service;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.utils.KartRaceUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class KartRaceService {

  public List<LogLap> buildGridFinal(List<LogLap> listLogs) {

    Map<String, SortedSet<LogLap>> allLapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(listLogs);

    SortedSet<LogLap> allFinalLapsOrderByHourLogAsc = KartRaceUtils.getAllFinalLapsOrderByHourLogAsc(allLapsByPilot);

    Map<String, String> arrivalDifference = KartRaceUtils.calcArrivalDifference(allFinalLapsOrderByHourLogAsc);

    Map<String, LogLap> bestLapByPilot = KartRaceUtils.findBestLapByPilot(allLapsByPilot);

    LogLap bestLapRace = KartRaceUtils.findBestLapRace(bestLapByPilot);

    Double averageSpeedyInRace = KartRaceUtils.calcAverageSpeedyInRace(listLogs);

    LocalTime localTime = KartRaceUtils.calcTotalTimeRace(listLogs);

    return null;
  }

}
