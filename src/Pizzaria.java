import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Pizzaria {
    // Declarando fora do main, e usando static, para ser acessível por outras
    // funções
    static ListaEncadeada listaPedidos = new ListaEncadeada();
    static Fila filaPreparo = new Fila(3);
    static Pilha pilhaEntrega = new Pilha(3);
    static Controle controlador = new Controle();

    public static void main(String[] args) throws Exception {
        int width = 400;
        int height = 350;
        JPanel panel = new JPanel();
        new Tela(width, height, panel);
        desenhaMenu(panel, width);

    }

    public static void desenhaMenu(JPanel panel, int width) {
        panel.setLayout(null);

        JLabel titulo = new JLabel("Menu da Pizzaria");
        titulo.setBounds((width - 105) / 2, 2, 100, 25);
        panel.add(titulo);

        // Botão de adicionar pedido
        JButton adicionarPedido = new JButton("Adicionar pedido");
        adicionarPedido.setBounds((width - 160) / 2, 30, 150, 20);
        // Adiciona uma ação ao botão
        adicionarPedido.addActionListener(e -> {
            controlador.anotaPedido();
        });

        // Botão de cancelar pedido
        JButton cancelarPedido = new JButton("Cancelar pedido");
        cancelarPedido.setBounds((width - 160) / 2, 60, 150, 20);
        // Adiciona uma ação ao botão
        cancelarPedido.addActionListener(e -> {
            controlador.cancelaPedido();
        });

        // Botão de listar pedidos
        JButton listarPedidos = new JButton("Listar pedidos");
        listarPedidos.setBounds((width - 160) / 2, 90, 150, 20);
        // Adiciona uma ação ao botão
        listarPedidos.addActionListener(e -> {
            controlador.listarPedidos();
        });

        // Botão de incluir pedidos para preparo
        JButton incluirPreparo = new JButton("Incluir pedidos para preparo");
        incluirPreparo.setBounds((width - 210) / 2, 130, 200, 20);
        // Adiciona uma ação ao botão
        incluirPreparo.addActionListener(e -> {
            controlador.preparaPedido();
        });

        // Botão de incluir pedidos para entrega
        JButton empilhaPedido = new JButton("Incluir pedidos para entrega");
        empilhaPedido.setBounds((width - 210) / 2, 160, 200, 20);
        // Adiciona uma ação ao botão
        empilhaPedido.addActionListener(e -> {
            controlador.empilhaPedido();
        });

        // Botão de gerar relatório para entrega
        JButton geraRelatorio = new JButton("Gerar relatório de entrega entrega");
        geraRelatorio.setBounds((width - 210) / 2, 190, 200, 20);
        // Adiciona uma ação ao botão
        geraRelatorio.addActionListener(e -> {
            controlador.geraRelatorio();
        });

        // Botão de inclui pedidos para entrega
        JButton entregaPedido = new JButton("Entregar pedidos da mochila");
        entregaPedido.setBounds((width - 210) / 2, 220, 200, 20);
        // Adiciona uma ação ao botão
        entregaPedido.addActionListener(e -> {
            controlador.entregaPedido();
        });

        // Adiciona o botão ao painel
        panel.add(adicionarPedido);
        panel.add(cancelarPedido);
        panel.add(listarPedidos);
        panel.add(incluirPreparo);
        panel.add(empilhaPedido);
        panel.add(geraRelatorio);
        panel.add(entregaPedido);

    }
}
