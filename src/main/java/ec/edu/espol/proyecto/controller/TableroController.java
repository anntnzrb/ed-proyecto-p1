package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.MainApp;
import ec.edu.espol.proyecto.controller.MainController;
import ec.edu.espol.proyecto.juego.Tablero;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.application.Platform;
import ec.edu.espol.proyecto.tda.List;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class TableroController implements Initializable {

    private final String[] insertarEn = {"fila", "columna"};
    
    @FXML
    private GridPane tableroGP;
    
    @FXML
    private ChoiceBox<String> choiceBoxFilaCol;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private Button btnEmpezar;
    
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
    
    @FXML
    private Button btnRegresar;
    
    @FXML
    private void onRegresarBtnClick() {
        
        try {
            MainApp.setRoot("main");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    @FXML
    private void onClicArmarTablero() {
        final Tablero tbl = new Tablero("animales" + ".txt", 11);
        
        final ec.edu.espol.proyecto.tda.List<CircularDoublyLinkedList<Button>> listCLL = tbl.getTabla();
        for (int i = 0; i < listCLL.size(); i++) {
            for (int j = 0; j < listCLL.get(i).size(); j++) {
                tableroGP.add(listCLL.get(i).get(j), i, j);
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxFilaCol.getItems().addAll(insertarEn);
        choiceBoxFilaCol.setValue(insertarEn[0]);
    }
}
