/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta;

/**
 *
 * @author mfsalomao
 */
public class ContaEspecial extends Conta {
    private double limite;
    private static double taxa = 0.05/100;

    public ContaEspecial(int numero, String cliente, double valor, double limite) {
        super(numero, cliente, valor);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public static double getTaxa() {
        return taxa;
    }
    
    public String saldo () {
        double saldoTotal = getValor() + getLimite();
         String saldo = super.saldo();
                 saldo += "Limite: R$ " + String.format("%1$,.2f", getLimite());
                 saldo += "\nSaldo Total: R$ " + String.format("%1$,.2f",saldoTotal);
         return saldo;
    }
    
    public void atualiza (double t, int numDias) {
        double atualizacao = Math.pow((1-t),numDias);
        setValor(getValor() - (getValor() * atualizacao));
    }
    
}