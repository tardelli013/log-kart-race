package br.com.tardelli.service;

import br.com.tardelli.model.LogLap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.tardelli.utils.FunctionsUtils.*;

public class KartRaceService {

  public List<LogLap> buildGridFinal(List<LogLap> listLogs) {

    Map<String, List<LogLap>> allLapsByPilot = getLapsByPilot(listLogs);
    List<LogLap> finalLaps = getListFinalLap(allLapsByPilot);

    calcArrivalDifference(finalLaps);
    findBestLapByPilot(finalLaps, listLogs);
    calcAverageSpeedyInRace(finalLaps, listLogs);
    calcTotalTimeRace(finalLaps, listLogs);

    return finalLaps;
  }

  private List<LogLap> getListFinalLap(Map<String, List<LogLap>> allLapsByPilot) {
    List<LogLap> finalLaps = new ArrayList<>();
    allLapsByPilot.forEach((s, laps) -> {

      laps.sort(Comparator.comparingInt(LogLap::getLapNumber).reversed());

      finalLaps.add(laps.get(0));
    });

    finalLaps.sort((o1, o2) -> {
      int cmp = o2.getLapNumber().compareTo(o1.getLapNumber());
      if (cmp == 0)
        cmp = o1.getHourLog().compareTo(o2.getHourLog());
      return cmp;
    });

    return finalLaps;
  }

  private Map<String, List<LogLap>> getLapsByPilot(List<LogLap> listLogs) {
    Map<String, List<LogLap>> map = new HashMap<>();
    Set<String> idPilots = new LinkedHashSet<>();

    listLogs.forEach(logLap -> idPilots.add(logLap.getPilot().getId()));

    idPilots.forEach(id -> map.put(id, listLogs.stream().filter(obj -> id.equals(obj.getPilot().getId())).collect(Collectors.toList())));

    return map;
  }


  private void calcTotalTimeRace(List<LogLap> listFinalLap, List<LogLap> listLogs) {
    LocalTime timeStart = null;
    LocalTime timeFinish = null;

    for (LogLap finish : listFinalLap) {

      for (LogLap lap : listLogs) {

        if (lap.getPilot().getId().equals(finish.getPilot().getId())) {
          if (lap.getLapNumber().equals(1)) {
            timeStart = convertHoursStringToLocalTime(subtractHoursInLocalTime(lap.getLapTime(), lap.getHourLog()));
          } else if (lap.getLapNumber().equals(4)) {
            timeFinish = lap.getHourLog();
          }
        }
      }

      if (timeFinish != null && timeStart != null) {
        finish.setTotalTimeRace(subtractHoursInLocalTime(timeStart, timeFinish));
      }
    }
  }

  private void calcAverageSpeedyInRace(List<LogLap> listFinalLap, List<LogLap> listLogs) {
    BigDecimal sumSpeed;
    for (LogLap lastLap : listFinalLap) {
      sumSpeed = new BigDecimal(0);
      int totalLaps = 0;

      for (LogLap lap : listLogs) {
        if (lap.getPilot().getId().equals(lastLap.getPilot().getId())) {
          totalLaps ++;
          sumSpeed = sumSpeed.add(lap.getAverageSpeed());
        }
      }
      sumSpeed = sumSpeed.divide(new BigDecimal(totalLaps), 3, RoundingMode.HALF_UP);
      lastLap.setAverageRaceSpeedy(sumSpeed);
    }
  }

  private void findBestLapByPilot(List<LogLap> listFinalLap, List<LogLap> listLogs) {
    listFinalLap.forEach(finish -> {
      LocalTime best = null;

      for (LogLap lap : listLogs) {

        if (lap.getPilot().getId().equals(finish.getPilot().getId())) {
          if (best == null) {
            best = lap.getLapTime();
          } else {
            if (best.compareTo(lap.getLapTime()) > 0) {
              best = lap.getLapTime();
            }
          }
        }
      }
      finish.setBestLap(best);
    });
  }

  private void calcArrivalDifference(List<LogLap> listFinalLap) {
    LocalTime hourChampion = null;

    for (int i = 0; i < listFinalLap.size(); i++) {
      if (i == 0) {
        hourChampion = listFinalLap.get(i).getHourLog();
        listFinalLap.get(i).setHourFinishRace("  " + convertLocalTimeToHoursString(hourChampion));
      } else {
        if (listFinalLap.get(i).getLapNumber() == 4) {
          listFinalLap.get(i).setHourFinishRace("+ " + subtractHoursInLocalTime(hourChampion, listFinalLap.get(i).getHourLog()));
        }
      }
    }
  }

  public LogLap findBestLapRace(List<LogLap> listFinalLap) {
    LocalTime best = null;
    LogLap bestLap = null;

    for (LogLap lap : listFinalLap) {

      if (best == null) {
        best = lap.getLapTime();
        bestLap = lap;
      } else {
        if (best.compareTo(lap.getLapTime()) > 0) {
          best = lap.getLapTime();
          bestLap = lap;
        }
      }
    }
    return bestLap;
  }
}
