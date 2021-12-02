package ec.edu.espol.proyecto.controller;

import ec.edu.espol.proyecto.juego.Jugador;
import ec.edu.espol.proyecto.juego.Letra;
import ec.edu.espol.proyecto.juego.Tablero;
import ec.edu.espol.proyecto.tda.ArrayList;
import ec.edu.espol.proyecto.tda.CircularDoublyLinkedList;
import ec.edu.espol.proyecto.tda.List;
import ec.edu.espol.proyecto.utils.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

final public class TableroController {
    private final String[] inserciones = {"fila", "columna"};

    /* juego (sopa de letras) */
    private Tablero                                    tbl;
    private ArrayList<CircularDoublyLinkedList<Letra>> tabla;
    private Jugador                                    jugador;
    private int                                        comodines;
    private int                                        dimensiones;
    private int                                        numErrores;
    private StringBuilder                              strBld;
    private Deque<Letra>                               listaLetrasSeleccionadas;

    /* JFX */
    private Stage              stage;
    @FXML
    private GridPane           tableroGP;
    @FXML
    private ChoiceBox<String>  insercionesCB;
    @FXML
    private ChoiceBox<Integer> desplazarCB;
    @FXML
    private ListView<String>   listView;
    @FXML
    private Label              lblPuntajeJug;
    @FXML
    private Label              lblVidasJug;
    @FXML
    private Label              lblNombreJug;
    @FXML
    private Label              lblTema;
    @FXML
    private Label              lblPresionar;
    @FXML
    private Button             btnIniciar;
    @FXML
    private Button             btnInsertar;
    @FXML
    private Button             btnEliminar;
    @FXML
    private Button             btnDespIzq;
    @FXML
    private Button             btnDespDer;
    @FXML
    private Button             btnVerificar;
    @FXML
    private Button             btnAyuda;

    /* constructor */
    public TableroController() {}

    public static void ayudaUsuario(final Button btn) {
        btn.setOnAction(ev -> {
            final String mensaje =
                    "1.Para iniciar el juego debes dar click en la opción Play.\n"
                    + "2.Una vez seleccionado Play se habilitara el juego "
                    + "y tendras a tu disposicion la sopa de letras en donde deberas encontrar las palabras mostradas en la lista de la parte inferior.\n"
                    + "3.Ahora deberas dar click en cada uno de los botones que conforman la palabra y una vez hecho esto procedemos a presionar el boton con un visto el cual verificara la palabra seleccionada, si acertaste se te iran sumando puntos acorde a la longitud de la palabra formada"
                    + ", de igual forma si te equivocas al seleccionar una palabra se te restaran puntos acorde a la longitud de tu palabra errada teniendo en cuenta que tienes un maximo de 3 vidas para continuar con la partida"
                    + ".\n"
                    + "4. Si deseas agregar mas filas y columnas pudes hacerlo con un maximo de 2 oportunidades con el fin de encontrar mas palabras."
                    + "\n"
                    + "5. Ahora bien, si deseas desplazar las letras de izquierda a derecha o viceversa para encontrar una palabra lo puedes hacer con los botones desplazar izquierda"
                    + "y derecha sin limite de oportunidades a fin de poner a prueba tu creatividad de encontrar y armar palabras.\n"
                    + "6. Si te aburriste y deseas cambiar de tema de juego puedes hacerlo en cualquier momento, tan solo necesitas dar click en regresar y volveras a iniciar nuevamente el juego.\n "
                    + "!Que esperas empecemos el Juego!";
            final Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
            dialogo.setTitle("Instrucciones");
            dialogo.setHeaderText("Instrucciones");
            dialogo.setContentText(mensaje);
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        });
    }

    /* getters & setters */
    public void setNombre(final String nombre) {
        lblNombreJug.setText(nombre);
    }

    public void setDimension(final int dim) {
        dimensiones = dim;
    }

    public void setTema(final String tema) {
        lblTema.setText(tema);
    }

    public String getPalabraVerif() {
        return strBld.toString();
    }

    public void limpiarStrBld() {
        strBld.setLength(0);
    }

    /**
     * Método encargado de establecer ciertos valores prederminados.
     */
    private void setUp() {
        /* menu inserciones */
        insercionesCB.getItems().addAll(inserciones);
        insercionesCB.setValue(inserciones[0]);

        listView.setMaxSize(200, 160);
        for (int i = 0, palValSize = tbl.getListPalabrasValidas().size();
             i < palValSize;
             ++i) {
            listView.getItems().add(tbl.getListPalabrasValidas().get(i));
        }

        actualizarItems();
    }

    /**
     * Verificar si ha ganado el juego.
     *
     * @return @{@code true} si se ha ganado el juego
     */
    private boolean checkJuego() {
        return tbl.getListPalabrasEncontradas().size()
               == Tablero.MAX_PALS_JUEGO;
    }

    /**
     * Arma el tablero a partir de parámetros específicos.
     */
    private void crearTablero() {
        tbl = new Tablero(lblTema.getText() + ".txt", dimensiones);
        tabla = tbl.getTabla();
        armarTablero();

        strBld = new StringBuilder(dimensiones * 2);

        /* debug */
        tbl.mostrarTablero();
    }

    /**
     * Método encargado de crear & actualizar el {@link Tablero}
     */
    private void armarTablero() {
        tableroGP.getChildren().clear();
        /* recorrer el tablero y crear un panel que contenga la letra */
        for (int i = 0; i < tabla.size(); i++) {
            for (int j = 0; j < tabla.get(0).size(); j++) {
                final Letra letra = tabla.get(i).get(j);
                final Text txtLetra = new Text(Character.toString(letra.getContenido()));
                txtLetra.setFont(new Font(20));
                final StackPane stackPane = new StackPane(txtLetra);
                stackPane.setAlignment(Pos.CENTER);
                if (letra.isMarcado()) {
                    stackPane.setBackground(new Background(new BackgroundFill(
                            Color.BLACK,
                            new CornerRadii(0),
                            new Insets(0))));
                }

                stackPane.setOnMouseClicked(ev -> marcarLetra(letra));

                /* agregar letras al GridPane */
                GridPane.setMargin(stackPane, new Insets(0, 4, 0, 4));
                tableroGP.add(stackPane, j, i);
            }
        }
    }

    /**
     * Marca una letra del {@link Tablero}.
     *
     * @param letra letra a marcar
     */
    public void marcarLetra(final Letra letra) {
        if (numErrores == 3) {
            return;
        }

        listaLetrasSeleccionadas.push(letra);
        System.out.println(listaLetrasSeleccionadas);

        if (checkMov(letra, listaLetrasSeleccionadas.pop())) {
            letra.setMarcado(true);
            armarTablero();

            strBld.append(letra.getContenido());

            /* debug */
            System.out.println(strBld);
        }
    }

    public boolean checkMov(final Letra letra1, final Letra letra2) {
        final int x1 = letra1.getFil();
        final int x2 = letra2.getFil();
        final int y1 = letra1.getCol();
        final int y2 = letra2.getCol();

        System.out.printf("Letra 1: (%d, %d)\n", x1, y1);
        System.out.printf("Letra 2: (%d, %d)\n", x2, y2);

        return (x1 - 1) == x2
               || (x1 + 1) == x2
               || (y1 - 1) == y2
               || (y1 + 1) == y2;
    }

    /**
     * Método encargado de crear el {@link Jugador} del {@link Tablero}.
     */
    private void crearJugador() {
        jugador = new Jugador(lblNombreJug.getText());
        lblNombreJug.setText(String.format("Hola, '%s'",
                                           jugador.getNickname()));
        lblPuntajeJug.setText(String.valueOf(tbl.getPuntaje()));
        lblVidasJug.setText(String.valueOf(numErrores));
        lblTema.setText(String.format("Temática: '%s'", lblTema.getText()));
    }

    /**
     * Método que activa/desactiva ciertos controles del programa.
     */
    private void toggleControles() {
        /* habilitar controles */
        btnInsertar.setDisable(false);
        btnEliminar.setDisable(false);
        btnDespIzq.setDisable(false);
        btnDespDer.setDisable(false);
        btnVerificar.setDisable(false);
        insercionesCB.setDisable(false);
        desplazarCB.setDisable(false);
    }

    /**
     * Actualiza items/controles del programa posterior a una operación.
     */
    private void actualizarItems() {
        /* llenar CB con las filas del tablero */
        desplazarCB.getItems().clear();
        IntStream.range(0, tbl.getTabla().size())
                 .forEach(desplazarCB.getItems()::add);
        desplazarCB.setValue(0);
    }

    /**
     * Actualiza los valores de los elementos gráficos.
     *
     * @param estado @{true} si se actualiza cuando el jugador ha ganado
     */
    private void actualizarJuego(final boolean estado) {
        if (!estado) {
            lblVidasJug.setText(String.valueOf(++numErrores));
        }

        lblPuntajeJug.setText(String.valueOf(tbl.getPuntaje()));
    }

    @FXML
    private void onIniciarBtnClick() {
        crearTablero();
        crearJugador();
        setUp();
        toggleControles();
        ayudaUsuario(btnAyuda);
        btnIniciar.setDisable(true);

    }

    @FXML
    private void onRegresarBtnClick(final ActionEvent ae) throws IOException {
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(Util.getNewScene("main"));
        stage.show();
    }

    @FXML
    private void onBtnDespIzqClick() {
        tbl.desplazar(desplazarCB.getValue(), 'i');
        System.out.println("Ha desplazado las filas hacia la izquierda.");
        tbl.mostrarTablero();

        armarTablero();
    }

    @FXML
    private void onBtnDespDerClick() {
        tbl.desplazar(desplazarCB.getValue(), 'd');
        System.out.println("Ha desplazado las filas hacia la derecha.");
        tbl.mostrarTablero();

        armarTablero();
    }

    @FXML
    private void onBtnInsertarClick() {
        if (comodines > 0) {
            if (insercionesCB.getValue().equals("fila")) {
                tbl.addFila();
                Util.log("El jugador ha insertado una fila.");
            } else if (insercionesCB.getValue().equals("columna")) {
                tbl.addColumna();
                Util.log("El jugador ha insertado una columna.");
            }

            // actualizar tablero
            armarTablero();
            actualizarItems();
            tbl.mostrarTablero();
        }

        --comodines;

        if (comodines == 0) {
            btnInsertar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    @FXML
    private void onBtnEliminarClick() {
        if (comodines > 0) {
            if (insercionesCB.getValue().equals("fila")) {
                tbl.removeFila();
                Util.log("El jugador ha eliminado una fila.");
            } else if (insercionesCB.getValue().equals("columna")) {
                tbl.removeColumna();
                Util.log("El jugador ha eliminado una columna.");
            }

            // actualizar tablero
            armarTablero();
            actualizarItems();
            tbl.mostrarTablero();
        }

        --comodines;

        if (comodines == 0) {
            btnInsertar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    @FXML
    private void onBtnVerificarClick(final ActionEvent ae) throws IOException {
        if (numErrores < 3) {
            final List<String> listPalabras = tbl.getListPalabrasValidas();
            final String palabra = getPalabraVerif();

            if (listPalabras.contains(palabra)) {
                Util.alert("Usted ha encontrado la palabra: " + palabra,
                           true);
                tbl.setPuntaje(tbl.getPuntaje() + palabra.length());

                /* agregar a lista de palabras encontradas */
                tbl.getListPalabrasEncontradas().addLast(palabra);

                /* actualizar */
                actualizarJuego(true);

                /* debug */
                System.out.printf("Total palabras marcadas: %s\n",
                                  tbl.getListPalabrasEncontradas());
            } else {
                Util.err(String.format("La palabra '%s' no es válida", palabra),
                         true);
                tbl.setPuntaje(tbl.getPuntaje() - palabra.length());

                /* actualizar */
                actualizarJuego(false);
            }

            /* siempre se limpiará posterior a verificar */
            limpiarStrBld();
            listaLetrasSeleccionadas.clear();

        } else {
            Util.err("Juego terminado, ya no tiene mas intentos", true);
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            stage.setScene(Util.getNewScene("main"));
            stage.show();
        }

        /* verificar si se ha ganado el juego */
        if (checkJuego()) {
            Util.alert("Has ganado el juego, encontraste todas las palabras",
                       true);
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            stage.setScene(Util.getNewScene("main"));
            stage.show();
        }
    }

    @FXML
    public void initialize() {
        /* inicialmente el jugador tiene 2 comodines y 0 errores */
        numErrores = 0;
        comodines = 2;
        listaLetrasSeleccionadas = new ArrayDeque<>();
    }
}
