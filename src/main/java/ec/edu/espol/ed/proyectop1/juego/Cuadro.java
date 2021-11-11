package ec.edu.espol.ed.proyectop1.juego;

public class Cuadro {
     private int x;
    private int y;
    private char data;
    
    public Cuadro(int x, int y, char data){
        this.x = x;
        this.y = y;
        this.data = data;
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

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Casilla{" + "x=" + x + ", y=" + y + ", data=" + data + '}';
    }

}
