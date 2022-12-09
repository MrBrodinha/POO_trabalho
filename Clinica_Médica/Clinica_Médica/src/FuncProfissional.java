import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FuncProfissional {
	public static void criarProfissional(ArrayList<Profissional> p) {
		System.out.println("Qual o nome do Profissional? ");
		String nomeP = Ler.umaString();

		Profissional l = new Profissional(nomeP);

		p.add(l);

		atualizarfileP(p);

	}

	public static void editarnomeP(ArrayList<Profissional> p, int ind) {
		System.out.println("Insira o novo nome de Paciente:");
		String nome = Ler.umaString();

		p.get(ind).setNome(nome);

		atualizarfileP(p);
	}

	public static void addHab(ArrayList<Profissional> p, int ind) {
		System.out.println("Que habilitação deseja adicionar?");
		String hab = Ler.umaString();

		p.get(ind).addHab(hab);
	}

	public static void remHab(ArrayList<Profissional> p, int ind) {
		System.out.println("Que habilitação deseja remover?");
		String hab = Ler.umaString();

		p.get(ind).removerHab(hab);

	}

	public static void removerP(ArrayList<Profissional> p) {
		System.out.println("Qual o número do profissional? ");
		int id = Ler.umInt();

		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getNumero() == id) {
				p.remove(i);
			}
		}

		atualizarfileP(p);
	}

	public static void atualizarfileP(ArrayList<Profissional> p) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/file/Profissional.dat"));
			os.writeObject(p);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}