package ifmg.ia.trabalho1;

public class Algoritmo {

    int qtdeExpansoes = 0;

    public void rodaAlgoritmo(int tamanho) {
        // Mostramos um texto de legenda para orientar o usuário
        System.out.println("\nLegenda:");
        System.out.println("1 = Rainha");
        System.out.println("4 = Indisponível");
        System.out.println("0 = Vazio");

        Tabuleiro.setTamanho(tamanho);

        // Definimos um tabuleiro inicial
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.adicionaRainhaAleatoriamente();
        System.out.println("\nEstado inicial do tabuleiro:");
        tabuleiro.imprimeNoConsole();

        // Executamos o algoritmo enquanto nosso objetivo não foi atingido
        while(tabuleiro.getHeuristica() > 0) {
            // Criamos um tabuleiro de referência (para salvar o melhor estado encotrado)
            Tabuleiro melhorTab = new Tabuleiro(tabuleiro);
            int melhorHeuristica = -1;

            // Percorremos todos os pontos vazios do tabuleiro
            Pilha posicoesVazias = tabuleiro.getPosicoesVazias();
            while (posicoesVazias.tamanho() > 0) {
                // Criamos um novo tabuleiro (cópia do atual) e adicionamos uma rainha aleatoriamente nele,
                // calculando a heurística (internamente)
                Tabuleiro tab2 = new Tabuleiro(tabuleiro);
                tab2.adicionaRainhaAleatoriamente();

                // Se a heurística do novo tabuleiro é maior que a do melhor já encontrado, este
                // vira o nosso melhor atual
                if(tab2.getHeuristica() > melhorHeuristica)
                {
                    melhorTab = new Tabuleiro(tab2);
                    melhorHeuristica = melhorTab.getHeuristica();
                }

                // Desempilhamos o elemento vazio atual
                posicoesVazias.desempilha();

                // Contamos a quantidade de vezes que expandimos os nós
                qtdeExpansoes++;

//                System.out.println("f(e1): " + tabuleiro.heuristica);
//                System.out.println("f(e2): " + tab2.heuristica);
//                System.out.println("f(eDiff): " + tabuleiro.comparaCom(tab2));
            }

            // Salvamos o melhor caso no tabuleiro original
            tabuleiro = new Tabuleiro(melhorTab);
        }

        // Imprimimos os resultados
        System.out.println("\nResultado:");
        tabuleiro.imprimeNoConsole();
        System.out.println("\nQuantidade de expansões da árvore: " + qtdeExpansoes);
        System.out.println("Quantidade de rainhas organizadas no tabuleiro: " + tabuleiro.getQtdeRainhas());
    }
}
