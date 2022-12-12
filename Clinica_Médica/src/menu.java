import java.util.ArrayList;

public class menu {
	public static void Principal() {
		System.out.println("Bem-vindo há Clinica Médica! O que deseja fazer?");
		System.out.println("1 - Marcar/Desmarcar Consulta");
		System.out.println("2 - Operações com Utentes");
		System.out.println("3 - Operações com Profissionais");
		System.out.println("4 - Estatísticas");
		System.out.println("5 - Consultas Total");
		System.out.println("6 - Sair");
	}

	public static void Utente1(ArrayList<Utente> u) {

		System.out.println("\nQue operações deseja fazer com Utentes?");
		System.out.println("1 - Adicionar Utente");
		if (u.size() != 0) {
			System.out.println("2 - Editar Utente");
		}
		System.out.println();
		System.out.println("3 - Voltar Atrás");

	}

	public static void Utente2(Utente u) {
		System.out.println("Que operações deseja realizar com este Utente?");
		System.out.println("1 - Remover Utente");
		System.out.println("2 - Editar informaçoes do Utente");
		System.out.println("3 - Adicionar Médico de Família (Profissional)");
		System.out.println();
		System.out.println("4 - Operações com a Ficha Técnica deste Utente");
		System.out.println();
		System.out.println("5 - Futuras Consultas");
		System.out.println();
		System.out.println("6 - Voltar atrás");
	}

	public static void ft1() {
		System.out.println("Que operações deseja realizar com a Ficha Técnica");
		System.out.println("1 - Adicionar/Remover medicamentos");
		System.out.println("2 - Adicionar/Remover alergias");
		System.out.println();
		System.out.println("3 - Voltar atrás");
		System.out.println("AVISO: Para marcar/desmarcar consultas é no menu principal");
	}

	public static void consulta() {
		System.out.println("\nQuer marcar ou desmarcar uma consulta?");
		System.out.println("1 - Marcar");
		System.out.println("2 - Desmarcar");
		System.out.println();
		System.out.println("3 - Voltar Atrás");
	}

	public static void Profissional1(ArrayList<Profissional> p) {

		System.out.println("\nQue operações deseja fazer com Profissionais?");
		System.out.println("1 - Adicionar Profissional");
		if (p.size() != 0) {
			System.out.println("2 - Editar Profissional");
		}
		System.out.println();
		System.out.println("3 - Voltar Atrás");
	}

	public static void Profissional2() {
		System.out.println("Que operações deseja realizar com este Profissional?");
		System.out.println("1 - Remover Profissioanl");
		System.out.println("2 - Alterar o nome a Profissional");
		System.out.println("3 - Alterar Salário do Profissional");
		System.out.println("4 - Adicionar/Remover Habilitações a Profissional");
		System.out.println();
		System.out.println("5 - Consultas Consultas");
		System.out.println();
		System.out.println("6 - Voltar atrás");
	}

	public static void utentes(ArrayList<Utente> u) {
		System.out.println("Utentes:");
		for (int i = 0; i < u.size(); i++) {
			System.out.println(u.get(i).toString());
		}
	}

	public static void profissionais(ArrayList<Profissional> p) {
		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i).toString());
		}
	}

}
