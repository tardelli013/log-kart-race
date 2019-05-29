package br.com.tardelli;

import br.com.tardelli.dto.LogLapDTO;
import br.com.tardelli.service.KartRaceService;
import br.com.tardelli.utils.file.FlatFileParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KartRaceMain {

  private static KartRaceService kartRaceService = new KartRaceService();

  @SuppressWarnings("ConstantConditions")
  public static void main(String[] args) {
    BufferedReader fileLogs = new BufferedReader(new InputStreamReader(KartRaceMain.class.getClassLoader().getResourceAsStream("logs")));

    FlatFileParser fileParser = new FlatFileParser();

    List<LogLapDTO> logLaps = fileParser.convertFileToObjects(fileLogs);

    List<LogLapDTO> finalGrid = kartRaceService.buildGridFinal(logLaps);
    LogLapDTO bestLapRace = kartRaceService.findBestLapRace(finalGrid);

    printResult(finalGrid, bestLapRace);
  }

  private static void printResult(List<LogLapDTO> finalGrid, LogLapDTO bestRoundRace) {
    System.out.println("\n*** RESULTADO FINAL *** \n");
    AtomicInteger position = new AtomicInteger(1);
    finalGrid.forEach(logLapDTO -> {
      System.out.println(position + " - " + logLapDTO);
      position.getAndIncrement();
    });

    System.out.println("\n*** MELHOR VOLTA DA CORRIDA ***");
    System.out.println(bestRoundRace.getPilotId().concat("-").concat(bestRoundRace.getPilotName()).concat(" Tempo: ") + bestRoundRace.getBestLap() + "     Vel. média: " + bestRoundRace.getAverageSpeed());
  }
}
