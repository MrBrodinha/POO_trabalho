import java.util.ArrayList;

public class menu {
	public static void Principal() {
		System.out.println("\nBem-vindo há Clinica Médica! O que deseja fazer?");
		System.out.println("1 - Operações com Utentes");
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
		System.out.println("1 - Remover Utente");
		System.out.println("2 - Ver Utente");
		System.out.println("3 - Editar informaçoes do Utente");
		System.out.println();
		System.out.println("4 - Criar Ficha Técnica para Utente");
		System.out.println("5 - Ver Ficha Técnica de Utente");
		System.out.println("6 - Editar Ficha Técnica de Utente (Medicamentos / Alergias)");
		
		System.out.println("7 - Voltar atrás");
	}
}
