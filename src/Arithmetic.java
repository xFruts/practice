import java.util.stream.LongStream;

public final class Arithmetic {
  public static long getArithmeticProgressionSum(int firstNumber, int secondNumber) {
    return LongStream.range(firstNumber, secondNumber)
        .sum();
  }
}
