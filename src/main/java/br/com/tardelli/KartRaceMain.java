package br.com.tardelli;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.service.KartRaceService;
import br.com.tardelli.utils.file.FlatFileParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KartRaceMain {

  private static KartRaceService kartRaceService = new KartRaceService();
  private static FlatFileParser flatFileParser = new FlatFileParser();

  @SuppressWarnings("ConstantConditions")
  public static void main(String[] args) {
    BufferedReader fileLogs = new BufferedReader(new InputStreamReader(KartRaceMain.class.getClassLoader().getResourceAsStream("logs")));


    List<LogLap> logLaps = flatFileParser.fileToObject(fileLogs);

    List<LogLap> finalGrid = kartRaceService.buildGridFinal(logLaps);
    LogLap bestLapRace = kartRaceService.findBestLapRace(finalGrid);

    printResult(finalGrid, bestLapRace);
  }

  private static void printResult(List<LogLap> finalGrid, LogLap bestRoundRace) {
    System.out.println("\n*** RESULTADO FINAL *** \n");
    AtomicInteger position = new AtomicInteger(1);
    finalGrid.forEach(logLapDTO -> {
      System.out.println(position + " - " + logLapDTO);
      position.getAndIncrement();
    });

    System.out.println("\n*** MELHOR VOLTA DA CORRIDA ***");
    System.out.println(bestRoundRace.getPilot().getId().concat("-").concat(bestRoundRace.getPilot().getName()).concat(" Tempo: ") + bestRoundRace.getBestLap() + "     Vel. média: " + bestRoundRace.getAverageSpeed());
  }
}
