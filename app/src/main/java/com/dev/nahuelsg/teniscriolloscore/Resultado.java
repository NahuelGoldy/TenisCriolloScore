package com.dev.nahuelsg.teniscriolloscore;

/**
 * Created by Nahuel SG on 10/02/2017.
 */
public class Resultado{
    private String ganadorYPerdedor;
    private String score;
    private String fecha;

    public Resultado(String jugadores, String marcador, String date){
        this.setGanadorYPerdedor(jugadores);
        this.setScore(marcador);
        this.setFecha(date);
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
}
