import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainCM {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) {
		ArrayList<Consulta> consultas = new ArrayList<>();
		ArrayList<Utente> utentes = new ArrayList<>();
		ArrayList<Profissional> profissionais = new ArrayList<>();
		// para sair de whiles
		boolean sair1, sair2, sair3, sair4;
		// opções do menu
		int opcao1, opcao2, opcao3, opcao4;
		// utente indice (ui), profissional indice (pi)
		int ui = -1, pi = -1;
		// variáveis extras usadas ao longo do programa
		int contadoru = 0, contadorp = 0;
		long NUS, numero;

		// Ir buscar informaçções aos ficheiros
		// u -> Utentes
		// p -> Profissionais
		// c -> Consultas
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

		// Funções para remover consultas que sejam anteriores à data de hoje
		// Caso remova, avisa ao utilizador no inicio quantas consultas foram removidas
		for (int i = 0; i < utentes.size(); i++) {
			for (int j = 0; j < utentes.get(i).getFT().getConsultas().size(); j++) {
				if (utentes.get(i).getFT().getConsultas().get(j).getData().isBefore(LocalDateTime.now())) {
					utentes.get(i).getFT().removeConsulta(j);
					contadoru++;
					FuncUtentes.atualizarfileU(utentes);
				}
			}
		}
		for (int i = 0; i < profissionais.size(); i++) {
			for (int j = 0; j < profissionais.get(i).getConsultas().size(); j++) {
				if (profissionais.get(i).getConsultas().get(j).getData().isBefore(LocalDateTime.now())) {
					profissionais.get(i).getConsultas().remove(j);
					contadorp++;
					FuncProfissional.atualizarfileP(profissionais);
				}
			}
		}
		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).getData().isBefore(LocalDateTime.now())) {
				consultas.remove(i);
				FuncConsulta.atualizarfileC(consultas);

			}
		}
		if (contadoru != 0 || contadorp != 0) {
			System.out.println("UPDATE ENQUANTO ESTEVE FORA: ");
			System.out.println("Removemos " + contadoru + " consultas de Utentes que já aconteceram");
			System.out.println("Removemos " + contadorp + " consultas de Profissionais que já aconteceram");
		}

		// Onde começa o nosso menu, e onde tudo vai ser realizado
		sair1 = false;
		while (!sair1) {
			menu.Principal();
			opcao1 = Ler.umInt();
			switch (opcao1) {
			// "1 - Marcar/Desmarcar Consulta"
			case 1:
				// Verifica se as listas utentes e profissionais não estão vazias
				if (utentes.size() != 0 && profissionais.size() != 0) {
					sair2 = false;
					while (!sair2) {
						menu.consulta();
						opcao2 = Ler.umInt();

						switch (opcao2) {
						// Marcar Consultas
						case 1:
							// Imprime a lista de utentes para escolher o utente de forma mais fácil
							System.out.println("\n------------------------------------------------------------");
							menu.utentes(utentes);
							System.out.println("------------------------------------------------------------");
							// Pede o NUS do utente
							System.out.println("Insira o NUS do Utente a que deseja marcar a consulta:");
							NUS = Ler.umLong();

							// Procuramos o utente
							for (int i = 0; i < utentes.size(); i++) {
								if (utentes.get(i).getNUS() == NUS) {
									// ui -> Utente Indice
									ui = i;
									i = utentes.size();
								} else if (utentes.size() - 1 == i) {
									System.out.println("\nInseriu o NUS de um Utente inexistente");
									ui = -1;
								}
							}

							// caso o utente não exista ignora o resto
							if (ui != -1) {
								// Imprime a lista de profissionais
								System.out.println("\n------------------------------------------------------------");
								menu.profissionais(profissionais);
								System.out.println("------------------------------------------------------------");
								// pede o número do profissional que vai fazer a consulta
								System.out.println("Insira o número do Profissional que vai realizar a consulta:");
								numero = Ler.umLong();

								// procura o profissional
								for (int i = 0; i < profissionais.size(); i++) {
									if (profissionais.get(i).getNumero() == numero) {
										pi = i;
										i = profissionais.size();
									} else if (profissionais.size() - 1 == i) {
										System.out.println("\nInseriu o número de um Profissional inexistente");
										pi = -1;
									}
								}

								// caso profissional não exista ignora o resto
								if (pi != -1) {
									sair3 = false;
									// dá try para criar a consulta, caso não seja possivel marcar vai gerar uma
									// exceção
									try {
										FuncConsulta.criarC(consultas, utentes, ui, profissionais, pi);
										sair3 = true;
									} catch (ConsultaInvalida e) {
										System.out.println(e.getMessage());
									}
								}
							}

							// dar "reset" a variáveis
							ui = -1;
							pi = -1;
							break;

						// Desmarcar Consultas
						case 2:
							// Insere a lista de Utentes
							System.out.println("\n------------------------------------------------------------");
							menu.utentes(utentes);
							System.out.println("------------------------------------------------------------");
							// Inserir NUS do Utente
							System.out.println("Insira o NUS do utente a que deseja desmarcar a consulta:");
							NUS = Ler.umLong();

							// Procuramos o utente
							for (int i = 0; i < utentes.size(); i++) {
								if (utentes.get(i).getNUS() == NUS) {
									// ui -> Utente Indice
									ui = i;
									i = utentes.size();
								} else if (utentes.size() - 1 == i) {
									System.out.println("\nInseriu o NUS de um Utente inexistente");
									ui = -1;
								}
							}
							if (ui != -1 && utentes.get(ui).getFT().getConsultas().size() != 0) {
								FuncConsulta.removerC(consultas, utentes, ui, profissionais);
							} else if (ui == -1) {
								System.out.println("\nInseriu o NUS de um Utente inexistente");
							} else {
								System.out.println("\nO Utente não tem consultas marcadas");
							}

							// dar "reset" a variáveis
							ui = -1;
							pi = -1;
							break;

						// voltar atrás
						case 3:
							sair2 = true;
							break;

						default:
							System.out.println("\nOpção Inválida");
							break;
						}

					}
				} else {
					System.out.println(
							"\nNão existem Utentes ou Profissionais suficientes nesta Clinica para marcar Consulta");
				}
				break;

			// "2 - Operações com Utentes"
			case 2:
				sair2 = false;
				while (!sair2) {
					menu.Utente1(utentes);
					opcao2 = Ler.umInt();
					switch (opcao2) {
					// Adicionar Utentes
					case 1:
						FuncUtentes.adicionarU(utentes);
						break;

					// Editar Utentes
					case 2:
						if (utentes.size() != 0) {
							// Imprime lista de Utentes
							System.out.println("\n------------------------------------------------------------");
							menu.utentes(utentes);
							System.out.println("------------------------------------------------------------");
							// Inserir NUS to utente a ser alterado
							System.out.println("Insira o NUS do utente a que deseja realizar operações:");
							NUS = Ler.umLong();

							sair3 = false;
							// Procura Utente
							for (int i = 0; i < utentes.size(); i++) {
								if (utentes.get(i).getNUS() == NUS) {
									System.out.println();
									while (!sair3) {
										// Imprime informações do utente de forma diferente
										System.out.println(
												"------------------------------------------------------------");
										System.out.println("Nome: " + utentes.get(i).getNome());
										System.out.println("NUS: " + utentes.get(i).getNUS());
										System.out.println("Género: " + utentes.get(i).getGenero());
										if (utentes.get(i).getMedicoFamilia().getNumero() != 0) {
											System.out.println("Médico de Familia: "
													+ utentes.get(i).getMedicoFamilia().getNome());
										}
										System.out.println(
												"------------------------------------------------------------");
										menu.Utente2(utentes.get(i));
										opcao3 = Ler.umInt();
										if (utentes.get(i).getFT() == null && opcao3 == 5) {
											opcao3 = 10;
										}

										switch (opcao3) {
										// remover utente
										case 1:
											utentes.remove(i);
											System.out.println("Utente removido com sucesso!");
											FuncUtentes.atualizarfileU(utentes);
											sair3 = true;
											break;

										// Editar informações do utente
										case 2:
											FuncUtentes.editarU(utentes, i);
											break;

										// Adicionar médico de familia
										case 3:
											if (profissionais.size() != 0) {
												// imprime lista de profissionais
												System.out.println(
														"------------------------------------------------------------");
												menu.profissionais(profissionais);
												System.out.println(
														"------------------------------------------------------------");
												System.out.println(
														"Insira o número do Profissional que vai se tornar Médico de Familia:");
												int n = Ler.umInt();
												
												//procura Profissional pelo número e depois insere o no Utente
												for (int j = 0; j < profissionais.size(); j++) {
													if (profissionais.get(j).getNumero() == n) {
														utentes.get(i).setMedicoFamilia(profissionais.get(j));
													} else if (j == profissionais.size() - 1) {
														System.out.println(
																"Inseriu o número de um Profissional inexistente");
													}
												}
											} else {
												System.out.println(
														"\nA lista de Profissionais encontrasse de momento vazia");
											}
											break;
											
										//Operações com a ficha técnica
										case 4:
											sair4 = false;
											while (!sair4) {
												// imprime ficha técnica de utente
												System.out.println(
														"\n------------------------------------------------------------");
												System.out.println(utentes.get(i).getFT().toString());
												System.out.println(
														"------------------------------------------------------------");
												menu.ft1();
												opcao4 = Ler.umInt();

												switch (opcao4) {
												//Alterar medicamentos
												case 1:
													FuncFichaTecnica.Medicamentos(utentes, i);
													break;
													
												//Alterar alergias
												case 2:
													FuncFichaTecnica.Alergias(utentes, i);
													break;

												//voltar atrás
												case 3:
													sair4 = true;
													break;

												default:
													System.out.println("Opção inválida");
													break;
												}
												System.out.println();
											}
											break;

										// consultar consultas
										case 5:
											System.out.println(
													"\n------------------------------------------------------------");
											System.out.println("Futuras Consultas: "
													+ utentes.get(i).getFT().getConsultas().toString());
											break;

										// voltar atrás
										case 6:
											sair3 = true;
											break;

										default:
											System.out.println("\nOpção inválida!");
											break;
										}
									}
									break;
								} else {
									System.out.println("\nInseriu o NUS de um Utente inexistente");
								}
							}
						} else {
							System.out.println("\nOpção Inválida");
						}
						break;
						
					// voltar atrás
					case 3:
						sair2 = true;
						break;
					default:
						System.out.println("\nOpção inválida");
						break;
					}
				}
				break;

			//"3 - Operações com Profissionais"
			case 3:
				sair2 = false;
				while (!sair2) {
					menu.Profissional1(profissionais);
					opcao2 = Ler.umInt();
					switch (opcao2) {
					//Criar Profissional
					case 1:
						FuncProfissional.criarP(profissionais);
						break;
						
					//Editar um profissional
					case 2:
						if (profissionais.size() != 0) {
							System.out.println();
							System.out.println("------------------------------------------------------------");
							menu.profissionais(profissionais);
							System.out.println("------------------------------------------------------------");

							System.out.println("Insira o número do Profissional a que deseja realizar operações");
							numero = Ler.umLong();

							for (int i = 0; i < profissionais.size(); i++) {
								if (profissionais.get(i).getNumero() == numero) {
									sair3 = false;
									System.out.println();
									while (!sair3) {
										//informações do profissional
										System.out.println(
												"------------------------------------------------------------");
										System.out.println("Nome: " + profissionais.get(i).getNome());
										System.out.println("Género: " + profissionais.get(i).getGenero());
										System.out.println("Salário: " + profissionais.get(i).getSalario());
										System.out.println("Número: " + profissionais.get(i).getNumero());
										System.out.println("Habilitações: " + profissionais.get(i).getHab().toString());
										System.out.println(
												"------------------------------------------------------------");

										menu.Profissional2();
										opcao3 = Ler.umInt();

										switch (opcao3) {
										//Remover
										case 1:
											profissionais.remove(i);
											System.out.println("Profissional removido com sucesso!");
											FuncProfissional.atualizarfileP(profissionais);
											sair3 = true;
											break;
											
										//Editar nome
										case 2:
											FuncProfissional.editarnomeP(profissionais, i);
											System.out.println();
											break;
											
										//Editar salario
										case 3:
											FuncProfissional.editarSalario(profissionais, i);
											System.out.println();
											break;
											
										//Adicionar/Remover habilidades
										case 4:
											FuncProfissional.hab(profissionais, i);
											System.out.println();
											break;
											
										//Consultas do profissional
										case 5:
											System.out.println(
													"\n------------------------------------------------------------");
											System.out.println("Futuras Consultas: "
													+ profissionais.get(i).getConsultas().toString());
											break;
											
										//Voltar atrás
										case 6:
											sair3 = true;
											break;
											
										default:
											System.out.println();
											System.out.println("Opção Inválida");
											break;
										}
									}
								} else {
									System.out.println("\nInseriu o número de um Profissional inexistente");
								}
							}

						} else {
							System.out.println("\nOpcão Inválida");
						}
						break;
						
					//Voltar atrás
					case 3:
						sair2 = true;
					default:
						System.out.println("\nOpcão Inválida");
						break;
					}
				}
				break;
			//"4 - Estatísticas"
			case 4:
				System.out.println("\nEstatísticas:");
				if (utentes.size() != 0)
					FuncUtentes.NrHeM(utentes);

				if (profissionais.size() != 0) {
					FuncProfissional.biggestG(profissionais);
					FuncProfissional.maisHab(profissionais);
				}
				break;
				
			//"5 - Consultas Total"
			case 5:
				System.out.println(consultas.toString());
				break;
			
			//Sair
			case 6:
				sair1 = true;
				break;
				
			default:
				System.out.println("\nOpção Inválida");
			} // 1
			System.out.println();
		} // while(!sair1)
	}
}
