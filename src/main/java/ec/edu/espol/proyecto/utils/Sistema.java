package ec.edu.espol.proyecto.utils;


import ec.edu.espol.proyecto.MainApp;
import ec.edu.espol.proyecto.tda.ArrayList;
import ec.edu.espol.proyecto.tda.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Enum Sistema.
 * Provee métodos (estáticos exclusivamente) para el funcionamiento del juego.
 */
public enum Sistema {
    ;

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
     * Retorna un número aleatorio del rango especificado.
     *
     * @param minv valor mínimo
     * @param maxv valor máximo
     * @return entero aleatorio dentro del rango
     */
    public static int getRandomInt(final int minv, final int maxv) {
        return ThreadLocalRandom.current().nextInt(minv, maxv);
    }

    /**
     * Wrapper de {@link #getRandomInt(int, int)}
     */
    public static int getRandomInt(final int maxv) {
        return getRandomInt(0, maxv);
    }

    /**
     * Retorna un String random de una colección que contiene elementos
     * procesados de la lectura de un archivo pasada por parámetro.
     *
     * @param archivo Archivo a analizar
     * @return String random de la colección
     */
    @Deprecated
    public static String obtenerPalabra(final String archivo) {
        final List<String> listPalabras = leerArchivo(archivo);

        return Objects.requireNonNull(listPalabras)
                      .get(getRandomInt(listPalabras.size()));
    }

    /**
     * Obtiene un caracter random de una palabra pasada por parámetro.
     *
     * @param palabra palabra a analizar
     * @return caracter random de la palabra pasa por parámetro
     */
    @Deprecated
    public static char obtenerCharPalabra(final String palabra) {
        return palabra.charAt(getRandomInt((palabra.length())));
    }

    /**
     * Retorna un caracter aleatorio del alfabeto inglés.
     * <p>
     * En la tabla ASCII, la letra 'a' es el valor 97 y la 'z' es el 122, por
     * lo que simplemente se retorna genera un número aleatorio entre ese
     * rango (inclusivo) y se transforma a char.
     *
     * @param isUpperCase si se desea el valor del caracter en mayúsculas
     * @return caracter del alfabeto inglés
     */
    public static char getRandomCharABC(final boolean isUpperCase) {
        return isUpperCase
               ? (char) getRandomInt('A', 'Z' + 1)
               : (char) getRandomInt('a', 'z' + 1);
    }

    /**
     * Retorna un String aleatorio (tamaño 1) del alfabeto inglés.
     * <p>
     * Este método invoca internamente a {@link #getRandomCharABC(boolean)}.
     *
     * @param isUpperCase si se desea el valor del caracter en mayúsculas
     * @return String del alfabeto inglés
     */
    @Deprecated
    public static String getRandomStringABC(final boolean isUpperCase) {
        return Character.toString(getRandomCharABC(isUpperCase));
    }

    /**
     * Retorna una colección de caracteres con las letras de una palabra.
     *
     * @param palabra palabra a ser transformada a colección de caracteres
     * @return colección de caracteres
     */
    @Deprecated
    public static List<Character> palComoCharList(final String palabra) {
        final List<Character> xs = new ArrayList<>();
        for (final char ch : palabra.toCharArray()) {
            xs.addLast(ch);
        }

        return xs;
    }

    /**
     * Mezcla aleatoriamente los elementos de la colección pasada por parámetro.
     * <p>
     * Recursos:
     * - https://stackoverflow.com/a/16014748
     *
     * @param xs  colección la cual sus elementso serán mezclados aleatoriamente
     * @param <T> tipo de la colección
     */
    @Deprecated
    public static <T> void shuffleList(final List<T> xs) {
        final int xsSize = xs.size();
        for (int i = 0; i < xsSize; i++) {
            final int change =
                    i + getRandomInt(xsSize - i);
            swap(xs, i, change);
        }
    }

    /**
     * Función helper para cambiar 2 índices pasados por parámetro de una
     * colección.
     * <p>
     * NOTA: Este método fue diseñado para emular el compartamiento de el
     * método shuffle de {@link java.util.Collections}.
     *
     * @param xs  colección a ser procesada
     * @param i   índice 1
     * @param j   índice 2
     * @param <T> tipo de la colección
     */
    private static <T> void swap(final List<T> xs, final int i, final int j) {
        final T helper = xs.get(i);
        xs.set(i, xs.get(j));
        xs.set(j, helper);
    }
}