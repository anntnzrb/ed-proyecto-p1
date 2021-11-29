package ec.edu.espol.proyecto.juego;

/**
 * @author Personal
 */
public class Jugador {
    private String nickname;
    private int    puntaje;

    public Jugador(String nickname, int puntaje) {
        this.nickname = nickname;
        this.puntaje = puntaje;
    }

    public Jugador(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }


}
