package ec.edu.espol.proyecto.utils;

import ec.edu.espol.proyecto.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

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

    public static FXMLLoader getFXMLLoader(final String fxml) {
        return new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
    }

    /**
     * Busca y carga el archivo FXML.
     *
     * @param fxml archivo de tipo FXML de la escena
     * @return nodo de tipo Parent
     * @throws IOException arroja error si no se encuentra el archivo FXML
     */
    public static Parent loadFXML(final String fxml) throws IOException {
        return getFXMLLoader(fxml).load();
    }

    /* *************************************************************************
     * JFX
     * *********************************************************************** */

    /**
     * Método para mostrar información por consola.
     *
     * @param msg mensaje a mostrar en pantalla
     */
    public static void log(final String msg) {
        System.out.println(msg);
    }

    /**
     * Método que muestra una alerta de error.
     *
     * @param msg   mensaje a mostrar
     * @param isLog @{@code true} si se desea mostrar en consola
     */
    public static void err(final String msg, final boolean isLog, final boolean isExit) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();

        if (isLog) {
            log(msg);
        }

        if (isExit) {
            Platform.exit();
        }
    }

    /**
     * Wrapper de {@link #err(String, boolean, boolean)}
     */
    public static void err(final String msg, final boolean isLog) {
        err(msg, isLog, false);
    }

    /**
     * Método que muestra una alerta de información.
     *
     * @param msg   mensaje a mostrar
     * @param isLog @{@code true} si se desea mostrar en consola
     */
    public static void alert(final String msg, final boolean isLog) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
        if (isLog) {
            log(msg);
        }
    }
}
