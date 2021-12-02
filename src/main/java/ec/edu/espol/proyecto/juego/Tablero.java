package ec.edu.espol.proyecto.juego;

import ec.edu.espol.proyecto.tda.ArrayList;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import ec.edu.espol.proyecto.utils.Sistema;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

final public class Tablero {
    /* juego */
    /* cantidad de palabras máximas a generar */
    public static final int MAX_PALS_JUEGO = 10;
    private final       int fil;
    private final       int col;

    /* colecciones */
    private final ArrayList<CircularDoublyLinkedList<Letra>> tabla;
    private final List<String>                               listPalabras;
    private final List<String>                               listPalabrasValidas;
    private final List<String>                               listPalabrasEncontradas;
    private final List<Character>                            letras;

    /* cantidad de inserciones extras realizadas */
    private int extraFils;
    private int extraCols;
    private int puntaje;

    public Tablero(final String archivo, final int dimension) {
        fil = dimension;
        col = 1; // siempre es 1 (ya que es una CLL por fila)

        /* se inicia con puntaje en 0 */
        puntaje = 0;

        /* modificaciones del tablero en 0 */
        extraFils = extraCols = 0;

        /* lista que contiene todas las palabras */
        listPalabras = Sistema.leerArchivo(archivo);

        /* lista de palabras válidas */
        listPalabrasValidas = new ArrayList<>();

        /* lista de palabras marcadas (inicialmente vacía) */
        listPalabrasEncontradas = new ArrayList<>();

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
                listPalabrasValidas.addLast(pal.toUpperCase(Locale.ROOT));
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
            final CircularDoublyLinkedList<Letra> cll =
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

                final Letra letra = new Letra(letras.get(num), i, j);
                cll.addLast(letra);
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
        final CircularDoublyLinkedList<Letra> newFilCLL =
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
        final CircularDoublyLinkedList<Letra> newColCLL =
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
    private CircularDoublyLinkedList<Letra> genCLL(final int size) {
        final CircularDoublyLinkedList<Letra> newCLL =
                new CircularDoublyLinkedList<>();

        for (int i = 0; i < size; ++i) {
            final Letra letra = new Letra(Sistema.getRandomCharABC(true));
            newCLL.addLast(letra);
        }

        return newCLL;
    }

    /**
     * Método auxiliar para mostrar el tablero en consola.
     */
    public void mostrarTablero() {
        tabla.forEach(cll -> System.out.printf("%s\n", cll));
    }

    /* getters & setters */
    public ArrayList<CircularDoublyLinkedList<Letra>> getTabla() {
        return tabla;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public List<String> getListPalabras() {
        return listPalabras;
    }

    public List<String> getListPalabrasValidas() {
        return listPalabrasValidas;
    }

    public List<String> getListPalabrasEncontradas() {
        return listPalabrasEncontradas;
    }
}
