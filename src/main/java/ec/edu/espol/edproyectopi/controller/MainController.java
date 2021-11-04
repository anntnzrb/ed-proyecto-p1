package ec.edu.espol.edproyectopi.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainController {
    @FXML
    private void onSalirBtnClick() {
        Platform.exit();
    }
}