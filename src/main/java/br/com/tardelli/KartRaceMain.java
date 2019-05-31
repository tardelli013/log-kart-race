package br.com.tardelli;

import br.com.tardelli.dto.FinalResultsDTO;
import br.com.tardelli.model.LogLap;
import br.com.tardelli.service.KartRaceService;
import br.com.tardelli.utils.file.FlatFileParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class KartRaceMain {

  @SuppressWarnings("ConstantConditions")
  public static void main(String[] args) {
    BufferedReader fileLogs = new BufferedReader(new InputStreamReader(KartRaceMain.class.getClassLoader().getResourceAsStream("logs")));

    KartRaceService kartRaceService = new KartRaceService();
    FlatFileParser flatFileParser = new FlatFileParser();

    List<LogLap> logLaps = flatFileParser.fileToObject(fileLogs);

    List<FinalResultsDTO> finalGrid = kartRaceService.buildGridFinal(logLaps);

    printResult(finalGrid);
  }

  private static void printResult(List<FinalResultsDTO> finalGrid) {
    System.out.println("\n*** RESULTADO FINAL *** \n");
    finalGrid.forEach(System.out::println);


    LogLap bestLapRace = finalGrid.get(0).getBestLapRace();
    System.out.println("\n*** MELHOR VOLTA DA CORRIDA ***");
    System.out.println(bestLapRace.getPilot().getId().concat("-").concat(bestLapRace.getPilot().getName()).concat(" Tempo: ") + bestLapRace.getLapTime() + "     Vel. média: " + bestLapRace.getAverageSpeed());
  }
}
