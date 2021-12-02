package ec.edu.espol.proyecto.juego;

final public class Letra {
    private char    contenido;
    private boolean isMarcado;
    private int     fil;
    private int     col;

    public Letra(final char contenido, final int fil, final int col) {
        this.contenido = contenido;
        this.fil = fil;
        this.col = col;
        isMarcado = false;
    }

    public Letra(final char contenido) {
        this(contenido, 0, 0);
    }

    public char getContenido() {
        return contenido;
    }

    public void setContenido(final char contenido) {
        this.contenido = contenido;
    }

    public boolean isMarcado() {
        return isMarcado;
    }

    public void setMarcado(final boolean marcado) {
        isMarcado = marcado;
    }

    public int getFil() {
        return fil;
    }

    public void setFil(final int fil) {
        this.fil = fil;
    }

    public int getCol() {
        return col;
    }

    public void setCol(final int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return Character.toString(contenido);
    }
}
