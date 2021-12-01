package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.MainApp;
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

public class MainController {
    /**
     * arreglo con las posibles dimensiones del tablero
     */
    private final String[]          dimsTablero = {"8x8", "9x9", "10x10", "11x11", "12x12", "13x13", "14x14"};
    private final String[]          temas       = {"animales", "deportes", "frutas", "paises"};
    @FXML
    protected     ChoiceBox<String> temaCB;
    /* JFX */
    private       Stage             stage;
    @FXML
    private       ChoiceBox<String> dimTableroCB;
    @FXML
    private       TextField         txtNombre;


    @FXML
    private void onJugarBtnClick(final ActionEvent ae) throws IOException {
        final int dim = getDimTablero();
        System.out.printf(
                "El jugador ha elegido un tablero de dimensiones: %dx%d\n",
                dim,
                dim);

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(
                "second.fxml"));
        Parent parent = loader.load();

        TableroController tableroController = loader.getController();
        tableroController.setNombre(txtNombre.getText());
        tableroController.setDimension(getDimTablero());
        tableroController.setTema(getTema());

        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void onSalirBtnClick() {
        Platform.exit();
    }

    /**
     * Retorna la dimeensión del tablero, en realidad solo es el primer número
     * ya que el tablero siempre es cuadrado.
     *
     * @return la dimensión del tablero
     */
    private int getDimTablero() {
        return Integer.parseInt(dimTableroCB.getValue().split("x")[0]);
    }

    private String getTema() {
        return temaCB.getValue();
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
