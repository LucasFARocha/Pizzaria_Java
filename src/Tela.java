import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tela {

    public Tela(int width, int height, JPanel panel) {
        // Cria um novo frame (janela)
        JFrame frame = new JFrame("Gerenciamento de Pedidos da Pizzaria");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Cria um painel para colocar componentes

        // Adiciona o painel ao frame
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        // Define o frame como visível
        frame.setVisible(true);
    }

}
