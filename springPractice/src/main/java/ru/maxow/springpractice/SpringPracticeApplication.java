package ru.maxow.springpractice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.maxow.springpractice.operation.Calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class SpringPracticeApplication {

  private final Calculator calculator;

  public SpringPracticeApplication(@Qualifier("calculator") Calculator calculator) {
    this.calculator = calculator;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringPracticeApplication.class, args);
  }

  @Bean
  public boolean outToConsole() {
    Scanner in = new Scanner(System.in);
    String operationType;
    double a, b;

    try {
      System.out.println("Введите число a");
      a = in.nextDouble();
      System.out.println("Введите число b");
      b = in.nextDouble();
      System.out.println("Введите тип операции: " + calculator.getSupportedOperations());
      operationType = in.next();
    } catch (InputMismatchException e) {
      System.out.println("Ошибка: Введено не число");
      return true;
    }

    try {
      calculator.calc(operationType, a, b);
    } catch (Exception e) {
      System.out.println("Ошибка: Операция не найдена");
    }
    return true;
  }

}
