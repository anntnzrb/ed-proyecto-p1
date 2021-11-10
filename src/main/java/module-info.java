module ec.edu.espol.ed.proyectop1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.ed.proyectop1 to javafx.fxml;
    exports ec.edu.espol.ed.proyectop1;
    exports ec.edu.espol.ed.proyectop1.controller;
    opens ec.edu.espol.ed.proyectop1.controller to javafx.fxml;
}
