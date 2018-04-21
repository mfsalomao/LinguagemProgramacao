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
public interface Transacao {
    public void deposito (double valor);
    public boolean saque (double valor);
    public String saldo ();
    public boolean transferencia (double valor,  Conta conta2);
}
