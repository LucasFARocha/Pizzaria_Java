import javax.swing.JOptionPane;

public class Fila {
    private int tamanho;
    private int inicio;
    private int fim;
    private int total;
    private Object vetor[];

    public Fila(int tam) // método construtor da classe fila
    {
        inicio = 0;
        fim = 0;
        total = 0;
        vetor = new Object[tam];
        tamanho = tam;
    }

    public boolean vazia() {
        // return total==0; forma não redundante
        if (total == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cheia() {
        // return total==tamanho;
        if (total == tamanho) {
            return true;
        } else {
            return false;
        }
    }

    public Object getPedido(int indice) {
        return vetor[indice];
    }

    public Object[] getVetor() {
        return vetor;
    }

    public void enfileirar(IntNoSimples elem) {
        if (!cheia()) {
            vetor[fim] = elem;
            fim++;
            total++;
        } else {
            JOptionPane.showMessageDialog(null, "Fila Cheia!");
        }
    }

    // public Object desenfileirar() {
    // Object elem;
    // if (!vazia()) {
    // elem = vetor[inicio];
    // inicio++;
    // } else {
    // elem = "Fila Vazia";

    // }
    // return elem;
    // }
    public void esvaziar() {
        for (int i = inicio; i < fim; i++) {
            vetor[i] = null;
        }
        total = 0;
        inicio = 0;
        fim = 0;
    }

    public void exibeFila() {
        for (int i = inicio; i < fim; i++) {
            IntNoSimples pedido = (IntNoSimples) vetor[i];

            System.out.println("FILA: Pedido número: " + pedido.valor.getNumPedido() + "| posição: " + i);
        }
    }
}