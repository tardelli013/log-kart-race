package br.com.tardelli.model;

import java.time.LocalTime;

public class LogLap {

  private LocalTime hourLog;
  private Pilot pilot;
  private Integer lapNumber;
  private LocalTime lapTime;
  private Double averageSpeed;

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

  public Double getAverageSpeed() {
    return averageSpeed;
  }

  public void setAverageSpeed(Double averageSpeed) {
    this.averageSpeed = averageSpeed;
  }

}
