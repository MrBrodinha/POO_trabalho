import java.util.ArrayList;

public class FuncFichaTecnica {
	public static void Medicamentos(ArrayList<Utente> u, int i) {
		System.out.println("Quer adicionar ou remover medicamentos");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Remover");
		int opcao = Ler.umInt();
		System.out.println("Quantos?");
		int nm = Ler.umInt();

		if (opcao == 1) {
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println(
							"Insira os medicamentos: (Caso eles já se encontrem na lista, não iremos adicionar");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().addMedicamentos(nome);
				
			}
		} else if (opcao == 2) {
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
		System.out.println("Deseja adicionar ou remover Alergias? (adicionar/remover)");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Remover");
		int opcao = Ler.umInt();
		System.out.println("Quantas?");
		int nm = Ler.umInt();

		if (opcao == 1) {
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println(
							"Insira as alergias: (Caso eles já se encontrem na lista, não iremos adicionar");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().addAlergias(nome);
				
			}
		} else if (opcao == 2) {
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
