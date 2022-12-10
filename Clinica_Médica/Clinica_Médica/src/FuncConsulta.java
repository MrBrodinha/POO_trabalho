import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FuncConsulta {
	public static void criarC(ArrayList<Consulta> c, ArrayList<Utente> u, int ui, ArrayList<Profissional> p, int pi)
			throws ConsultaInvalida {

		System.out.println("Qual a data? Formato:yyyy-MM-dd HH:mm");
		String nomeData = Ler.umaString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(nomeData, formatter);

		for (int j = 0; j < p.get(pi).getConsultas().size(); j++) {
			if (p.get(pi).getConsultas().get(j).getData().isEqual(data)) {
				throw new ConsultaInvalida("Profissional já tem consulta marcada a essa hora, escolha outra hora. Formato:yyyy-MM-dd HH:mm");
			}
		}

		for (int j = 0; j < u.get(ui).getFT().getConsultas().size(); j++) {
			if (u.get(ui).getFT().getConsultas().get(j).getData().isEqual(data)) {
				throw new ConsultaInvalida("Utente já tem consulta marcada a essa hora, escolha outra hora. Formato:yyyy-MM-dd HH:mm");
			}
		}
		
		Consulta c1 = new Consulta(u.get(ui), p.get(pi), data);
		c.add(c1);
		u.get(ui).getFT().addConsulta(c1);
		p.get(pi).addConsulta(c1);
		System.out.println("Consulta criada com sucesso");
		atualizarfileC(c);
		FuncUtentes.atualizarfileU(u);
		FuncProfissional.atualizarfileP(p);
	}

	public static void removerC(ArrayList<Consulta> c, ArrayList<Utente> u, int i, ArrayList<Profissional> p) {
		System.out.println("Consultas: " + u.get(i));
		System.out.println("Qual o nome do Profissional responsável pela consulta? ");
		String nom = Ler.umaString();

		System.out.println("Qual a data? Formato:yyyy-MM-dd HH:mm ");
		String nomeData = Ler.umaString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(nomeData, formatter);



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