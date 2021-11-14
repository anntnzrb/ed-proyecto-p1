package ventanas;

//import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ec.edu.espol.ed.proyectop1.juego.Cuadro;
import ec.edu.espol.ed.proyectop1.juego.Jugador;
import ec.edu.espol.ed.proyectop1.juego.Tablero;


public class VentanaJuego extends Application{
    private final SplitPane root;
   // private final CircularDoublyLinkedList<Tablero> TablaJuego;
    private final Jugador jugador1;
    private final String tipoJuego;
    private final Tablero tableroActual;
    private final BorderPane rootJuego;
    private boolean terminado;
    private Cuadro casilla;
    private final TextField turno = new TextField();
    private final Button btnRegresar;
    private final Button btnGuardar;
    private final Button btnPista;
    
    public VentanaJuego(Jugador jugador1,String tipoJuego){
        root = new SplitPane();
        tableroActual = new Tablero();
        //TablaJuego = new CircularDoublyLinkedList<Tablero>(tableroActual);
        this.jugador1 = jugador1;
        this.tipoJuego = tipoJuego;
        rootJuego = new BorderPane();
        terminado = false;
        btnRegresar = new Button("Regresar");
        btnGuardar = new Button("Guardar Partida");
        btnPista = new Button();
        //rootBorder();
       
        System.out.println("\n\n");
       
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 800, 650);        
        scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

