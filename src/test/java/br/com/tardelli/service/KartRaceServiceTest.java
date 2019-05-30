package br.com.tardelli.service;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.model.Pilot;
import br.com.tardelli.utils.FunctionsUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class KartRaceServiceTest {

  private KartRaceService kartService = new KartRaceService();

  @Test
  public void buildGridFinal() {
    List<LogLap> list = this.kartService.buildGridFinal(getMockListLogLaps());

    assertEquals(list.get(0).toString(), "001-T. MOURA 1     4 voltas completas    Tempo total: 00:11:04.313    Melhor volta: 00:01:04.313    Vel. Média: 60.110    Termino:   23:41:08.277");
    assertEquals(list.get(1).toString(), "002-T. MOURA 2     4 voltas completas    Tempo total: 00:11:04.323    Melhor volta: 00:01:04.323    Vel. Média: 60.120    Termino: + 00:01:00.000");
    assertEquals(list.get(2).toString(), "004-T. MOURA 4     4 voltas completas    Tempo total: 00:11:04.343    Melhor volta: 00:01:04.343    Vel. Média: 60.140    Termino: + 00:03:00.000");
    assertEquals(list.get(3).toString(), "005-T. MOURA 5     4 voltas completas    Tempo total: 00:11:04.353    Melhor volta: 00:01:04.353    Vel. Média: 60.150    Termino: + 00:04:00.000");
    assertEquals(list.get(4).toString(), "006-T. MOURA 6     4 voltas completas    Tempo total: 00:11:04.363    Melhor volta: 00:01:04.363    Vel. Média: 60.160    Termino: + 00:05:00.000");
    assertEquals(list.get(5).toString(), "003-T. MOURA 3     3 voltas completas    Tempo total: 00:14:04.333    Melhor volta: 00:01:04.333    Vel. Média: 60.130    Termino: ");
  }

  @Test
  public void findBestLapRace() {
    List<LogLap> list = this.kartService.buildGridFinal(getMockListLogLaps());

    LogLap r = this.kartService.findBestLapRace(list);

    assertEquals(FunctionsUtils.convertLocalTimeToHoursString(r.getBestLap()), "00:01:04.313");
  }

  private List<LogLap> getMockListLogLaps() {
    ArrayList<LogLap> logLaps = new ArrayList<>();

    AtomicInteger i = new AtomicInteger(0);
    while (i.get() < 6) {
      i.getAndIncrement();

      LogLap logLap1 = new LogLap();
      logLap1.setPilot(new Pilot(i.get(), "T. MOURA " + i.get() + "    "));
      logLap1.setAverageSpeed(new BigDecimal("60.1" + i.get()));
      logLap1.setLapNumber(1);
      logLap1.setHourLog(FunctionsUtils.convertHoursStringToLocalTime("23:3" + i.get() + ":08.277"));
      logLap1.setLapTime(FunctionsUtils.convertHoursStringToLocalTime("1:04.3" + i.get() + 3));

      logLaps.add(logLap1);

      LogLap logLap = new LogLap();
      logLap1.setPilot(new Pilot(i.get(), "T. MOURA " + i.get() + "    "));
      logLap.setAverageSpeed(new BigDecimal("60.1" + i.get()));
      logLap.setLapNumber(i.get() == 3 ? 3 : 4);
      logLap.setHourLog(FunctionsUtils.convertHoursStringToLocalTime("23:4" + i.get() + ":08.277"));
      logLap.setLapTime(FunctionsUtils.convertHoursStringToLocalTime("1:04.3" + i.get() + 3));

      logLaps.add(logLap);
    }
    return logLaps;
  }
}
