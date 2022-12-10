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
		ArrayList<Profissional> profissionais = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		boolean sair1, sair2, sair3, sair4;
		int opcao1, opcao2, opcao3, opcao4;

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

		sair1 = false;
		while (!sair1) {
			System.out.println(profissionais.toString());
			menu.Principal();
			opcao1 = Ler.umInt();
			switch (opcao1) {
			case 1:
				sair2 = false;
				while (!sair2) {
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

						sair3 = false;
						for (int i = 0; i < utentes.size(); i++) {
							if (utentes.get(i).getNUS() == NUS) {
								while (!sair3) {
									menu.Utente2();
									opcao3 = Ler.umInt();

									switch (opcao3) {
									case 1:
										System.out.println(utentes.get(i).toString());
										break;

									case 2:
										utentes.remove(i);
										FuncUtentes.atualizarfileU(utentes);
										sair3 = true;
										break;

									case 3:
										FuncUtentes.editarU(utentes, i);
										break;

									case 4:
										if (profissionais.size() != 0) {
											System.out.println("Qual o número do Profissional?");
											int n = Ler.umInt();

											for (int j = 0; j < profissionais.size(); j++) {
												if (profissionais.get(j).getNumero() == n) {
													utentes.get(j).setMedicoFamilia(profissionais.get(j));
												} else if (j == profissionais.size() - 1) {
													System.out.println("Tal Profissional não existe");
												}
											}
										} else {
											System.out.println("A lista de Profissionais encontrasse de momento vazia");
										}
										break;

									case 5:
										FuncFichaTecnica.criarFT(utentes, i);
										break;

									case 6:
										sair4 = false;
										while (!sair4) {
											menu.ft1();
											opcao4 = Ler.umInt();

											switch (opcao4) {
											case 1:
												System.out.println(utentes.get(i).getFT().toString());
												break;
											case 2:
												FuncFichaTecnica.Medicamentos(utentes, i);
												break;
											case 3:
												FuncFichaTecnica.Alergias(utentes, i);
												break;
											case 4:
												sair4 = true;
												break;
											default:
												System.out.println("Opção inválida!");
												break;

											}
										}
										break;

									case 7:
										sair3 = true;
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
						sair2 = true;
						break;
					default:
						System.out.println("Opção inválida!");
						break;
					}
				}
				break;

			case 2:
				System.out.println(
						"\nDeseja realizar operações nas consultas de qual Utente? (Inserir Nº de Utente de Sáude)");
				long NUS = Ler.umLong();

				sair2 = false;
				for (int i = 0; i < utentes.size(); i++) {
					if (utentes.get(i).getNUS() == NUS) {
						while (!sair2) {
							menu.Consulta1();
							opcao3 = Ler.umInt();

							switch (opcao3) {
							case 1:
								System.out.println(utentes.get(i).getFT().getConsultas().toString());
								break;
							case 2:
								break;
							case 3:
								break;
							case 4:
								sair2 = true;
								break;
							}
						}
					}
				}
				break;
			case 3:
				if(utentes.size() != 0 && profissionais.size() != 0) {
					int ui = -1, pi = -1;
					System.out.println("\nInsira o NUS do utente a que deseja marcar a consulta");
					NUS = Ler.umLong();
					for (int i = 0; i < utentes.size(); i++) {
						if(utentes.get(i).getNUS() == NUS && utentes.get(i).getFT() == null) {
							System.out.println("Utente não tem Ficha Técnica! Voltando ao menu...");
							System.out.println("Sugestão: Para criar uma Ficha Técnica, selecione \"Operações com Utentes\" e se seguida \"Editar Utente\" ");
						}
					else if (utentes.get(i).getNUS() == NUS) {
							ui = i;
							i = utentes.size();
						} else if (utentes.size() - 1 == i) {
							System.out.println("Inseriu o NUS de um utente inexistente, voltando ao menu....");
						}
					}
					if (ui != -1) {
						System.out
								.println("\nInsira o nu"
										+ "mero completo registado do Profissional que vai realizar a consulta");
						long numero = Ler.umLong();
						for (int i = 0; i < profissionais.size(); i++) {
							if (profissionais.get(i).getNumero() == numero) {
								pi = i;
								i = profissionais.size();
							} else if (profissionais.size() - 1 == i) {
								System.out.println("Inseriu o nome de um profissional inexistente, voltando ao menu...");
							}
						}
						if (pi != -1) {
							try {
								FuncConsulta.criarC(consultas, utentes, ui, profissionais, pi);
							} catch (ConsultaInvalida e) {
								System.out.println(e.getMessage());
							}
						}
					}
				}
				else {System.out.println("Não existem Utentes ou Profissionais suficientes nesta Clinica para marcar Consulta");}
				break;
			case 4:
				sair1 = true;
				break;
			}
		}
	}
}
