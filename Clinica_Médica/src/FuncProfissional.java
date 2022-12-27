import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FuncProfissional {

	// Adicionar Profissional
	public static void criarP(ArrayList<Profissional> p) {
		boolean end_loop = false;
		char genero = 'a';
		System.out.println("\nInsira o nome do Profissional: "); // Nome do Profissional
		String nomeP = Ler.umaString();

		System.out.println("\nInsira o género do Profissional: (M/F) "); // Género do Profissional
		while (!end_loop) {
			genero = Ler.umChar();
			if (Character.toUpperCase(genero) != 'M' && Character.toUpperCase(genero) != 'F') {
				System.out.println("Género errado, volte a inserir: (M/F)");
			} else {
				end_loop = true;
			}
		}

		System.out.println("\nInsira o Salário do Profissional: "); // Salário do Profissional
		float sal = Ler.umFloat();

		p.add(new Profissional(new Pessoa(nomeP, genero), sal)); // Adicionar à lista profissionais na Main

		// Atualizar File dos Profissionais
		atualizarfileP(p);

	}

	// Editar nome a Profissional
	public static void editarnomeP(ArrayList<Profissional> p, int pi) {
		System.out.println("\nInsira o novo nome de Profissional:"); // Nome do Profissional
		String nome = Ler.umaString();
		p.get(pi).setNome(nome);

		atualizarfileP(p);
	}

	// Editar Salário a Profissional
	public static void editarSalario(ArrayList<Profissional> p, int pi) {
		System.out.println("\nInsira o novo salário:"); // Salário do Profissional
		float sal = Ler.umFloat();

		p.get(pi).setSalario(sal);
		
		// Atualizar File dos Profissionais
		atualizarfileP(p);
	}

	// Adicionar e Remover Habilitações
	public static void hab(ArrayList<Profissional> p, int pi) {
		System.out.println("\nDeseja adicionar ou remover habilitações?");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Remover");
		int opcao = Ler.umInt();
		int nm; // Quantidade a ser adicionada/removida

		System.out.println("Quantas?");
		if (opcao == 1) {
			nm = Ler.umInt();

			System.out.println("Insira as habilitações a serem adicionadas:");
			for (int i = 0; i < nm; i++) {
				String hab = Ler.umaString();

				p.get(pi).addHab(hab);
			}

		} else if (opcao == 2) {
			nm = Ler.umInt();

			System.out.println("Insira as habilitações a serem removidas:");
			for (int i = 0; i < nm; i++) {
				String hab = Ler.umaString();

				p.get(pi).removerHab(hab);
			}
		}
		
		// Atualizar File dos Profissionais
		atualizarfileP(p);
	}

	// Maior Salário
	public static void biggestG(ArrayList<Profissional> p) {
		float maior = 0;
		String nomeeString = "";

		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getSalario() > maior) {
				maior = p.get(i).getSalario();
				nomeeString = p.get(i).getNome();
			}
			System.out.println("O Profissional com maior salário é " + nomeeString + " com " + maior + " $$.\n");
		}
	}

	// Médico com mais habilitações
	public static void maisHab(ArrayList<Profissional> p) {
		int count = 0;
		int maior = 0;
		String nome = null;

		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.get(i).getHab().size(); j++) {
				count++;
			}
			if (count > maior) {
				maior = count;
				nome = p.get(i).getNome();
			}

		}
		System.out.println("Profissional com mais habilitações é " + nome + " com " + maior + " habilitações.\n");
	}

	// Atualizar File dos Profissionais
	public static void atualizarfileP(ArrayList<Profissional> p) {
		try {
			@SuppressWarnings("resource")
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Profissional.dat"));
			os.writeInt(Profissional.getUltimo());
			os.writeObject(p);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}