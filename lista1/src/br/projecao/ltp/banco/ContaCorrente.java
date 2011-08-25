package br.projecao.ltp.banco;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContaCorrente {

	private static int proximo_numero_conta = 0;

	private int numero;
	private boolean ehEspecial;
	private double limite;

	private Movimentacao[] movimentacoes;

	private int quantidade_movimentacoes_efetuadas = -1;

	public ContaCorrente() {
		this.numero = ++proximo_numero_conta;
		movimentacoes = new Movimentacao[100];
		this.limite = 300;
	}

	/**
	 * Obtém o saldo da conta. O saldo da conta é obtido somando todas as
	 * movimentações de crédito mais o limite, menos o somantário das
	 * movimentações de débito.
	 * 
	 * @return
	 */
	public double saldo() {
		double total_credito = 0;
		double total_debito = 0;

		for (int i = 0; i <= this.quantidade_movimentacoes_efetuadas; i++) {
			if (movimentacoes[i].isDebito()) {
				total_debito += movimentacoes[i].getValor();
			} else {
				total_credito += movimentacoes[i].getValor();
			}
		}
		return (total_credito + limite) - total_debito;
	}

	/**
	 * Realiza o saque de um dado valor caso haja saldo suficiente.
	 * 
	 * @param valor
	 *            Valor a ser sacada.
	 */
	public void saque(double valor) {
		double saldo = saldo();
		if (saldo >= valor) {
			this.movimentar(valor, "Saque no terminal XXXX", true);
		}
	}

	/**
	 * Transfere um dado valor a uma conta dada. A transferência é realizada
	 * caso o saldo existente seja maior ou igual ao valor solicitado.
	 * 
	 * @param conta
	 * @param valor
	 */
	public void transferencia(ContaCorrente conta, double valor) {
		if (saldo() >= valor) {
			this.saque(valor);
			conta.depositar(valor);
		}
	}

	/**
	 * Imprime o extrato de movimentações desta conta.
	 */
	public String extrato() {
		String linha = "\n---------------------------------------\n";
		StringBuffer sb = new StringBuffer(linha);
		sb.append("Número:").append(this.getNumero()).append("\t Data: ").append(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		sb.append(linha);
		
		for (int i = 0; i <= quantidade_movimentacoes_efetuadas; i++) {
			sb.append((movimentacoes[i].isDebito() ? "- " : "+ ") + movimentacoes[i].getValor()).append("\n");

		}
		
		sb.append(linha);
		sb.append("Saldo: " + saldo());
		sb.append(linha);
		
		return sb.toString();
	}

	/**
	 * Deposita um dado valor nesta conta.
	 * @param valor
	 */
	public void depositar(double valor) {
		this.movimentar(valor, "Crédito no terminal XXX", false);
	}

	/**
	 * Efetua uma determinada movimentação nesta conta.
	 * @param valor
	 * @param descricao
	 * @param isDebito
	 */
	private void movimentar(double valor, String descricao, boolean isDebito) {
		this.movimentacoes[++quantidade_movimentacoes_efetuadas] = new Movimentacao(isDebito, valor, descricao);
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @return the ehEspecial
	 */
	public boolean isEhEspecial() {
		return ehEspecial;
	}

	/**
	 * @return the limite
	 */
	public double getLimite() {
		return limite;
	}
}