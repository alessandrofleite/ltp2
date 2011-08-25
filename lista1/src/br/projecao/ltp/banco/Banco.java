package br.projecao.ltp.banco;

public class Banco {
	
	private ContaCorrente[] contas = new ContaCorrente[1000];
	private int quantidade_contas_abertas = -1;

	
	public ContaCorrente abrirConta() {
		ContaCorrente conta = new ContaCorrente();
		contas[++quantidade_contas_abertas] = conta;
		return conta;
	}
	
	public static void main(String[] args) {
		Banco banco = new Banco();
		ContaCorrente conta = banco.abrirConta();
		
		conta.depositar(100);
		conta.saque(50);
		ContaCorrente conta1= banco.abrirConta();
		
		conta.transferencia(conta1, 20);
		System.out.println(conta.extrato());
	}
}