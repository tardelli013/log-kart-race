package br.com.tardelli.utils.file;

import br.com.tardelli.utils.file.exception.FlatFileParserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexUtils {

  final static String REGEX_HOUR_LOG = "(\\d{2}):(\\d{2}):(\\d{2}).(\\d{3})";
  final static String REGEX_PILOT_ID = "( \\d{3} )";
  final static String REGEX_PILOT_NAME = "([a-zA-Z][.][a-zA-Z]+)";
  final static String REGEX_LAP_NUMBER = "(( \\d{1} )|( \\d{1}\t))";
  final static String REGEX_LAP_TIME = "(( \\d{1}:\\d{2}.\\d{3})|(\t\t\\d{1}:\\d{2}.\\d{3}))";
  final static String REGEX_AVERAGE_SPEED = "(\\d{2},(?:\\d{3}|\\d{2}))";

  static String regexMatcher(String s, String regex) throws FlatFileParserException {
    Matcher matcher = Pattern.compile(regex).matcher(s);
    if (matcher.find()) {
      return matcher.group().trim();
    }
    throw new FlatFileParserException("Erro ao converter o arquivo, linha: " + s);
  }
}
