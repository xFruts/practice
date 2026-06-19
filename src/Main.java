import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите число a: ");
    double a = scanner.nextDouble();
    System.out.print("Введите число b: ");
    double b = scanner.nextDouble();

    Calculator adderCalculator = new Calculator(new Adder());
    System.out.println("Результат сложения a и b: " + adderCalculator.calculate(a, b));

    Calculator subtractorCalculator = new Calculator(new Subtractor());
    System.out.println("Результат вычитания a и b: " + subtractorCalculator.calculate(a, b));

    Calculator multiplierCalculator = new Calculator(new Multiplier());
    System.out.println("Результат умножения a и b: " + multiplierCalculator.calculate(a, b));

    Calculator dividerCalculator = new Calculator(new Divider());
    System.out.println("Результат деления a и b: " + dividerCalculator.calculate(a, b));
  }
}