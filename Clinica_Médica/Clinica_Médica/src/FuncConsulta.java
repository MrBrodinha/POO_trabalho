import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FuncConsulta {
	public static void criarConsulta(ArrayList<Consulta> consultas,  Utente u, ArrayList<Profissional> p) {
			System.out.println("Qual o nome do Profissional? ");
			String nomeP = Ler.umaString();

			for(int i = 0; i < p.size(); i++) {
				if(p.get(i).getNome().equals(nomeP)) {
					System.out.println("Qual a data? Formato:yyyy-MM-dd HH:mm ");
					String nomeData = Ler.umaString();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					LocalDateTime data = LocalDateTime.parse(nomeData, formatter);
				}
			}
		atualizarfileC(consultas);

	}

	public static void removerC(ArrayList<Consulta> consultas) {
		System.out.println("Qual o nome do utente? ");
		String nom = Ler.umaString();

		System.out.println("Qual a data? Formato:yyyy-MM-dd HH:mm ");
		String nomeData = Ler.umaString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(nomeData, formatter);

		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).getNomeU() == nom && consultas.get(i).getData().equals(data)) {
				consultas.remove(i);
			}
		}

		atualizarfileC(consultas);
	}

	public static void atualizarfileC(ArrayList<Consulta> consultas) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Consulta.dat"));
			os.writeObject(consultas);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}