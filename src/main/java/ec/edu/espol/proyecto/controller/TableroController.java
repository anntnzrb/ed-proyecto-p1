package ec.edu.espol.proyecto.controller;

import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class TableroController implements Initializable {
    @FXML
    private GridPane tableroGP;    
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnDespDer;

    @FXML
    private Button btnDespIzq;

    @FXML
    private Button btnPuntaje;

    @FXML
    private Button btnVidas;
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
