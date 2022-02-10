package Peca;
import Modelo.Principal;
import javax.swing.*;

public class Pecas extends JPanel{

        private final int[][] tabela;
        private final int vermelhas = 1, pretas = 2, damaVermelha = 3, damaPreta = 4, preenche = 5;
        private String cor = "Pretas";
        private int pontosV=11;
        private int pontosP=11;

        public int getFundoEscuro() {return preenche;}
        public int getVermelhas() {return vermelhas;}
        public int getPretas() {return pretas;}
        public int getDamaVermelha() {return damaVermelha;}
        public int getDamaPreta() {return damaPreta;}
        public String getCor() {return this.cor;}

        public void mudarTurno() {
            if(cor.equals("Vermelhas")) {
                cor = "Pretas";
            }else {
                cor = "Vermelhas";
            }
            Principal.setVez(this.cor);
        }

        public Pecas() {
            tabela = new int[8][8];
        }

        public int verDamas(int i,int j) {
            possibilidadeDama();
            return tabela[i][j];
        }

        public void colocarPecas() {
            for (int i = 0; i < tabela.length; i++) {
                for (int j = 0; j < tabela.length; j++) {
                    if (j % 2 == 0) {
                        tabela[1][j] = vermelhas;
                        tabela[7][j] = pretas;
                        tabela[5][j] = pretas;
                        tabela[3][j] = preenche;
                    } else {
                        tabela[0][j] = vermelhas;
                        tabela[6][j] = pretas;
                        tabela[2][j] = vermelhas;
                        tabela[4][j] = preenche;
                    }
                }
            }
        }

        public boolean verificarExistePeca(String color, int x,int y) {
            if(color.equals("Pretas")) {
                return tabela[x][y] == pretas || tabela[x][y] == damaPreta;
            }else if(color.equals("Vermelhas")){
                return tabela[x][y] == vermelhas || tabela[x][y] == damaVermelha;
            }
            return false;
        }

        public void imprimir() {
        }

        public void verificar() {//verifica quantas peças ainda existem
            int contadorP = 0, contadorV = 0;
            for (int[] ints : tabela) {
               for (int j = 0; j < tabela[0].length; j++) {
                if (ints[j] == vermelhas || ints[j] == damaVermelha) {
                    contadorV++;
                } else if (ints[j] == pretas || ints[j] == damaPreta) {
                    contadorP++;
                }
            }
            }
            imprimir();
            if (contadorP == 0 && contadorV > 0) {
            JOptionPane.showMessageDialog(null, "Branco Ganhou");
            }   else if (contadorV == 0 && contadorP > 0) {
            JOptionPane.showMessageDialog(null, "Preto Ganhou");
            }
        }
        public void possibilidadeDama() {
        for (int j = 0; j < tabela[0].length; j++) {
            if (tabela[0][j] == pretas) {
                tabela[0][j] = damaPreta;
            } else if (tabela[7][j] == vermelhas) {
                tabela[7][j] = damaVermelha;
            }
        }
        }


        public boolean jogar(String cor, int x, int y, int x1, int y1) {
            boolean realiza = false;

            int variavel, dama, inimigo, damaInimiga;
            if (cor.equals("Vermelhas")) {
                inimigo = pretas;
                damaInimiga= damaPreta;
                variavel = vermelhas;
                dama = damaVermelha;

            } else {
                damaInimiga = damaVermelha;
                inimigo = vermelhas;
                variavel = pretas;
                dama = damaPreta;
            }
            while (!realiza) {
                //verificação e movimento das peças peão
                if (tabela[x][y] == variavel) {
                    if (y1 > -1 && x1 > -1 && y1 < 8 && x1 < 8) {
                        if (tabela[x1][y1] == 5) {
                            //movimentos peças pretas
                            if (variavel == pretas) {
                                if (x1 == x - 1) { //preta anda uma casa pra frente
                                    if (y1 == y - 1 || y1 == y + 1) {
                                        tabela[x][y] = 5;
                                        tabela[x1][y1] = pretas;
                                        realiza = true;
                                    }
                                }

                                if(x1 == x+2){ // peça preta come pra trás
                                    if (y1 == y + 2) {
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = pretas;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                    }
                                    if (y1 == y - 2) {
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = pretas;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                    }
                                }

                                if (x1 == x - 2) { // peça preta come pra frente
                                    if (y1 == y + 2) {
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = pretas;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                    }
                                    if (y1 == y - 2) {
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = pretas;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                    }
                                }
                            //movimento peças Vermelhas
                            } else {
                                if (x1 == x + 1) { // peca vermelha anda uma casa pra frente
                                    if (y1 == y - 1 || y1 == y + 1) {
                                        tabela[x][y] = 5;
                                        tabela[x1][y1] = vermelhas;
                                        realiza = true;
                                    }
                                }

                                if(x1 == x-2){ // peça vermelha come pra trás
                                    if (y1 == y + 2) {
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = vermelhas;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                    }
                                    if (y1 == y - 2) {
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = vermelhas;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                    }
                                }

                                if (x1 == x + 2) { //peça vermelha come pra frente
                                    if (y1 == y + 2) {
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = vermelhas;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                    }
                                    if (y1 == y - 2) {
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = vermelhas;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }

                                    }
                                }
                            }
                        }

                    }
                }
                //se recebe uma dama
                else if (tabela[x][y] == dama) {
                    if (y1 > -1 && x1 > -1 && y1 < 8 && x1 < 8) {
                        if (tabela[x1][y1] == 5) {
                            if (dama == damaPreta) {
                                //dama preta se movimenta para baixo no tabuleiro
                                if (x1 == x+1||x1==x-1) { //dama anda uma casa para cima ou para baixo
                                    if (y1 == y - 1 || y1 == y + 1) {//coluna da esquerda ou direita
                                        tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }

                                //dama anda duas casas para baixo
                                if (x1 == x + 2) { // dama anda duas linhas
                                    if (y1 == y - 2) { // dama anda duas colunas
                                        if (tabela[x + 1][y - 1] == pretas || tabela[x + 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 2) {
                                        if (tabela[x + 1][y + 1] == pretas || tabela[x + 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }

                                if (x1 == x - 2) { // dama anda duas casas pra cima
                                    if (y1 == y - 2) { // dama anda duas colunas
                                        if (tabela[x - 1][y - 1] == pretas || tabela[x - 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 2) {
                                        if (tabela[x - 1][y + 1] == pretas || tabela[x - 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }


                                //dama anda 3 casas pra baixo
                                if (x1 == x + 3) {
                                    if (y1 == y - 3) {
                                        if (tabela[x + 1][y - 1] == pretas || tabela[x + 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == pretas || tabela[x + 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 3) {
                                        if (tabela[x + 1][y + 1] == pretas || tabela[x + 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == pretas || tabela[x + 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 3 casas pra cima
                                if (x1 == x - 3) {
                                    if (y1 == y - 3) {
                                        if (tabela[x - 1][y - 1] == pretas || tabela[x - 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x -2][y - 2] == pretas || tabela[x - 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x - 2][y - 2] == damaInimiga) {
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 3) {
                                        if (tabela[x - 1][y + 1] == pretas || tabela[x - 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == pretas || tabela[x - 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x -2][y + 2] == damaInimiga) {
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }
                                }
                                //dama anda 4 casas
                                if (x1 == x + 4) {
                                    if (y1 == y - 4) {
                                        if (tabela[x + 1][y - 1] == pretas || tabela[x + 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == pretas || tabela[x + 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == pretas || tabela[x + 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga){
                                                if(tabela[x+3][y-3] == inimigo||tabela[x+3][y-3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if(tabela[x+3][y-3] == inimigo||tabela[x+3][y-3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 4) {
                                        if (tabela[x + 1][y + 1] == pretas || tabela[x + 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == pretas || tabela[x + 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == pretas || tabela[x + 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga){
                                                if(tabela[x+3][y+3] == inimigo||tabela[x+3][y+3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if(tabela[x+3][y+3] == inimigo||tabela[x+3][y+3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 4 casas
                                if (x1 == x - 4) {
                                    if (y1 == y - 4) {
                                        if (tabela[x - 1][y - 1] == pretas || tabela[x - 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == pretas || tabela[x - 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == pretas || tabela[x - 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga){
                                                if(tabela[x-3][y-3] == inimigo||tabela[x-3][y-3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x - 2][y - 2] == damaInimiga) {
                                            if(tabela[x-3][y-3] == inimigo||tabela[x-3][y-3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 4) {
                                        if (tabela[x - 1][y + 1] == pretas || tabela[x - 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == pretas || tabela[x - 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == pretas || tabela[x - 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga){
                                                if(tabela[x-3][y+3] == inimigo||tabela[x-3][y+3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x - 2][y + 2] == damaInimiga) {
                                            if(tabela[x-3][y+3] == inimigo||tabela[x-3][y+3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 5 casas para baixo
                                if (x1 == x + 5) {
                                    if (y1 == y - 5) {
                                        if (tabela[x + 1][y - 1] == pretas || tabela[x + 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == pretas || tabela[x + 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == pretas || tabela[x + 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 4][y - 4] == pretas || tabela[x + 4][y - 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga) {
                                                if (tabela[x +3][y - 3] == inimigo || tabela[x +3][y - 3] == damaInimiga) {
                                                    if (tabela[x +4][y -4] == inimigo || tabela[x +4][y -4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if (tabela[x +3][y - 3] == inimigo || tabela[x +3][y - 3] == damaInimiga) {
                                                if (tabela[x +4][y -4] == inimigo || tabela[x +4][y -4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            if (tabela[x +4][y -4] == inimigo || tabela[x +4][y -4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 4][y - 4] == inimigo || tabela[x + 4][y - 4] == damaInimiga) {
                                            tabela[x + 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 5) {
                                        if (tabela[x + 1][y + 1] == pretas || tabela[x + 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == pretas || tabela[x + 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == pretas || tabela[x + 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 4][y + 4] == pretas || tabela[x + 4][y + 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga) {
                                                if (tabela[x +3][y + 3] == inimigo || tabela[x +3][y + 3] == damaInimiga) {
                                                    if (tabela[x +4][y +4] == inimigo || tabela[x +4][y +4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if (tabela[x +3][y + 3] == inimigo || tabela[x +3][y + 3] == damaInimiga) {
                                                if (tabela[x +4][y +4] == inimigo || tabela[x +4][y +4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            if (tabela[x +4][y +4] == inimigo || tabela[x +4][y +4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 4][y + 4] == inimigo || tabela[x + 4][y + 4] == damaInimiga) {
                                            tabela[x + 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 5 casas para cima
                                if (x1 == x - 5) {
                                    if (y1 == y - 5) {
                                        if (tabela[x - 1][y - 1] == pretas || tabela[x - 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == pretas || tabela[x - 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == pretas || tabela[x - 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 4][y - 4] == pretas || tabela[x - 4][y - 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga) {
                                                if (tabela[x -3][y - 3] == inimigo || tabela[x -3][y - 3] == damaInimiga) {
                                                    if (tabela[x -4][y -4] == inimigo || tabela[x -4][y -4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x -2][y - 2] == damaInimiga) {
                                            if (tabela[x -3][y - 3] == inimigo || tabela[x -3][y - 3] == damaInimiga) {
                                                if (tabela[x -4][y -4] == inimigo || tabela[x -4][y -4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            if (tabela[x -4][y -4] == inimigo || tabela[x -4][y -4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 4][y - 4] == inimigo || tabela[x -4][y - 4] == damaInimiga) {
                                            tabela[x - 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }
                                    if (y1 == y + 5) {
                                        if (tabela[x - 1][y + 1] == pretas || tabela[x - 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == pretas || tabela[x - 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == pretas || tabela[x - 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 4][y + 4] == pretas || tabela[x - 4][y + 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga) {
                                                if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                    if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x - 2][y + 2] == damaInimiga) {
                                            if (tabela[x-3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                if (tabela[x-4][y +4] == inimigo || tabela[x -4][y +4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            if (tabela[x -4][y +4] == inimigo || tabela[x -4][y +4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 4][y + 4] == inimigo || tabela[x -4][y + 4] == damaInimiga) {
                                            tabela[x - 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }
                                }
                                //dama anda 6 casas para baixo
                                if (x1 == x + 6) {
                                    if (y1 == y - 6) {
                                        if (tabela[x + 1][y - 1] == pretas || tabela[x + 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == pretas || tabela[x + 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == pretas || tabela[x + 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 4][y - 4] == pretas || tabela[x + 4][y - 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 5][y - 5] == pretas || tabela[x + 5][y - 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga) {
                                                if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                    if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                        if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                    if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 4][y - 4] == inimigo || tabela[x + 4][y - 4] == damaInimiga) {
                                            if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 5][y - 5] == inimigo || tabela[x + 5][y - 5] == damaInimiga) {
                                            tabela[x + 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 6) {
                                        if (tabela[x + 1][y + 1] == pretas || tabela[x + 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == pretas || tabela[x + 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == pretas || tabela[x + 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 4][y + 4] == pretas || tabela[x + 4][y + 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 5][y + 5] == pretas || tabela[x + 5][y + 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga) {
                                                if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                    if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                        if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                    if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP++);
                                        }
                                        if (tabela[x + 4][y + 4] == inimigo || tabela[x + 4][y + 4] == damaInimiga) {
                                            if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 5][y + 5] == inimigo || tabela[x + 5][y + 5] == damaInimiga) {
                                            tabela[x + 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }

                                }
                                //dama anda 6 casas para cima
                                if (x1 == x - 6) {
                                    if (y1 == y - 6) {
                                        if (tabela[x - 1][y - 1] == pretas || tabela[x - 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == pretas || tabela[x - 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == pretas || tabela[x - 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 4][y - 4] == pretas || tabela[x - 4][y - 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 5][y - 5] == pretas || tabela[x - 5][y - 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga) {
                                                if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                    if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                        if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x- 2][y - 2] == damaInimiga) {
                                            if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                    if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 4][y - 4] == inimigo || tabela[x - 4][y - 4] == damaInimiga) {
                                            if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 5][y - 5] == inimigo || tabela[x - 5][y - 5] == damaInimiga) {
                                            tabela[x - 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }
                                    if (y1 == y + 6) {
                                        if (tabela[x - 1][y + 1] == pretas || tabela[x - 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == pretas || tabela[x - 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == pretas || tabela[x - 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 4][y + 4] == pretas || tabela[x - 4][y + 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 5][y + 5] == pretas || tabela[x - 5][y + 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga) {
                                                if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                    if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                        if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x - 2][y + 2] == damaInimiga) {
                                            if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                    if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 4][y + 4] == inimigo || tabela[x - 4][y + 4] == damaInimiga) {
                                            if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 5][y + 5] == inimigo || tabela[x - 5][y + 5] == damaInimiga) {
                                            tabela[x - 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }

                                }
                                //dama anda 7 casas para baixo
                                if (x1 == x + 7) {
                                    if (y1 == y - 7) {
                                        if (tabela[x + 1][y - 1] == pretas || tabela[x + 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == pretas || tabela[x + 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == pretas || tabela[x + 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 4][y - 4] == pretas || tabela[x + 4][y - 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 5][y - 5] == pretas || tabela[x + 5][y - 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 6][y - 6] == pretas || tabela[x + 6][y - 6] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x+1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga) {
                                                if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                    if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                        if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                            if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                    if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                        if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                    if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 4][y - 4] == inimigo || tabela[x + 4][y - 4] == damaInimiga) {
                                            if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 5][y - 5] == inimigo || tabela[x + 5][y - 5] == damaInimiga) {
                                            if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 6][y - 6] == inimigo || tabela[x + 6][y - 6] == damaInimiga) {
                                            tabela[x + 6][y - 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 7) {
                                        if (tabela[x + 1][y + 1] == pretas || tabela[x + 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == pretas || tabela[x + 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == pretas || tabela[x + 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 4][y + 4] == pretas || tabela[x + 4][y + 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 5][y + 5] == pretas || tabela[x + 5][y + 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 6][y + 6] == pretas || tabela[x + 6][y + 6] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga) {
                                                if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                    if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                        if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                            if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                    if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                        if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                    if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 4][y + 4] == inimigo || tabela[x + 4][y + 4] == damaInimiga) {
                                            if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 5][y + 5] == inimigo || tabela[x + 5][y + 5] == damaInimiga) {
                                            if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x + 6][y + 6] == inimigo || tabela[x + 6][y + 6] == damaInimiga) {
                                            tabela[x + 6][y + 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }

                                }
                                //dama anda 7 casas para cima
                                if (x1 == x - 7) {
                                    if (y1 == y - 7) {
                                        if (tabela[x - 1][y - 1] == pretas || tabela[x - 1][y - 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == pretas || tabela[x - 2][y - 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == pretas || tabela[x - 3][y - 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 4][y - 4] == pretas || tabela[x - 4][y - 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 5][y - 5] == pretas || tabela[x - 5][y - 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 6][y - 6] == pretas || tabela[x - 6][y - 6] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x-1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga) {
                                                if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                    if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                        if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                            if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x - 2][y - 2] == damaInimiga) {
                                            if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                    if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                        if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                    if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 4][y - 4] == inimigo || tabela[x-4][y - 4] == damaInimiga) {
                                            if (tabela[x-5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                if (tabela[x-6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x -4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 5][y - 5] == inimigo || tabela[x - 5][y - 5] == damaInimiga) {
                                            if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 6][y - 6] == inimigo || tabela[x - 6][y - 6] == damaInimiga) {
                                            tabela[x - 6][y - 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 7) {
                                        if (tabela[x - 1][y + 1] == pretas || tabela[x - 1][y + 1] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == pretas || tabela[x - 2][y + 2] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == pretas || tabela[x - 3][y + 3] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 4][y + 4] == pretas || tabela[x - 4][y + 4] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 5][y + 5] == pretas || tabela[x - 5][y + 5] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 6][y + 6] == pretas || tabela[x - 6][y + 6] == damaPreta) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga) {
                                                if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                    if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                        if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                            if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x -2][y + 2] == damaInimiga) {
                                            if (tabela[x-3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                if (tabela[x-4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                    if (tabela[x-5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                        if (tabela[x-6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                    if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 4][y + 4] == inimigo || tabela[x - 4][y + 4] == damaInimiga) {
                                            if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 5][y + 5] == inimigo || tabela[x - 5][y + 5] == damaInimiga) {
                                            if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        }
                                        if (tabela[x - 6][y + 6] == inimigo || tabela[x - 6][y + 6] == damaInimiga) {
                                            tabela[x - 6][y + 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosP(pontosP--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }

                                }

                            }
                            // se recebe dama vermelha
                            else if (dama == damaVermelha) {
                                if (x1 == x+1||x1==x-1) if (y1 == y - 1 || y1 == y + 1) {
                                    tabela[x][y] = 5;
                                    tabela[x1][y1] = dama;
                                    realiza = true;
                                }

                                if (x1 == x + 2) { // dama anda duas linhas
                                    if (y1 == y - 2) { // dama anda duas colunas
                                        if (tabela[x + 1][y - 1] == vermelhas || tabela[x + 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 2) {
                                        if (tabela[x + 1][y + 1] == vermelhas || tabela[x + 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }

                                if (x1 == x - 2) { // dama anda duas casas pra cima
                                    if (y1 == y - 2) { // dama anda duas colunas
                                        if (tabela[x - 1][y - 1] == vermelhas || tabela[x - 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 2) {
                                        if (tabela[x - 1][y + 1] == vermelhas || tabela[x - 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }


                                //dama anda 3 casas pra baixo
                                if (x1 == x + 3) {
                                    if (y1 == y - 3) {
                                        if (tabela[x + 1][y - 1] == vermelhas || tabela[x + 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == vermelhas|| tabela[x + 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 3) {
                                        if (tabela[x + 1][y + 1] == vermelhas || tabela[x + 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == vermelhas || tabela[x + 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if(tabela[x+1][y+1] == inimigo||tabela[x+1][y+1] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 3 casas pra cima
                                if (x1 == x - 3) {
                                    if (y1 == y - 3) {
                                        if (tabela[x - 1][y - 1] == vermelhas || tabela[x - 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x -2][y - 2] == vermelhas || tabela[x - 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x - 2][y - 2] == damaInimiga) {
                                            if(tabela[x-1][y-1] == inimigo||tabela[x-1][y-1] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 3) {
                                        if (tabela[x - 1][y + 1] == vermelhas || tabela[x - 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == vermelhas || tabela[x - 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x -2][y + 2] == damaInimiga) {
                                            if(tabela[x-1][y+1] == inimigo||tabela[x-1][y+1] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 4 casas
                                if (x1 == x + 4) {
                                    if (y1 == y - 4) {
                                        if (tabela[x + 1][y - 1] == vermelhas || tabela[x + 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == vermelhas || tabela[x + 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == vermelhas || tabela[x + 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga){
                                                if(tabela[x+3][y-3] == inimigo||tabela[x+3][y-3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if(tabela[x+3][y-3] == inimigo||tabela[x+3][y-3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 4) {
                                        if (tabela[x + 1][y + 1] == vermelhas || tabela[x + 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == vermelhas || tabela[x + 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == vermelhas || tabela[x + 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga){
                                                if(tabela[x+3][y+3] == inimigo||tabela[x+3][y+3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if(tabela[x+3][y+3] == inimigo||tabela[x+3][y+3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 4 casas
                                if (x1 == x - 4) {
                                    if (y1 == y - 4) {
                                        if (tabela[x - 1][y - 1] == vermelhas || tabela[x - 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == vermelhas || tabela[x - 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == vermelhas || tabela[x - 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga){
                                                if(tabela[x-3][y-3] == inimigo||tabela[x-3][y-3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x - 2][y - 2] == damaInimiga) {
                                            if(tabela[x-3][y-3] == inimigo||tabela[x-3][y-3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 4) {
                                        if (tabela[x - 1][y + 1] == vermelhas || tabela[x - 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == vermelhas || tabela[x - 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == vermelhas || tabela[x - 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga){
                                                if(tabela[x-3][y+3] == inimigo||tabela[x-3][y+3] == damaInimiga){
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x - 2][y + 2] == damaInimiga) {
                                            if(tabela[x-3][y+3] == inimigo||tabela[x-3][y+3] == damaInimiga){
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 5 casas para baixo
                                if (x1 == x + 5) {
                                    if (y1 == y - 5) {
                                        if (tabela[x + 1][y - 1] == vermelhas || tabela[x + 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == vermelhas || tabela[x + 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == vermelhas || tabela[x + 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 4][y - 4] == vermelhas || tabela[x + 4][y - 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga) {
                                                if (tabela[x +3][y - 3] == inimigo || tabela[x +3][y - 3] == damaInimiga) {
                                                    if (tabela[x +4][y -4] == inimigo || tabela[x +4][y -4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if (tabela[x +3][y - 3] == inimigo || tabela[x +3][y - 3] == damaInimiga) {
                                                if (tabela[x +4][y -4] == inimigo || tabela[x +4][y -4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            if (tabela[x +4][y -4] == inimigo || tabela[x +4][y -4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 4][y - 4] == inimigo || tabela[x + 4][y - 4] == damaInimiga) {
                                            tabela[x + 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 5) {
                                        if (tabela[x + 1][y + 1] == vermelhas || tabela[x + 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == vermelhas || tabela[x + 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == vermelhas || tabela[x + 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 4][y + 4] == vermelhas || tabela[x + 4][y + 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga) {
                                                if (tabela[x +3][y + 3] == inimigo || tabela[x +3][y + 3] == damaInimiga) {
                                                    if (tabela[x +4][y +4] == inimigo || tabela[x +4][y +4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if (tabela[x +3][y + 3] == inimigo || tabela[x +3][y + 3] == damaInimiga) {
                                                if (tabela[x +4][y +4] == inimigo || tabela[x +4][y +4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            if (tabela[x +4][y +4] == inimigo || tabela[x +4][y +4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 4][y + 4] == inimigo || tabela[x + 4][y + 4] == damaInimiga) {
                                            tabela[x + 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 5 casas para cima
                                if (x1 == x - 5) {
                                    if (y1 == y - 5) {
                                        if (tabela[x - 1][y - 1] == vermelhas || tabela[x - 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == vermelhas || tabela[x - 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == vermelhas || tabela[x - 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 4][y - 4] == vermelhas || tabela[x - 4][y - 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga) {
                                                if (tabela[x -3][y - 3] == inimigo || tabela[x -3][y - 3] == damaInimiga) {
                                                    if (tabela[x -4][y -4] == inimigo || tabela[x -4][y -4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x -2][y - 2] == damaInimiga) {
                                            if (tabela[x -3][y - 3] == inimigo || tabela[x -3][y - 3] == damaInimiga) {
                                                if (tabela[x -4][y -4] == inimigo || tabela[x -4][y -4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            if (tabela[x -4][y -4] == inimigo || tabela[x -4][y -4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 4][y - 4] == inimigo || tabela[x -4][y - 4] == damaInimiga) {
                                            tabela[x - 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 5) {
                                        if (tabela[x - 1][y + 1] == vermelhas || tabela[x - 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] ==vermelhas || tabela[x - 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == vermelhas || tabela[x - 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 4][y + 4] == vermelhas || tabela[x - 4][y + 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga) {
                                                if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                    if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x - 2][y + 2] == damaInimiga) {
                                            if (tabela[x-3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                if (tabela[x-4][y +4] == inimigo || tabela[x -4][y +4] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            if (tabela[x -4][y +4] == inimigo || tabela[x -4][y +4] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 4][y + 4] == inimigo || tabela[x -4][y + 4] == damaInimiga) {
                                            tabela[x - 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                }
                                //dama anda 6 casas para baixo
                                if (x1 == x + 6) {
                                    if (y1 == y - 6) {
                                        if (tabela[x + 1][y - 1] == vermelhas || tabela[x + 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == vermelhas || tabela[x + 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == vermelhas || tabela[x + 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 4][y - 4] == vermelhas || tabela[x + 4][y - 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 5][y - 5] == vermelhas || tabela[x + 5][y - 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga) {
                                                if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                    if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                        if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                    if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 4][y - 4] == inimigo || tabela[x + 4][y - 4] == damaInimiga) {
                                            if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 5][y - 5] == inimigo || tabela[x + 5][y - 5] == damaInimiga) {
                                            tabela[x + 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 6) {
                                        if (tabela[x + 1][y + 1] == vermelhas || tabela[x + 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == vermelhas || tabela[x + 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == vermelhas || tabela[x + 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 4][y + 4] == vermelhas || tabela[x + 4][y + 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 5][y + 5] == vermelhas || tabela[x + 5][y + 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga) {
                                                if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                    if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                        if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                    if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 4][y + 4] == inimigo || tabela[x + 4][y + 4] == damaInimiga) {
                                            if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 5][y + 5] == inimigo || tabela[x + 5][y + 5] == damaInimiga) {
                                            tabela[x + 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }

                                }
                                //dama anda 6 casas para cima
                                if (x1 == x - 6) {
                                    if (y1 == y - 6) {
                                        if (tabela[x - 1][y - 1] == vermelhas || tabela[x - 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == vermelhas || tabela[x - 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == vermelhas || tabela[x - 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 4][y - 4] == vermelhas || tabela[x - 4][y - 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 5][y - 5] == vermelhas || tabela[x - 5][y - 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga) {
                                                if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                    if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                        if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x- 2][y - 2] == damaInimiga) {
                                            if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                    if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 4][y - 4] == inimigo || tabela[x - 4][y - 4] == damaInimiga) {
                                            if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 5][y - 5] == inimigo || tabela[x - 5][y - 5] == damaInimiga) {
                                            tabela[x - 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 6) {
                                        if (tabela[x - 1][y + 1] == vermelhas || tabela[x - 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == vermelhas || tabela[x - 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == vermelhas || tabela[x - 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 4][y + 4] == vermelhas || tabela[x - 4][y + 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 5][y + 5] == vermelhas || tabela[x - 5][y + 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga) {
                                                if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                    if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                        if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x - 2][y + 2] == damaInimiga) {
                                            if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                    if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 4][y + 4] == inimigo || tabela[x - 4][y + 4] == damaInimiga) {
                                            if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 5][y + 5] == inimigo || tabela[x - 5][y + 5] == damaInimiga) {
                                            tabela[x - 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }

                                }
                                //dama anda 7 casas para baixo
                                if (x1 == x + 7) {
                                    if (y1 == y - 7) {
                                        if (tabela[x + 1][y - 1] == vermelhas || tabela[x + 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y - 2] == vermelhas || tabela[x + 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y - 3] == vermelhas || tabela[x + 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 4][y - 4] == vermelhas || tabela[x + 4][y - 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 5][y - 5] == vermelhas || tabela[x + 5][y - 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 6][y - 6] == vermelhas || tabela[x + 6][y - 6] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x+1][y - 1] == inimigo || tabela[x + 1][y - 1] == damaInimiga) {
                                            if(tabela[x+2][y-2] == inimigo||tabela[x+2][y-2] == damaInimiga) {
                                                if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                    if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                        if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                            if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y - 2] == inimigo || tabela[x + 2][y - 2] == damaInimiga) {
                                            if (tabela[x +3][y - 3] == inimigo || tabela[x+3][y - 3] == damaInimiga) {
                                                if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                    if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                        if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y - 3] == inimigo || tabela[x + 3][y - 3] == damaInimiga) {
                                            if (tabela[x +4][y -4] == inimigo || tabela[x+4][y -4] == damaInimiga) {
                                                if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                    if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 4][y - 4] == inimigo || tabela[x + 4][y - 4] == damaInimiga) {
                                            if (tabela[x +5][y -5] == inimigo || tabela[x+5][y -5] == damaInimiga){
                                                if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 5][y - 5] == inimigo || tabela[x + 5][y - 5] == damaInimiga) {
                                            if (tabela[x +6][y -6] == inimigo || tabela[x+6][y -6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 6][y - 6] == inimigo || tabela[x + 6][y - 6] == damaInimiga) {
                                            tabela[x + 6][y - 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 7) {
                                        if (tabela[x + 1][y + 1] == vermelhas || tabela[x + 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 2][y + 2] == vermelhas || tabela[x + 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 3][y + 3] == vermelhas || tabela[x + 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 4][y + 4] == vermelhas || tabela[x + 4][y + 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 5][y + 5] == vermelhas || tabela[x + 5][y + 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 6][y + 6] == vermelhas || tabela[x + 6][y + 6] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x + 1][y + 1] == inimigo || tabela[x + 1][y + 1] == damaInimiga) {
                                            if(tabela[x+2][y+2] == inimigo||tabela[x+2][y+2] == damaInimiga) {
                                                if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                    if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                        if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                            if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 2][y + 2] == inimigo || tabela[x + 2][y + 2] == damaInimiga) {
                                            if (tabela[x +3][y + 3] == inimigo || tabela[x+3][y + 3] == damaInimiga) {
                                                if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                    if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                        if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 3][y + 3] == inimigo || tabela[x + 3][y + 3] == damaInimiga) {
                                            if (tabela[x +4][y +4] == inimigo || tabela[x+4][y +4] == damaInimiga) {
                                                if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                    if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 4][y + 4] == inimigo || tabela[x + 4][y + 4] == damaInimiga) {
                                            if (tabela[x +5][y +5] == inimigo || tabela[x+5][y +5] == damaInimiga){
                                                if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x + 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 5][y + 5] == inimigo || tabela[x + 5][y + 5] == damaInimiga) {
                                            if (tabela[x +6][y +6] == inimigo || tabela[x+6][y +6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x + 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x + 6][y + 6] == inimigo || tabela[x + 6][y + 6] == damaInimiga) {
                                            tabela[x + 6][y + 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }

                                }
                                //dama anda 7 casas para cima
                                if (x1 == x - 7) {
                                    if (y1 == y - 7) {
                                        if (tabela[x - 1][y - 1] == vermelhas || tabela[x - 1][y - 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y - 2] == vermelhas || tabela[x - 2][y - 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y - 3] == vermelhas || tabela[x - 3][y - 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 4][y - 4] == vermelhas || tabela[x - 4][y - 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 5][y - 5] == vermelhas || tabela[x - 5][y - 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 6][y - 6] == vermelhas || tabela[x - 6][y - 6] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x-1][y - 1] == inimigo || tabela[x - 1][y - 1] == damaInimiga) {
                                            if(tabela[x-2][y-2] == inimigo||tabela[x-2][y-2] == damaInimiga) {
                                                if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                    if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                        if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                            if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y - 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y - 2] == inimigo || tabela[x - 2][y - 2] == damaInimiga) {
                                            if (tabela[x -3][y - 3] == inimigo || tabela[x-3][y - 3] == damaInimiga) {
                                                if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                    if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                        if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y - 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y - 3] == inimigo || tabela[x - 3][y - 3] == damaInimiga) {
                                            if (tabela[x -4][y -4] == inimigo || tabela[x-4][y -4] == damaInimiga) {
                                                if (tabela[x -5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                    if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y - 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 4][y - 4] == inimigo || tabela[x-4][y - 4] == damaInimiga) {
                                            if (tabela[x-5][y -5] == inimigo || tabela[x-5][y -5] == damaInimiga){
                                                if (tabela[x-6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x -4][y - 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 5][y - 5] == inimigo || tabela[x - 5][y - 5] == damaInimiga) {
                                            if (tabela[x -6][y -6] == inimigo || tabela[x-6][y -6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 5][y - 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 6][y - 6] == inimigo || tabela[x - 6][y - 6] == damaInimiga) {
                                            tabela[x - 6][y - 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                        tabela[x1][y1] = dama;
                                        realiza = true;
                                    }
                                    if (y1 == y + 7) {
                                        if (tabela[x - 1][y + 1] == vermelhas || tabela[x - 1][y + 1] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 2][y + 2] == vermelhas || tabela[x - 2][y + 2] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 3][y + 3] == vermelhas || tabela[x - 3][y + 3] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 4][y + 4] == vermelhas || tabela[x - 4][y + 4] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 5][y + 5] == vermelhas || tabela[x - 5][y + 5] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 6][y + 6] == vermelhas || tabela[x - 6][y + 6] == damaVermelha) {
                                            break;
                                        }
                                        if (tabela[x - 1][y + 1] == inimigo || tabela[x - 1][y + 1] == damaInimiga) {
                                            if(tabela[x-2][y+2] == inimigo||tabela[x-2][y+2] == damaInimiga) {
                                                if (tabela[x -3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                    if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                        if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                            if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 1][y + 1] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 2][y + 2] == inimigo || tabela[x -2][y + 2] == damaInimiga) {
                                            if (tabela[x-3][y + 3] == inimigo || tabela[x-3][y + 3] == damaInimiga) {
                                                if (tabela[x-4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                    if (tabela[x-5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                        if (tabela[x-6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 2][y + 2] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 3][y + 3] == inimigo || tabela[x - 3][y + 3] == damaInimiga) {
                                            if (tabela[x -4][y +4] == inimigo || tabela[x-4][y +4] == damaInimiga) {
                                                if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                    if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 3][y + 3] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 4][y + 4] == inimigo || tabela[x - 4][y + 4] == damaInimiga) {
                                            if (tabela[x -5][y +5] == inimigo || tabela[x-5][y +5] == damaInimiga){
                                                if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                    break;
                                                }
                                                break;
                                            }
                                            tabela[x - 4][y + 4] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 5][y + 5] == inimigo || tabela[x - 5][y + 5] == damaInimiga) {
                                            if (tabela[x -6][y +6] == inimigo || tabela[x-6][y +6] == damaInimiga) {
                                                break;
                                            }
                                            tabela[x - 5][y + 5] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        }
                                        if (tabela[x - 6][y + 6] == inimigo || tabela[x - 6][y + 6] == damaInimiga) {
                                            tabela[x - 6][y + 6] = 5;
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                            Principal.setPontosV(pontosV--);
                                        } else
                                            tabela[x][y] = 5;
                                            tabela[x1][y1] = dama;
                                            realiza = true;
                                    }

                                }

                            }
                        }
                    }
                }
                if(!realiza) {
                    break;
                }
            }
            return realiza;
        }
}

