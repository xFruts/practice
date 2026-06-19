public class Calculator {

  Operation operation;

  public Calculator(Operation operation) {
    this.operation = operation;
  }

  public double calculate(double a, double b) {
    return operation.getResult(a, b);
  }
}
