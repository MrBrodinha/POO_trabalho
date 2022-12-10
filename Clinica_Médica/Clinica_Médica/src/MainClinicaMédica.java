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
		// para sair de whiles
		boolean sair1, sair2, sair3, sair4;
		// opcções do menu utente indice / profissional indice
		int opcao1, opcao2, opcao3, opcao4, ui = -1, pi = -1;
		;
		long NUS;

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
			menu.Principal();
			opcao1 = Ler.umInt();
			switch (opcao1) {
			case 1:
				if (utentes.size() != 0 && profissionais.size() != 0) {
					menu.consulta();
					opcao2 = Ler.umInt();
					sair2 = false;
					while (!sair2) {
						switch (opcao2) {
						case 1:
							menu.utentes(utentes);
							System.out.println("\nInsira o NUS do utente a que deseja marcar a consulta");
							NUS = Ler.umLong();
							for (int i = 0; i < utentes.size(); i++) {
								if (utentes.get(i).getNUS() == NUS && utentes.get(i).getFT() == null) {
									System.out.println("Utente não tem Ficha Técnica! Voltando ao menu...");
									System.out.println(
											"Sugestão: Para criar uma Ficha Técnica, selecione \"Operações com Utentes\" e se seguida \"Editar Utente\" ");
								} else if (utentes.get(i).getNUS() == NUS) {
									ui = i;
									i = utentes.size();
								} else if (utentes.size() - 1 == i) {
									System.out.println("Inseriu o NUS de um utente inexistente, voltando ao menu....");
								}
							}
							if (ui != -1) {
								menu.profissionais(profissionais);
								System.out
										.println("\nInsira o nu" + "mero do Profissional que vai realizar a consulta");
								long numero = Ler.umLong();
								for (int i = 0; i < profissionais.size(); i++) {
									if (profissionais.get(i).getNumero() == numero) {
										pi = i;
										i = profissionais.size();
									} else if (profissionais.size() - 1 == i) {
										System.out.println(
												"Inseriu o número de um profissional inexistente, voltando ao menu...");
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
							break;

						case 2:
							System.out.println("Insira o NUS do Utente e de seguida o número do Profissional");
							NUS = Ler.umLong();
							long numero = Ler.umLong();
							ui = -1; 
							for (int i = 0; i < utentes.size(); i++) {
								if (utentes.get(i).getNUS() == NUS) {
									ui = i;
								}
							}

							for (int i = 0; i < profissionais.size(); i++) {
								if (profissionais.get(i).getNumero() == numero) {
									pi = i;
								}
							}
							
							if(ui != -1 && pi != -1) {
								FuncConsulta.removerC(consultas, utentes, ui, profissionais, pi);
							}
							break;
						case 3:
							sair2 = true;
							break;
						default:
							System.out.println("Opção Inválida");
						}
					}
				} else {
					System.out.println(
							"Não existem Utentes ou Profissionais suficientes nesta Clinica para marcar Consulta");
				}

			case 2:
				sair2 = false;
				while (!sair2) {
					menu.Utente1(utentes);
					opcao2 = Ler.umInt();
					switch (opcao2) {
					case 1:
						FuncUtentes.adicionarU(utentes);
						break;

					case 2:
						if (utentes.size() != 0) {
							menu.utentes(utentes);
							System.out.println("\nDeseja realizar operações com que Utente? (Inserir NUS)");
							NUS = Ler.umLong();

							sair3 = false;
							for (int i = 0; i < utentes.size(); i++) {
								if (utentes.get(i).getNUS() == NUS) {
									while (!sair3) {
										System.out.println(utentes.get(i));
										menu.Utente2();
										opcao3 = Ler.umInt();

										switch (opcao3) {
										case 1:
											if (utentes.get(i).getFT() != null) {
												System.out.println(utentes.get(i).getFT().getConsultas().toString());
											} else {
												System.out.println(
														"Cliente ainda não tem ficha técnica, então não existem consultas");
											}
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
												System.out.println(
														"A lista de Profissionais encontrasse de momento vazia");
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
						} else {
							System.out.println("Opção Inválida");
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

			case 3:
				sair2 = false;
				while (!sair2) {
					menu.Profissional1(profissionais);
					opcao2 = Ler.umInt();
					switch (opcao2) {
					case 1:
						FuncProfissional.criarP(profissionais);
						break;
					case 2:
						if (profissionais.size() != 0) {
							menu.profissionais(profissionais);

							System.out.println("Insira o número do Profissional a que deseja realizar operações");
							long numero = Ler.umLong();

							for (int i = 0; i < profissionais.size(); i++) {
								if (profissionais.get(i).getNumero() == numero) {
									sair3 = false;
									while (!sair3) {
										System.out.println(profissionais.get(i).toString());
										menu.Profissional2();
										opcao3 = Ler.umInt();

										switch (opcao3) {
										case 1:
											System.out.println(profissionais.get(i).getConsultas().toString());
											break;
										case 2:
											profissionais.remove(i);
											FuncProfissional.atualizarfileP(profissionais);
											sair3 = true;
											break;
										case 3:
											FuncProfissional.editarnomeP(profissionais, i);
											break;
										case 4:
											FuncProfissional.editarSalario(profissionais, i);
											break;
										case 5:
											FuncProfissional.hab(profissionais, i);
											break;
										case 6:
											sair3 = true;
											break;
										default:
											System.out.println("Opção Inválida");
											break;
										}
									}
								}
							}

						} else {
							System.out.println("Opcão Inválida");
						}
						break;
					case 3:
						sair2 = true;
					}
				}
				break;
			case 6:
				sair1 = true;
				break;
			}
		}
	}
}
