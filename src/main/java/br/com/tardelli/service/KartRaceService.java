package br.com.tardelli.service;

import br.com.tardelli.dto.LogLapDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.tardelli.utils.FunctionsUtils.*;

public class KartRaceService {

  private static final String HOUR_PATTERN = "HH:mm:ss.SSS";

  public List<LogLapDTO> buildGridFinal(List<LogLapDTO> listLogs) {

    Map<String, List<LogLapDTO>> allLapsByPilot = getLapsByPilot(listLogs);
    List<LogLapDTO> finalLaps = getListFinalLap(allLapsByPilot);

    calcArrivalDifference(finalLaps);
    findBestLapByPilot(finalLaps, listLogs);
    calcAverageSpeedyInRace(finalLaps, listLogs);
    calcTotalTimeRace(finalLaps, listLogs);

    return finalLaps;
  }

  private List<LogLapDTO> getListFinalLap(Map<String, List<LogLapDTO>> allLapsByPilot) {
    List<LogLapDTO> finalLaps = new ArrayList<>();
    allLapsByPilot.forEach((s, laps) -> {

      laps.sort(Comparator.comparingInt(LogLapDTO::getLapNumber).reversed());

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

  private Map<String, List<LogLapDTO>> getLapsByPilot(List<LogLapDTO> listLogs) {
    Map<String, List<LogLapDTO>> map = new HashMap<>();
    Set<String> idPilots = new LinkedHashSet<>();

    listLogs.forEach(logLap -> idPilots.add(logLap.getPilotId()));

    idPilots.forEach(id -> map.put(id, listLogs.stream().filter(obj -> id.equals(obj.getPilotId())).collect(Collectors.toList())));

    return map;
  }


  private void calcTotalTimeRace(List<LogLapDTO> listFinalLap, List<LogLapDTO> listLogs) {
    LocalTime timeStart = null;
    LocalTime timeFinish = null;

    for (LogLapDTO finish : listFinalLap) {

      for (LogLapDTO lap : listLogs) {

        if (lap.getPilotId().equals(finish.getPilotId())) {
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

  private void calcAverageSpeedyInRace(List<LogLapDTO> listFinalLap, List<LogLapDTO> listLogs) {
    BigDecimal sumSpeed;
    for (LogLapDTO lastLap : listFinalLap) {
      sumSpeed = new BigDecimal(0);
      int totalLaps = 0;

      for (LogLapDTO lap : listLogs) {
        if (lap.getPilotId().equals(lastLap.getPilotId())) {
          totalLaps ++;
          sumSpeed = sumSpeed.add(lap.getAverageSpeed());
        }
      }
      sumSpeed = sumSpeed.divide(new BigDecimal(totalLaps), 3, RoundingMode.HALF_UP);
      lastLap.setAverageRaceSpeedy(sumSpeed);
    }
  }

  private void findBestLapByPilot(List<LogLapDTO> listFinalLap, List<LogLapDTO> listLogs) {
    listFinalLap.forEach(finish -> {
      LocalTime best = null;

      for (LogLapDTO lap : listLogs) {

        if (lap.getPilotId().equals(finish.getPilotId())) {
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

  private void calcArrivalDifference(List<LogLapDTO> listFinalLap) {
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

  public LogLapDTO findBestLapRace(List<LogLapDTO> listFinalLap) {
    LocalTime best = null;
    LogLapDTO bestLap = null;

    for (LogLapDTO lap : listFinalLap) {

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
