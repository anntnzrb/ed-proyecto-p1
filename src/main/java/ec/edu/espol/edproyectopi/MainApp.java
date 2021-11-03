package ec.edu.espol.edproyectopi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static ec.edu.espol.edproyectopi.Config.SCENE_HEIGHT;
import static ec.edu.espol.edproyectopi.Config.SCENE_WIDTH;

public class MainApp extends Application {
    public static void main(final String[] argv) {
        launch(argv);
    }

    @Override
    public final void start(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), SCENE_WIDTH, SCENE_HEIGHT);

        stage.setResizable(false); // no resize

        stage.setTitle("Sopa de Letras -- ED Proyecto P1 2021");
        stage.getIcons()
             .add(new Image(Objects.requireNonNull(MainApp
                     .class
                     .getResourceAsStream("icon.png"))));
        stage.setScene(scene);
        stage.show();
    }
}