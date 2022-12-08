import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class testerandom {

	public static void main(String[] args) {
		String data = "2023-04-31 10:30";
		String data1 = "2023-04-31 10:30";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
		LocalDateTime dataTime1 = LocalDateTime.parse(data1, formatter);
		Consulta c1 = new Consulta("Emanuel", "Rui", dateTime);
		System.out.println(dateTime.isBefore(dataTime1));
		

	}

}
