package Modelo;

import Peca.Pecas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.BorderFactory.createLineBorder;


public class Jogo extends JPanel implements MouseListener {

    private JButton[][] botao;
    private Pecas pecas;
    private final int[][] tabuleiro = new int[8][8];

    public void iniciar() {
        botao = new JButton[8][8];
        pecas = new Pecas();
        pecas.colocarPecas();
        removeAll();
        for (int i = 0; i < botao.length; i++) {
            for (int j = 0; j < botao[0].length; j++) {

                botao[i][j] = new JButton();
                botao[i][j].addMouseListener(this);
                botao[i][j].setIcon(new ImageIcon("src/Imagens/FundoClaro.png"));
            }
        }
    }

    public void ResetaTabuleiro() {
        pecas.colocarPecas();
    }

    public void desenhaTabuleiro() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (j % 2 == 0) {
                    tabuleiro[1][j] = 5;
                    tabuleiro[7][j] = 5;
                    tabuleiro[5][j] = 5;
                    tabuleiro[3][j] = 5;
                } else {
                    tabuleiro[0][j] = 5;
                    tabuleiro[6][j] = 5;
                    tabuleiro[2][j] = 5;
                    tabuleiro[4][j] = 5;
                }
            }
        }
    }

    public void criaTabuleiro() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < botao.length; j++) {
                if (tabuleiro[i][j] == pecas.getPretas()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/PecaPreta.png"));
                } else if (tabuleiro[i][j] == pecas.getVermelhas()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/PecaVermelha.png"));
                } else if (tabuleiro[i][j] == pecas.getDamaVermelha()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/DamaVermelha.png"));
                } else if (tabuleiro[i][j] == pecas.getDamaPreta()) {
                    botao[i][j].setIcon(new ImageIcon("src/DamaPreta.png"));
                } else if (tabuleiro[i][j] == pecas.getFundoEscuro()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/FundoEscuro.png"));
                }
            }
        }
    }

    public void desenhaBotoes() {
        pecas.verificar();
        for (int i = 0; i < botao.length; i++) {
            for (int j = 0; j < botao.length; j++) {
                if (pecas.verDamas(i, j) == pecas.getPretas()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/PecaPreta.png"));
                } else if (pecas.verDamas(i, j) == pecas.getVermelhas()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/PecaVermelha.png"));
                } else if (pecas.verDamas(i, j) == pecas.getDamaVermelha()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/DamaVermelha.png"));
                } else if (pecas.verDamas(i, j) == pecas.getDamaPreta()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/DamaPreta.png"));
                } else if (pecas.verDamas(i, j) == pecas.getFundoEscuro()) {
                    botao[i][j].setIcon(new ImageIcon("src/Imagens/FundoEscuro.png"));
                }
            }
        }
    }

    public boolean IA(boolean realiza) {//ia que joga usando numeros aleatórios
        int x = (int) (Math.random() * 8);
        int y = (int) (Math.random() * 8);
        int x1 = (int) (Math.random() * 8);
        int y1 = (int) (Math.random() * 8);
        if (pecas.jogar(pecas.getCor(), x, y, x1, y1)) {
            desenhaBotoes();
            botao[x][y].setIcon(new ImageIcon("src/Imagens/FundoEscuro.png"));
            return true;
        }
        return realiza;
    }

    public void alinhar() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));

        for (JButton[] jButtons : botao) {
            for (int j = 0; j < botao[0].length; j++) {
                panel.add(jButtons[j]);
            }
        }
        add(panel);
    }

    public Jogo() {
        iniciar();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.blue,2));
        setVisible(true);
        alinhar();
        desenhaTabuleiro();
        criaTabuleiro();
    }

    public void trocar(int vermelhas,int pretas) {
        this.vermelhas = vermelhas;
        this.pretas = pretas;
    }

    int contar = 0;
    int x = 0;
    int y = 0;
    int vermelhas;
    int pretas;

    @Override
    public void mouseClicked(MouseEvent e)  {
        boolean realiza = false;
        for (int i = 0; i < botao.length; i++) {
            for (int j = 0; j < botao[0].length; j++) {
                if(e.getSource() == botao[i][j]) {

                    if(vermelhas == 1 && pretas == 1) {//Vermelhas:Computador Vs Pretas:Computador
                        while(!IA(realiza)) {
                        }
                        pecas.mudarTurno();
                    }else if(vermelhas == 2 && pretas == 2) {  //Vermelhas:Humano Vs Pretas:Humano
                        if(contar == 0) {                      //condicional apaga se marcada a mesma cor em seguida
                            if (pecas.verificarExistePeca(pecas.getCor(), i, j)) {//verifica se existe peça na célula
                                botao[i][j].setBorder(createLineBorder(Color.GREEN, 3));
                                x = i;
                                y = j;
                                contar++;
                            }
                        }
                        else if(contar == 1) {
                            botao[x][y].setBorder(createLineBorder(Color.gray,2));
                            if(pecas.jogar(pecas.getCor(), x, y, i, j)) {
                                desenhaBotoes();
                                pecas.mudarTurno();
                            }else {
                                JOptionPane.showMessageDialog(null,"Movimento Invalido");
                            }
                            contar--;
                        }

                    }else if(vermelhas == 1 && pretas == 2) { //Vermelhas:Computador Vs Pretas:Humano
                        if(pecas.getCor().equals("Pretas")) {
                            if(contar == 0) {
                                if (pecas.verificarExistePeca(pecas.getCor(), i, j)) {
                                    botao[i][j].setBorder(createLineBorder(Color.GREEN, 3));
                                    x = i;
                                    y = j;
                                    contar++;
                                }
                            }
                            else if(contar == 1) {
                                botao[x][y].setBorder(createLineBorder(Color.gray,2));
                                if(pecas.jogar(pecas.getCor(), x, y, i, j)) {
                                    desenhaBotoes();
                                    pecas.mudarTurno();
                                }else {
                                    JOptionPane.showMessageDialog(null,"Movimento Invalido");
                                }
                                contar--;
                            }
                        }else {
                            while(!IA(realiza)) {

                            }
                            pecas.mudarTurno();
                        }


                    }else if(vermelhas == 2 && pretas == 1) {//Vermelhas:Humano Vs Pretas:Computador
                        if(pecas.getCor().equals("Vermelhas")) {
                            if(contar == 0) {
                                if (pecas.verificarExistePeca(pecas.getCor(), i, j)) {
                                    botao[i][j].setBorder(createLineBorder(Color.GREEN, 3));
                                    x = i;
                                    y = j;
                                    contar++;
                                }
                            }
                            else if(contar == 1) {
                                botao[x][y].setBorder(createLineBorder(Color.gray,2));
                                if(pecas.jogar(pecas.getCor(), x, y, i, j)) {
                                    desenhaBotoes();
                                    pecas.mudarTurno();
                                }else {
                                    JOptionPane.showMessageDialog(null,"Movimento Invalido");
                                }
                                contar--;
                            }
                        }else {
                            while(!IA(realiza)) {

                            }
                            pecas.mudarTurno();
                        }

                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
