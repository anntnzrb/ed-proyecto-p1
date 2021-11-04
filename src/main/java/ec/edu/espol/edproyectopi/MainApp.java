package ec.edu.espol.edproyectopi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {
    public static void main(final String[] argv) {
        launch(argv);
    }

    @Override
    public void init() {
        System.out.println("Inicializando aplicación...");
    }

    @Override
    public void stop() {
        System.out.println("Cerrando aplicación...");
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        /* FXML */
        final FXMLLoader fxmlLoader =
                new FXMLLoader(MainApp.class.getResource("Main.fxml"));

        /* ********************************************************************
         * Stage
         * ***************************************************************** */
        primaryStage.setResizable(false); // no resize
        primaryStage.setTitle("Sopa de Letras -- ED Proyecto P1 2021 (G11)");
        primaryStage.getIcons()
                    .add(new Image(Objects.requireNonNull(MainApp
                            .class
                            .getResourceAsStream("icon.png"))));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }
}