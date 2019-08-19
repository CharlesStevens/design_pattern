package com.prototypepattern;


public class Workshop {
	
  public Bike makeJaguar(Bike basicBike) {
    basicBike.makeAdvanced();
    return basicBike;
  }
  
  public static void main(String args[]){
    Bike bike = new Bike();
    Bike basicBike = bike.clone();
    Workshop workShop = new Workshop();
    Bike advancedBike = workShop.makeJaguar(basicBike);
    System.out.println("Prototype Design Pattern: Basic Bike - Model  <<"+ bike.getModel() + ">>  :: advanced/cloned - Model :<<" +advancedBike.getModel()+">>");
  }
}