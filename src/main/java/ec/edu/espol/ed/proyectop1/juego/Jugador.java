/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ed.proyectop1.juego;

/**
 *
 * @author Personal
 */
public class Jugador {   
    private String nickname;
    private int puntaje;

    public Jugador(String nickname, int puntaje) {
        this.nickname = nickname;
        this.puntaje = puntaje;
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
