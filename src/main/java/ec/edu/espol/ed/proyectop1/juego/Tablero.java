package ec.edu.espol.ed.proyectop1.juego;

import ec.edu.espol.ed.proyectop1.tda.ArrayList;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import ec.edu.espol.ed.proyectop1.tda.List;

public class Tablero {
    int fil;
    int col;

    ArrayList<CircularDoublyLinkedList<Character>> tabla;

    public Tablero(final String archivo, final int dimension) {
        fil = dimension;
        col = 1;

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

            /* transformar la palabra a una colección de caracteres para
             * posterior randomizar el órden de sus caracteres (letras).
             */
            final List<Character> palComoList = Sistema.palComoCharList(pal);
            Sistema.shuffleList(palComoList);

            for (int c = 0; c < fil; ++c) {
                if (c < palLenght) {
                    cll.addLast(palComoList.get(c));
                } else {
                    cll.addLast(Sistema.getRandomCharABC());
                }
            }

            tabla.addLast(cll);
        }
    }

    // cll = ['p', 'e', 'r', 'r', 'o'. 'x', 'y']
    // cll mod = ['y', 'p', 'e', 'r', 'r', 'o', 'x']


    public void desplazarDerecha(final int idx) {
        final var cll = tabla.get(idx);
        final CircularDoublyLinkedList<Character> cllMod =
                new CircularDoublyLinkedList<>();

        for (int i = 0; i < cll.size(); i++) {
            final char ch = cll.get(i); // 'p'
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
