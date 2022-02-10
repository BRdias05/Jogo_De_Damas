package Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraFerramentas extends JPanel implements ActionListener {

        private JMenuBar barra;
        private JMenuItem novaPartida,sair,trocarControle;
        private final Principal principal;
        private final Jogo jogo;

        private void iniciar() {
            novaPartida = new JMenuItem("Nova Partida");
            trocarControle = new JMenuItem("Trocar Controle");
            sair = new JMenuItem("Sair");
            JMenu menu = new JMenu("Menu");
            menu.setBorder(BorderFactory.createLineBorder(Color.gray));
            menu.add(novaPartida);
            menu.add(trocarControle);
            menu.add(sair);
            trocarControle.addActionListener(this);
            novaPartida.addActionListener(this);
            sair.addActionListener(this);
            barra = new JMenuBar();
            barra.add(menu);

        }

        private void alinhar() {
            setLayout(new BorderLayout());
            add(barra,BorderLayout.CENTER);
        }

        public BarraFerramentas(Principal principal,Jogo jogo) {
            iniciar();
            alinhar();
            this.jogo = jogo;
            this.principal = principal;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == novaPartida) {
                new PainelCriaPartida(principal,jogo);
            }
            if(e.getSource() == sair) {
                principal.dispose();
            }
            if(e.getSource() == trocarControle) {
                new PainelTrocaControle(principal,jogo);
            }
        }
}

