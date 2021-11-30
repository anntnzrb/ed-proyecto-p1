package ec.edu.espol.proyecto.utils;

import ec.edu.espol.proyecto.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Enum Util.
 * Provee métodos (estáticos exclusivamente) para el funcionamiento del proyecto.
 */
public enum Util {
    ;

    public static Scene getNewScene(final String fxml) throws IOException {
        return new Scene(loadFXML(fxml));
    }

    /**
     * Busca y carga el archivo FXML.
     *
     * @param fxml archivo de tipo FXML de la escena
     * @return nodo de tipo Parent
     * @throws IOException arroja error si no se encuentra el archivo FXML
     */
    public static Parent loadFXML(final String fxml) throws IOException {
        return new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"))
                .load();
    }
}
