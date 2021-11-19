package ec.edu.espol.ed.proyectop1.juego;

import ec.edu.espol.ed.proyectop1.tda.ArrayList;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import ec.edu.espol.ed.proyectop1.tda.List;

import java.util.Locale;
import java.util.stream.IntStream;

public class Tablero {
    int fil;
    int col;

    /* cantidad de inserciones extras realizadas */
    int extraFils;
    int extraCols;

    ArrayList<CircularDoublyLinkedList<Character>> tabla;

    public Tablero(final String archivo, final int dimension) {
        fil = dimension;
        col = 1;

        extraFils = extraCols = 0;

        tabla = new ArrayList<>();

        /* iterator sobre el ArrayList */
        for (int i = 0; i < fil; ++i) {
            final CircularDoublyLinkedList<Character> cll =
                    new CircularDoublyLinkedList<>();

            /* obtener una palabra random del archivo de palabras */
            String pal = Sistema.obtenerPalabra(archivo)
                                .toUpperCase(Locale.ROOT);
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
                    cll.addLast(Character.toUpperCase(Sistema.getRandomCharABC()));
                }
            }

            tabla.addLast(cll);
        }
    }

    public void desplazar(final int idx, final char lado) {
        tabla.get(idx).desplazarNodos(lado);
    }

    /**
     * Agrega una nueva fila (CircularDoublyLinkedList) al final del arreglo
     * que las contiene.
     */
    public void addFila() {
        /* recordar que una fila es una CLL */
        final CircularDoublyLinkedList<Character> newFilCLL =
                genCLL(fil + extraCols);

        tabla.addLast(newFilCLL);

        ++extraFils;
    }

    /**
     * Agrega una nueva columna (CircularDoublyLinkedList) a la derecha de
     * cada CLL del arreglo que las contiene.
     */
    public void addColumna() {
        /* recordar que una fila es una CLL */
        final CircularDoublyLinkedList<Character> newColCLL =
                genCLL(fil + extraFils);

        /* iterator sobre el ArrayList */
        IntStream.range(0, fil + extraFils)
                 .forEachOrdered(i -> tabla.get(i)
                                           .addLast(newColCLL.get(i)));

        ++extraCols;
    }

    /**
     * Genera una {@link CircularDoublyLinkedList} que contiene letras
     * random acorde a las dimensiones del tablero.
     *
     * @return una {@link CircularDoublyLinkedList}
     */
    private CircularDoublyLinkedList<Character> genCLL(final int size) {
        final CircularDoublyLinkedList<Character> newCLL =
                new CircularDoublyLinkedList<>();

        IntStream.range(0, size)
                 .mapToObj(i -> Character.toUpperCase(Sistema.getRandomCharABC()))
                 .forEachOrdered(newCLL::addLast);

        return newCLL;
    }

    private void actualizarTablero() {
        ++fil;
    }

    public void mostrarTablero() {
        tabla.forEach(cll -> System.out.printf("%s\n", cll));
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
