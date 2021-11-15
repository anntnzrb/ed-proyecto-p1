package ventanas;

import ec.edu.espol.ed.proyectop1.juego.Jugador;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal extends Application {

    private Jugador jugador1;
    private final String[] dimTablero = {"6x6", "7x7", "8x8", "9x9", "10x10"};
    
    private BorderPane root;
    
    @FXML
    private final TextField txtJugador1;
    
    @FXML
    private final Button btnIniciar;

    @FXML
    private ChoiceBox<String> dimTableroChoiceBox;
    
     @FXML
    private void onSalirBtnClick() {
        Platform.exit();
    }

     @FXML
    private void onJugarBtnClick() {
        final int dim = getDimTablero();
        System.out.printf( "El jugador ha elegido un tablero de dimensiones: %dx%d\n", dim, dim);
    }
    
        public int getDimTablero() {
        return Integer.parseInt(dimTableroChoiceBox.getValue() .split("x")[0]);
    }
        
    public VentanaPrincipal() {
        root = new BorderPane();
        btnIniciar = new Button("Iniciar Juego");
        txtJugador1 = new TextField();
        organizarRoot();
    }

    private void organizarRoot() {
        borderTop();
        borderCenter();
        borderBottom();
        btnIniciar.setDisable(true);
        root.getStylesheets().add("/estilos/estilo.css");
    }

    private void borderTop() {
        HBox hbLogo = new HBox(5);
        ImageView logo = new ImageView(getClass().getResource("logo.jpg").toExternalForm());
        logo.setFitWidth(450);
        logo.setFitHeight(1000);
        hbLogo.getChildren().add(logo);
        root.setTop(hbLogo);
    }

    private void borderCenter() {
        HBox hb1 = new HBox(10);
        Label lbl = new Label("Nombre del jugador: ");
        lbl.setId("labelConfig");
        hb1.getChildren().addAll(lbl, this.txtJugador1);
        txtJugador1.setOnKeyPressed(e -> this.btnIniciar.setDisable(true));

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

    /*public void configBotones(){
        this.btnIniciar.setOnAction(e->{
            VentanaConfiguraciones vc = new VentanaConfiguraciones();
            vc.start(new Stage());
            cerrarVentana();
        });
    }*/
    
    public void cerrarVentana() {
        Stage myStage = (Stage) this.btnIniciar.getScene().getWindow();
        myStage.close();
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 450, 1000);
        scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
