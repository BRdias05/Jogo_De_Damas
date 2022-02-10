package Modelo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Principal extends JFrame {
    private static JTextField bvez, pontosVermelhas, pontosPretas;
    Jogo jogo;
    BarraFerramentas barraFerramentas;

    public Principal() {
        //Janela do Jogo
        super("Jogo de Damas");

        iniciar();
        alinhar();
        setVisible(true);
        setResizable(false);
        setSize(590,540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void alinhar() {
        //Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new TitledBorder("Informacion")));
        add(jogo, BorderLayout.CENTER);
        add(barraFerramentas, BorderLayout.NORTH);


        //Painel de vez e pontuação
        JPanel painelEsquerdo = new JPanel();
        bvez = new JTextField("Vez das: Pretas");
        painelEsquerdo.setLayout(new GridLayout(10,2));
        painelEsquerdo.add(bvez);
        pontosVermelhas = new JTextField("Peças Vermelhas: 12");
        pontosVermelhas.setForeground(Color.RED);
        painelEsquerdo.add(pontosVermelhas);
        pontosPretas = new JTextField("Peças Pretas: 12");
        painelEsquerdo.add(pontosPretas);
        this.add(painelEsquerdo, BorderLayout.WEST);
        bvez.setEditable(false);
        pontosVermelhas.setEditable(false);
    }

    public static void setVez(String cor){
        bvez.setText("Vez das: "+cor);
    }
    public static void setPontosP(int pontosP){
        pontosVermelhas.setText("Peças Vermelhas: "+pontosP);
     }
    public static void setPontosV(int pontosV) {
            pontosPretas.setText("Peças Pretas: "+pontosV);
    }


    public static void main(String[] args) {
        new Principal();
    }

    public void atualizarBotoes() {
        jogo.ResetaTabuleiro();
        jogo.desenhaBotoes();
    }

    public void iniciar() {
        jogo = new Jogo();
        barraFerramentas = new BarraFerramentas(this, jogo);
    }
}

