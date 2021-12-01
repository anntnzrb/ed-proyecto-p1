package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.juego.Jugador;
import ec.edu.espol.proyecto.juego.Tablero;
import ec.edu.espol.proyecto.tda.ArrayList;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import ec.edu.espol.proyecto.utils.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.IntStream;

public class TableroController {
    private final String[] inserciones = {"fila", "columna"};
    /* JFX */
    private       Stage    stage;

    /* juego */
    private Tablero tbl;
    private Jugador jugador;
    private int     numErrores;
    private int     comodines;
    private int     dimensiones;
    /* sopa de letras */
    ArrayList<CircularDoublyLinkedList<Button>> tabla;

    @FXML
    private GridPane           tableroGP;
    @FXML
    private ChoiceBox<String>  insercionesCB;
    @FXML
    private ChoiceBox<Integer> desplazarCB;
    @FXML
    private Label              lblPuntajeJug;
    @FXML
    private Label              lblVidasJug;
    @FXML
    private Label              lblNombreJug;
    @FXML
    private Label              lblTema;

    @FXML
    private void onIniciarBtnClick() {
        armarTablero();
        crearJugador();
        setUp();
    }

    @FXML
    private void onRegresarBtnClick(final ActionEvent ae) throws IOException {
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(Util.getNewScene("main"));
        stage.show();
    }

    private void setUp() {
        /* menu inserciones */
        insercionesCB.getItems().addAll(inserciones);
        insercionesCB.setValue(inserciones[0]);

        actualizarItems();
    }

    private void actualizarItems() {
        /* llenar CB con las filas del tablero */
        desplazarCB.getItems().clear();
        IntStream.range(0, tbl.getTabla().size())
                 .forEach(desplazarCB.getItems()::add);
        desplazarCB.setValue(0);
    }

    /**
     * Arma el tablero con valores aleatorios.
     */
    private void armarTablero() {
        tbl = new Tablero(lblTema.getText() + ".txt", dimensiones);
        tabla = tbl.getTabla();
        armarGP(tabla);

        /* debug */
        tbl.mostrarTablero();
    }

    private void armarGP(List<CircularDoublyLinkedList<Button>> listCLL) {
        tableroGP.getChildren().clear();
        for (int i = 0; i < listCLL.size(); i++) {
            for (int j = 0; j < listCLL.get(i).size(); j++) {
                tableroGP.add(listCLL.get(i).get(j), j, i);
            }
        }
    }

    private void crearJugador() {
        jugador = new Jugador(lblNombreJug.getText());
        lblNombreJug.setText("Hola, " + jugador.getNickname());
        lblPuntajeJug.setText(String.valueOf(tbl.getPuntaje()));
        lblVidasJug.setText(String.valueOf(numErrores));
    }

    @FXML
    private void onBtnDespIzqClick() {
        tbl.desplazar(desplazarCB.getValue(), 'i');
        System.out.println("Ha desplazado las filas hacia la izquierda.");
        tbl.mostrarTablero();

        armarGP(tabla);
    }

    @FXML
    private void onBtnDespDerClick() {
        tbl.desplazar(desplazarCB.getValue(), 'd');
        System.out.println("Ha desplazado las filas hacia la derecha.");
        tbl.mostrarTablero();

        armarGP(tabla);
    }

    @FXML
    private void onBtnInsertarClick() {
        if (comodines > 0) {
            if (insercionesCB.getValue().equals("fila")) {
                tbl.addFila();
                Util.log("El jugador ha insertado una fila.");
            } else if (insercionesCB.getValue().equals("columna")) {
                tbl.addColumna();
                Util.log("El jugador ha insertado una columna.");
            }

            // actualizar tablero
            armarGP(tabla);
            actualizarItems();
            tbl.mostrarTablero();
        } else {
            Util.err("No tiene mas comodines.", true);
        }

        --comodines;
    }

    @FXML
    private void onBtnEliminarClick() {
        if (comodines > 0) {
            if (insercionesCB.getValue().equals("fila")) {
                tbl.removeFila();
                Util.log("El jugador ha eliminado una fila.");
            } else if (insercionesCB.getValue().equals("columna")) {
                tbl.removeColumna();
                Util.log("El jugador ha eliminado una columna.");
            }

            // actualizar tablero
            armarGP(tabla);
            actualizarItems();
            tbl.mostrarTablero();
        } else {
            Util.err("Usted no tiene mas comodines", true);
        }

        --comodines;
    }


    @FXML
    private void onBtnVerificarClick() {
        if (numErrores < 3) {
            final List<String> listPalabras = tbl.getListPalabras();
            final String palabra = tbl.getPalabraVerif();

            if (listPalabras.contains(palabra)) {
                Util.alert("Usted ha encontrado la palabra: " + palabra,
                           true);
                tbl.setPuntaje(tbl.getPuntaje() + palabra.length());

                /* actualizar */
                actualizarJuego(true);
            } else {
                Util.err("La palabra ingresada no es válida", true);
                tbl.setPuntaje(tbl.getPuntaje() - palabra.length());

                /* actualizar */
                actualizarJuego(false);
            }

            /* siempre se limpiará posterior a verificar */
            tbl.limpiarStrBld();
        } else {
            Util.err("Juego terminado, ya no tiene mas intentos", true);
        }
    }

    /**
     * Actualiza los valores de los elementos gráficos.
     *
     * @param estado @{true} si se actualiza cuando el jugador ha ganado
     */
    private void actualizarJuego(final boolean estado) {
        if (!estado) {
            lblVidasJug.setText(String.valueOf(++numErrores));
        }

        lblPuntajeJug.setText(String.valueOf(tbl.getPuntaje()));
    }

    public void setNombre(final String nombre) {
        lblNombreJug.setText(nombre);
    }

    public void setDimension(final int dim) {
        dimensiones = dim;
    }

    public void setTema(final String tema) {
        lblTema.setText(tema);
    }

    @FXML
    public void initialize() {
        /* inicialmente el jugador tiene 2 comodines y 0 errores */
        numErrores = 0;
        comodines = 2;
    }
}