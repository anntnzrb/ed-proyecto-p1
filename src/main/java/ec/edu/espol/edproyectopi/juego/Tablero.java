package ec.edu.espol.edproyectopi.juego;

public class Tablero {
    Object[][] matriz;
    private final int fil;
    private final int col;

    Tablero(final int fil, final int col) {
        this.fil = fil;
        this.col = col;
        matriz = new Object[fil][col];
    }
}
