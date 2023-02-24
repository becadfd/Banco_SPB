package banco;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import banco.model.ContaCorrente;
import banco.model.ContaPoupança;
import banco.util.Cores;
import conta.repository.ContaController;

public class menu {
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		ContaController contas = new ContaController();

		System.out.println("\nCriar Contas\n");

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupança cp1 = new ContaPoupança(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupança cp2 = new ContaPoupança(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		contas.listarTodas();

		int numero, agencia, tipo, opcao, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		while (true) {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + Cores.ANSI_BLACK_BACKGROUND
					+ "****************************************************************");
			System.out.println("                                                                ");
			System.out.println("                 💰Banco Social Prime Brasil💰                   ");
			System.out.println("                                                                ");
			System.out.println("****************************************************************");
			System.out.println("                                                                ");
			System.out.println("               1 - Criar Conta                                  ");
			System.out.println("               2 - Listar todas as Contas                       ");
			System.out.println("               3 - Buscar Conta por Número                      ");
			System.out.println("               4 - Atualizar Dados da Conta                     ");
			System.out.println("               5 - Apagar Conta                                 ");
			System.out.println("               6 - Sacar                                        ");
			System.out.println("               7 - Depositar                                    ");
			System.out.println("               8 - Transferir valores entre Contas              ");
			System.out.println("               9 - Sair                                         ");
			System.out.println("                                                                ");
			System.out.println("****************************************************************");
			System.out.println("Entre com a opção desejada:                                     ");
			System.out.println("                                                                " + Cores.TEXT_RESET);

			try {
				opcao = ler.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco Social Prime Brasil agradece a sua visita - O banco que te acompanha!");
				ler.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1 -> {
				System.out.println("Criar conta\n\n");

				System.out.println("Número da Agencia: ");
				agencia = ler.nextInt();

				System.out.println("Nome do Titular: ");
				ler.skip("\\R?");
				titular = ler.nextLine();

				do {
					System.out.println("Tipo da conta (1 - CC / 2- CP): ");
					tipo = ler.nextInt();
				} while (tipo < 1 & tipo > 2);

				System.out.println("Saldo da Conta: ");
				saldo = ler.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Limite da Conta Corrente: ");
					limite = ler.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}

				case 2 -> {
					System.out.println("Aniversário da Conta Poupança: ");
					aniversario = ler.nextInt();
					contas.cadastrar(
							new ContaPoupança(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));

				}
				}
				keyPress();
			}
			case 2 -> {
				System.out.println("Listar todas as contas\n\n");
				contas.listarTodas();
				keyPress();
			}
			case 3 -> {
				System.out.println("Buscar Conta por Número\n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();
				contas.procurarPorNumero(numero);
				keyPress();
			}
			case 4 -> {
				System.out.println("Atualizar Dados da Conta\n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();

				if (contas.buscarNaCollection(numero) != null) {
					System.out.println("Número da Agencia: ");
					agencia = ler.nextInt();

					System.out.println("Nome do Titular: ");
					ler.skip("\\R?");
					titular = ler.nextLine();

					System.out.println("Saldo da Conta: ");
					saldo = ler.nextFloat();

					// condicional para incluir o nome do titular
					tipo = contas.retornaTipo(numero);

					switch (tipo) {
					case 1 -> {
						System.out.println("Limite da Conta Corrente: ");
						limite = ler.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

					}

					case 2 -> {
						System.out.println("Aniversário da Conta Poupança: ");
						aniversario = ler.nextInt();
						contas.atualizar(new ContaPoupança(numero, agencia, tipo, titular, saldo, aniversario));
					}
					}
				} else
					System.out.println("A conta não foi encontrada!");

				keyPress();
			}
			case 5 -> {
				System.out.println("Apagar conta \n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();

				contas.deletar(numero);

				keyPress();
			}
			case 6 -> {
				System.out.println("Sacar\n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();

				System.out.println("Valor do Saque: ");
				valor = ler.nextFloat();

				// chamada para o método sacar

				keyPress();
			}
			case 7 -> {
				System.out.println("Depositar\n\n");
				keyPress();
			}
			case 8 -> {
				System.out.println("Transferir Valores Entre Contas\n\n");

				System.out.println("Número da Conta - Origem: ");
				numero = ler.nextInt();

				System.out.println("Número da Conta - Destino: ");
				numeroDestino = ler.nextInt();

				System.out.println("Valor da Transferência: ");
				valor = ler.nextFloat();

				keyPress();
			}
			default -> {
				System.err.println("Opção Inválida!\n");
				keyPress();
			}
			}

		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}

}
