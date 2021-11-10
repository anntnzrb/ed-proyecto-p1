package ec.edu.espol.ed.proyectop1.juego;


import ec.edu.espol.ed.proyectop1.MainApp;
import ec.edu.espol.ed.proyectop1.tda.ArrayList;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import ec.edu.espol.ed.proyectop1.tda.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

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
                             new InputStreamReader(Objects.requireNonNull(MainApp.class.getResourceAsStream(archivo))))) {
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
     * Convierte una colección de palabras (tipo String) a una Lista
     * Circular doblemente enlazada de tipo Palabra.
     *
     * @param listPalabras lista de tipo String con palabras
     * @return lista de tipo Palabra
     */
    public static List<Palabra> genPalabras(List<String> listPalabras) {
        List<Palabra> palabrasCL = new CircularDoublyLinkedList<>();
        listPalabras.forEach(p -> palabrasCL.addLast(new Palabra(p)));

        return palabrasCL;
    }
}