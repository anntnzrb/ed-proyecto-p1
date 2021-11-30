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
    private Stage stage;
    private       Tablero  tbl;
    private       int      comodines   = 2;

    @FXML
    private GridPane          tableroGP;
    @FXML
    private ChoiceBox<String> insercionesCB;
    @FXML
    private ChoiceBox         desplazarCB;
    @FXML
    private Button            btnEliminar;
    @FXML
    private Button            btnInsertar;
    @FXML
    private Button            btnDespDer;
    @FXML
    private Button            btnDespIzq;
    @FXML
    private Button            btnPuntaje;
    @FXML
    private Label             lblPuntajeJug;
    @FXML
    private Button            btnVidas;
    @FXML
    private Label             lblVidasJug;
    @FXML
    private Label             lblNombreJug;
    @FXML
    private Button            btnRegresar;

    @FXML
    private void onRegresarBtnClick(final ActionEvent ae) throws IOException {
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(Util.getNewScene("main"));
        stage.show();
    }

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

    //@FXML
    //private void onBtnDespDerClick() {
    //    //
    //}

    //@FXML
    //private void onBtnDespIzqClick() {
    //    //
    //}

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

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        /* crear tablero */
        tbl = new Tablero("animales" + ".txt", 11);
        armarTablero();

        /* crear Jugador */
        final Jugador jugador = new Jugador("Carlos", 0);
        lblNombreJug.setText("Hola, " + jugador.getNickname());
        lblPuntajeJug.setText(String.valueOf(jugador.getPuntaje()));
        lblVidasJug.setText(String.valueOf(jugador.getVidas()));

        /* menu inserciones */
        insercionesCB.getItems().addAll(inserciones);
        insercionesCB.setValue(inserciones[0]);

        /* llenar CB con las filas del tablero */
        IntStream.range(0, tbl.getTabla().size())
                 .forEach(desplazarCB.getItems()::add);
        desplazarCB.setValue(0);
    }
}