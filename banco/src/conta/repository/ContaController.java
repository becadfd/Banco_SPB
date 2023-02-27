package conta.repository;

import java.util.ArrayList;
import java.util.Optional;

import banco.model.Conta;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent())
			conta.get().visualizar();
		else
			System.out.println("\nA conta número " + numero + " nao foi encontrada!");
	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas)
			conta.visualizar();

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A Conta foi criada!");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA conta número " + conta.getNumero() + " foi atualizada!");

		}

		else
			System.out.println("\nA conta número " + conta.getNumero() + " nao foi encontrada!");

	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("\nA conta número " + numero + " foi deletada!");
		} else
			System.out.println("\nA conta número " + numero + " nao foi encontrada!");

	}

	@Override
	public void sacar(int numero, float valor) {
		Optional<Conta> buscaConta = buscarNaCollection(numero);

		if (buscaConta.isPresent()) {
			if (buscaConta.get().sacar(valor) == true)
				System.out.println("Saque efetuado com sucesso!");
		} else {
			System.out.println("\nA conta número " + numero + " nao foi encontrada!");
		}

	}

	@Override
	public void depositar(int numero, float valor) {
		Optional<Conta> contaD = buscarNaCollection(numero);

		if (contaD.isPresent()) {
			contaD.get().depositar(valor);
			System.out.println("Depósito efetuado com sucesso!");
		} else {
			System.out.println("\nA conta número " + numero + " nao foi encontrada!");
		}

	}

	@Override
	public void transferir(int numero, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = buscarNaCollection(numero);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);

		if (contaOrigem != null && contaDestino != null) {
			if (contaOrigem.get().sacar(valor) == true) {
				contaDestino.get().depositar(valor);
				System.out.println("Transferencia efetuada com sucesso!");
			} else {
				System.out.println(
						"\nA conta de Origem e/ou Destino " + numero + " nao foi/foram encontrada/encontradas!");
			}
		}

	}
	/* Implementar métodos auxiliares */

	public int gerarNumero() {
		return ++numero;
	}

	public Optional<Conta> buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero)
				return Optional.ofNullable(conta);
		}
		return Optional.empty();
	}

	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero)
				return conta.getTipo();
		}
		return 0;

	}
}
