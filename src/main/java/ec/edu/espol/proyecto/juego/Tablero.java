package ec.edu.espol.proyecto.juego;

import ec.edu.espol.proyecto.tda.ArrayList;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import ec.edu.espol.proyecto.utils.Sistema;
import javafx.scene.control.Button;

import java.util.Locale;
import java.util.stream.IntStream;

public class Tablero {
    int fil;
    int col;

    /* cantidad de inserciones extras realizadas */
    int extraFils;
    int extraCols;

    /*  */
    StringBuilder strBld;

    private int puntaje;

    ArrayList<CircularDoublyLinkedList<Button>> tabla;
    List<String> listPalabras;

    public Tablero(final String archivo, final int dimension) {
        fil = dimension;
        col = 1; // siempre es 1 (ya que es una CLL por fila)

        /* se inicia con puntaje en 0 */
        puntaje = 0;

        /* modificaciones del tablero en 0 */
        extraFils = extraCols = 0;

        /* la tabla es un ArrayList que contiene muchos CLL */
        tabla = new ArrayList<>();

        /* lista que contiene las palabras válidas */
        listPalabras = new ArrayList<>();

        /* StringBuilder que arma la palabra */
        strBld = new StringBuilder();

        /* iterator sobre el ArrayList */
        for (int i = 0; i < fil; ++i) {
            final CircularDoublyLinkedList<Button> cll =
                    new CircularDoublyLinkedList<>();

            /* obtener una palabra random del archivo de palabras */
            String pal = Sistema.obtenerPalabra(archivo)
                                .toUpperCase(Locale.ROOT);
            int palLenght = pal.length();
            while (palLenght > dimension) {
                pal = Sistema.obtenerPalabra(archivo).toUpperCase(Locale.ROOT);
                palLenght = pal.length();
            }
            listPalabras.addLast(pal);

            /* transformar la palabra a una colección de caracteres para
             * posterior randomizar el órden de sus caracteres (letras).
             */
            final List<Character> palComoList = Sistema.palComoCharList(pal);
            //Sistema.shuffleList(palComoList);

            for (int c = 0; c < fil; ++c) {
                Button btn;
                if (c < palLenght) {
                    btn = new Button(Character.toString(palComoList.get(c)));
                } else {
                    btn = new Button(Sistema.getRandomStringABC(true));
                }

                btn.setOnAction(ev -> {
                    strBld.append(btn.getText());
                });
                cll.addLast(btn);
            }

            /* finalmente agregar la CLL con sus letras al tablero */
            tabla.addLast(cll);
        }
    }

    /**
     * Desplaza una fila de letras en direcciones izquierda o derecha.
     *
     * @param idx  índice de la fila a desplazar
     * @param lado dirección a desplazar (izquierda o derecha)
     */
    public void desplazar(final int idx, final char lado) {
        tabla.get(idx).desplazarNodos(lado);
    }

    /**
     * Agrega una nueva fila (CircularDoublyLinkedList) al final del arreglo
     * que las contiene.
     */
    public void addFila() {
        /* recordar que una fila es una CLL */
        final CircularDoublyLinkedList<Button> newFilCLL =
                genCLL(fil + extraCols);

        /* finalmente agregar la CLL con sus letras al tablero */
        tabla.addLast(newFilCLL);

        ++extraFils;
    }

    /**
     * Agrega una nueva columna (CircularDoublyLinkedList) a la derecha de
     * cada CLL del arreglo que las contiene.
     */
    public void addColumna() {
        /* recordar que una fila es una CLL */
        final CircularDoublyLinkedList<Button> newColCLL =
                genCLL(fil + extraFils);

        /* iterator sobre el ArrayList */
        IntStream.range(0, fil + extraFils)
                 .forEachOrdered(i -> tabla.get(i)
                                           .addLast(newColCLL.get(i)));

        ++extraCols;
    }

    /**
     * Remueve una fila del tablero.
     * NOTA: Por predeterminado se remueve la última fila del tablero.
     */
    public void removeFila() {
        tabla.removeLast();
        --extraFils;
    }

    /**
     * Remueve una columna del tablero.
     * NOTA: Por predeterminado se remueve la última columna del tablero.
     */
    public void removeColumna() {
        /* iterator sobre el ArrayList */
        IntStream.range(0, fil + extraFils)
                 .forEachOrdered(i -> tabla.get(i).removeLast());

        --extraCols;
    }

    /**
     * Genera una {@link CircularDoublyLinkedList} que contiene letras (botones)
     * random acorde a las dimensiones del tablero.
     *
     * @return una {@link CircularDoublyLinkedList}
     */
    private CircularDoublyLinkedList<Button> genCLL(final int size) {
        final CircularDoublyLinkedList<Button> newCLL =
                new CircularDoublyLinkedList<>();

        IntStream.range(0, size)
                 .mapToObj(i -> new Button(Sistema.getRandomStringABC(true)))
                 .forEachOrdered(newCLL::addLast);

        return newCLL;
    }

    /**
     * Método auxiliar para mostrar el tablero en consola.
     */
    public void mostrarTablero() {
        tabla.forEach(cll -> System.out.printf("%s\n", cll));
    }

    public void limpiarStrBld() {
        strBld.setLength(0);
    }

    /* getters & setters */
    public ArrayList<CircularDoublyLinkedList<Button>> getTabla() {
        return tabla;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getPalabraVerif() {
        return strBld.toString();
    }

    public List<String> getListPalabras() {
        return listPalabras;
    }
}
