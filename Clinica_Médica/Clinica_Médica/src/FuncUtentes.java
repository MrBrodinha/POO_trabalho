import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FuncUtentes {
	public static void adicionarU(ArrayList<Utente> u) {
		boolean end_loop = false;
		System.out.println("Insira o nome do Utente:");
		String nome = Ler.umaString();

		System.out.println("Insira o Nº de Utente de Sáude:");
		long NUS = Ler.umLong();

		for (int i = 0; i < u.size(); i++) {
			if (u.get(i).getNUS() == NUS) {
				System.out.println("Utente com este Nº de Utente de Sáude já existe, voltando ao menu...");
				return;
			}
		}

		System.out.println("Insira o género do Utente: (M/F)");
		char genero = 'l';
		while (!end_loop) {
			genero = Ler.umChar();
			if (Character.toUpperCase(genero) != 'M' && Character.toUpperCase(genero) != 'F') {
				System.out.println("Género errado, volte a inserir: (M/F)");
			} else {
				end_loop = true;
			}
		}
		u.add(new Utente(nome, NUS, genero));

		atualizarfileU(u);
	}

	public static void editarU(ArrayList<Utente> u, int ind) {
		boolean end_loop = false;

		System.out.println("Insira o novo nome do Utente:");
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
		u.get(ind).setGenero(genero);
		
		atualizarfileU(u);
	}


	public static void atualizarfileU(ArrayList<Utente> u) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Utente.dat"));
			os.writeObject(u);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}