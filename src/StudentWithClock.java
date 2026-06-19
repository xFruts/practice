import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudentWithClock extends Student {

  @Override
  public void learn() {
    super.learn();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime now =  LocalTime.now();
    System.out.println("Текущее время: " + now.format(dtf));
  }
}
