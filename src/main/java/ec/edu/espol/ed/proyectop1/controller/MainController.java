package ec.edu.espol.ed.proyectop1.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable{
    /**
     * arreglo con las posibles dimensiones del tablero
     */
   
    private final String[] dimsTablero = {
            "6x6", "7x7", "8x8", "9x9", "10x10"
    };

    @FXML
    private ChoiceBox<String> dimTableroChoiceBox;

    @FXML
    private Button btnJugar;

    @FXML
    private void onSalirBtnClick() {
        Platform.exit();
    }

    @FXML
    private void onJugarBtnClick() {
        final int dim = getDimTablero();
        System.out.printf( "El jugador ha elegido un tablero de dimensiones: %dx%d\n", dim, dim);
    }

    /**
     * Retorna la dimeensión del tablero, en realidad solo es el primer
     * número ya que el tablero siempre es cuadrado.
     *
     * @return la dimensión del tablero
     */
    public int getDimTablero() {
        return Integer.parseInt(dimTableroChoiceBox.getValue() .split("x")[0]);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        /* agregar las dimensiones al ChoiceBox */
        dimTableroChoiceBox.getItems().addAll(dimsTablero);
        /* valor predeterminado del ChoiceBox */
        dimTableroChoiceBox.setValue(dimsTablero[0]);
    } 
      
}
