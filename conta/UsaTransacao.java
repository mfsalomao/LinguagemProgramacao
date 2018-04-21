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
import javax.swing.JOptionPane;

public class UsaTransacao {
    
    public static void main (String [] args) {
       
       menu(); 
    }
    
    public static void menu () {
        char opt;
        int tam = 5;
        int nConta;
        Transacao [] vetor = new Transacao [tam];
        Conta c1 = new Conta((int) 1, (String) "Joao", (double) 1000);
        Conta c2 = new Conta((int) 2, (String) "Maria", (double) 2500);
        ContaEspecial c3 = new ContaEspecial((int) 3, (String) "Carlos",
               (double) 30000, (double) 10000);
        ContaEspecial c4 = new ContaEspecial((int) 4, (String) "Ana",
               (double) 180000, (double) 20000);
        Conta c5 = new Conta((int) 5, (String) "Luis", (double) 2500);
        vetor[0] = c1;
        vetor[1] = c2;
        vetor[2] = c3;
        vetor[3] = c4;
        vetor[4] = c5;
        do {
            opt = JOptionPane.showInputDialog("Escolha sua opção: "
                    + "\n1 - Saque."
                    + "\n2 - Saldo."
                    + "\n3 - Depósito."
                    + "\n4 - Transferência."
                    + "\n5 - Utilizar Cheque Especial."
                    + "\n6 - Sair.").charAt(0);
            switch(opt) {
                case '1':
                    nConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta: "));
                    sacar(vetor, nConta, tam);
                    break;
                case '2':
                    nConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta: "));
                    mostrarSaldo(vetor, nConta, tam);
                    break;
                case '3':
                    nConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta: "));
                    depositar(vetor, nConta, tam);
                    break;
                case '4':
                    nConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta: "));
                    transferir(vetor, nConta, tam);
                    break;
                case '5':
                    nConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta: "));
                    utilizarChequeEspecial(vetor, nConta, tam);
                    break;
                case '6': 
                    JOptionPane.showMessageDialog(null,"Encerrando o programa.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opção inválida.");
                    break;
            }
        } while (opt != '6');
    }
    
    public static int obterNumContaCliente (Transacao [] v, int nConta, int tam) {
        int [] nCliente = new int [tam];
        boolean nContaEncontrado = false;
        int posicaoEncontrada = -1;
        for (int i=0; i<v.length; i++) {
            if (v[i] instanceof Conta) {
                Conta conta = (Conta) v[i];
                nCliente[i] = conta.getNumero();
            }else{
                ContaEspecial contaEsp = (ContaEspecial) v[i];
                nCliente[i] = contaEsp.getNumero();
            }
        }
        for (int i=0; i<v.length; i++) {
            if (nCliente[i] == nConta) {
                nContaEncontrado = true;
                posicaoEncontrada = i;
            }
        }
        return posicaoEncontrada;
    }
    
    public static void sacar(Transacao[] v, int nConta, int tam) { 
        int posicao = obterNumContaCliente (v, nConta, tam);
        if (posicao != -1) {
            double valorSacado = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor a ser sacado: "));
            boolean info = v[posicao].saque(valorSacado);
            if (info) {
                 JOptionPane.showMessageDialog(null,"Conta: "+ nConta + "\n"
                         + "Saque efetuado no valor de R$: " + valorSacado); 
            }else{
               JOptionPane.showMessageDialog(null,"Saldo insuficiente.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Número de conta não encontrado. ");  
        }
    }
    
    public static void mostrarSaldo(Transacao[] v, int nConta, int tam) { 
        int posicao = obterNumContaCliente (v, nConta, tam);
        if (posicao != -1) {
            JOptionPane.showMessageDialog(null,v[posicao].saldo());
        }else{
            JOptionPane.showMessageDialog(null,"Número de conta não encontrado. ");  
        }
    }
    
    public static void depositar(Transacao[] v, int nConta, int tam) { 
        int posicao = obterNumContaCliente (v, nConta, tam);
        if (posicao != -1) {
            double valorDepositado = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor a ser depositado: "));
            v[posicao].deposito(valorDepositado) ;
            JOptionPane.showMessageDialog(null,"Conta: "+ nConta + "\n"
                         + "Depósito efetuado no valor de R$: " + valorDepositado); 
        }else{
            JOptionPane.showMessageDialog(null,"Número de conta não encontrado. ");  
        }
    }
    
    public static void transferir(Transacao[] v, int nConta, int tam) { 
        int posicao = obterNumContaCliente (v, nConta, tam);
        if (posicao != -1) {
            int nConta2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta de destino: "));
            int posicao2 = obterNumContaCliente (v, nConta2, tam);
            if (posicao2 != -1) {
                if (v[posicao2] instanceof Conta) {
                    double valorTransferir = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor a ser transferido: "));
                    boolean transfere = v[posicao].transferencia(valorTransferir, (Conta) v[posicao2]);
                    if (transfere) {
                        JOptionPane.showMessageDialog(null, "Transferência efetuada com sucesso. \n"
                            + "Conta de destino: "+ nConta2 + "\n"
                            + "Valor transferido: "+ valorTransferir);
                    }else{
                        JOptionPane.showMessageDialog(null,"Saldo insuficiente para transferência."); 
                    }
                }
            }else{
             JOptionPane.showMessageDialog(null,"Número da conta destino não encontrado.");   
            }
        }else{
            JOptionPane.showMessageDialog(null,"Número de conta não encontrado.");
            }
    }
    
    public static void utilizarChequeEspecial(Transacao[] v, int nConta, int tam) {
        int nDias; 
        double taxa = ContaEspecial.getTaxa();
        double totalPagar;
        int posicao = obterNumContaCliente (v, nConta, tam);
        if (posicao != -1) {
            if (v[posicao] instanceof ContaEspecial) {
                nDias = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de dias para usar o cheque especial: "));
                ContaEspecial temp = (ContaEspecial) v[posicao];
                totalPagar = temp.getValor() * Math.pow((1-taxa),nDias);
                temp.atualiza(taxa, nDias);
                v[posicao] = temp;
                JOptionPane.showMessageDialog(null,"O cheque especial foi utilizado por " +nDias+ " dias pela taxa de " +(taxa*100)+ "% ao dia."
                        + "\nO total a pagar é: " +String.format("%1$,.2f",totalPagar) + ".\n"
                        + "\n"+temp.saldo());
            }else{
                JOptionPane.showMessageDialog(null,"Sua conta é comum. Opção disponível apenas para clientes que possuem conta especial.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Número de conta não encontrado. "); 
        }
    }
    
}