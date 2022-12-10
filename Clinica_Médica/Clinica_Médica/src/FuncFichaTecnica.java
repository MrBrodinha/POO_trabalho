import java.util.ArrayList;

public class FuncFichaTecnica {
	public static void criarFT(ArrayList<Utente> u, int i) {
		String opcao;
		System.out.println(
				"Aviso: Se o Utente já tem ficha técnica, criando uma nova vai eliminar a existente, deseja continuar? (Sim/Não)");
		opcao = Ler.umaString();

		if (opcao.toUpperCase().equals("SIM")) {
			u.get(i).criarFT();
			System.out.println("Ficha Técnica criada com sucesso");

		} else if (opcao.toUpperCase().equals("NÃO")) {
			System.out.println("Voltando ao menu...");
			return;
		} else {
			System.out.println("Opção inválida! Voltando ao menu...");
			return;
		}

		FuncUtentes.atualizarfileU(u);
	}

	public static void Medicamentos(ArrayList<Utente> u, int i) {
		System.out.println("Medicamentos: " + u.get(i).getFT().getMedicamentos().toString());
		System.out.println("Deseja remover ou adicionar medicamentos? (adicionar/remover)");
		String opcao = Ler.umaString();
		int nm;

		if (opcao.toUpperCase().equals("ADICIONAR")) {
			System.out.println("Deseja adicionar quantos medicamentos?");
			nm = Ler.umInt();
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println(
							"Insira os medicamentos: (Caso eles já se encontrem na lista, não iremos adicionar");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().addMedicamentos(nome);
				
			}
		} else if (!opcao.toUpperCase().equals("REMOVER")) {
			System.out.println("Deseja remover quantos medicamentos?");
			nm = Ler.umInt();
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println("Insira os medicamentos: ");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().removeMedicamentos(nome);
			}
		}
		else {
			System.out.println("Opção inválida, voltando ao menu...");
		}
		FuncUtentes.atualizarfileU(u);
	}
	
	public static void Alergias(ArrayList<Utente> u, int i) {
		System.out.println("Alergias: " + u.get(i).getFT().getAlergias().toString());
		System.out.println("Deseja remover ou adicionar Alergias? (adicionar/remover)");
		String opcao = Ler.umaString();
		int nm;

		if (opcao.toUpperCase().equals("ADICIONAR")) {
			System.out.println("Deseja adicionar quantas alergias?");
			nm = Ler.umInt();
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println(
							"Insira as alergias: (Caso eles já se encontrem na lista, não iremos adicionar");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().addAlergias(nome);
				
			}
		} else if (!opcao.toUpperCase().equals("REMOVER")) {
			System.out.println("Deseja remover quantas alergias?");
			nm = Ler.umInt();
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println("Insira as alergias: ");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().removeAlergias(nome);
			}
		}
		else {
			System.out.println("Opção inválida, voltando ao menu...");
		}
		FuncUtentes.atualizarfileU(u);
	}

}
