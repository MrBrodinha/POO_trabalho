import java.util.ArrayList;

public class FuncFichaTecnica {
	//Adicionar/Remover medicamentos
	public static void Medicamentos(ArrayList<Utente> u, int i) {
		System.out.println("\nQuer adicionar ou remover medicamentos");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Remover");
		int opcao = Ler.umInt();
		int nm; //Quantidade de medicamentos a ser adicionado/removido

		if (opcao == 1) {
			System.out.println("Quantos?");
			nm = Ler.umInt();
			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println(
							"Insira os medicamentos: (Caso eles já se encontrem na lista, não iremos adicionar");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().addMedicamentos(nome);
				
			}
		} else if (opcao == 2) {
			System.out.println("Quantos?");
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
			System.out.println("\nOpção inválida");
		}
		
		//Atualizar file dos Utentes porque é lá que se encontra as Fichas Técnicas
		FuncUtentes.atualizarfileU(u);
	}
	
	//Adicionar/Remover Alergias
	public static void Alergias(ArrayList<Utente> u, int i) {
		System.out.println("\nDeseja adicionar ou remover Alergias? (adicionar/remover)");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Remover");
		int opcao = Ler.umInt();
		int nm; //Quantidade de alerigas a ser adicionada/removida
	
		if (opcao == 1) {
			System.out.println("Quantas?");
			nm = Ler.umInt();

			for (int j = 1; j <= nm; j++) {
				if (j == 1) {
					System.out.println(
							"Insira as alergias: (Caso eles já se encontrem na lista, não iremos adicionar");
				}
				String nome = Ler.umaString();
				u.get(i).getFT().addAlergias(nome);
				
			}
		} else if (opcao == 2) {
			System.out.println("Quantas?");
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
			System.out.println("\nOpção inválida");
		}
		
		//Atualizar file dos Utentes porque é lá que se encontra as Fichas Técnicas
		FuncUtentes.atualizarfileU(u);
	}

}
