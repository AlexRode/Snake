import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int LARGURA = 300;
    private final int ALTURA = 300;
    private final int TAMANHO_PIXEL = 10;
    private final int TOTAL_PIXELS = (LARGURA * ALTURA) / (TAMANHO_PIXEL * TAMANHO_PIXEL);
    private final int[] x = new int[TOTAL_PIXELS];
    private final int[] y = new int[TOTAL_PIXELS];
    private int partesCorpo;
    private int macasComidas;
    private int macaX;
    private int macaY;
    private boolean esquerda = false;
    private boolean direita = true;
    private boolean cima = false;
    private boolean baixo = false;
    private boolean emJogo = true;
    private Timer timer;

    public SnakeGame() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(LARGURA, ALTURA));
        iniciarJogo();
    }

    private void iniciarJogo() {
        partesCorpo = 3;
        for (int z = 0; z < partesCorpo; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        colocarMaca();

        timer = new Timer(140, this);
        timer.start();
    }

    public void colocarMaca() {
        int r = (int) (Math.random() * (LARGURA/TAMANHO_PIXEL));
        macaX = ((r * TAMANHO_PIXEL));

        r = (int) (Math.random() * (ALTURA/TAMANHO_PIXEL));
        macaY = ((r * TAMANHO_PIXEL));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenhar(g);
    }

    private void desenhar(Graphics g) {
        if (emJogo) {
            g.setColor(Color.red);
            g.fillRect(macaX, macaY, TAMANHO_PIXEL, TAMANHO_PIXEL);

            for (int z = 0; z < partesCorpo; z++) {
                if (z == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[z], y[z], TAMANHO_PIXEL, TAMANHO_PIXEL);
                } else {
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[z], y[z], TAMANHO_PIXEL, TAMANHO_PIXEL);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            fimDeJogo(g);
        }
    }

    private void fimDeJogo(Graphics g) {
        // Exibe mensagem de fim de jogo
        String msg = "Fim de Jogo";
        Font fonte = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(fonte);

        g.setColor(Color.white);
        g.setFont(fonte);
        g.drawString(msg, (LARGURA - metrics.stringWidth(msg)) / 2, ALTURA / 2);
    }

    private void verificarMaca() {
        if ((x[0] == macaX) && (y[0] == macaY)) {
            partesCorpo++;
            macasComidas++;
            colocarMaca();
        }
    }

    private void mover() {
        for (int z = partesCorpo; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (esquerda) {
            x[0] -= TAMANHO_PIXEL;
        }

        if (direita) {
            x[0] += TAMANHO_PIXEL;
        }

        if (cima) {
            y[0] -= TAMANHO_PIXEL;
        }

        if (baixo) {
            y[0] += TAMANHO_PIXEL;
        }
    }

    private void verificarColisao() {
        for (int z = partesCorpo; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                emJogo = false;
            }
        }

        if (y[0] >= ALTURA) {
            emJogo = false;
        }

        if (y[0] < 0) {
            emJogo = false;
        }

        if (x[0] >= LARGURA) {
            emJogo = false;
        }

        if (x[0] < 0) {
            emJogo = false;
        }

        if (!emJogo) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (emJogo) {
            verificarMaca();
            verificarColisao();
            mover();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!direita)) {
                esquerda = true;
                cima = false;
                baixo = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!esquerda)) {
                direita = true;
                cima = false;
                baixo = false;
            }

            if ((key == KeyEvent.VK_UP) && (!baixo)) {
                cima = true;
                esquerda = false;
                direita = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!cima)) {
                baixo = true;
                esquerda = false;
                direita = false;
            }
        }
    }
}