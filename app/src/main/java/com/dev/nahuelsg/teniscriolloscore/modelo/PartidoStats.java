package com.dev.nahuelsg.teniscriolloscore.modelo;

/**
 * Created by Nahuel SG on 15/02/2017.
 */

public class PartidoStats {
    private String jugador1, jugador2;
    private String tiempoDeJuego;
    private String fecha;
    private int set1Jug1, set2Jug1, set3Jug1, set4Jug1, set5Jug1;
    private int set1Ju21, set2Jug2, set3Jug2, set4Jug2, set5Jug2;
    private int acesJug1, acesJug2;
    private int doblFaltasJug1, doblFaltasJug2;
    private int efectPrimeroJug1, efectPrimeroJug2; //entero que mide porcentaje
    private int ganadosPrimeroJug1, ganadosPrimeroJug2; //entero que mide porcentaje
    private int ganadosSegundoJug1, ganadosSegundoJug2; //entero que mide porcentaje
    private int ganadosDevolJug1, ganadosDevolJug2; //entero que mide porcentaje
    private int winnersDerechaJug1, winnersDerechaJug2;
    private int winnersRevesJug1, winnersRevesJug2;
    private int erroresDerechaJug1, erroresDerechaJug2;
    private int erroresRevesJug1, erroresRevesJug2;
    private int subidasJug1, subidasJug2;
    private int ganadosRedJug1, ganadosRedJug2;

    public PartidoStats(){}

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getTiempoDeJuego() {
        return tiempoDeJuego;
    }

    public void setTiempoDeJuego(String tiempoDeJuego) {
        this.tiempoDeJuego = tiempoDeJuego;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSet1Jug1() {
        return set1Jug1;
    }

    public void setSet1Jug1(int set1Jug1) {
        this.set1Jug1 = set1Jug1;
    }

    public int getSet2Jug1() {
        return set2Jug1;
    }

    public void setSet2Jug1(int set2Jug1) {
        this.set2Jug1 = set2Jug1;
    }

    public int getSet3Jug1() {
        return set3Jug1;
    }

    public void setSet3Jug1(int set3Jug1) {
        this.set3Jug1 = set3Jug1;
    }

    public int getSet4Jug1() {
        return set4Jug1;
    }

    public void setSet4Jug1(int set4Jug1) {
        this.set4Jug1 = set4Jug1;
    }

    public int getSet5Jug1() {
        return set5Jug1;
    }

    public void setSet5Jug1(int set5Jug1) {
        this.set5Jug1 = set5Jug1;
    }

    public int getSet1Ju21() {
        return set1Ju21;
    }

    public void setSet1Ju21(int set1Ju21) {
        this.set1Ju21 = set1Ju21;
    }

    public int getSet2Jug2() {
        return set2Jug2;
    }

    public void setSet2Jug2(int set2Jug2) {
        this.set2Jug2 = set2Jug2;
    }

    public int getSet3Jug2() {
        return set3Jug2;
    }

    public void setSet3Jug2(int set3Jug2) {
        this.set3Jug2 = set3Jug2;
    }

    public int getSet4Jug2() {
        return set4Jug2;
    }

    public void setSet4Jug2(int set4Jug2) {
        this.set4Jug2 = set4Jug2;
    }

    public int getSet5Jug2() {
        return set5Jug2;
    }

    public void setSet5Jug2(int set5Jug2) {
        this.set5Jug2 = set5Jug2;
    }

    public int getAcesJug1() {
        return acesJug1;
    }

    public void setAcesJug1(int acesJug1) {
        this.acesJug1 = acesJug1;
    }

    public int getAcesJug2() {
        return acesJug2;
    }

    public void setAcesJug2(int acesJug2) {
        this.acesJug2 = acesJug2;
    }

    public int getDoblFaltasJug1() {
        return doblFaltasJug1;
    }

    public void setDoblFaltasJug1(int doblFaltasJug1) {
        this.doblFaltasJug1 = doblFaltasJug1;
    }

    public int getDoblFaltasJug2() {
        return doblFaltasJug2;
    }

    public void setDoblFaltasJug2(int doblFaltasJug2) {
        this.doblFaltasJug2 = doblFaltasJug2;
    }

    public int getEfectPrimeroJug1() {
        return efectPrimeroJug1;
    }

    public void setEfectPrimeroJug1(int efectPrimeroJug1) {
        this.efectPrimeroJug1 = efectPrimeroJug1;
    }

    public int getEfectPrimeroJug2() {
        return efectPrimeroJug2;
    }

    public void setEfectPrimeroJug2(int efectPrimeroJug2) {
        this.efectPrimeroJug2 = efectPrimeroJug2;
    }

    public int getGanadosPrimeroJug1() {
        return ganadosPrimeroJug1;
    }

    public void setGanadosPrimeroJug1(int ganadosPrimeroJug1) {
        this.ganadosPrimeroJug1 = ganadosPrimeroJug1;
    }

    public int getGanadosPrimeroJug2() {
        return ganadosPrimeroJug2;
    }

    public void setGanadosPrimeroJug2(int ganadosPrimeroJug2) {
        this.ganadosPrimeroJug2 = ganadosPrimeroJug2;
    }

    public int getGanadosSegundoJug1() {
        return ganadosSegundoJug1;
    }

    public void setGanadosSegundoJug1(int ganadosSegundoJug1) {
        this.ganadosSegundoJug1 = ganadosSegundoJug1;
    }

    public int getGanadosSegundoJug2() {
        return ganadosSegundoJug2;
    }

    public void setGanadosSegundoJug2(int ganadosSegundoJug2) {
        this.ganadosSegundoJug2 = ganadosSegundoJug2;
    }

    public int getGanadosDevolJug1() {
        return ganadosDevolJug1;
    }

    public void setGanadosDevolJug1(int ganadosDevolJug1) {
        this.ganadosDevolJug1 = ganadosDevolJug1;
    }

    public int getGanadosDevolJug2() {
        return ganadosDevolJug2;
    }

    public void setGanadosDevolJug2(int ganadosDevolJug2) {
        this.ganadosDevolJug2 = ganadosDevolJug2;
    }

    public int getWinnersDerechaJug1() {
        return winnersDerechaJug1;
    }

    public void setWinnersDerechaJug1(int winnersDerechaJug1) {
        this.winnersDerechaJug1 = winnersDerechaJug1;
    }

    public int getWinnersDerechaJug2() {
        return winnersDerechaJug2;
    }

    public void setWinnersDerechaJug2(int winnersDerechaJug2) {
        this.winnersDerechaJug2 = winnersDerechaJug2;
    }

    public int getWinnersRevesJug1() {
        return winnersRevesJug1;
    }

    public void setWinnersRevesJug1(int winnersRevesJug1) {
        this.winnersRevesJug1 = winnersRevesJug1;
    }

    public int getWinnersRevesJug2() {
        return winnersRevesJug2;
    }

    public void setWinnersRevesJug2(int winnersRevesJug2) {
        this.winnersRevesJug2 = winnersRevesJug2;
    }

    public int getErroresDerechaJug1() {
        return erroresDerechaJug1;
    }

    public void setErroresDerechaJug1(int erroresDerechaJug1) {
        this.erroresDerechaJug1 = erroresDerechaJug1;
    }

    public int getErroresDerechaJug2() {
        return erroresDerechaJug2;
    }

    public void setErroresDerechaJug2(int erroresDerechaJug2) {
        this.erroresDerechaJug2 = erroresDerechaJug2;
    }

    public int getErroresRevesJug1() {
        return erroresRevesJug1;
    }

    public void setErroresRevesJug1(int erroresRevesJug1) {
        this.erroresRevesJug1 = erroresRevesJug1;
    }

    public int getErroresRevesJug2() {
        return erroresRevesJug2;
    }

    public void setErroresRevesJug2(int erroresRevesJug2) {
        this.erroresRevesJug2 = erroresRevesJug2;
    }

    public int getSubidasJug1() {
        return subidasJug1;
    }

    public void setSubidasJug1(int subidasJug1) {
        this.subidasJug1 = subidasJug1;
    }

    public int getSubidasJug2() {
        return subidasJug2;
    }

    public void setSubidasJug2(int subidasJug2) {
        this.subidasJug2 = subidasJug2;
    }

    public int getGanadosRedJug1() {
        return ganadosRedJug1;
    }

    public void setGanadosRedJug1(int ganadosRedJug1) {
        this.ganadosRedJug1 = ganadosRedJug1;
    }

    public int getGanadosRedJug2() {
        return ganadosRedJug2;
    }

    public void setGanadosRedJug2(int ganadosRedJug2) {
        this.ganadosRedJug2 = ganadosRedJug2;
    }
}
