package ru.maxow.springpractice.operation;

import org.springframework.stereotype.Component;

@Component
public class Divider implements Operation {

  @Override
  public double getResult(double a, double b) {
    return a / b;
  }
}