import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainClinicaMédica {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList<Consulta> consultas = new ArrayList<>();
		ArrayList<Utente> utentes = new ArrayList<>();
		ArrayList<Ficha_Tecnica> ficha_tecnica = new ArrayList<>();
		ArrayList<Profissional> profissionais = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		boolean sair1, sair2;
		;
		int opcao1, opcao2, opcao3;

		try {
			ObjectInputStream u = new ObjectInputStream(new FileInputStream("src/file/Utente.dat"));
			utentes = (ArrayList<Utente>) u.readObject();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			ObjectInputStream p = new ObjectInputStream(new FileInputStream("src/file/Profissional.dat"));
			int ult2 = p.readInt();
			Profissional.setUltimo(ult2);
			profissionais = (ArrayList<Profissional>) p.readObject();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			ObjectInputStream c = new ObjectInputStream(new FileInputStream("src/file/Consulta.dat"));
			consultas = (ArrayList<Consulta>) c.readObject();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			ObjectInputStream ft = new ObjectInputStream(new FileInputStream("src/file/FichaTecnica.dat"));
			ficha_tecnica = (ArrayList<Ficha_Tecnica>) ft.readObject();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		sair1 = false;
		while (!sair1) {
			menu.Principal();
			opcao1 = Ler.umInt();
			switch (opcao1) {
			case 1:
				menu.Utente1(utentes);
				opcao2 = Ler.umInt();
				switch (opcao2) {
				case 1:
					FuncUtentes.adicionarU(utentes);
					break;

				case 2:
					for (int i = 0; i < utentes.size(); i++) {
						System.out.print(utentes.get(i).toString());
					}
					System.out.println("\nDeseja realizar operações com que Utente? (Inserir NUS)");
					long NUS = Ler.umLong();

					for (int i = 0; i < utentes.size(); i++) {
						if (utentes.get(i).getNUS() == NUS) {
							sair2 = false;
							while (!sair2) {
								menu.Utente2();
								opcao3 = Ler.umInt();
								
								switch (opcao3) {
								case 1:
									utentes.remove(i);
									FuncUtentes.atualizarfileU(utentes);
									break;
								case 2:
									System.out.println(utentes.get(i).toString());
									break;
								case 3:
									FuncUtentes.editarU(utentes, i);
									break;
								case 4:
									FuncFichaTecnica.criarFT(ficha_tecnica, utentes.get(i));
									break;
								case 5:
									for (int j = 0; j < ficha_tecnica.size(); j++) {
										if (ficha_tecnica.get(j).getNUS() == utentes.get(i).getNUS()) {
											System.out.println(ficha_tecnica.get(j).toString());
										}
									}
									break;
								case 6:
									FuncFichaTecnica.editarFT(ficha_tecnica, utentes.get(i));
									break;
								case 7:
									sair2 = true;
									break;
								default:
									System.out.println("Opção inválida!");
									break;
								}
							}
							break;
						}
					}
					break;
				case 3:
					break;
				default:
					System.out.println("Opção inválida! Voltando ao menu...");
				}
				break;
				
			case 2:
				sair1 = true;
				break;
			}
		}
	}
}
