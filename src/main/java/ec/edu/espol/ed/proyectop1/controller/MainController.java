package ec.edu.espol.ed.proyectop1.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainController {
    @FXML
    private void onSalirBtnClick() {
        Platform.exit();
    }
}