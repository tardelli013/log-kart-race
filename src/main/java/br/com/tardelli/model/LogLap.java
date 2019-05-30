package br.com.tardelli.model;

import java.math.BigDecimal;
import java.time.LocalTime;

public class LogLap {

  private LocalTime hourLog;
  private Pilot pilot;
  private Integer lapNumber;
  private LocalTime lapTime;
  private BigDecimal averageSpeed;

  private String hourFinishRace;
  private LocalTime bestLap;
  private BigDecimal averageRaceSpeedy;
  private String totalTimeRace;

  public Pilot getPilot() {
    return pilot;
  }

  public void setPilot(Pilot pilot) {
    this.pilot = pilot;
  }

  public LocalTime getHourLog() {
    return hourLog;
  }

  public void setHourLog(LocalTime hourLog) {
    this.hourLog = hourLog;
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
    return pilot.getId() + "-" + pilot.getName() + " " + lapNumber + " voltas completas    Tempo total: " + totalTimeRace
            + "    Melhor volta: " + bestLap + "    Vel. Média: " + averageRaceSpeedy + "    Termino: " + (hourFinishRace == null ? "" : hourFinishRace);
  }
}
