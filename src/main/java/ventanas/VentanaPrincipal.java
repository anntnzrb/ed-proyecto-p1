package ventanas;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal extends Application {

    private BorderPane root;
    
    @FXML
    private final Button btnIniciar;

    public VentanaPrincipal() {
        root = new BorderPane();
        btnIniciar = new Button("Iniciar Juego");
        organizarRoot();
    }

    private void organizarRoot() {
        setTop();
        setCenter();
        configBotones();
    }

    private void setTop() {
        HBox hbLogo = new HBox(5);
        ImageView logo = new ImageView(getClass().getResource("logo.jpg").toExternalForm());
        logo.setFitWidth(450);
        logo.setFitHeight(100);
        hbLogo.getChildren().add(logo);
        root.setTop(hbLogo);
    }

    private void setCenter() {
        VBox vbCentro = new VBox(10);
        btnIniciar.setId("botonesPrincipal");
        vbCentro.getChildren().addAll(btnIniciar);
        vbCentro.setAlignment(Pos.CENTER);                
        this.root.setCenter(vbCentro);

    }

    private void borderBottom() {
        HBox hbBotones = new HBox(20);
        btnIniciar.setId("botonesConfig");
        hbBotones.setAlignment(Pos.CENTER);
        hbBotones.getChildren().addAll(this.btnIniciar);
        hbBotones.setPadding(new Insets(20));
        this.root.setBottom(hbBotones);
    }

    public BorderPane getRoot() {
        return root;
    }

    public void configBotones(){
        this.btnIniciar.setOnAction(e->{
            VentanaConfiguraciones vc = new VentanaConfiguraciones();
            vc.start(new Stage());
            cerrarVentana();
        });
    }
    
    public void cerrarVentana() {
        Stage myStage = (Stage) this.btnIniciar.getScene().getWindow();
        myStage.close();
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 450, 300);
        scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
