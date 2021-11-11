package ec.edu.espol.ed.proyectop1.juego;

public class Tablero {
    int fil;
    int col;
    Cuadro[][] tabla;

    // new Tablero(7, 7)
    public Tablero(int fil, int col) {
        this.fil = fil;
        this.col = col;

        tabla = new Cuadro[fil][col];
        llenarTablero();
    }

    private void llenarTablero() {
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                //
            }
        }
    }
}
