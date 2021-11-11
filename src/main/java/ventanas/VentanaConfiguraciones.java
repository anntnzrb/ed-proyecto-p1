/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import ec.edu.espol.ed.proyectop1.juego.Jugador;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

/**
 *
 * @author Personal
 */
public class VentanaConfiguraciones extends Application {

    private final BorderPane root;

    private final TextField txtJugador1;

    private final Button btnGuardar;
    private final Button btnJugar;
    private final Button btnRegresar;
    private String modoJuego;
    private char marca;
    private Jugador jugador1;

    public VentanaConfiguraciones() {
        root = new BorderPane();
        btnGuardar = new Button("Guardar");
        btnJugar = new Button("Jugar");
        btnRegresar = new Button("Regresar");
        txtJugador1 = new TextField();

        organizarRoot();
    }

    private void organizarRoot() {
       
        borderTop();
        borderCenter();
        borderBottom();
        btnJugar.setDisable(true);
        root.getStylesheets().add("/estilos/estilo.css");
    }
    
     private void borderBottom(){
        HBox hbBotones = new HBox(20);
        btnGuardar.setId("botonesConfig");
        btnJugar.setId("botonesConfig");
        btnRegresar.setId("botonesConfig");
        hbBotones.setAlignment(Pos.CENTER);
        hbBotones.getChildren().addAll(this.btnRegresar,this.btnGuardar,this.btnJugar);
        hbBotones.setPadding(new Insets(20));
        this.root.setBottom(hbBotones);
    }
     
      private void borderTop(){
        HBox hbLogo = new HBox(5);
        ImageView logo = new ImageView(getClass().getResource("/resources/imagenes/logo.jpg").toExternalForm());
        logo.setFitHeight(80);
        logo.setFitWidth(480);
        hbLogo.getChildren().add(logo);
        root.setTop(hbLogo);
    }
      
       private void borderCenter(){
        
    }
     
     
 @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 480, 500);        
        scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
