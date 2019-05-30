package br.com.tardelli.model;

public class Pilot {

  private String id;
  private String name;

  public Pilot(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Pilot(Integer id, String name) {
    this.id = id.toString();
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
