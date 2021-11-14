
package ventanas;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class VentanaPrincipal extends Application{
    private BorderPane root;
    private final Button btnIniciar;
   

    public VentanaPrincipal() {
        root = new BorderPane();
        btnIniciar = new Button("Iniciar Juego");
        organizarRoot();
    }

    private void organizarRoot(){
        setTop();
        setCenter();
        configBotones();
    }
    
    private void setTop(){
        HBox hbLogo = new HBox(5);
        //int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        //int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        ImageView logo = new ImageView(getClass().getResource("/resources/imagenes/logo.jpg").toExternalForm());
        logo.setFitWidth(450);
        logo.setFitHeight(100);
        hbLogo.getChildren().add(logo);
        root.setTop(hbLogo);
        //HBox.setHgrow(logo, Priority.ALWAYS);
    }
    
    private void setCenter(){
        VBox vbCentro = new VBox(10);
        btnIniciar.setId("botonesPrincipal");     
        vbCentro.getChildren().addAll(btnIniciar);
        vbCentro.setAlignment(Pos.CENTER);                
        this.root.setCenter(vbCentro);
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
    public void cerrarVentana(){
        Stage myStage = (Stage) this.btnIniciar.getScene().getWindow();
        myStage.close();
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    @Override
    public void start(Stage primaryStage)  {
        Scene scene = new Scene(root, 450, 300);        
        scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}