package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.MainApp;
import ec.edu.espol.proyecto.utils.Util;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class MainController {
    /**
     * arreglo con las posibles dimensiones del tablero
     */
    private final String[]          dimsTablero = {"6x6", "7x7", "8x8", "9x9", "10x10"};
    private final String[]          temas       =
            {"animales", "deportes", "frutas", "paises"};
    @FXML
    protected     ChoiceBox<String> temaCB;
    /* JFX */
    private       Stage             stage;
    @FXML
    private       ChoiceBox<String> dimTableroCB;
    @FXML
    private       GridPane          tableroGP;
    @FXML
    private       ChoiceBox<String> numFilaCB;
    @FXML
    private       Button            btnJugar;
    @FXML
    private       TextField            txtNombre;


    @FXML
    private void onJugarBtnClick(final ActionEvent ae) throws IOException {
        final int dim = getDimTablero();
        System.out.printf(
                "El jugador ha elegido un tablero de dimensiones: %dx%d\n",
                dim,
                dim);
        
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("second.fxml"));
        Parent parent = loader.load();
        
        TableroController tableroController = loader.getController();
        tableroController.setNombre(txtNombre.getText());
        tableroController.setDimension(getDimTablero());
        
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
    public int getDimTablero() {
        return Integer.parseInt(dimTableroCB.getValue().split("x")[0]);
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
