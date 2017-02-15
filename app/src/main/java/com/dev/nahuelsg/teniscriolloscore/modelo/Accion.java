package com.dev.nahuelsg.teniscriolloscore.modelo;

/**
 * Created by Nahuel SG on 13/02/2017.
 */

public class Accion {
    private String accion;
    private int saque;
    private int jugador;

    public Accion (String acc, int saq, int jug){
        accion = acc;
        saque = saq;
        jugador = jug;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getSaque() {
        return saque;
    }

    public void setSaque(int saque) {
        this.saque = saque;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }
}
