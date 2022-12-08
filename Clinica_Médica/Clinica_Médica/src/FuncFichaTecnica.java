import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FuncFichaTecnica {
	public static void criarFT(ArrayList<Ficha_Tecnica> FT, Utente u) {
		String opcao;
		System.out.println(
				"Aviso: Se o Utente já tem ficha técnica, criando uma nova vai eliminar a existente, deseja continuar? (Sim/Não)");
		opcao = Ler.umaString();

		if (opcao.toUpperCase().equals("SIM")) {
			for (int i = 0; i < FT.size(); i++) {
				if (FT.get(i).getNUS() == u.getNUS()) {
					FT.remove(i);
				}
			}

			FT.add(new Ficha_Tecnica(u));
			System.out.println("Ficha Técnica criada com sucesso");

		} else if (opcao.toUpperCase().equals("NÃO")) {
			System.out.println("Voltando ao menu...");
			return;
		} else {
			System.out.println("Opção inválida! Voltando ao menu...");
			return;
		}

		atualizarfileFT(FT);
	}
	
	public static void editarFT(ArrayList<Ficha_Tecnica> FT, Utente u) {
		for (int i = 0; i < FT.size(); i++) {
			if (FT.get(i).getNUS() == u.getNUS()) {
				System.out.println("Deseja adicionar quantos medicamentos?");
				int nm = Ler.umInt();
				
				for(int j = 1; j <= nm; j++) {
					if(j==1) {
						System.out.println("Insira os medicamentos: (Caso eles já se encontrem na lista, não iremos adicionar");
					}
					
					String nome = Ler.umaString();
					
					if(!FT.get(i).getMedicamentos().contains(nome)) {
						FT.get(i).addMedicamentos(nome);
					}
				}
				
				System.out.println("Deseja adicionar quantas Alergias?");
				nm = Ler.umInt();
				
				for(int j = 1; j <= nm; j++) {
					if(j==1) {
						System.out.println("Insira as Alergias: (Caso elas já se encontrem na lista, não iremos adicionar");
					}
					String nome = Ler.umaString();
					
					if(!FT.get(i).getAlergias().contains(nome)) {
						FT.get(i).addAlergias(nome);
					}
				}
			}
		}
		atualizarfileFT(FT);
	}

	public static void atualizarfileFT(ArrayList<Ficha_Tecnica> FT) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/FichaTecnica.dat"));
			os.writeObject(FT);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
