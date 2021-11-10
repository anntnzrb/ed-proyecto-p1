package ec.edu.espol.ed.proyectop1.controller;

import ec.edu.espol.ed.proyectop1.MainApp;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {

    @FXML
    private void switchToSecondary() throws IOException {
        MainApp.setRoot("secondary");
    }
}
