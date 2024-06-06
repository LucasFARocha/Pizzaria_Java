import javax.swing.JOptionPane;

public class Controle {
    // Declarando fora do main, e usando static, para ser acessível por outras
    // funções
    static ListaEncadeada historicoPedidos = new ListaEncadeada();
    static Fila filaPreparo = new Fila(3);
    static Pilha pilhaEntrega = new Pilha(3);

    // Variáveis para controlar as funções
    static int qtdPedidos = 0;
    static int qtdPedidosPreparo = 0;
    static int qtdPedidosNaCozinha = 0;
    static IntNoSimples pedidosEmEntrega[] = new IntNoSimples[3];
    static int qtdEntregas = 0;

    // ANOTA PEDIDO
    public void anotaPedido() {
        try {
            // Perguntando a quantidade de itens do pedido para determinar o
            // tamanho dos vetores/arrays na classe Pedido
            int qtdItens = Integer.parseInt(JOptionPane.showInputDialog("Quantas pizzas deseja pedir?"));

            if (qtdItens <= 0) {
                JOptionPane.showMessageDialog(null, "Informe uma quantidade maior que zero!");
            } else {
                Pedido pedido = new Pedido(qtdItens);

                // Um loop para armazenar os itens
                for (int i = 0; i < qtdItens; i++) {
                    pedido.setTamanhoPizza(i, JOptionPane.showInputDialog(
                            "Informe o tamanho da " + (i + 1) + "ª pizza" +
                                    "\nPequena, Média ou Grande"));

                    pedido.setSaborPizza(i, JOptionPane.showInputDialog(
                            "Informe o sabor da " + (i + 1) + "ª pizza" +
                                    "\nCalabresa | Muçarela | Napolitana" +
                                    "\nDoce de Leite | Morango | Nutella"));
                }

                pedido.setEndereco(JOptionPane.showInputDialog("Informe o endereço de entrega"));
                pedido.setDistancia(Float.parseFloat(JOptionPane.showInputDialog(
                        "Informe a distância até o local (em KM)")));
                        
                // Importante aumentar o valor ANTES de estabelecer o número do pedido
                qtdPedidos++;
                
                // Estabelecendo o número do pedido como o número da quantidade de pedidos
                // feitos, ou seja, o primeiro pedido terá número 1, o segundo número 2, e
                // assim por diante
                pedido.setNumPedido(qtdPedidos);

                // Criando um nó com seu valor sendo o pedido e inserindo na lista
                IntNoSimples pedidoAnotado = new IntNoSimples(pedido);
                historicoPedidos.insereNo_fim(pedidoAnotado);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "A quantidade de itens e a distância " +
                            "\ndevem ser númericas!");
        }

    }

    // CANCELA PEDIDO
    public void cancelaPedido() {
        try {
            int cod = Integer.parseInt(JOptionPane.showInputDialog(
                    "Informe o número do pedido que deseja cancelar"));

            // O número do pedido é sempre 1 a mais que o índice
            IntNoSimples pedidoCancelado = historicoPedidos.buscaIndice(cod - 1);

            if (cod <= qtdPedidosPreparo && cod > 0) {
                // Caso o número do pedido esteja na variável de controle da cozinha E
                // seja maior que 0
                JOptionPane.showMessageDialog(null,
                        "Não é possível cancelar o pedido, pois ele já foi enviado à cozinha");
            } else if (pedidoCancelado == null || cod <= 0) {
                // Caso o pedido não tenha sido anotado ainda ou
                // o usuário tenha informado um número negativo
                JOptionPane.showMessageDialog(null,
                        "Informe o número de um pedido existente!");
            } else {
                // Caso contrário, quando o número é válido e não está na cozinha

                // Armazenando o índice do pedido excluído
                int indiceExcluido = historicoPedidos.buscaNo(pedidoCancelado.valor);

                // Importante excluir o nó ANTES de usar a função ContarNos
                historicoPedidos.excluiNo(pedidoCancelado);

                // Loop para atualizar os números do pedidos, diminuindo seu número em 1
                for (int i = indiceExcluido; i < historicoPedidos.ContarNos(); i++) {
                    IntNoSimples temp_pedido = historicoPedidos.buscaIndice(i);

                    temp_pedido.valor.setNumPedido(temp_pedido.valor.getNumPedido() - 1);
                }

                JOptionPane.showMessageDialog(null,
                        "Pedido número " + cod + " cancelado com sucesso!");

                qtdPedidos--;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Você deve informar código numérico!");
        }
    }

    // LISTAR PEDIDOS
    public void listarPedidos() {

        if (historicoPedidos.ContarNos() == 0)
            JOptionPane.showMessageDialog(null, "Nenhum pedido foi anotado ainda!");
        else
            historicoPedidos.exibeLista();
        ;
    }

    // PREPARA PEDIDO
    public void preparaPedido() {
        if ((historicoPedidos.ContarNos() - qtdPedidosNaCozinha) >= 3) {
            // Caso existam 3 ou mais pedidos que ainda não foram para a cozinha

            // Loop garantindo que apenas 3 deles vão para a cozinha
            for (int i = 0; i < 3; i++) {
                // Passando a variável de controle da cozinha para garantir a continuidade da
                // sequência
                // ex: 1, 2 e 3 --- 4, 5 e 6 --- 7, 8 e 9
                // *Lembrando que a variável não teve seu valor aumentado ainda, portanto
                // corresponde
                // ao índice certo da lista de pedidos
                IntNoSimples temp_pedido = historicoPedidos.buscaIndice(qtdPedidosNaCozinha);

                filaPreparo.enfileirar(temp_pedido);

                JOptionPane.showMessageDialog(null,
                        "Pedido número " + temp_pedido.valor.getNumPedido() + " enviado para a cozinha");

                qtdPedidosPreparo++;
                qtdPedidosNaCozinha++;
            }
        } else if (filaPreparo.cheia()) {
            // Caso a fila da cozinha esteja cheia
            JOptionPane.showMessageDialog(null, "A fila da cozinha está cheia!");
        } else if (historicoPedidos.ContarNos() == 0) {
            // Caso não existam pedidos na lista
            JOptionPane.showMessageDialog(null, "Nenhum pedido foi anotado ainda!");
        } else {
            // Caso contrário, quando não existem pedidos o suficiente para serem preparados
            JOptionPane.showMessageDialog(null, "Pelo menos 3 pedidos devem ser enviados para a cozinha!");
        }
    }

    // INCLUI PARA ENTREGA
    public void empilhaPedido() {
        int tamanho = filaPreparo.getVetor().length;
        IntNoSimples[] comparador = new IntNoSimples[tamanho];

        if (pilhaEntrega.cheia()) {
            // Caso a pilha esteja cheia
            JOptionPane.showMessageDialog(null, "A mochila do Motoboy está cheia!");
        } else if (filaPreparo.vazia()) {
            // Caso a fila esteja vazia
            JOptionPane.showMessageDialog(null, "Não há pedidos na cozinha!");
        } else {
            // Caso contrário, quando a pilha está vazia e a fila cheia

            // Loop para armazenar os pedidos da fila
            for (int i = 0; i < filaPreparo.getVetor().length; i++) {
                comparador[i] = (IntNoSimples) filaPreparo.getPedido(i);
            }

            // Algoritmo de ordenação
            for (int i = 0; i < comparador.length - 1; i++) {
                for (int j = i + 1; j < comparador.length; j++) {
                    // Os dois loops garantem que cada número seja comparado
                    if (comparador[i].valor.getDistancia() < comparador[j].valor.getDistancia()) {
                        // Compara o valor do índice com o valor posterior e,
                        // se for menor, trocam de posições
                        // ex: 1, 3 | 1 < 3 -> Verdadeiro | trocam de posição -> 3, 1

                        Pedido temp_pedido = comparador[i].valor;

                        comparador[i].valor = comparador[j].valor;
                        comparador[j].valor = temp_pedido;
                    }
                }
            }

            // Empilha os valores ordenados
            for (int i = 0; i < comparador.length; i++) {
                pilhaEntrega.empilhar(comparador[i]);
            }

            // Esvaziando a fila para poder armazenar mais valores
            filaPreparo.esvaziar();

            JOptionPane.showMessageDialog(null,
                    "Os pedidos foram devidamente entregues ao Motoboy!");
        }
    }

    // MOSTRA A ORDEM DE ENTREGA DOS PEDIDOS
    public void geraRelatorio() {
        if (pilhaEntrega.vazia()) {
            JOptionPane.showMessageDialog(null, "Não há pedidos na mochila do Motoboy!");
        } else {
            pilhaEntrega.exibePilha();

            // Loop para armazenar os pedidos que são desempilhados
            for (int i = 0; i < pilhaEntrega.getVetor().length; i++) {
                IntNoSimples temp_pedido = (IntNoSimples) pilhaEntrega.desempilhar();
                pedidosEmEntrega[i] = temp_pedido;
            }
        }
    }

    // ENTREGA OS PEDIDOS
    public void entregaPedido() {
        if (pedidosEmEntrega[0] == null) {
            // Caso a lista de pedidos desempilhados esteja vazia
            JOptionPane.showMessageDialog(null, "Não há pedidos para serem entregues!");
        } else {
            qtdEntregas++;
            // System.out.println("\nENTREGA | qtdEntregas: " + qtdEntregas);

            // Loop para percorrer o vetor
            // *obs: o vetor é renovado toda vez que a função é chamada
            for (int i = 0; i < 3; i++) {
                JOptionPane.showMessageDialog(null,
                        "Pedido número " + pedidosEmEntrega[i].valor.getNumPedido()
                                + " entregue com sucesso!");
            }

            System.out.println("Antes do IF");
            if (qtdEntregas == 2) {
                // System.out.println("\nDENTRO DO IF" + "\nENTREGA | qtdEntregas: " +
                // qtdEntregas);

                // Loop para apagar os valores do histórico de pedidos
                // Cada entrega possui 3 pedidos, então 2 entregas possuem 6 pedidos
                for (int i = 0; i < 6; i++) {
                    // System.out.println("\nPedido da posição " + i + " excluído");

                    IntNoSimples temp_excluir = historicoPedidos.buscaIndice(0);
                    historicoPedidos.excluiNo(temp_excluir);
                }

                // Reiniciando a contagem
                qtdEntregas = 0;

                qtdPedidosNaCozinha -= 6;
            }

            // Loop para apagar os valores do vetor
            for (int i = 0; i < 3; i++) {
                pedidosEmEntrega[i] = null;
            }
        }
    }
}

/*
 * IDEIAS DE FUNCIONALIDADE EXTRA:
 * 1-Telas.
 * 2-Descontos (por código) ou promoções. ---\
 * 3-Sistema de valores. ---->Funcionalidades que requerem mais trabalho.
 * 4-Gerenciamento de estoque. ---/
 * 5-Vinculação cliente e pedido. ---\
 * 6-Gerenciamento de Clientes. ---->Funcionalidades mais fáceis de fazer.
 * 7-Emissão de notas fiscais. ---/
 * Ideias: José.
 */

// FUNCIONALIDADES FUNCIONANDO:
// TODAS :D
