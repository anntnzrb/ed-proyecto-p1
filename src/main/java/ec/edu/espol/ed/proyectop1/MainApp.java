package ec.edu.espol.ed.proyectop1;

import ec.edu.espol.ed.proyectop1.juego.Tablero;
import ec.edu.espol.ed.proyectop1.tda.CircularDoublyLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    private static Scene scene;

    /**
     * Actualiza la escena, método empleado para cambiar escenas.
     *
     * @param fxml archivo de tipo FXML de la escena
     * @throws IOException arroja error si no se encuentra el archivo FXML
     */
    public static void setRoot(final String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Busca y carga el archivo FXML.
     *
     * @param fxml archivo de tipo FXML de la escena
     * @return nodo de tipo Parent
     * @throws IOException arroja error si no se encuentra el archivo FXML
     */
    private static Parent loadFXML(final String fxml) throws IOException {
        return new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"))
                .load();
    }

    public static void main(final String... argv) {
        launch(argv);
    }

    @Override
    public final void init() {
        System.out.println("Inicializando aplicación...");
        final Tablero tb = new Tablero("animales.txt", 4);
//        tb.addColumna();
        tb.mostrarTablero();
    }

    @Override
    public final void stop() {
        System.out.println("Cerrando aplicación...");
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("main"));
        /* ********************************************************************
         * Stage
         * ***************************************************************** */
        primaryStage.setResizable(false); // no resize
        primaryStage.setTitle("Sopa de Letras (G11)");
        primaryStage.getIcons()
                    .add(new Image(Objects.requireNonNull(
                            MainApp.class
                                    .getResourceAsStream("icon.png"))));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}