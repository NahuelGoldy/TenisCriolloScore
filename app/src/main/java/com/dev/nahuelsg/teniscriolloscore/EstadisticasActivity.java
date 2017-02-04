package com.dev.nahuelsg.teniscriolloscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Nahuel SG on 02/02/2017.
 */

public class EstadisticasActivity extends AppCompatActivity {
    private String jugador1, jugador2;
    private int puntActualJugador1, puntActualJugador2, totalPuntosJugador1, totalPuntosJugador2;
    private int puntosSacandoJugador1, puntosSacandoJugador2;
    private int primerSaqueMetidoJugador1, primerSaqueMetidoJugador2;
    private int acesJugador1, acesJugador2;
    private int dobleFaltasJugador1, dobleFaltasJugador2;
    private int puntosGanadosPrimerSaqueJugador1, puntosGanadosPrimerSaqueJugador2;
    private int puntosGanadosSegundoSaqueJugador1, puntosGanadosSegundoSaqueJugador2;
    private int puntosGanadosDevolucionJugador1, puntosGanadosDevolucionJugador2;
    private int devolucionGanadoraJugador1, devolucionGanadoraJugador2;
    private int errorDevolucionJugador1, errorDevolucionJugador2;
    private int subidasRedJugador1, subidasRedJugador2;
    private int ptosGanadosRedJugador1, ptosGanadosRedJugador2;
    private int winnersDerechaJugador1, winnersDerechaJugador2;
    private int winnersRevesJugador1,winnersRevesJugador2;
    private int errorNoForzadoDerechaJugador1, errorNoForzadoDerechaJugador2;
    private int errorNoForzadoRevesJugador1, errorNoForzadoRevesJugador2;

    private TextView labelJug1, labelJug2, scoreJug1, scoreJug2, labelGuionScore;
    private TextView labelEstadisticasServicio, labelEstadisticasGolpes;

    private TextView labelAces, acesJug1, acesJug2;
    private TextView labelDobleFaltas, dobleFaltasJug1, dobleFaltasJug2;
    private TextView labelEfectPrimerSaque, efectPrimerSaqueJug1, efectPrimerSaqueJug2;
    private TextView labelPtosPrimerSaque, ptosPrimerSaqueJug1, ptosPrimerSaqueJug2;
    private TextView labelPtosSegundoSaque, ptosSegundoSaqueJug1, ptosSegundoSaqueJug2;
    private TextView labelPtosDevolucion, ptosDevolucionJug1, ptosDevolucionJug2;

    private TextView labelWinners, winnersJug1, winnersJug2;
    private TextView labelWinnersDerecha, winnersDerechaJug1, winnersDerechaJug2;
    private TextView labelWinnersReves, winnersRevesJug1, winnersRevesJug2;
    private TextView labelErroresNoForzados, erroresNoForzadosJug1, erroresNoForzadosJug2;
    private TextView labelErroresNoForzadosDerecha, erroresNoForzadosDerechaJug1, erroresNoForzadosDerechaJug2;
    private TextView labelErroresNoForzadosReves, erroresNoForzadosRevesJug1, erroresNoForzadosRevesJug2;
    private TextView labelEnLaRed, enLaRedJug1, enLaRedJug2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        labelJug1 = (TextView) findViewById(R.id.label_jugador1);
        labelJug2 = (TextView) findViewById(R.id.label_jugador2);
        scoreJug1 = (TextView) findViewById(R.id.score_jug1);
        scoreJug2 = (TextView) findViewById(R.id.score_jug2);
        labelGuionScore = (TextView) findViewById(R.id.textView5);

        labelEstadisticasServicio = (TextView) findViewById(R.id.textView_titulo1);

        labelAces = (TextView) findViewById(R.id.label_aces);
        acesJug1 = (TextView) findViewById(R.id.aces_jugador1);
        acesJug2 = (TextView) findViewById(R.id.aces_jugador2);
        labelDobleFaltas = (TextView) findViewById(R.id.label_doblefaltas);
        dobleFaltasJug1 = (TextView) findViewById(R.id.doblefaltas_jugador1);
        dobleFaltasJug2 = (TextView) findViewById(R.id.doblefaltas_jugador2);
        labelEfectPrimerSaque = (TextView) findViewById(R.id.label_efec1er);
        efectPrimerSaqueJug1 = (TextView) findViewById(R.id.efect1er_jugador1);
        efectPrimerSaqueJug2 = (TextView) findViewById(R.id.efect1er_jugador2);
        labelPtosPrimerSaque = (TextView) findViewById(R.id.label_ptos1er);
        ptosPrimerSaqueJug1 = (TextView) findViewById(R.id.ptos1er_jugador1);
        ptosPrimerSaqueJug2 = (TextView) findViewById(R.id.ptos1er_jugador2);
        labelPtosSegundoSaque = (TextView) findViewById(R.id.label_ptos2do);
        ptosSegundoSaqueJug1 = (TextView) findViewById(R.id.ptos2do_jugador1);
        ptosSegundoSaqueJug2 = (TextView) findViewById(R.id.ptos2do_jugador2);
        labelPtosDevolucion = (TextView) findViewById(R.id.label_ptosdevol);
        ptosDevolucionJug1 = (TextView) findViewById(R.id.ptosdevol_jugador1);
        ptosDevolucionJug2 = (TextView) findViewById(R.id.ptosdevol_jugador2);

        labelEstadisticasGolpes = (TextView) findViewById(R.id.textView_titulo2);

        labelWinners = (TextView) findViewById(R.id.label_winners);
        winnersJug1 = (TextView) findViewById(R.id.winners_jugador1);
        winnersJug2 = (TextView) findViewById(R.id.winners_jugador2);
        labelWinnersDerecha = (TextView) findViewById(R.id.label_winners_derecha);
        winnersDerechaJug1 = (TextView) findViewById(R.id.winners_derecha_jugador1);
        winnersDerechaJug2 = (TextView) findViewById(R.id.winners_derecha_jugador2);
        labelWinnersReves = (TextView) findViewById(R.id.label_winners_reves);
        winnersRevesJug1 = (TextView) findViewById(R.id.winners_reves_jugador1);
        winnersRevesJug2 = (TextView) findViewById(R.id.winners_reves_jugador2);
        labelErroresNoForzados = (TextView) findViewById(R.id.label_ptos1er);
        erroresNoForzadosJug1 = (TextView) findViewById(R.id.ptos1er_jugador1);
        erroresNoForzadosJug2 = (TextView) findViewById(R.id.ptos1er_jugador2);
        labelErroresNoForzadosDerecha = (TextView) findViewById(R.id.label_ptos2do);
        erroresNoForzadosDerechaJug1 = (TextView) findViewById(R.id.ptos2do_jugador1);
        erroresNoForzadosDerechaJug2 = (TextView) findViewById(R.id.ptos2do_jugador2);
        labelErroresNoForzadosReves = (TextView) findViewById(R.id.label_ptosdevol);
        erroresNoForzadosRevesJug1 = (TextView) findViewById(R.id.ptosdevol_jugador1);
        erroresNoForzadosRevesJug2 = (TextView) findViewById(R.id.ptosdevol_jugador2);
        labelEnLaRed = (TextView) findViewById(R.id.label_ptosdevol);
        enLaRedJug1 = (TextView) findViewById(R.id.ptosdevol_jugador1);
        enLaRedJug2 = (TextView) findViewById(R.id.ptosdevol_jugador2);


    }
}
