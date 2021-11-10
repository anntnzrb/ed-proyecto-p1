package ec.edu.espol.ed.proyectop1;

import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        MainApp.setRoot("primary");
    }
}