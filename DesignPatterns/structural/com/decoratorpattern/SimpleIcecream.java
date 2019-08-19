package com.decoratorpattern;

public class SimpleIcecream implements Icecream {
 
  @Override
  public String makeIcecream() {
    return "Base Icecream";
  }
}
