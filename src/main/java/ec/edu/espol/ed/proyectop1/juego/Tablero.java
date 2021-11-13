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
        public Tablero(){
        tabla = new Cuadro[fil][col];
        
        llenarTablero();
    }
    

     private void llenarTablero(){
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                tabla[i][j] = new Cuadro(i,j,' ');                
            }
        }
    }
    
    public void mostrarTablero(){
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(tabla[i][j].getData()+" ");
            }
            System.out.println("");
        }
    }

    public int getFil() {
        return fil;
    }

    public void setFil(int fil) {
        this.fil = fil;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Cuadro[][] getTabla() {
        return tabla;
    }

    public void setTabla(Cuadro[][] tabla) {
        this.tabla = tabla;
    }
    
    
}
