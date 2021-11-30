package ec.edu.espol.proyecto.juego;

/**
 * @author Personal
 */
public class Jugador {
    private String nickname;

    public Jugador(final String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
