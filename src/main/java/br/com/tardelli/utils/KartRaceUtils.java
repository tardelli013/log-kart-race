package br.com.tardelli.utils;

import br.com.tardelli.model.LogLap;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class KartRaceUtils {

  private static final DateTimeFormatter HOUR_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

  public static SortedSet<LogLap> getAllFinalLapsOrderByHourLogAsc(Map<String, SortedSet<LogLap>> allLapsByPilot) {
    SortedSet<LogLap> list = new TreeSet<>(Comparator.comparing(LogLap::getHourLog));
    allLapsByPilot.forEach((s, logLaps) -> list.add(logLaps.last()));
    return list;
  }

  public static Map<String, SortedSet<LogLap>> getLapsByPilotOrderByLapNumberAsc(List<LogLap> listLogs) {
    Map<String, SortedSet<LogLap>> map = new TreeMap<>();
    Set<String> idPilots = new LinkedHashSet<>();

    listLogs.forEach(logLap -> idPilots.add(logLap.getPilot().getId()));

    idPilots.forEach(id -> {
      List<LogLap> lapsByPilot = listLogs.stream().filter(logLap -> id.equals(logLap.getPilot().getId())).collect(Collectors.toList());
      SortedSet<LogLap> sortedSet = new TreeSet<>(Comparator.comparing(LogLap::getLapNumber));
      sortedSet.addAll(lapsByPilot);
      map.put(id, sortedSet);
    });
    return map;
  }

  public static Map<String, String> calcArrivalDifference(SortedSet<LogLap> listFinalLap) {
    Map<String, String> map = new HashMap<>();

    AtomicInteger i = new AtomicInteger(1);
    AtomicReference<LocalTime> hourChampion = new AtomicReference<>();

    listFinalLap.forEach(logLap -> {
      if (i.get() == 1) {
        hourChampion.set(logLap.getHourLog());
        map.put(logLap.getPilot().getId(), "  " + HOUR_FORMATTER.format(logLap.getHourLog()));
      } else {
        Duration between = Duration.between(hourChampion.get(), logLap.getHourLog());
        map.put(logLap.getPilot().getId(), "+ " + LocalTime.ofNanoOfDay(between.toNanos()).format(HOUR_FORMATTER));
      }
      i.getAndIncrement();
    });
    return map;
  }

  public static Map<String, LogLap> findBestLapByPilot(Map<String, SortedSet<LogLap>> lapsByPilot) {
    Map<String, LogLap> bestLaps = new HashMap<>();

    lapsByPilot.forEach((s, logLaps) -> {
      SortedSet<LogLap> sortedByBestLap = new TreeSet<>(Comparator.comparing(LogLap::getLapTime));
      sortedByBestLap.addAll(logLaps);

      bestLaps.put(s, sortedByBestLap.first());
    });

    return bestLaps;
  }

  public static LogLap findBestLapRace(Map<String, LogLap> bestLapsByPilot) {
    SortedSet<LogLap> sortedByBestLap = new TreeSet<>(Comparator.comparing(LogLap::getLapTime));
    bestLapsByPilot.forEach((s, logLaps) -> sortedByBestLap.add(logLaps));

    return sortedByBestLap.first();
  }

  public static Double calcAverageSpeedyInRace(List<LogLap> listLogs) {
    DoubleSummaryStatistics average = listLogs.stream().collect(Collectors.summarizingDouble(LogLap::getAverageSpeed));
    return average.getAverage();
  }

  public static LocalTime calcTotalTimeRace(List<LogLap> listLogs) {
    SortedSet<LogLap> sortedSet = new TreeSet<>(Comparator.comparing(LogLap::getHourLog));
    sortedSet.addAll(listLogs);

    LocalTime timeStart = LocalTime.ofNanoOfDay(ChronoUnit.NANOS.between(sortedSet.first().getLapTime(), sortedSet.first().getHourLog()));
    LocalTime timeFinish = sortedSet.last().getHourLog();

    return LocalTime.ofNanoOfDay(ChronoUnit.NANOS.between(timeStart, timeFinish));
  }
}
