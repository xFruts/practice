package ru.maxow.springpractice.operation;

import org.springframework.stereotype.Component;

@Component
public class Subtractor implements Operation {

  @Override
  public double getResult(double a, double b) {
    return a - b;
  }
}