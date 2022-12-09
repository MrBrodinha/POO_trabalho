import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FuncConsulta {
	public static void criarC(ArrayList<Consulta> consultas,  Utente u, ArrayList<Profissional> p) {
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

	public static void removerC(ArrayList<Consulta> c, ArrayList<Utente> u, int i, ArrayList<Profissional> p) {
		System.out.println("Consultas: " + u.get(i));
		System.out.println("Qual o nome do Profissional responsável pela consulta? ");
		String nom = Ler.umaString();

		System.out.println("Qual a data? Formato:yyyy-MM-dd HH:mm ");
		String nomeData = Ler.umaString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(nomeData, formatter);

		for (int j = 0; j < c.size(); j++) {
			if (c.get(j).getNomeU() == nom && c.get(j).getData().equals(data)) {
				c.remove(j);
			}
		}
		
		FuncUtentes.atualizarfileU(u);
		FuncProfissional.atualizarfileP(p);
		atualizarfileC(c);
	}

	public static void atualizarfileC(ArrayList<Consulta> c) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Consulta.dat"));
			os.writeObject(c);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}