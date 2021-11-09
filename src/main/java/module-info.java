module ec.edu.espol.edproyectopi {
    requires javafx.controls;
    requires javafx.fxml;


    opens ec.edu.espol.edproyectopi to javafx.fxml;
    exports ec.edu.espol.edproyectopi;
    exports ec.edu.espol.edproyectopi.controller;
    opens ec.edu.espol.edproyectopi.controller to javafx.fxml;
    exports ec.edu.espol.edproyectopi.juego;
    opens ec.edu.espol.edproyectopi.juego to javafx.fxml;
}