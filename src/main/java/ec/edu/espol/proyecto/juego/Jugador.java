package ec.edu.espol.proyecto.juego;

/**
 * @author Personal
 */
public class Jugador {
    private String nickname;
    private int    puntaje;
    private int    vidas;

    public Jugador(String nickname, int puntaje) {
        this.nickname = nickname;
        this.puntaje = puntaje;
        this.vidas = 3;
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

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }


}
