package ec.edu.espol.ed.proyectop1.juego;


import ec.edu.espol.ed.proyectop1.MainApp;
import ec.edu.espol.ed.proyectop1.tda.ArrayList;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import ec.edu.espol.ed.proyectop1.tda.List;
import ec.edu.espol.ed.proyectop1.tda.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase Sistema.
 * Provee métodos (estáticos exclusivamente) para el funcionamiento del juego.
 */
public class Sistema {
    /**
     * Lee un archivo pasado por parámetro y agrega cada línea como un
     * elemento de la colección.
     *
     * @param archivo archivo a analizar
     * @return lista de tipo String con cada línea del archivo como un elemento
     */
    public static List<String> leerArchivo(final String archivo) {
        try (final BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(Objects.requireNonNull(
                                     MainApp.class.getResourceAsStream(archivo))))) {
            final List<String> xs = new ArrayList<>();
            br.lines()
              .skip(1) // omitir cabezera
              .forEachOrdered(xs::addLast);

            return xs;
        } catch (final IOException ioEx) {
            ioEx.printStackTrace();
        }

        return null;
    }

    /**
     * Retorna un String random de una colección que contiene elementos
     * procesados de la lectura de un archivo pasada por parámetro.
     *
     * @param archivo Archivo a analizar
     * @return String random de la colección
     */
    public static String obtenerPalabra(final String archivo) {
        final List<String> listPalabras = leerArchivo(archivo);

        return Objects.requireNonNull(listPalabras)
                      .get(new Random().nextInt(listPalabras.size()));
    }

    /**
     * Obtiene un caracter random de una palabra pasada por parámetro.
     *
     * @param palabra palabra a analizar
     * @return caracter random de la palabra pasa por parámetro
     */
    public static char obtenerCharPalabra(final String palabra) {
        return palabra.charAt(new Random().nextInt(palabra.length()));
    }

    /**
     * Retorna un caracter aleatorio del alfabeto inglés.
     * <p>
     * En la tabla ASCII, la letra 'a' es el valor 97 y la 'z' es el 122, por
     * lo que simplemente se retorna genera un número aleatorio entre ese
     * rango (inclusivo) y se transforma a char.
     *
     * @return caracter del alfabeto inglés
     */
    public static char getRandomCharABC() {
        return (char) ThreadLocalRandom.current().nextInt('a', 'z' + 1);
    }

    public static List<Character> palComoCharList(final String palabra) {
        List<Character> xs = new ArrayList<>();
        for (int i = 0, palLenght = palabra.length(); i < palLenght; ++i) {
            xs.addLast(palabra.charAt(i));
        }

        return xs;
    }

    public static <T> void shuffleList(final List<T> xs) {
        final int xsSize = xs.size();
        for (int i = 0; i < xsSize; i++) {
            final int change = i + ThreadLocalRandom.current().nextInt(xsSize - i);
            swap(xs, i, change);
        }
    }

    private static <T> void swap(final List<T> xs, final int i,
                              final int change) {
        final T helper = xs.get(i);
        xs.set(i, xs.get(change));
        xs.set(change, helper);
    }
    
        
    public  static CircularDoublyLinkedList<String> moverDerecha(CircularDoublyLinkedList<String> np){
       Node<String> tmp;
       CircularDoublyLinkedList<String> resultado = new CircularDoublyLinkedList<>();
        int i = 1;
        for (tmp= np.getLast().getNext();i<=np.size(); tmp = tmp.getNext()) {
                resultado.addLast(tmp.getPrev().getData());
                i++;
        }
        return resultado;
    }
    
    public  static CircularDoublyLinkedList<String> moverIzquierda(CircularDoublyLinkedList<String> np){
       Node<String> tmp;
       CircularDoublyLinkedList<String> resultado = new CircularDoublyLinkedList<>();
        int i = 1;
        for (tmp= np.getLast().getNext();i<=np.size(); tmp = tmp.getNext()) {
                resultado.addLast(tmp.getNext().getData());
                i++;
        }
        return resultado;
    }
}