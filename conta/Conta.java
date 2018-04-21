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
public class Conta implements Transacao {
    private int numero;
    private String  cliente;
    private double valor;

    public Conta(int numero, String cliente, double valor) {
        this.numero = numero;
        this.cliente = cliente;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    private void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    protected void setValor(double valor) {
        this.valor = valor;
    }
    
    public void deposito (double v) {
        setValor(getValor() + v);
    }
    
    public boolean saque (double v) {
        boolean temSaldo;
        if (getValor() >= v) {
            setValor(valor - v);
            temSaldo = true;
        }else{
            temSaldo = false;
        }
        return temSaldo;
    }
    
    public String saldo() {
        String saldo = "Conta: " + getNumero() + "\n"
                + "Cliente: " + getCliente() + "\n"
                + "Saldo: R$ " + String.format("%1$,.2f",getValor()) + "\n";
        return saldo;
    }
    
    public boolean transferencia (double v, Conta c2) {
        boolean transfere;
        if (v > getValor()) {
            transfere = false;
        }else{
            transfere = true;
            setValor(getValor()- v);
            c2.setValor(c2.getValor() + v);
        }
        return transfere;
    }
}


