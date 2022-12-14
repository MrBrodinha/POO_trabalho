import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FuncConsulta {
	// Cria a consulta e adiciona à Ficha Técnica do Utente, ás Consultas e aos
	// Utentes
	// "Throws" ConsultaInvalida no caso de Profissional/Utente ter consulta a essa
	// hora ou caso a data seja passada
	public static void criarC(ArrayList<Consulta> c, ArrayList<Utente> u, int ui, ArrayList<Profissional> p, int pi)
			throws ConsultaInvalida {
		// Existe este Try para dar "Catch" a datas inválidas, ou seja, caso o formato
		// esteja errado ou insira um mês/dia inválida
		try {
			System.out.println();
			System.out.println("Insira o motivo para a marcação desta consulta: "); // Motivo
			String motivo = Ler.umaString();

			System.out.println("\nInsira a data onde se vai realizar a consulta: (Formato:yyyy-MM-dd HH:mm)"); // Data
			String nomeData = Ler.umaString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime data = LocalDateTime.parse(nomeData, formatter); // Converte uma String do tipo "yyyy-MM-dd
																			// HH:mm" numa data yyyy-MM-ddTHH:mm

			// Verifica se data é futura à data atual
			if (data.isBefore(LocalDateTime.now())) {
				throw new ConsultaInvalida("\nData já passou!");
			}

			// Verifica se o Profissional tem consultas nessa hora
			for (int j = 0; j < p.get(pi).getConsultas().size(); j++) {
				if (p.get(pi).getConsultas().get(j).getData().isEqual(data)) {
					throw new ConsultaInvalida("\nProfissional já tem consulta marcada a essa hora");
				}
			}

			// Verifica se Utente tem consultas nessa hora
			for (int j = 0; j < u.get(ui).getFT().getConsultas().size(); j++) {
				// u -> Obtemos Ficha Técnica -> Obtemos Consultas
				if (u.get(ui).getFT().getConsultas().get(j).getData().isEqual(data)) {
					throw new ConsultaInvalida("\nUtente já tem consulta marcada a essa hora");
				}
			}

			// Cria a consulta caso passe pelas verificações anteriores
			Consulta c1 = new Consulta(motivo, u.get(ui), p.get(pi), data);

			// Adiciona ás respetivas listas de cada classe (Profissional | Utentes -> Ficha
			// Técnica | Consulas)
			c.add((Consulta) c1.clone());
			u.get(ui).getFT().addConsulta((Consulta) c1.clone());
			p.get(pi).addConsulta((Consulta) c1.clone());

			// Print das informações da consulta
			System.out.println();
			System.out.println("------------------------------------------------------------");
			System.out.println(c1.toString());
			System.out.println();
			System.out.println("------------------------------------------------------------");
			System.out.println("Consulta criada com sucesso!\n");
		} catch (DateTimeException e) {
			System.out.println("\nData inválida");
		}

		// Atualiza os respetivos files (Consultas | Utentes | Profissionais)
		atualizarfileC(c);
		FuncUtentes.atualizarfileU(u);
		FuncProfissional.atualizarfileP(p);
	}

	// Remove Consultas pelo Utente e Data
	// Remove ás listas Utente -> Ficha Técnica | Profissioanl | Consultas
	public static void removerC(ArrayList<Consulta> c, ArrayList<Utente> u, int ui, ArrayList<Profissional> p) {
		System.out.println();
		System.out.println(u.get(ui).getFT().getConsultas().toString()); // Dá print ás consultas todas do utente para
																			// remover mais facilmente
		System.out.println("Insira a data da consulta a ser removida: (Formato:yyyy-MM-dd HH:mm)");
		String nomeData = Ler.umaString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(nomeData, formatter); // Converte uma String do tipo "yyyy-MM-dd
																		// HH:mm" numa data yyyy-MM-ddTHH:mm
		
		// Variável que se for 3, é porque a consulta foi removida com sucesso, caso
		// seja 0 ou diferente é porque algo correu mal ou não existe
		int consulta = 0;
		
		//Remove consultas aos Utentes (Utentes -> Ficha Técnica -> Consultas)
		for (int i = 0; i < u.get(ui).getFT().getConsultas().size(); i++) {
			if (u.get(ui).getFT().getConsultas().get(i).getData().isEqual(data)) {
				u.get(ui).getFT().removeConsulta(i);
				consulta++;
			}
		}
		
		//Remove consultas à lista consulta
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getData().isEqual(data) && c.get(i).getUtente().equals(u.get(ui))) {
				c.remove(i);
				consulta++;
			}
		}

		//Remove consultas aos Profissionais
		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.get(i).getConsultas().size(); j++) {
				if (p.get(i).getConsultas().get(j).getData().isEqual(data)
						&& p.get(i).getConsultas().get(j).getUtente().equals(u.get(ui))) {
					p.get(i).removeConsulta(i);
					consulta++;
				}
			}
		}
		
		if (consulta == 3) {
			System.out.println("\nConsulta removida com sucesso!");
		} else {
			System.out.println("\nConsulta não existe");
		}

		// Atualiza os respetivos files (Consultas | Utentes | Profissionais)
		FuncUtentes.atualizarfileU(u);
		FuncProfissional.atualizarfileP(p);
		atualizarfileC(c);
	}

	//Função para atualizar o file das Consultas
	public static void atualizarfileC(ArrayList<Consulta> c) {
		try {
			@SuppressWarnings("resource")
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Consulta.dat"));
			os.writeObject(c);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}