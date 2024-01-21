import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private JButton iniciarJogoBotao;

    public Menu() {
        setLayout(new BorderLayout());
        iniciarJogoBotao = new JButton("Iniciar Jogo");
        iniciarJogoBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        add(iniciarJogoBotao, BorderLayout.CENTER);
    }

    private void iniciarJogo() {
        JFrame janelaDoJogo = new JFrame("Jogo Snake");
        SnakeGame jogo = new SnakeGame();

        janelaDoJogo.add(jogo);
        janelaDoJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaDoJogo.setResizable(false);
        janelaDoJogo.pack();
        janelaDoJogo.setVisible(true);
        janelaDoJogo.setLocationRelativeTo(null);
    }
}
