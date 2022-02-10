package Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelTrocaControle extends JFrame implements ActionListener{

        private Label vermelhas,pretas,vs;
        private JButton botao1,botao2;
        private JComboBox<Object> combo1,combo2;
        private final Principal principal;
        private final Jogo painelJogo;

        public void iniciar() {

            botao1= new JButton("Confirmar");
            botao1.setBounds(30,100,100,30);
            botao1.addActionListener(this);
            botao1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

            botao2= new JButton("Voltar");
            botao2.setBounds(250,100,100,30);
            botao2.addActionListener(this);
            botao2.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

            vermelhas = new Label("Vermelhas");
            vermelhas.setBounds(78,10,70,20);
            pretas = new Label("Pretas");
            pretas.setBounds(250,10,40,20);
            vs = new Label("VS");
            vs.setBounds(185, 35, 30, 20);

            combo1 = new JComboBox<>();
            combo1.addItem("");
            combo1.addItem("Computador");
            combo1.addItem("Humano");
            combo2 = new JComboBox<>();
            combo2.addItem("");
            combo2.addItem("Computador");
            combo2.addItem("Humano");

            combo1.setBounds(60,35,100,20);
            combo2.setBounds(230,35,100,20);
        }

        public PainelTrocaControle(Principal Pprincipal,Jogo jogo) {
            iniciar();
            principal = Pprincipal;
            painelJogo = jogo;
            setLayout(null);
            getContentPane().add(vermelhas);
            getContentPane().add(pretas);
            getContentPane().add(combo1);
            getContentPane().add(combo2);
            getContentPane().add(vs);
            getContentPane().add(botao1);
            getContentPane().add(botao2);
            setResizable(false);
            setSize(400,180);
            setLocationRelativeTo(principal);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

    @SuppressWarnings("null")
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == botao1) {
                if(combo1.getSelectedIndex() == 0 || combo2.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(principal,"Os campos não podem estar vazios");
                }else {
                    painelJogo.trocar(combo1.getSelectedIndex(), combo2.getSelectedIndex());
                    dispose();
                }
            }else {
                dispose();
            }

        }
}

