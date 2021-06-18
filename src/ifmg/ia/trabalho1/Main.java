package ifmg.ia.trabalho1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Pede ao usuário o tamanho do tabuleiro
        Scanner myObj = new Scanner(System.in);
        System.out.println("Digite o tamanho do tabuleiro (NxN)");
        System.out.println("(deve ser maior que 3):");
        String tamanhoStr = myObj.nextLine();
        int tamanho = Integer.parseInt(tamanhoStr);

        // Confere se o tamanho digitado foi correto
        // (se não foi, sai do programa)
        if(tamanho < 3)
        {
            System.out.println("Tamanho digitado não é válido");
            System.exit(1);
        }

        // Roda o algoritmo
        Algoritmo algo = new Algoritmo();
        algo.rodaAlgoritmo(tamanho);
    }
}
