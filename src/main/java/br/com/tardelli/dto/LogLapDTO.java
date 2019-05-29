package br.com.tardelli.dto;

import br.com.tardelli.utils.file.PositionalField;

import java.math.BigDecimal;
import java.time.LocalTime;

public class LogLapDTO {

  @PositionalField(initialPosition = 1, finalPosition = 13, pattern = "HH:mm:ss.SSS")
  private LocalTime hourLog;
  @PositionalField(initialPosition = 19, finalPosition = 22)
  private String pilotId;
  @PositionalField(initialPosition = 25, finalPosition = 40)
  private String pilotName;
  @PositionalField(initialPosition = 59, finalPosition = 60)
  private Integer lapNumber;
  @PositionalField(initialPosition = 65, finalPosition = 73, pattern = "HH:mm:ss.SSS")
  private LocalTime lapTime;
  @PositionalField(initialPosition = 97, finalPosition = 102)
  private BigDecimal averageSpeed;

  private String hourFinishRace;
  private LocalTime bestLap;
  private BigDecimal averageRaceSpeedy;
  private String totalTimeRace;

  public LocalTime getHourLog() {
    return hourLog;
  }

  public void setHourLog(LocalTime hourLog) {
    this.hourLog = hourLog;
  }

  public String getPilotId() {
    return pilotId;
  }

  public void setPilotId(String pilotId) {
    this.pilotId = pilotId;
  }

  public String getPilotName() {
    return pilotName;
  }

  public void setPilotName(String pilotName) {
    this.pilotName = pilotName;
  }

  public Integer getLapNumber() {
    return lapNumber;
  }

  public void setLapNumber(Integer lapNumber) {
    this.lapNumber = lapNumber;
  }

  public LocalTime getLapTime() {
    return lapTime;
  }

  public void setLapTime(LocalTime lapTime) {
    this.lapTime = lapTime;
  }

  public BigDecimal getAverageSpeed() {
    return averageSpeed;
  }

  public void setAverageSpeed(BigDecimal averageSpeed) {
    this.averageSpeed = averageSpeed;
  }

  public String getHourFinishRace() {
    return hourFinishRace;
  }

  public void setHourFinishRace(String hourFinishRace) {
    this.hourFinishRace = hourFinishRace;
  }

  public LocalTime getBestLap() {
    return bestLap;
  }

  public void setBestLap(LocalTime bestLap) {
    this.bestLap = bestLap;
  }

  public BigDecimal getAverageRaceSpeedy() {
    return averageRaceSpeedy;
  }

  public void setAverageRaceSpeedy(BigDecimal averageRaceSpeedy) {
    this.averageRaceSpeedy = averageRaceSpeedy;
  }

  public String getTotalTimeRace() {
    return totalTimeRace;
  }

  public void setTotalTimeRace(String totalTimeRace) {
    this.totalTimeRace = totalTimeRace;
  }

  @Override
  public String toString() {
    return pilotId + "-" + pilotName + " " + lapNumber + " voltas completas    Tempo total: " + totalTimeRace
            + "    Melhor volta: " + bestLap + "    Vel. Média: " + averageRaceSpeedy + "    Termino: " + (hourFinishRace == null ? "" : hourFinishRace);
  }
}
