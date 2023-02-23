package banco;

import java.io.IOException;
import java.util.Scanner;

import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.ContaPoupança;
import banco.util.Cores;

public class menu {
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		// Teste da Conta Corrente
		ContaCorrente cc1 = new ContaCorrente(3, 123, 1, "Vitória", 30000f, 1000f);
		cc1.visualizar();
		cc1.sacar(32000);
		cc1.visualizar();

		System.out.println("\n");

		// Teste da Conta Poupança
		ContaPoupança cp1 = new ContaPoupança(4, 123, 2, "Taylor", 40000f, 4);
		cp1.visualizar();
		System.out.println("\n");
		cp1.sacar(100);
		cp1.visualizar();
		System.out.println("\n");
		cp1.depositar(500);
		cp1.visualizar();

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

			opcao = ler.nextInt();

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
					ContaCorrente cc = new ContaCorrente(0, agencia, tipo, titular, saldo, limite);
					cc.visualizar();
				}

				case 2 -> {
					System.out.println("Aniversário da Conta Poupança: ");
					aniversario = ler.nextInt();
					ContaPoupança cp = new ContaPoupança(0, agencia, tipo, titular, saldo, aniversario);
					cp.visualizar();
				}
				}
				keyPress();
			}
			case 2 -> {
				System.out.println("Listar todas as contas\n\n");
				keyPress();
			}
			case 3 -> {
				System.out.println("Buscar Conta por Número\n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();

				keyPress();
			}
			case 4 -> {
				System.out.println("Atualizar Dados da Conta\n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();

				System.out.println("Número da Agencia: ");
				agencia = ler.nextInt();

				System.out.println("Nome do Titular: ");
				ler.skip("\\R?");
				titular = ler.nextLine();

				// condicional para incluir o nome do titular
				tipo = 0;

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
					ContaCorrente cc = new ContaCorrente(0, agencia, tipo, titular, saldo, limite);
					cc.visualizar();
				}

				case 2 -> {
					System.out.println("Aniversário da Conta Poupança: ");
					aniversario = ler.nextInt();
					ContaPoupança cp = new ContaPoupança(0, agencia, tipo, titular, saldo, aniversario);
					cp.visualizar();
				}
				}
				keyPress();
			}
			case 5 -> {
				System.out.println("Apagar conta \n\n");

				System.out.println("Número da Conta: ");
				numero = ler.nextInt();

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
