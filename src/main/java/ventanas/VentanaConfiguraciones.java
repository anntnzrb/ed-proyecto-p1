/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import ec.edu.espol.ed.proyectop1.juego.Jugador;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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

    private Jugador jugador1;

    @FXML
    private final TextField txtJugador1;

    @FXML
    private final BorderPane root;

    private final Button btnJugar;
    private final Button btnRegresar;

    public VentanaConfiguraciones() {
        root = new BorderPane();
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

    private void borderBottom() {
        HBox hbBotones = new HBox(20);
        btnJugar.setId("botonesConfig");
        btnRegresar.setId("botonesConfig");
        hbBotones.setAlignment(Pos.CENTER);
        hbBotones.getChildren().addAll(this.btnRegresar, this.btnJugar);
        hbBotones.setPadding(new Insets(20));
        this.root.setBottom(hbBotones);
    }

    private void borderTop() {
        HBox hbLogo = new HBox(5);
        ImageView logo = new ImageView(getClass().getResource("/resources/imagenes/logo.jpg").toExternalForm());
        logo.setFitHeight(80);
        logo.setFitWidth(480);
        hbLogo.getChildren().add(logo);
        root.setTop(hbLogo);
    }

    private void borderCenter() {
        HBox hbox1 = new HBox(10);
        Label lbl = new Label("Nombre del jugador: ");
        lbl.setId("labelConfig");
        hbox1.getChildren().addAll(lbl, this.txtJugador1);
        txtJugador1.setOnKeyPressed(e -> this.btnJugar.setDisable(true));
        
        HBox hbox2 = new HBox(10);
        ObservableList<String> dimTablero = FXCollections.observableArrayList();
        dimTablero.addAll("6x6", "7x7", "8x8", "9x9", "10x10");
        ComboBox<String> combDimTablero = new ComboBox<>(dimTablero);
        
        HBox hbox3 = new HBox(10);
        ObservableList<String> tema = FXCollections.observableArrayList();
        tema.addAll("animales","deportes", "frutas", "paises");
        ComboBox<String> combTema = new ComboBox<>(tema);
        root.getChildren().addAll(hbox1,lbl,hbox2, combDimTablero,hbox3, combTema);

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
