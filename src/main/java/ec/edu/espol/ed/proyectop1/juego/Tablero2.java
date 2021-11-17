package ec.edu.espol.ed.proyectop1.juego;

import ec.edu.espol.ed.proyectop1.tda.ArrayList;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import ec.edu.espol.ed.proyectop1.tda.List;

import java.util.Arrays;
import java.util.Random;

public class Tablero2 {

    int fil;
    int col;
    ArrayList<CircularDoublyLinkedList<Character>> lista;
    private static final String ABC = "abcdefghijklmnopqrstuvwxyz";

    public Tablero2(final String archivo, final int dimension) {
        this.fil = dimension;
        this.col = 1;

        lista = new ArrayList<>();

        /* iterator sobre el ArrayList */
        for (int i = 0; i < fil; ++i) {
            CircularDoublyLinkedList<Character> cll = new CircularDoublyLinkedList<>();

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
                    if (randomPals) {
                        cll.addLast(Sistema.obtenerCharPalabra(pal));
                    } else {
                        cll.addLast(pal.charAt(c));
                    }
                } else {
                    cll.addLast(ABC.charAt(new Random().nextInt(ABC.length())));
                }
            }

            lista.addLast(cll);
        }
    }

    public void mostrarTablero() {
        System.out.println(lista);
    }
}
