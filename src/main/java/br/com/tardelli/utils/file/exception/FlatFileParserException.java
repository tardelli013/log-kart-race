package br.com.tardelli.utils.file.exception;

public class FlatFileParserException extends RuntimeException {

  public FlatFileParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public FlatFileParserException(String message) {
    super(message);
  }

}
