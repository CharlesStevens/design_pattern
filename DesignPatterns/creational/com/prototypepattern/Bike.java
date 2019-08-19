package com.prototypepattern;


class Bike implements Cloneable {
  private int gears;
  private String bikeType;
  private String model;
  public Bike() {
    bikeType = "Standard";
    model = "Leopard";
    gears = 4;
  }
 
  public Bike clone() {
    return new Bike();
  }
 
  public void makeAdvanced() {
    bikeType = "Advanced";
    model = "Jaguar";
    gears = 6;
  }
  public String getModel(){
    return model;
  }
}
 