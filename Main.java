import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Menu menu = new Menu();

        frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Jogo Snake");

        frame.setSize(300, 300); // Tamanho para mostrar o menu
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
