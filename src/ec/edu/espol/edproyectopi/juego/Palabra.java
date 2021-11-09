package ec.edu.espol.edproyectopi.juego;

public class Palabra {
    final String data;
    final int tam;

    public Palabra(final String data) {
        this.data = data;
        this.tam = data.length();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public String toString() {
        return data;
    }
}
