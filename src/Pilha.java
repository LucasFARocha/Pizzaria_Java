import javax.swing.JOptionPane;

public class Pilha {
    private int tamanho;
    private int topo;
    private IntNoSimples vetor[];

    Pilha(int tam) {
        topo = -1;
        tamanho = tam;
        vetor = new IntNoSimples[tam];
    }

    public boolean vazia() {
        if (topo == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cheia() {
        if (topo == tamanho - 1) {
            return true;
        } else {
            return false;
        }
    }

    public IntNoSimples[] getVetor(){
        return vetor;
    }

    public void empilhar(IntNoSimples elem) {
        if (cheia() == true) {
            System.out.println("Pilha Cheia");
        } else {
            topo++;
            vetor[topo] = elem;
        }
    }

    public Object desempilhar() {
        Object valorDesempilhado = null;
        if (vazia() == true) {
            JOptionPane.showMessageDialog(null, "Pilha Vazia");
        } else {
            valorDesempilhado = vetor[topo];
            topo--;
        }
        return valorDesempilhado;
    }

    public void exibePilha() {
        String msg = "";
        for (int i = topo; i >= 0; i--) {

            msg += "\nPedido número: " + vetor[i].valor.getNumPedido() + 
            "\nEndereço: " + vetor[i].valor.getEndereco() +
            "\nDistância: " + vetor[i].valor.getDistancia() + "km"
                    + "\n";
        }
        JOptionPane.showMessageDialog(null, 
        "Ordem dos pedidos a serem entregues:\n" + msg);
    }

}
