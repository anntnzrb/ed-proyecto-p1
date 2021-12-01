package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.utils.Util;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

final public class MainController {
    /**
     * arreglo con las posibles dimensiones del tablero
     */
    private final String[] dimsTablero = {"8x8", "9x9", "10x10", "11x11", "12x12", "13x13", "14x14"};

    /**
     * arreglo con los temas del juego
     */
    private final String[] temas = {"animales", "deportes", "frutas", "paises"};

    /* JFX */
    private Stage             stage;
    @FXML
    private ChoiceBox<String> temaCB;
    @FXML
    private ChoiceBox<String> dimTableroCB;
    @FXML
    private TextField         txtNombre;

    /**
     * Retorna la dimeensión del tablero, en realidad solo es el primer número
     * ya que el tablero siempre es cuadrado.
     *
     * @return la dimensión del tablero
     */
    private int getDimTablero() {
        return Integer.parseInt(dimTableroCB.getValue().split("x")[0]);
    }

    /**
     * Retorna el tema seleccionado.
     *
     * @return String con el nombre del tema seleccionado.
     */
    private String getTema() {
        return temaCB.getValue();
    }

    @FXML
    private void onJugarBtnClick(final ActionEvent ae) throws IOException {
        final int dim = getDimTablero();
        System.out.printf(
                "El jugador ha elegido un tablero de dimensiones: %dx%d\n",
                dim,
                dim);


        /* crear instancia del controlador Tablero y pasar info */
        final FXMLLoader fxmlLoader = Util.getFXMLLoader("second");
        final Parent root = fxmlLoader.load();

        final TableroController tblController = fxmlLoader.getController();
        tblController.setNombre(txtNombre.getText());
        tblController.setDimension(getDimTablero());
        tblController.setTema(getTema());

        /* trasladar a nueva escena */
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    private void onSalirBtnClick() {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        /* agregar las dimensiones al ChoiceBox */
        dimTableroCB.getItems().addAll(dimsTablero);
        temaCB.getItems().addAll(temas);
        /* valor predeterminado de los ChoiceBox */
        dimTableroCB.setValue(dimsTablero[0]);
        temaCB.setValue(temas[0]);
    }
}
