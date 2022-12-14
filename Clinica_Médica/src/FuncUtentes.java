import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FuncUtentes {
	
	//Adicionar Utentes
	public static void adicionarU(ArrayList<Utente> u) {
		boolean end_loop = false;
		System.out.println("\nInsira o nome do Utente:"); // Nome Utente
		String nome = Ler.umaString();

		System.out.println("Insira o Nº de Utente de Sáude:"); //NUS Utente
		long NUS = Ler.umLong();

		// Como NUS é único, verifica se o NUS é inexistente na lista de Utentes
		for (int i = 0; i < u.size(); i++) {
			if (u.get(i).getNUS() == NUS) {
				System.out.println("Utente com este Nº de Utente de Sáude já existe");
				return;
			}
		}

		System.out.println("Insira o género do Utente: (M/F)"); //Género do Utente
		char genero = 'l';
		while (!end_loop) {
			genero = Ler.umChar();
			if (Character.toUpperCase(genero) != 'M' && Character.toUpperCase(genero) != 'F') {
				System.out.println("Género errado, volte a inserir: (M/F)");
			} else {
				end_loop = true;
			}
		}
		u.add(new Utente(new Pessoa(nome, genero), NUS));

		atualizarfileU(u);
	}

	public static void editarU(ArrayList<Utente> u, int ind) {
		boolean end_loop = false;

		System.out.println("\nInsira o novo nome do Utente:");
		String nome = Ler.umaString();

		System.out.println("Insira o novo Nº de Utente de Sáude:");
		long NUS = Ler.umLong();

		System.out.println("Insira o novo género do Utente: (M/F)");
		char genero = 'l';
		while (!end_loop) {
			genero = Ler.umChar();
			if (Character.toUpperCase(genero) != 'M' && Character.toUpperCase(genero) != 'F') {
				System.out.println("Género errado, volte a inserir: (M/F)");
			} else {
				end_loop = true;
			}
		}
		u.get(ind).setNome(nome);
		u.get(ind).setNUS(NUS);
		u.get(ind).setGenero(Character.toUpperCase(genero));

		atualizarfileU(u);
	}
	
	//Número de homens e mulheres
	public static void NrHeM(ArrayList<Utente> u) {
		int macho = 0;
		int femia = 0;
		for (int i = 0; i < u.size(); i++) {
			if (u.get(i).getGenero() == 'M') {
				macho++;
			} else {
				femia++;
			}
			System.out.println("Nesta Clínica exitem: " +  macho + " homens e " + femia + " mulheres\n");
		}
	}

	public static void atualizarfileU(ArrayList<Utente> u) {
		try {
			@SuppressWarnings("resource")
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Utente.dat"));
			os.writeObject(u);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}