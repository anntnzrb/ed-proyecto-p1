package ec.edu.espol.proyecto.juego;

final public class Jugador {
    private final String nickname;

    public Jugador(final String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
