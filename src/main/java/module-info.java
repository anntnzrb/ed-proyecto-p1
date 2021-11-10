module ec.edu.espol.ed.proyectop1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.ed.proyectop1 to javafx.fxml;
    exports ec.edu.espol.ed.proyectop1;
}
