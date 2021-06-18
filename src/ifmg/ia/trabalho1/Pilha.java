package ifmg.ia.trabalho1;

public class Pilha {

    public Object[] pilha;
    public int posicaoPilha;

    public Pilha(int tamanho) {
        // Indica que a pilha está nula vazia
        this.posicaoPilha = -1;
        // Cria a pilha com o tamanho informado
        this.pilha = new Object[tamanho];
    }

    // Retorna true se a pilha estiver vazia
    // (isEmpty)
    public boolean estaVazia() {
        return this.posicaoPilha == -1;
    }

    // Retorna o tamanho da pilha
    // (is)
    public int tamanho() {
        if (this.estaVazia()) {
            return 0;
        }
        return this.posicaoPilha + 1;
    }

    // Retorna o último valor inserido
    // (top)
    public Object exibeUltimoValor() {
        if (this.estaVazia()) {
            return null;
        }
        return this.pilha[this.posicaoPilha];
    }

    // Desempilha
    // (pop)
    public Object desempilha() {
        if (estaVazia()) {
            return null;
        }
        return this.pilha[this.posicaoPilha--];
    }

    // Empilha
    // (push)
    public void empilha(Object valor) {
        if (this.posicaoPilha < this.pilha.length - 1) {
            this.pilha[++posicaoPilha] = valor;
        }
    }
}