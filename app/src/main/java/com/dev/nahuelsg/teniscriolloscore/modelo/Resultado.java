package com.dev.nahuelsg.teniscriolloscore.modelo;

/**
 * Created by Nahuel SG on 10/02/2017.
 */
public class Resultado{
    private String ganadorYPerdedor;
    private String score;
    private String fecha;
    private String tiempoDeJuego;

    public Resultado(String jugadores, String marcador, String date, String tiempoDeJuego){
        this.setGanadorYPerdedor(jugadores);
        this.setScore(marcador);
        this.setFecha(date);
        this.setTiempoDeJuego(tiempoDeJuego);
    }

    public String getGanadorYPerdedor() {
        return ganadorYPerdedor;
    }

    public void setGanadorYPerdedor(String ganadorYPerdedor) {
        this.ganadorYPerdedor = ganadorYPerdedor;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTiempoDeJuego() {
        return tiempoDeJuego;
    }

    public void setTiempoDeJuego(String tiempoDeJuego) {
        this.tiempoDeJuego = tiempoDeJuego;
    }
}
