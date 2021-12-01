package ec.edu.espol.proyecto.juego;

import ec.edu.espol.proyecto.tda.ArrayList;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import ec.edu.espol.proyecto.utils.Sistema;
import javafx.scene.control.Button;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Tablero {
    /* juego */
    /* cantidad de palabras máximas a generar */
    private static final int MAX_PALS_JUEGO = 12;
    int                                         fil;
    int                                         col;
    /* cantidad de inserciones extras realizadas */
    int                                         extraFils;
    int                                         extraCols;
    /*  */
    StringBuilder                               strBld;
    ArrayList<CircularDoublyLinkedList<Button>> tabla;
    List<String>                                listPalabras;
    List<String>                                listPalabrasValidas;
    List<Character>                             letras;
    private int puntaje;

    public Tablero(final String archivo, final int dimension) {
        fil = dimension;
        col = 1; // siempre es 1 (ya que es una CLL por fila)

        /* se inicia con puntaje en 0 */
        puntaje = 0;

        /* modificaciones del tablero en 0 */
        extraFils = extraCols = 0;

        /* StringBuilder que arma la palabra */
        strBld = new StringBuilder();

        /* lista que contiene todas las palabras */
        listPalabras = Sistema.leerArchivo(archivo);

        /* lista de palabras válidas */
        listPalabrasValidas = new ArrayList<>();

        /*
         * crear una colección que contenga las MAX_PALS_JUEGO cantidad de
         * palabras (distintas).
         */
        int numPals = 0;
        while (numPals < MAX_PALS_JUEGO) {
            final String pal = listPalabras.get(
                    ThreadLocalRandom.current()
                                     .nextInt(0, listPalabras.size()));

            if (!listPalabrasValidas.contains(pal)) {
                listPalabrasValidas.addLast(pal);
                ++numPals;
            }
        }

        /* hacer una colección de caracteres a partir de las palabras válidas */
        letras = new ArrayList<>();
        listPalabrasValidas.forEach(pal -> {
            for (final char ch : pal.toCharArray()) {
                letras.addLast(ch);
            }
        });

        /* colección que contiene los índices de las letras a escoger */
        final List<Integer> indiceLetrasUsadas = new ArrayList<>();

        /* la tabla es un ArrayList que contiene CLLs como filas */
        tabla = new ArrayList<>();
        for (int i = 0; i < fil; ++i) {
            /* crear una CLL por fila */
            final CircularDoublyLinkedList<Button> cll =
                    new CircularDoublyLinkedList<>();

            for (int j = 0; j < fil; ++j) {
                /* obtener índice random */
                int num = ThreadLocalRandom.current().nextInt(0, letras.size());
                /* si el índice no está en la colección, agregarlo */
                if (!indiceLetrasUsadas.contains(num)) {
                    indiceLetrasUsadas.addLast(num);
                    /* si está presente, obtener mas randoms hasta que no esté */
                } else {
                    do {
                        num = ThreadLocalRandom.current()
                                               .nextInt(0, letras.size());
                    } while (indiceLetrasUsadas.contains(num));
                }

                final Button btn = new Button(Character.toString(letras.get(num)));
                btn.setOnAction(ev -> strBld.append(btn.getText()));
                cll.addLast(btn);
            }

            /* finalmente agregar la CLL con sus letras al tablero */
            tabla.addLast(cll);
        }

        /* debug */
        System.out.printf("Lista de palabras por jugar: %s\n",
                          listPalabrasValidas);
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

    public List<String> getListPalabrasValidas() {
        return listPalabrasValidas;
    }
}
