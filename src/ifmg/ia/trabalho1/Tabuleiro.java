package ifmg.ia.trabalho1;

import java.util.Random;

import static ifmg.ia.trabalho1.Constants.*;

public class Tabuleiro {

    // Estado do tabuleiro. Ou seja, a matrix 2D que o representa
    int[][] estado;
    int heuristica;

    static int tamanho = 5;

    // Construtor
    public Tabuleiro() {
        this.estado = new int[tamanho][tamanho];
    }

    // Construtor (cópia de tabuleiro)
    public Tabuleiro(Tabuleiro tabuleiro) {
        this.estado = tabuleiro.estado;
    }

    public int[][] getEstado() {
        return estado;
    }

    public void setEstado(int[][] estado) {
        this.estado = estado;
    }

    // Calcula a heurística toda vez que esta é solicitada
    public int getHeuristica() {
        calculaHeuristica();
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public static void setTamanho(int tamanho) {
        Tabuleiro.tamanho = tamanho;
    }

    // Compara 2 tabuleiros
    public int comparaCom(Tabuleiro tabuleiro) {
        return Integer.compare(this.heuristica, tabuleiro.heuristica);
    }

    // A heurística usada aqui é o número de espaços em branco, portanto a ideia é diminuir a heurística à 0
    // Ou seja, preencher todos os espaços vazios com rainhas
    private int calculaHeuristica() {
        int h = 0;
        for (int lin = 0; lin < tamanho; lin++) {
            for (int col = 0; col < tamanho; col++) {
                if (estado[lin][col] == espacoVazio)
                    h++;
            }
        }
        setHeuristica(h);
        return h;
    }

    // Adiciona uma rainha aleatoriamente no tabuleiro
    // (somente nos espaços vazios)
    public void adicionaRainhaAleatoriamente() {
        Random random = new Random();
        int lin = random.nextInt(tamanho);
        int col = random.nextInt(tamanho);

        // Só posiciona uma nova rainha em espaços vazios do tabuleiro
        if (estado[lin][col] == espacoVazio) {
            estado[lin][col] = espacoRainha;

            // Calcula os ataques (espaços indisponíveis)
            // conforme a rainha posicionada

            // Linha
            for (int cont = 0; cont < estado[lin].length; cont++) {
                if (estado[lin][cont] == espacoVazio)
                    estado[lin][cont] = espacoIndisponivel;
            }
            // Coluna
            for (int cont = 0; cont < estado.length; cont++) {
                if (estado[cont][col] == espacoVazio)
                    estado[cont][col] = espacoIndisponivel;
            }
            // Diagonais
            int l = lin;
            int c = col;
            // inferior direita
            while (l < estado.length && c < estado[lin].length) {
                if (estado[l][c] == espacoVazio) {
                    estado[l][c] = espacoIndisponivel;
                }
                l++;
                c++;
            }
            // superior esquerda
            l = lin;
            c = col;
            while (l >= 0 && c >= 0) {
                if (estado[l][c] == espacoVazio) {
                    estado[l][c] = espacoIndisponivel;
                }
                l--;
                c--;
            }
            // inferior esquerda
            l = lin;
            c = col;
            while (l < estado.length && c >= 0) {
                if (estado[l][c] == espacoVazio) {
                    estado[l][c] = espacoIndisponivel;
                }
                l++;
                c--;
            }
            // superior direita
            l = lin;
            c = col;
            while (l >= 0 && c < estado[0].length) {
                if (estado[l][c] == espacoVazio) {
                    estado[l][c] = espacoIndisponivel;
                }
                l--;
                c++;
            }

        }

        // Após adicionar a rainha no tabuleiro, calculamos a heurística do mesmo
        calculaHeuristica();
    }

    // Retorna a quantidade de rainhas presentes no tabuleiro
    public int getQtdeRainhas() {
        int rainhas = 0;
        for (int lin = 0; lin < tamanho; lin++) {
            for (int col = 0; col < tamanho; col++) {
                if (estado[lin][col] == espacoRainha) {
                    rainhas++;
                }
            }
        }
        return rainhas;
    }

    // Busca todas as posições vazias do tabuleiro (em forma de pilha)
    public Pilha getPosicoesVazias() {
        // A quantidade de espaços vazios no tabuleiro é exatamente nossa heurística
        int qtdeVazios = heuristica;

        // Criamos uma pilha com o número de
        Pilha p = new Pilha(qtdeVazios);

        // Percorremos o tabuleiro para adicionar os pontos vazios na pilha
        for (int lin = 0; lin < tamanho; lin++) {
            for (int col = 0; col < tamanho; col++) {
                if(estado[lin][col] == espacoVazio){
                    p.empilha(new Ponto(lin, col));
                }
            }
        }

        return p;
    }

    // Mostra no console de texto uma representação visual do tabuleiro
    public void imprimeNoConsole() {
//        System.out.println("\n");
        for (int lin = 0; lin < tamanho; lin++) {
            for (int col = 0; col < tamanho; col++) {
                int espacoAtual = estado[lin][col];
                if (espacoAtual == espacoVazio)
                    System.out.print(COR_PRETO + estado[lin][col] + " " + COR_RESET);
                if (espacoAtual == espacoRainha)
                    System.out.print(COR_VERMELHO + estado[lin][col] + " " + COR_RESET);
                if (espacoAtual == espacoIndisponivel)
                    System.out.print(COR_AMARELO + estado[lin][col] + " " + COR_RESET);
            }
            System.out.println();
        }

        System.out.println("f(e) = " + heuristica);
    }

}
