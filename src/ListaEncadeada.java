import javax.swing.JOptionPane;

// import java.awt.Dimension;

// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;

public class ListaEncadeada {
    IntNoSimples primeiro, ultimo;
    int numero_nos = 0;

    ListaEncadeada() {
        primeiro = ultimo = null;
    }

    void insereNo_fim(IntNoSimples novoNo) {
        novoNo.prox = null;
        if (primeiro == null)
            primeiro = novoNo;
        if (ultimo != null)
            ultimo.prox = novoNo;
        ultimo = novoNo;
        numero_nos++;
    }

    void insereNo_inicio(IntNoSimples novoNo) {
        novoNo.prox = primeiro;
        if (primeiro == null && ultimo == null) // Só há um elemento na lista
        {
            ultimo = novoNo;
        }
        primeiro = novoNo;
        numero_nos++;
    }

    int ContarNos() {
        int tamanho = 0;
        IntNoSimples temp_no = primeiro;
        while (temp_no != null) {
            tamanho++;
            temp_no = temp_no.prox;
        }
        return tamanho;
    }

    void insereNo_posicao(IntNoSimples novoNo, int posicao) {
        IntNoSimples temp_no = primeiro;
        int numero_nos = ContarNos();
        int pos_aux;
        if (posicao == 0) {
            novoNo.prox = primeiro;
            if (primeiro == ultimo) {
                ultimo = novoNo;
            }
            primeiro = novoNo;
        } else {
            if (posicao <= numero_nos) {
                pos_aux = 1;
                while (temp_no != null && posicao > pos_aux) {
                    temp_no = temp_no.prox;
                    pos_aux++;
                }
                novoNo.prox = temp_no.prox;
                temp_no.prox = novoNo;
            } else {
                if (posicao > numero_nos) {
                    ultimo.prox = novoNo;
                    ultimo = novoNo;
                }
            }
        }
    }

    int buscaNo(Pedido buscaValor) {
        IntNoSimples temp_no = primeiro;
        int i = 0;
        while (temp_no != null) {
            if (temp_no.valor == buscaValor) {
                return i;
            }
            temp_no = temp_no.prox;
            i++;
        }
        return 0;
    }

    IntNoSimples buscaIndice(int indice) {
        IntNoSimples temp_no = primeiro;
        for (int i = 0; (i < indice) && (temp_no != null); i++) {
            temp_no = temp_no.prox;
        }
        return temp_no;
    }

    void excluiNo(IntNoSimples valor) {
        IntNoSimples temp_no = primeiro;
        IntNoSimples anterior_no = null;
        while (temp_no != null && temp_no != valor) {
            anterior_no = temp_no;
            temp_no = temp_no.prox;
        }
        if (temp_no == primeiro) {
            if (temp_no.prox != null)
                primeiro = temp_no.prox;
            else
                primeiro = null;
        } else {
            anterior_no.prox = temp_no.prox;
        }

        if (ultimo == temp_no)
            ultimo = anterior_no;
    }

    public void exibeLista() {
        IntNoSimples temp_no = primeiro;
        int i = 0;
        String msg = "";

        while (temp_no != null) {
            for (int j = 0; j < temp_no.valor.getTamanhosPizzas().length; j++) {
                msg += "\n\nTamanho da " + (j + 1) + "ª pizza: " + temp_no.valor.getTamanhoPizza(j) +
                        "\nSabor da " + (j + 1) + "ª pizza: " + temp_no.valor.getSaborPizza(j);
            }
            JOptionPane.showMessageDialog(null,
                    "Número do pedido: " + temp_no.valor.getNumPedido() +
                            "\nEndereço: " + temp_no.valor.getEndereco() +
                            "\nDistância: " + temp_no.valor.getDistancia() + "km" +
                            msg);

            System.out.println("LISTA: " +
                    temp_no.valor.getNumPedido() +
                    " - Endereço: " + temp_no.valor.getEndereco() +
                    " - Distância: " + temp_no.valor.getDistancia() +
                    " | posição: " + i);

            msg = "";
            temp_no = temp_no.prox;
            i++;
        }
    }

    // public void exibirListas() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(null);

    //     JScrollPane scrollPane = new JScrollPane(panel);
    //     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    //     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    //     scrollPane.setBounds(0, 0, 400, 500);

    //     JFrame frame = new JFrame();
    //     frame.setSize(400, 500);
    //     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //     frame.add(scrollPane);
    //     frame.setVisible(true);

    //     IntNoSimples temp_no = primeiro;
    //     int posicao = 0;

    //     int height = 0;

    //     while (temp_no != null) {
    //         String pizzas = "";
    //         int qntPizzas = temp_no.valor.getTamanhosPizzas().length;

    //         for (int j = 0; j < temp_no.valor.getTamanhosPizzas().length; j++) {
    //             pizzas += "\n\nTamanho da " + (j + 1) + "ª pizza: " +
    //                     temp_no.valor.getTamanhoPizza(j) +
    //                     "\nSabor da " + (j + 1) + "ª pizza: " + temp_no.valor.getSaborPizza(j);
    //         }

    //         String infoText = "Número do pedido: " + temp_no.valor.getNumPedido() +
    //                 "\nEndereço: " + temp_no.valor.getEndereco() +
    //                 "\nDistância: " + temp_no.valor.getDistancia() + "km" + pizzas;

    //         JTextArea informacoes = new JTextArea(infoText);
    //         informacoes.setBounds(50, height, 300, 100 + (30 * qntPizzas));
    //         informacoes.setLineWrap(true);
    //         informacoes.setWrapStyleWord(true);
    //         informacoes.setEditable(false);

    //         panel.add(informacoes);

    //         System.out.println("LISTA: " +
    //                 temp_no.valor.getNumPedido() +
    //                 " - Endereço: " + temp_no.valor.getEndereco() +
    //                 " - Distância: " + temp_no.valor.getDistancia() +
    //                 " | posição: " + posicao);

    //         pizzas = "";
    //         temp_no = temp_no.prox;
    //         posicao++;
    //         height += 120 + (20 * qntPizzas);
    //     }

    //     panel.setPreferredSize(new Dimension(400, 500));
    //     panel.revalidate();
    // }
}
