package ec.edu.espol.ed.proyectop1;

import ec.edu.espol.ed.proyectop1.juego.Tablero2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;
import ventanas.VentanaPrincipal;

public class SopaLetras extends Application {
    private static Scene scene;

    public static void setRoot(final String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void main(final String... argv) {
        launch(argv);
    }

    @Override
    public final void init() {
        System.out.println("Inicializando aplicación...");
        Tablero2 tb = new Tablero2("animales.txt", 8);
        tb.mostrarTablero();
    }

    @Override
    public final void stop() {
        System.out.println("Cerrando aplicación...");
    }

    private static Parent loadFXML(String fxml) throws IOException {
        return new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"))
                .load();
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.start(primaryStage);
    }
}
