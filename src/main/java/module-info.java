module ec.edu.espol.edproyectopi {
    requires javafx.controls;
    requires javafx.fxml;


    opens ec.edu.espol.edproyectopi to javafx.fxml;
    exports ec.edu.espol.edproyectopi;
}