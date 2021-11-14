package ec.edu.espol.ed.proyectop1.juego;

import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import ec.edu.espol.ed.proyectop1.tda.List;

import java.util.Arrays;

public class Tablero2 {
    int fil;
    int col;
    List<Character>[][] xs;

    public Tablero2(final String archivo, final int dimension) {
        this.fil = dimension;
        this.col = dimension;

        xs = new CircularDoublyLinkedList[fil][col];

        for (int i = 0; i < fil; ++i) {
            for (int j = 0; j < col; ++j) {
                /* crear una CLL con un caracter random de una palabra
                 * random.
                 */
                final CircularDoublyLinkedList<Character> charCLL =
                        new CircularDoublyLinkedList<>();
                charCLL.addLast(Sistema.obtenerCharPalabra(
                        Sistema.obtenerPalabra(archivo)));
                if (xs != null) {
                    xs[i][j] = charCLL;
                }
            }
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                System.out.println(xs[i][j]);
            }
        }
        System.out.println(Arrays.deepToString(xs));
    }
}
