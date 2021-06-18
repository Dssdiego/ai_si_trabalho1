package ifmg.ia.trabalho1;

public class Ponto {

    // Posição no tabuleiro
    // (x -> linha)
    // (y -> coluna)
    int x, y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
