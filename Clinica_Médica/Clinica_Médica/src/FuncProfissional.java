import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;


public class FuncProfissional {
	public static void criarP(ArrayList<Profissional> p) {
		System.out.println("Qual o nome do Profissional? ");
		String nomeP = Ler.umaString();
		
		System.out.println("Qual o salário do Profissional?");
		float jj = Ler.umFloat();

		p.add(new Profissional(nomeP, jj));

		atualizarfileP(p);

	}
	
	public static void biggestG(ArrayList<Profissional> p) {
		float maior = 0;
		String nomeeString = null;
		
	
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getSalario() > maior){
			maior = p.get(i).getSalario();
			nomeeString = p.get(i).getNome();
		}
		System.out.println("O profissional com maior salário é "+nomeeString+" com "+maior+" $$.");
	}}
	
	public static void maisHab(ArrayList<Profissional> p) {
		int count = 0;
		int maior = 0;
		String nome=null;
		
		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.get(i).getHab().size(); j++) {
				count++;
			}
			if (count > maior) {
				maior = count;
				nome = p.get(i).getNome();
			}
			
		}
		System.out.println("Profissional com mais habilitações é "+nome+" com "+maior+" habilitações.");
	}
	public static void editarnomeP(ArrayList<Profissional> p, int ind) {
		System.out.println("Insira o novo nome de Paciente:");
		String nome = Ler.umaString();

		p.get(ind).setNome(nome);

		atualizarfileP(p);
	}
	public static void editarSalario(ArrayList<Profissional> p, int ind) {
		System.out.println("Insira o novo salário:");
		float ni = Ler.umFloat();

		
		p.get(ind).setSalario(ni);
		
		
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
			os.writeInt(Profissional.getUltimo());
			os.writeObject(p);
			os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}