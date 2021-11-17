package ec.edu.espol.ed.proyectop1.juego;

import ec.edu.espol.ed.proyectop1.tda.ArrayList;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;

public class Tablero {
    int fil;
    int col;

    ArrayList<CircularDoublyLinkedList<Character>> tabla;

    public Tablero(final String archivo, final int dimension) {
        this.fil = dimension;
        this.col = 1;

        tabla = new ArrayList<>();

        /* iterator sobre el ArrayList */
        for (int i = 0; i < fil; ++i) {
            final CircularDoublyLinkedList<Character> cll =
                    new CircularDoublyLinkedList<>();

            String pal = Sistema.obtenerPalabra(archivo);
            final int palLenght = pal.length();
            while (palLenght > dimension) {
                pal = Sistema.obtenerPalabra(archivo);
            }

            // ['p', 'e', 'r', 'r', 'o']
            // .shuffle()
            // ['e', 'p', 'r', 'o', 'r']
            // pickAny

            boolean randomPals = false;
            for (int c = 0; c < fil; ++c) {
                if (c < palLenght) {
                    cll.addLast(randomPals
                                ? Sistema.obtenerCharPalabra(pal)
                                : pal.charAt(c));
                } else {
                    cll.addLast(Sistema.getRandomCharABC());
                }
            }

            tabla.addLast(cll);
        }
    }

    public void mostrarTablero() {
        System.out.println(tabla);
    }

    public int getFil() {
        return fil;
    }

    public int getCol() {
        return col;
    }

    public ArrayList<CircularDoublyLinkedList<Character>> getTabla() {
        return tabla;
    }
}
