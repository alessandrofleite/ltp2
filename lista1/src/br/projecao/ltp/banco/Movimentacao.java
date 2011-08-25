package br.projecao.ltp.banco;

public class Movimentacao {

	private boolean debito;

	private double valor;

	private String descricao;

	/**
	 * @param debito
	 * @param valor
	 * @param descricao
	 */
	public Movimentacao(boolean debito, double valor, String descricao) {
		this.debito = debito;
		this.valor = valor;
		this.descricao = descricao;
	}

	/**
	 * @return the debito
	 */
	public boolean isDebito() {
		return debito;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}