package ec.edu.espol.proyecto;

import ec.edu.espol.proyecto.utils.Util;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    private static Scene scene;

    public static void main(final String... argv) {
        launch(argv);
    }

    @Override
    public final void init() {
        System.out.println("Inicializando aplicación...");
    }

    @Override
    public final void stop() {
        System.out.println("Cerrando aplicación...");
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        scene = new Scene(Util.loadFXML("main"));

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