package br.com.tardelli.dto;

import br.com.tardelli.model.LogLap;
import br.com.tardelli.model.Pilot;
import br.com.tardelli.utils.KartRaceUtils;

import java.time.LocalTime;

public class FinalResultsDTO {

  private Integer position;
  private Pilot pilot;
  private Integer completedLaps;
  private LocalTime totalTimeOfTheKartRace;
  private LocalTime bestLap;
  private Double averageSpeed;
  private String hourFinishRace;
  private LogLap lastLap;
  private String arrivalDifference;
  private Double averageSpeedyInRace;
  private LogLap bestLapRace;

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Pilot getPilot() {
    return pilot;
  }

  public void setPilot(Pilot pilot) {
    this.pilot = pilot;
  }

  public Integer getCompletedLaps() {
    return completedLaps;
  }

  public void setCompletedLaps(Integer completedLaps) {
    this.completedLaps = completedLaps;
  }

  public LocalTime getTotalTimeOfTheKartRace() {
    return totalTimeOfTheKartRace;
  }

  public void setTotalTimeOfTheKartRace(LocalTime totalTimeOfTheKartRace) {
    this.totalTimeOfTheKartRace = totalTimeOfTheKartRace;
  }

  public LocalTime getBestLap() {
    return bestLap;
  }

  public void setBestLap(LocalTime bestLap) {
    this.bestLap = bestLap;
  }

  public Double getAverageSpeed() {
    return averageSpeed;
  }

  public void setAverageSpeed(Double averageSpeed) {
    this.averageSpeed = averageSpeed;
  }

  public String getHourFinishRace() {
    return hourFinishRace;
  }

  public void setHourFinishRace(String hourFinishRace) {
    this.hourFinishRace = hourFinishRace;
  }

  public LogLap getLastLap() {
    return lastLap;
  }

  public void setLastLap(LogLap lastLap) {
    this.lastLap = lastLap;
  }

  public String getArrivalDifference() {
    return arrivalDifference;
  }

  public void setArrivalDifference(String arrivalDifference) {
    this.arrivalDifference = arrivalDifference;
  }

  public Double getAverageSpeedyInRace() {
    return averageSpeedyInRace;
  }

  public void setAverageSpeedyInRace(Double averageSpeedyInRace) {
    this.averageSpeedyInRace = averageSpeedyInRace;
  }

  public LogLap getBestLapRace() {
    return bestLapRace;
  }

  public void setBestLapRace(LogLap bestLapRace) {
    this.bestLapRace = bestLapRace;
  }

  @Override
  public String toString() {
    return position + " - " + pilot.getId() + "-" + String.format("%1$-" + 20 + "s", pilot.getName()) + " " + completedLaps + " voltas completas    Tempo total: " + totalTimeOfTheKartRace.format(KartRaceUtils.HOUR_FORMATTER)
            + "    Melhor volta: " + bestLap + "    Vel. Média: " + String.format("%.2f", averageSpeed) + "    Termino: " + (arrivalDifference == null ? "" : arrivalDifference) + "    Horário término: " + (hourFinishRace == null ? "" : hourFinishRace);
  }
}
