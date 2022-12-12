import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FuncConsulta {
	public static void criarC(ArrayList<Consulta> c, ArrayList<Utente> u, int ui, ArrayList<Profissional> p, int pi)
			throws ConsultaInvalida {
		try {
			System.out.println();
			System.out.println("Insira o motivo para a marcação desta consulta: ");
			String motivo = Ler.umaString();

			System.out.println("\nInsira a data onde se vai realizar a consulta: (Formato:yyyy-MM-dd HH:mm)");
			String nomeData = Ler.umaString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime data = LocalDateTime.parse(nomeData, formatter);

			if (data.isBefore(LocalDateTime.now())) {
				throw new ConsultaInvalida("\nData já passou!");
			}
			for (int j = 0; j < p.get(pi).getConsultas().size(); j++) {
				if (p.get(pi).getConsultas().get(j).getData().isEqual(data)) {
					throw new ConsultaInvalida("\nProfissional já tem consulta marcada a essa hora");
				}
			}

			for (int j = 0; j < u.get(ui).getFT().getConsultas().size(); j++) {
				if (u.get(ui).getFT().getConsultas().get(j).getData().isEqual(data)) {
					throw new ConsultaInvalida("\nUtente já tem consulta marcada a essa hora");
				}
			}

			Consulta c1 = new Consulta(motivo, u.get(ui), p.get(pi), data);
			c.add((Consulta) c1.clone());
			u.get(ui).getFT().addConsulta((Consulta) c1.clone());
			p.get(pi).addConsulta((Consulta) c1.clone());
			System.out.println();
			System.out.println("------------------------------------------------------------");
			System.out.println(c1.toString());
			System.out.println();
			System.out.println("------------------------------------------------------------");
			System.out.println("Consulta criada com sucesso!\n");
		} catch (DateTimeException e) {
			System.out.println("\nData inválida");
		}
		atualizarfileC(c);
		FuncUtentes.atualizarfileU(u);
		FuncProfissional.atualizarfileP(p);
	}

	public static void removerC(ArrayList<Consulta> c, ArrayList<Utente> u, int ui, ArrayList<Profissional> p) {
		System.out.println();
		System.out.println(u.get(ui).getFT().getConsultas().toString());
		System.out.println("Insira a data da consulta a ser removida: (Formato:yyyy-MM-dd HH:mm)");
		String nomeData = Ler.umaString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(nomeData, formatter);
		int consulta = 0;

		for (int i = 0; i < u.get(ui).getFT().getConsultas().size(); i++) {
			if (u.get(ui).getFT().getConsultas().get(i).getData().isEqual(data)) {
				u.get(ui).getFT().removeConsulta(i);
				consulta++;
			}
		}

		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getData().isEqual(data) && c.get(i).getUtente().equals(u.get(ui))) {
				c.remove(i);
				consulta++;
			}
		}

		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.get(i).getConsultas().size(); j++) {
				if (p.get(i).getConsultas().get(j).getData().isEqual(data)
						&& p.get(i).getConsultas().get(j).getUtente().equals(u.get(ui))) {
					p.get(i).removeConsulta(i);
					consulta++;
				}
			}
		}
		
		if(consulta == 3) {
			System.out.println("\nConsulta removida com sucesso!");
		}
		else {
			System.out.println("\nConsulta não existe");
		}

		FuncUtentes.atualizarfileU(u);
		FuncProfissional.atualizarfileP(p);
		atualizarfileC(c);
	}

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