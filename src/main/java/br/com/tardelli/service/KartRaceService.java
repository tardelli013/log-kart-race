package br.com.tardelli.service;

import br.com.tardelli.dto.FinalResultsDTO;
import br.com.tardelli.model.LogLap;
import br.com.tardelli.utils.KartRaceUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicInteger;

public class KartRaceService {

  public List<FinalResultsDTO> buildGridFinal(List<LogLap> listLogs) {

    Map<String, SortedSet<LogLap>> allLapsByPilot = KartRaceUtils.getLapsByPilotOrderByLapNumberAsc(listLogs);

    SortedSet<LogLap> allFinalLapsOrderByHourLogAsc = KartRaceUtils.getAllFinalLapsOrderByHourLogAsc(allLapsByPilot);

    Map<String, String> arrivalDifference = KartRaceUtils.calcArrivalDifference(allFinalLapsOrderByHourLogAsc);

    Map<String, LogLap> bestLapByPilot = KartRaceUtils.findBestLapByPilot(allLapsByPilot);

    LogLap bestLapRace = KartRaceUtils.findBestLapRace(bestLapByPilot);

    Double averageSpeedyInRace = KartRaceUtils.calcAverageSpeedyInRace(listLogs);

    Map<String, Double> averageSpeedyInRaceByPilot = KartRaceUtils.calcAverageSpeedyInRaceByPilot(allLapsByPilot);

    Map<String, LocalTime> totalTimeRaceByPilots = KartRaceUtils.calcTotalTimeRaceByPilots(allLapsByPilot);

    List<FinalResultsDTO> results = new ArrayList<>();

    AtomicInteger position = new AtomicInteger(1);
    allFinalLapsOrderByHourLogAsc.forEach(finalLap -> {
      FinalResultsDTO dto = new FinalResultsDTO();
      dto.setPilot(finalLap.getPilot());
      dto.setBestLap(bestLapByPilot.get(finalLap.getPilot().getId()).getLapTime());
      dto.setCompletedLaps(finalLap.getLapNumber());
      dto.setHourFinishRace(finalLap.getHourLog().format(KartRaceUtils.HOUR_FORMATTER));
      dto.setLastLap(finalLap);
      dto.setArrivalDifference(arrivalDifference.get(finalLap.getPilot().getId()));
      dto.setBestLapRace(bestLapRace);
      dto.setAverageSpeedyInRace(averageSpeedyInRace);
      dto.setAverageSpeed(averageSpeedyInRaceByPilot.get(finalLap.getPilot().getId()));
      dto.setTotalTimeOfTheKartRace(totalTimeRaceByPilots.get(finalLap.getPilot().getId()));
      dto.setPosition(position.getAndIncrement());

      results.add(dto);
    });

    return results;
  }
}
