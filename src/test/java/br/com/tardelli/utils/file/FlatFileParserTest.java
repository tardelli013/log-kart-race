package br.com.tardelli.utils.file;

import br.com.tardelli.model.LogLap;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlatFileParserTest {

  private FlatFileParser flatFileParser = new FlatFileParser();

  @Test
  public void convertFileToObjects() {
    List<LogLap> logLaps = flatFileParser.fileToObject(mockFile());

    assertEquals(logLaps.size(), 8);
  }

  private BufferedReader mockFile() {
    String str =
            "Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta\n" +
            "23:49:08.277      038 – F.MASSA                           1     1:02.852                        44,275\n" +
            "23:49:10.858      033 – R.BARRICHELLO                     1     1:04.352                        43,243\n" +
            "23:50:11.447      038 – F.MASSA                           2     1:03.170                        44,053\n" +
            "23:50:14.860      033 – R.BARRICHELLO                     2     1:04.002                        43,480\n" +
            "23:51:14.216      038 – F.MASSA                           3     1:02.769                        44,334\n" +
            "23:51:18.576      033 – R.BARRICHELLO                     3     1:03.716                        43,675\n" +
            "23:52:17.003      038 – F.MASS                            4     1:02.787                        44,321\n" +
            "23:52:22.586      033 – R.BARRICHELLO                     4     1:04.010                        43,474\n";

    Reader inputString = new StringReader(str);
    return new BufferedReader(inputString);
  }
}
