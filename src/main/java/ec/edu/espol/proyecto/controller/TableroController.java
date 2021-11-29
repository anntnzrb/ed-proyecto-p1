package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.MainApp;
import ec.edu.espol.proyecto.juego.Jugador;
import ec.edu.espol.proyecto.juego.Tablero;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.scene.control.Label;

public class TableroController implements Initializable {

    private final String[] inserciones = {"fila", "columna"};
    private       int      comodines   = 2;
    private       Tablero  tbl;

    @FXML
    private GridPane tableroGP;

    @FXML
    private ChoiceBox<String> choiceBoxFilaCol;

    @FXML
    private ChoiceBox desplazarChoiceBox;

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
    private Label LabelPuntaje;
    
    @FXML
    private Button btnVidas;
    
    @FXML
    private Label LabelVidas;
    
    @FXML
    private Label LabelNombre;

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

    private void armarTablero() {
        final List<CircularDoublyLinkedList<Button>> listCLL = tbl.getTabla();
        for (int i = 0; i < listCLL.size(); i++) {
            for (int j = 0; j < listCLL.get(i).size(); j++) {
                tableroGP.add(listCLL.get(i).get(j), i, j);
            }
        }
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
            if (choiceBoxFilaCol.getValue().equals("fila")) {
                tbl.addFila();
            } else if (choiceBoxFilaCol.getValue().equals("columna")) {
                tbl.addColumna();
            }

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
            if (choiceBoxFilaCol.getValue().equals("fila")) {
                tbl.removeFila();
            } else if (choiceBoxFilaCol.getValue().equals("columna")) {
                tbl.removeColumna();
            }

            // actualizar tablero
            System.out.println(tbl.getTabla().size());
        } else {
            System.out.println("El jugador no tiene mas comodines");
        }

        --comodines;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        tbl = new Tablero("animales" + ".txt", 11);
        armarTablero();
        
        Jugador jugador= new Jugador("Carlos",0);
        LabelNombre.setText("Hola " + jugador.getNickname());
        
        LabelPuntaje.setText(String.valueOf(jugador.getPuntaje()));
        
        LabelVidas.setText(String.valueOf(jugador.getVidas()));

        choiceBoxFilaCol.getItems().addAll(inserciones);
        choiceBoxFilaCol.setValue(inserciones[0]);

        /* llenar CB con las filas del tablero */
        IntStream.range(0, tbl.getTabla().size())
                 .forEach(desplazarChoiceBox.getItems()::add);
        desplazarChoiceBox.setValue(0);
    }
}