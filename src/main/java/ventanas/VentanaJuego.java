package ventanas;

//import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import java.util.ArrayList;

import ec.edu.espol.ed.proyectop1.juego.Tablero;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ec.edu.espol.ed.proyectop1.juego.Jugador;


public class VentanaJuego extends Application{
    private final SplitPane root;
   // private final CircularDoublyLinkedList<Tablero> TablaJuego;
    private final Jugador jugador1;
    private final String     tipoJuego;
    private final Tablero    tableroActual;
    private final BorderPane rootJuego;
    private boolean terminado;
    //private Cuadro casilla;
    //private final TextField turno = new TextField();
    private final Button btnRegresar;
    private final Button btnGuardar;
    //private final Button btnPista;
    
    public VentanaJuego(Jugador jugador1,String tipoJuego){
        root = new SplitPane();
        // FIXME
        tableroActual = new Tablero("test.txt", 8);
        //TablaJuego = new CircularDoublyLinkedList<Tablero>(tableroActual);
        this.jugador1 = jugador1;
        this.tipoJuego = tipoJuego;
        rootJuego = new BorderPane();
        terminado = false;
        btnRegresar = new Button("Regresar");
        btnGuardar = new Button("Guardar Partida");
        //btnPista = new Button();     
        empezarJuego();
        System.out.println("\n\n");   
    }
    

    private void setTop(){
        ImageView logo = new ImageView(getClass().getResource("logo.jpg").toExternalForm());
        logo.setFitWidth(450);
        logo.setFitHeight(100);
        HBox titulo = new HBox(10);
        titulo.setAlignment(Pos.CENTER);
        titulo.setId("topRoot");
        titulo.getChildren().add(logo);
        rootJuego.setTop(titulo);
        HBox.setHgrow(rootJuego, Priority.ALWAYS);
        rootJuego.getTop().setId("topRoot");
    }
    
    private void setCenter(){
        ArrayList<Integer> p = new ArrayList<>();
        VBox vbCentro = new VBox();
        vbCentro.setPadding(new Insets(20));
        vbCentro.setAlignment(Pos.CENTER);
        VBox.setVgrow(rootJuego, Priority.ALWAYS);
        for (int f = 0; f < tableroActual.getFil(); f++) {
            HBox fila = new HBox();
            fila.setAlignment(Pos.CENTER);
            HBox.setHgrow(vbCentro, Priority.ALWAYS);
            for (int c = 0; c < tableroActual.getCol(); c++) {
                StackPane stkCasilla = new StackPane();
                ImageView caja = new ImageView(getClass().getResource("cuadrado.png").toExternalForm());
                caja.setFitWidth(140);
                caja.setFitHeight(140);
                stkCasilla.getChildren().add(new Label(f+","+c));
                String[] valores = ((Label)stkCasilla.getChildren().get(0)).getText().split(",");
                int i = Integer.parseInt(valores[0]);
                int j = Integer.parseInt(valores[1]);
               
                stkCasilla.getChildren().add(caja);
                fila.getChildren().add(stkCasilla);
            }
            vbCentro.getChildren().add(fila);
        }
        
        vbCentro.setPadding(new Insets(30));
        this.rootJuego.setCenter(vbCentro);
    }
    
    private void actualizarTablero(){
        //this.tableroActual.getTabla()[casilla.getX()][casilla.getY()].setData(casilla.getData());
        //this.tableroActual.mostrarTablero();
    }
   
    private void setLeft(){
        
        VBox vbLeft = new VBox(10);
        VBox vbLeftRoot = new VBox(10);
        vbLeftRoot.setAlignment(Pos.TOP_CENTER);
        
        vbLeftRoot.setId("rooLeft");
        VBox.setVgrow(rootJuego, Priority.ALWAYS);        
        HBox hb = new HBox(5);
        hb.setPadding(new Insets(20));
       
        HBox.setHgrow(vbLeft, Priority.ALWAYS);        
        
        Label lblJugadores = new Label("JUGADOR:");
        lblJugadores.setId("subtitulos");
        vbLeftRoot.getChildren().addAll(lblJugadores,new Label(jugador1.getNickname()+" "));
        vbLeftRoot.getChildren().addAll(new Label(""),new Label(""),new Label(""),new Label(""),new Label(""),new Label(""));
        vbLeftRoot.getChildren().addAll(this.btnGuardar,this.btnRegresar);        
        
        this.rootJuego.setLeft(vbLeftRoot);
    }
   

    private void empezarJuego(){    
    }
    
    private void hiloJugador(Jugador jugador){
        Thread tr = new Thread(()->{
            while(!this.terminado){
                System.out.print("");
                if(this.terminado){
                    Platform.runLater(()->{
                        Alert a = new Alert(AlertType.INFORMATION,"GANÓ");
                        a.setContentText(jugador.getNickname() + "HA GANADO");
                        a.show();
                        this.rootJuego.getCenter().setDisable(true);});
                }     
            }
        });
        tr.setDaemon(true);
        tr.start();
    }
    
     /*Pista
    private void rootBorder(){
        ImageView pista = new ImageView(getClass().getResource("/resources/imagenes/pista.png").toExternalForm());
        pista.setFitHeight(40);
        pista.setFitWidth(40);
        this.btnPista.setGraphic(pista);
        root.getItems().addAll(rootJuego);
        rootJuego.getStylesheets().add("/estilos/estilo.css");
        this.turno.setOpacity(1);
        this.turno.setAlignment(Pos.CENTER);
        setTop();
        setCenter();
        setLeft();
    }*/
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 800, 650);        
        scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

