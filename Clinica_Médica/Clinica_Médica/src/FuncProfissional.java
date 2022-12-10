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
		float sal = Ler.umFloat();

		p.add(new Profissional(nomeP, sal));

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
	public static void editarnomeP(ArrayList<Profissional> p, int pi) {
		System.out.println("Insira o novo nome de Profissional:");
		String nome = Ler.umaString();

		p.get(pi).setNome(nome);

		atualizarfileP(p);
	}
	public static void editarSalario(ArrayList<Profissional> p, int pi) {
		System.out.println("Insira o novo salário:");
		float sal = Ler.umFloat();

		
		p.get(pi).setSalario(sal);
		
		
		atualizarfileP(p);
	}
	
	public static void hab(ArrayList<Profissional> p, int pi) {
		System.out.println("Habilitações " + p.get(pi).getHab().toString());
		System.out.println("Deseja remover ou adicionar Habilitações? (adicionar/remover)");
		String opcao = Ler.umaString();
		int nm;
		
		if(opcao.toUpperCase().equals("ADICIONAR")) {
			System.out.println("Deseja adicionar quantas habilitações?");
			nm = Ler.umInt();
			
			for(int i = 0; i < nm; i++) {
				System.out.println("Que habilitação deseja adicionar?");
				String hab = Ler.umaString();

				p.get(pi).addHab(hab);
			}
		}
		else if(opcao.toUpperCase().equals("REMOVER")) {
			System.out.println("Deseja remover quantas habilitações?");
			nm = Ler.umInt();
			
			for(int i = 0; i < nm; i++) {
				System.out.println("Que habilitação deseja remover?");
				String hab = Ler.umaString();

				p.get(pi).removerHab(hab);
			}
		}
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