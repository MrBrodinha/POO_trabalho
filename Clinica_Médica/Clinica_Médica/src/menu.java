import java.util.ArrayList;

public class menu {
	public static void Principal() {
		System.out.println("\nBem-vindo há Clinica Médica! O que deseja fazer?");
		System.out.println("1 - Operações com Utentes");
		System.out.println("2 - Operações com Consultas");
		System.out.println("3 - Marcar Consulta");
		System.out.println();
	}

	public static void Utente1(ArrayList<Utente> u) {
		if(u.size() != 0) {
		System.out.println("\nQue operações deseja fazer com Utentes?");
		System.out.println("1 - Adicionar Utente");
		System.out.println("2 - Editar Utente");
		System.out.println("3 - Voltar Atrás");
		}
		else {
			System.out.println("\nQue operações deseja fazer com Utentes?");
			System.out.println("1 - Adicionar Utente");
			System.out.println("3 - Voltar Atrás");
		}
	}
	
	public static void Utente2() {
		System.out.println("\nQue operações deseja realizar com este Utente?");
		System.out.println("1 - Ver Utente");
		System.out.println("2 - Remover Utente");
		System.out.println("3 - Editar informaçoes do Utente");
		System.out.println("4 - Adicionar Médico de Família (Profissional)");
		System.out.println();
		System.out.println("5 - Criar Ficha Técnica para Utente");
		System.out.println("6 - Operações com a Ficha Técnica deste Utente");
		System.out.println();
		System.out.println("7 - Voltar atrás");
		System.out.println("AVISO: Para marcar consultas é no menu principal");
	}
	
	public static void ft1() {
		System.out.println("\nQue operações deseja realizar com a Ficha Técnica");
		System.out.println("1 - Ver Ficha Técnica");
		System.out.println("2 - Adicionar/Remover medicamentos");
		System.out.println("3 - Adicionar/Remover alergias");
		System.out.println("4 - Voltar atrás");
	}
	
	public static void Consulta1() {
		System.out.println("\nQue operações deseja realizar com as Consulas?");
		System.out.println("1 - Ver Consulta");
		System.out.println("2 - Remover Consulta");
		System.out.println("3 - Editar Consulta");
		System.out.println("4 - Voltar ao Menu");
		System.out.println("AVISO: Para marcar consultas é no menu principal");
	}
}
