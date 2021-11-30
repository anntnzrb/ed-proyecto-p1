package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.juego.Jugador;
import ec.edu.espol.proyecto.juego.Tablero;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import ec.edu.espol.proyecto.utils.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class TableroController implements Initializable {
    private final String[] inserciones = {"fila", "columna"};
    /* JFX */
    private       Stage    stage;

    /* juego */
    private Tablero tbl;
    private Jugador jugador;
    private int     numErrores;
    private int     comodines;

    @FXML
    private GridPane           tableroGP;
    @FXML
    private ChoiceBox<String>  insercionesCB;
    @FXML
    private ChoiceBox<Integer> desplazarCB;
    @FXML
    private Button             btnEliminar;
    @FXML
    private Button             btnInsertar;
    @FXML
    private Button             btnDespDer;
    @FXML
    private Button             btnDespIzq;
    @FXML
    private Button             btnPuntaje;
    @FXML
    private Label              lblPuntajeJug;
    @FXML
    private Button             btnVidas;
    @FXML
    private Label              lblVidasJug;
    @FXML
    private Label              lblNombreJug;
    @FXML
    private Button             btnRegresar;

    @FXML
    private void onRegresarBtnClick(final ActionEvent ae) throws IOException {
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(Util.getNewScene("main"));
        stage.show();
    }

    /**
     * Arma el tablero con valores aleatorios.
     */
    private void armarTablero() {
        final List<CircularDoublyLinkedList<Button>> listCLL = tbl.getTabla();
        for (int i = 0; i < listCLL.size(); i++) {
            for (int j = 0; j < listCLL.get(i).size(); j++) {
                tableroGP.add(listCLL.get(i).get(j), i, j);
            }
        }

        /* debug */
        tbl.mostrarTablero();
    }

    @FXML
    private void onBtnDespIzqClick() {
        tbl.desplazar(desplazarCB.getValue(), 'i');
    }

    @FXML
    private void onBtnDespDerClick() {
        tbl.desplazar(desplazarCB.getValue(), 'd');
    }

    @FXML
    private void onBtnInsertarClick() {
        if (comodines > 0) {
            if (insercionesCB.getValue().equals("fila")) {
                tbl.addFila();
            } else if (insercionesCB.getValue().equals("columna")) {
                tbl.addColumna();
            }

            /* debug */
            tbl.mostrarTablero();

            // actualizar tablero
            System.out.println(tbl.getTabla().size());
        } else {
            System.out.println("El jugador no tiene mas comodines");
        }

        --comodines;
    }

    @FXML
    private void onBtnVerificarClick() {
        if (numErrores < 3) {
            final List<String> listPalabras = tbl.getListPalabras();
            final String palabra = tbl.getPalabraVerif();

            if (listPalabras.contains(palabra)) {
                System.out.println("El jugador ha encontrado la palabra: "
                                   + palabra);
                tbl.setPuntaje(tbl.getPuntaje() + palabra.length());

                /* actualizar */
                actualizarJuego(true);
            } else {
                System.out.println("La palabra ingresada no es válida");
                tbl.setPuntaje(tbl.getPuntaje() - palabra.length());

                /* actualizar */
                actualizarJuego(false);
            }

            /* siempre se limpiará posterior a verificar */
            tbl.limpiarStrBld();
            System.out.println(tbl.getPuntaje());
        } else {
            System.out.println("El jugador ya no puede seguir jugando.");
        }
    }

    @FXML
    private void onBtnEliminarClick() {
        if (comodines > 0) {
            if (insercionesCB.getValue().equals("fila")) {
                tbl.removeFila();
            } else if (insercionesCB.getValue().equals("columna")) {
                tbl.removeColumna();
            }

            /* debug */
            tbl.mostrarTablero();

            // actualizar tablero
            System.out.println(tbl.getTabla().size());
        } else {
            System.out.println("El jugador no tiene mas comodines");
        }

        --comodines;
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

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        /* juego */
        /* inicialmente el jugador tiene 2 comodines y 0 errores */
        numErrores = 0;
        comodines = 2;

        /* crear tablero */
        tbl = new Tablero("animales" + ".txt", 6);
        armarTablero();

        /* crear Jugador */
        jugador = new Jugador("Carlos");
        lblNombreJug.setText("Hola, " + jugador.getNickname());
        lblPuntajeJug.setText(String.valueOf(tbl.getPuntaje()));
        lblVidasJug.setText(String.valueOf(numErrores));

        /* menu inserciones */
        insercionesCB.getItems().addAll(inserciones);
        insercionesCB.setValue(inserciones[0]);

        /* llenar CB con las filas del tablero */
        IntStream.range(0, tbl.getTabla().size())
                 .forEach(desplazarCB.getItems()::add);
        desplazarCB.setValue(0);
    }
}