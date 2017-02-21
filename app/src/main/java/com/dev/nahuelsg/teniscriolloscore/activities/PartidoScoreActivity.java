package com.dev.nahuelsg.teniscriolloscore.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dev.nahuelsg.teniscriolloscore.R;
import com.dev.nahuelsg.teniscriolloscore.modelo.Accion;

import java.util.Stack;

/**
 * Created by Nahuel SG on 01/02/2017.
 */

public class PartidoScoreActivity extends AppCompatActivity {
    private Stack<Accion> pilaAcciones;
    private boolean doubleBackToExitPressedOnce = false;
    private long tiempoInicioPartido;
    private String jugador1, jugador2;
    private int setsJug1=0, setsJug2=0;
    private int maxSets, maxPuntos, puntActualJugador1=0, puntActualJugador2=0, totalPuntosJugador1=0, totalPuntosJugador2=0;
    private int set1Jug1=0, set2Jug1=0, set3Jug1=0, set4Jug1=0, set5Jug1=0;
    private int set1Jug2=0, set2Jug2=0, set3Jug2=0, set4Jug2=0, set5Jug2=0;
    private boolean sacaJugador1=false, sacaJugador2=false, flagFalta=false, partidoTerminado=false;
    private int puntosSacandoJugador1=0, puntosSacandoJugador2=0;
    private int primerSaqueMetidoJugador1=0, primerSaqueMetidoJugador2=0;
    private int acesJugador1=0, acesJugador2=0;
    private int dobleFaltasJugador1=0, dobleFaltasJugador2=0;
    private int puntosGanadosPrimerSaqueJugador1=0, puntosGanadosPrimerSaqueJugador2=0;
    private int puntosGanadosSegundoSaqueJugador1=0, puntosGanadosSegundoSaqueJugador2=0;
    private int puntosGanadosDevolucionJugador1=0, puntosGanadosDevolucionJugador2=0;
    private int subidasRedJugador1=0, subidasRedJugador2=0;
    private int ptosGanadosRedJugador1=0, ptosGanadosRedJugador2=0;
    private int winnersDerechaJugador1=0, winnersDerechaJugador2=0;
    private int winnersRevesJugador1=0,winnersRevesJugador2=0;
    private int errorNoForzadoDerechaJugador1=0, errorNoForzadoDerechaJugador2=0;
    private int errorNoForzadoRevesJugador1=0, errorNoForzadoRevesJugador2=0;

    private TextView tvJugador1, tvJugador2, tvScoreJugador1, tvScoreJugador2, tvGuionScore;
    private ImageView iconoPelotaJug1, iconoPelotaJug2;
    private Button btnEsquina, btnLabelSet1, btnLabelSet2, btnLabelSet3, btnLabelSet4, btnLabelSet5;
    private Button btnJug1, btnSet1Jug1, btnSet2Jug1, btnSet3Jug1, btnSet4Jug1, btnSet5Jug1;
    private Button btnJug2, btnSet1Jug2, btnSet2Jug2, btnSet3Jug2, btnSet4Jug2, btnSet5Jug2;
    private TabHost tabhost;
    private Button btnAce, btnFalta, btnSaqueGanador, btnEnJuego;
    private ToggleButton enLaRedJug1, enLaRedJug2;
    private Button winnerDerechaJug1, winnerDerechaJug2, winnerRevesJug1, winnerRevesJug2;
    private Button errorDerechaJug1,errorDerechaJug2, errorRevesJug1, errorRevesJug2;
    private Button puntoGenericoJug1, puntoGenericoJug2;

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_score);

        tvJugador1 = (TextView) findViewById(R.id.jugador1);
        tvJugador2 = (TextView) findViewById(R.id.jugador2);
        tvScoreJugador1 = (TextView) findViewById(R.id.score_jugador1);
        tvScoreJugador2 = (TextView) findViewById(R.id.score_jugador2);
        tvGuionScore = (TextView) findViewById(R.id.textView);
        iconoPelotaJug1 = (ImageView) findViewById(R.id.icono_pelota_saque1);
        iconoPelotaJug2 = (ImageView) findViewById(R.id.icono_pelota_saque2);

        btnEsquina = (Button) findViewById(R.id.button9);
        btnLabelSet1 = (Button) findViewById(R.id.label_set1);
        btnLabelSet2 = (Button) findViewById(R.id.label_set2);
        btnLabelSet3 = (Button) findViewById(R.id.label_set3);
        btnLabelSet4 = (Button) findViewById(R.id.label_set4);
        btnLabelSet5 = (Button) findViewById(R.id.label_set5);
        btnJug1 = (Button) findViewById(R.id.label_jugador1_sets);
        btnSet1Jug1 = (Button) findViewById(R.id.set1_jug1);
        btnSet2Jug1 = (Button) findViewById(R.id.set2_jug1);
        btnSet3Jug1 = (Button) findViewById(R.id.set3_jug1);
        btnSet4Jug1 = (Button) findViewById(R.id.set4_jug1);
        btnSet5Jug1 = (Button) findViewById(R.id.set5_jug1);
        btnJug2 = (Button) findViewById(R.id.label_jugador2_sets);
        btnSet1Jug2 = (Button) findViewById(R.id.set1_jug2);
        btnSet2Jug2 = (Button) findViewById(R.id.set2_jug2);
        btnSet3Jug2 = (Button) findViewById(R.id.set3_jug2);
        btnSet4Jug2 = (Button) findViewById(R.id.set4_jug2);
        btnSet5Jug2 = (Button) findViewById(R.id.set5_jug2);

        tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        //Tab 1
        TabHost.TabSpec spec = tabhost.newTabSpec("1er saque");
        spec.setContent(R.id.prim_saque);
        spec.setIndicator("1er saque");
        tabhost.addTab(spec);

        //Tab 2
        spec = tabhost.newTabSpec("en juego");
        spec.setContent(R.id.en_juego);
        spec.setIndicator("en juego");
        tabhost.addTab(spec);

        intent = getIntent();
        tiempoInicioPartido = intent.getLongExtra("TiempoInicio",System.currentTimeMillis());
        maxSets = Integer.parseInt(intent.getStringExtra("MaxSets"));
        maxPuntos = Integer.parseInt(intent.getStringExtra("MaxPuntos"));
        jugador1 = intent.getStringExtra("Jugador1");
        jugador2 = intent.getStringExtra("Jugador2");
        if(intent.getStringExtra("AlSaque").equals("Jugador 1")) {
            sacaJugador1 = true;
            iconoPelotaJug2.setVisibility(View.INVISIBLE);
        }
        else {
            sacaJugador2 = true;
            iconoPelotaJug1.setVisibility(View.INVISIBLE);
        }

        if(jugador1.length()<9) tvJugador1.setText(jugador1);
        else tvJugador1.setText(jugador1.substring(0,9));
        if(jugador2.length()<9) tvJugador2.setText(jugador2);
        else tvJugador2.setText(jugador2.substring(0,9));
        tvScoreJugador1.setText("0"); tvScoreJugador2.setText("0");
        if(jugador1.length()>3) btnJug1.setText(jugador1.substring(0,4)); else btnJug1.setText(jugador1);
        if(jugador2.length()>3) btnJug2.setText(jugador2.substring(0,4)); else btnJug2.setText(jugador2);
        btnSet1Jug1.setText("-"); btnSet1Jug2.setText("-");
        btnSet2Jug1.setText("-"); btnSet2Jug2.setText("-");
        btnSet3Jug1.setText("-"); btnSet3Jug2.setText("-");
        btnSet4Jug1.setText("-"); btnSet4Jug2.setText("-");
        btnSet5Jug1.setText("-"); btnSet5Jug2.setText("-");

        tabhost.getTabWidget().getChildTabViewAt(1).setEnabled(false);

        pilaAcciones = new Stack<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAce = (Button) findViewById(R.id.button_ace);
        btnAce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagFalta){
                    accionBotonAcePrimerSaque();
                }
                else{
                    accionBotonAceSegundoSaque();
                    btnFalta.setText("Falta");
                }
            }
        });

        btnFalta = (Button) findViewById(R.id.button_falta);
        btnFalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagFalta){
                    accionBotonFaltaPrimerSaque();
                    btnFalta.setText("Doble falta");
                }
                else{
                    accionBotonFaltaSegundoSaque();
                    btnFalta.setText("Falta");
                }
            }
        });

        btnSaqueGanador = (Button) findViewById(R.id.button_saque_ganador);
        btnSaqueGanador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagFalta){
                    accionBotonSaqueGanadorPrimerSaque();
                }
                else{
                    accionBotonSaqueGanadorSegundoSaque();
                    btnFalta.setText("Falta");
                }
            }
        });

        btnEnJuego = (Button) findViewById(R.id.button_bola_en_juego);
        btnEnJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagFalta){
                    accionBotonEnJuegoPrimerSaque();
                }
                else{
                    accionBotonEnJuegoSegundoSaque();
                    btnFalta.setText("Falta");
                }
            }
        });

        winnerDerechaJug1 = (Button) findViewById(R.id.winner_derecha_jugador1);
        winnerDerechaJug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosPrimerSaqueJugador1++;
                        accionBotonWinnerDerechaJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonWinnerDerechaJug1(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosSegundoSaqueJugador1++;
                        accionBotonWinnerDerechaJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonWinnerDerechaJug1(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        winnerDerechaJug2 = (Button) findViewById(R.id.winner_derecha_jugador2);
        winnerDerechaJug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonWinnerDerechaJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosPrimerSaqueJugador2++;
                        accionBotonWinnerDerechaJug2(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonWinnerDerechaJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosSegundoSaqueJugador2++;
                        accionBotonWinnerDerechaJug2(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        winnerRevesJug1 = (Button) findViewById(R.id.winner_reves_jugador1);
        winnerRevesJug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosPrimerSaqueJugador1++;
                        accionBotonWinnerRevesJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonWinnerRevesJug1(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosSegundoSaqueJugador1++;
                        accionBotonWinnerRevesJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonWinnerRevesJug1(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        winnerRevesJug2 = (Button) findViewById(R.id.winner_reves_jugador2);
        winnerRevesJug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonWinnerRevesJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosPrimerSaqueJugador2++;
                        accionBotonWinnerRevesJug2(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonWinnerRevesJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosSegundoSaqueJugador2++;
                        accionBotonWinnerRevesJug2(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        errorDerechaJug1 = (Button) findViewById(R.id.error_derecha_jugador1);
        errorDerechaJug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonErrorDerechaJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosPrimerSaqueJugador2++;
                        accionBotonErrorDerechaJug1(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonErrorDerechaJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosSegundoSaqueJugador2++;
                        accionBotonErrorDerechaJug1(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        errorDerechaJug2 = (Button) findViewById(R.id.error_derecha_jugador2);
        errorDerechaJug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosPrimerSaqueJugador1++;
                        accionBotonErrorDerechaJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonErrorDerechaJug2(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosSegundoSaqueJugador1++;
                        accionBotonErrorDerechaJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonErrorDerechaJug2(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        errorRevesJug1 = (Button) findViewById(R.id.error_reves_jugador1);
        errorRevesJug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonErrorRevesJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosPrimerSaqueJugador2++;
                        accionBotonErrorRevesJug1(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonErrorRevesJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosSegundoSaqueJugador2++;
                        accionBotonErrorRevesJug1(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        errorRevesJug2 = (Button) findViewById(R.id.error_reves_jugador2);
        errorRevesJug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosPrimerSaqueJugador1++;
                        accionBotonErrorRevesJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonErrorRevesJug2(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosSegundoSaqueJugador1++;
                        accionBotonErrorRevesJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonErrorRevesJug2(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        puntoGenericoJug1 = (Button) findViewById(R.id.button_ptoganado_jugador1);
        puntoGenericoJug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosPrimerSaqueJugador1++;
                        accionBotonPuntoGanadoJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonPuntoGanadoJug1(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosSegundoSaqueJugador1++;
                        accionBotonPuntoGanadoJug1(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosDevolucionJugador1++;
                        accionBotonPuntoGanadoJug1(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });

        puntoGenericoJug2 = (Button) findViewById(R.id.button_ptoganado_jugador2);
        puntoGenericoJug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton tgJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
                ToggleButton tgJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
                boolean enRedJug1=tgJug1.isChecked(); boolean enRedJug2=tgJug2.isChecked();
                tgJug1.setChecked(false); tgJug2.setChecked(false);
                if(!flagFalta){ //jugando con el primer saque
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonPuntoGanadoJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosPrimerSaqueJugador2++;
                        accionBotonPuntoGanadoJug2(enRedJug1, enRedJug2);
                    }
                }
                else{
                    if(sacaJugador1){
                        puntosGanadosDevolucionJugador2++;
                        accionBotonPuntoGanadoJug2(enRedJug1, enRedJug2);
                    }
                    else{
                        puntosGanadosSegundoSaqueJugador2++;
                        accionBotonPuntoGanadoJug2(enRedJug1, enRedJug2);
                    }
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    flagFalta=false;
                }
                cambiarTab(0,1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_estadisticas: {
                if(tabhost.getCurrentTab()==0 && !flagFalta){
                    Intent i = new Intent(PartidoScoreActivity.this,EstadisticasActivity.class);
                    i = putExtrasParaEstadisticas(i);
                    //TODO startactivityforresult?
                    startActivity(i);
                }
                else{
                    Toast.makeText(PartidoScoreActivity.this, "No puede ver las estadisticas durante un punto",
                            Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.action_deshacer: {
                if(pilaAcciones!=null && pilaAcciones.size()>0) deshacerAccion(pilaAcciones.pop());
                break;
            }
            case R.id.action_abandonar: {
                generarDialogAbandonar();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void accionBotonAcePrimerSaque(){
        if(sacaJugador1){
            pilaAcciones.push(new Accion("ace", 1, 1));
            puntActualJugador1++;
            puntosGanadosPrimerSaqueJugador1++;
            totalPuntosJugador1++;
            puntosSacandoJugador1++;
            acesJugador1++;
            primerSaqueMetidoJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            pilaAcciones.push(new Accion("ace", 1, 2));
            puntActualJugador2++;
            puntosGanadosPrimerSaqueJugador2++;
            totalPuntosJugador2++;
            puntosSacandoJugador2++;
            acesJugador2++;
            primerSaqueMetidoJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void accionBotonFaltaPrimerSaque(){
        flagFalta = true;
        if(sacaJugador1){
            pilaAcciones.push(new Accion("falta", 1, 1));
            puntosSacandoJugador1++;
        }
        else{
            pilaAcciones.push(new Accion("falta", 1, 2));
            puntosSacandoJugador2++;
        }
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");

    }

    public void accionBotonSaqueGanadorPrimerSaque(){
        if(sacaJugador1){
            pilaAcciones.push(new Accion("saque_ganador", 1, 1));
            puntActualJugador1++;
            puntosGanadosPrimerSaqueJugador1++;
            totalPuntosJugador1++;
            puntosSacandoJugador1++;
            primerSaqueMetidoJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            pilaAcciones.push(new Accion("saque_ganador", 1, 2));
            puntActualJugador2++;
            puntosGanadosPrimerSaqueJugador2++;
            totalPuntosJugador2++;
            puntosSacandoJugador2++;
            primerSaqueMetidoJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void accionBotonEnJuegoPrimerSaque(){
        if(sacaJugador1){
            pilaAcciones.push(new Accion("en_juego", 1, 1));
            puntosSacandoJugador1++;
            primerSaqueMetidoJugador1++;
        }
        else{
            pilaAcciones.push(new Accion("en_juego", 1, 2));
            puntosSacandoJugador2++;
            primerSaqueMetidoJugador2++;
        }
        cambiarTab(1,0);
    }

    public void accionBotonAceSegundoSaque(){
        flagFalta=false;
        if(sacaJugador1){
            pilaAcciones.push(new Accion("ace", 2, 1));
            puntActualJugador1++;
            puntosGanadosSegundoSaqueJugador1++;
            totalPuntosJugador1++;
            acesJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            pilaAcciones.push(new Accion("ace", 2, 2));
            puntActualJugador2++;
            puntosGanadosSegundoSaqueJugador2++;
            totalPuntosJugador2++;
            acesJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
    }

    public void accionBotonFaltaSegundoSaque(){
        flagFalta=false;
        if(sacaJugador1){
            pilaAcciones.push(new Accion("doble_falta", 2, 1));
            dobleFaltasJugador1++;
            puntActualJugador2++;
            totalPuntosJugador2++;
            puntosGanadosDevolucionJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            pilaAcciones.push(new Accion("doble_falta", 2, 2));
            dobleFaltasJugador2++;
            puntActualJugador1++;
            totalPuntosJugador1++;
            puntosGanadosDevolucionJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
    }

    public void accionBotonSaqueGanadorSegundoSaque(){
        flagFalta=false;
        if(sacaJugador1){
            pilaAcciones.push(new Accion("saque_ganador", 2, 1));
            puntActualJugador1++;
            puntosGanadosSegundoSaqueJugador1++;
            totalPuntosJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            pilaAcciones.push(new Accion("saque_ganador", 2, 2));
            puntActualJugador2++;
            puntosGanadosSegundoSaqueJugador2++;
            totalPuntosJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
    }

    public void accionBotonEnJuegoSegundoSaque(){
        if(sacaJugador1){
            pilaAcciones.push(new Accion("en_juego", 2, 1));
        }
        else{
            pilaAcciones.push(new Accion("en_juego", 2, 2));
        }
        cambiarTab(1,0);
    }

    public void accionBotonWinnerDerechaJug1(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador2) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador1++;
        totalPuntosJugador1++;
        winnersDerechaJugador1++;
        if(enRedJug1){
            pilaAcciones.push(new Accion("winner_derecha_red", saq, 1));
            subidasRedJugador1++;
            ptosGanadosRedJugador1++;
        }
        else{
            pilaAcciones.push(new Accion("winner_derecha_fondo", saq, 1));
        }
        if(enRedJug2){
            subidasRedJugador2++;
        }
        tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
        chequearEsFinSetJugador1();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonWinnerDerechaJug2(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador1) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador2++;
        totalPuntosJugador2++;
        winnersDerechaJugador2++;
        if(enRedJug2){
            pilaAcciones.push(new Accion("winner_derecha_red", saq, 2));
            subidasRedJugador2++;
            ptosGanadosRedJugador2++;
        }
        else{
            pilaAcciones.push(new Accion("winner_derecha_fondo", saq, 2));
        }
        if(enRedJug1){
            subidasRedJugador1++;
        }
        tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
        chequearEsFinSetJugador2();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonWinnerRevesJug1(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador2) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador1++;
        totalPuntosJugador1++;
        winnersRevesJugador1++;
        if(enRedJug2){
            subidasRedJugador2++;
        }
        if(enRedJug1){
            subidasRedJugador1++;
            ptosGanadosRedJugador1++;
            pilaAcciones.push(new Accion("winner_reves_red", saq, 1));
        }
        else pilaAcciones.push(new Accion("winner_reves_fondo", saq, 1));
        tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
        chequearEsFinSetJugador1();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonWinnerRevesJug2(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador1) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador2++;
        totalPuntosJugador2++;
        winnersRevesJugador2++;
        if(enRedJug2){
            subidasRedJugador2++;
            ptosGanadosRedJugador2++;
            pilaAcciones.push(new Accion("winner_reves_red", saq, 2));
        }
        else pilaAcciones.push(new Accion("winner_reves_fondo", saq, 2));
        if(enRedJug1){
            subidasRedJugador1++;
        }
        tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
        chequearEsFinSetJugador2();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonErrorDerechaJug1(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador2) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador2++;
        totalPuntosJugador2++;
        errorNoForzadoDerechaJugador1++;
        if(enRedJug2){
            subidasRedJugador2++;
            ptosGanadosRedJugador2++;
        }
        if(enRedJug1){
            subidasRedJugador1++;
            pilaAcciones.push(new Accion("error_derecha_red", saq, 1));
        }
        else pilaAcciones.push(new Accion("error_derecha_fondo", saq, 1));
        tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
        chequearEsFinSetJugador2();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonErrorDerechaJug2(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador1) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador1++;
        totalPuntosJugador1++;
        errorNoForzadoDerechaJugador2++;
        if(enRedJug2){
            subidasRedJugador2++;
            pilaAcciones.push(new Accion("error_derecha_red", saq, 2));
        }
        else pilaAcciones.push(new Accion("error_derecha_fondo", saq, 2));
        if(enRedJug1){
            subidasRedJugador1++;
            ptosGanadosRedJugador1++;
        }
        tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
        chequearEsFinSetJugador1();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonErrorRevesJug1(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador2) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador2++;
        totalPuntosJugador2++;
        errorNoForzadoRevesJugador1++;
        if(enRedJug2){
            subidasRedJugador2++;
            ptosGanadosRedJugador2++;
        }
        if(enRedJug1){
            subidasRedJugador1++;
            pilaAcciones.push(new Accion("error_reves_red", saq, 1));
        }
        else pilaAcciones.push(new Accion("error_reves_fondo", saq, 1));
        tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
        chequearEsFinSetJugador2();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonErrorRevesJug2(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador1) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador1++;
        totalPuntosJugador1++;
        errorNoForzadoRevesJugador2++;
        if(enRedJug2){
            subidasRedJugador2++;
            pilaAcciones.push(new Accion("error_reves_red", saq, 2));
        }
        else pilaAcciones.push(new Accion("error_reves_fondo", saq, 2));
        if(enRedJug1){
            subidasRedJugador1++;
            ptosGanadosRedJugador1++;
        }
        tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
        chequearEsFinSetJugador1();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonPuntoGanadoJug1(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador2) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador1++;
        totalPuntosJugador1++;
        if(enRedJug2){
            subidasRedJugador2++;
        }
        if(enRedJug1){
            subidasRedJugador1++;
            ptosGanadosRedJugador1++;
            pilaAcciones.push(new Accion("pto_ganado_red", saq, 1));
        }
        else pilaAcciones.push(new Accion("pto_ganado_fondo", saq, 1));
        tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
        chequearEsFinSetJugador1();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void accionBotonPuntoGanadoJug2(boolean enRedJug1, boolean enRedJug2) {
        int saq;
        if(sacaJugador1) { //saque=0 significa que el jugador esta devolviendo (10 devolucion de primer saque, 20 delovuion de segundo saque)
            if(!flagFalta) saq=10;
            else saq=20;
        }
        else{
            if(!flagFalta) saq=1;
            else saq=2;
        }
        puntActualJugador2++;
        totalPuntosJugador2++;
        if(enRedJug2){
            subidasRedJugador2++;
            ptosGanadosRedJugador2++;
            pilaAcciones.push(new Accion("pto_ganado_red", saq, 2));
        }
        else pilaAcciones.push(new Accion("pto_ganado_fondo", saq, 2));
        if(enRedJug1){
            subidasRedJugador1++;
        }
        tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
        chequearEsFinSetJugador2();
        if((puntActualJugador1+puntActualJugador2)>0 && (puntActualJugador1+puntActualJugador2)%4==0){
            if(sacaJugador1){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
            else{
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
                iconoPelotaJug1.setVisibility(View.VISIBLE);
            }
        }
        flagFalta=false;
    }

    public void chequearEsFinSetJugador1(){
        if(puntActualJugador1 >= maxPuntos && puntActualJugador1-puntActualJugador2>=2){
            setsJug1++;
            switch (setsJug1+setsJug2){
                case 1: {
                    set1Jug1=puntActualJugador1;
                    set1Jug2=puntActualJugador2;
                    btnSet1Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet1Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 2: {
                    set2Jug1=puntActualJugador1;
                    set2Jug2=puntActualJugador2;
                    btnSet2Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet2Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 3: {
                    set3Jug1=puntActualJugador1;
                    set3Jug2=puntActualJugador2;
                    btnSet3Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet3Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 4: {
                    set4Jug1=puntActualJugador1;
                    set4Jug2=puntActualJugador2;
                    btnSet4Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet4Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 5: {
                    set5Jug1=puntActualJugador1;
                    set5Jug2=puntActualJugador2;
                    btnSet5Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet5Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
            }
            puntActualJugador1=0;
            puntActualJugador2=0;
            tvScoreJugador1.setText("0");
            tvScoreJugador2.setText("0");
            chequearQuienSaca();
            if(setsJug1 > (maxSets/2)) {
                partidoTerminado=true;
                Toast.makeText(PartidoScoreActivity.this, "PARTIDO TERMINADO! Ganador: "+jugador1,
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(PartidoScoreActivity.this,EstadisticasActivity.class);
                i = putExtrasParaEstadisticas(i);
                i.putExtra("Ganador", "jugador1");
                i.putExtra("CantSets", setsJug1+setsJug2);
                //TODO startactivityforresult?
                startActivity(i);
            }
        }
    }

    public void chequearEsFinSetJugador2(){
        if(puntActualJugador2 >= maxPuntos && puntActualJugador2-puntActualJugador1>=2){
            setsJug2++;
            switch (setsJug1+setsJug2){
                case 1: {
                    set1Jug1=puntActualJugador1;
                    set1Jug2=puntActualJugador2;
                    btnSet1Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet1Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 2: {
                    set2Jug1=puntActualJugador1;
                    set2Jug2=puntActualJugador2;
                    btnSet2Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet2Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 3: {
                    set3Jug1=puntActualJugador1;
                    set3Jug2=puntActualJugador2;
                    btnSet3Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet3Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 4: {
                    set4Jug1=puntActualJugador1;
                    set4Jug2=puntActualJugador2;
                    btnSet4Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet4Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
                case 5: {
                    set5Jug1=puntActualJugador1;
                    set5Jug2=puntActualJugador2;
                    btnSet5Jug1.setText(String.valueOf(puntActualJugador1));
                    btnSet5Jug2.setText(String.valueOf(puntActualJugador2));
                    break;
                }
            }
            puntActualJugador1=0;
            puntActualJugador2=0;
            tvScoreJugador1.setText("0");
            tvScoreJugador2.setText("0");
            chequearQuienSaca();
            if(setsJug2 > (maxSets/2)) {
                partidoTerminado=true;
                Toast.makeText(PartidoScoreActivity.this, "PARTIDO TERMINADO! Ganador: "+jugador2,
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(PartidoScoreActivity.this,EstadisticasActivity.class);
                i = putExtrasParaEstadisticas(i);
                i.putExtra("Ganador", "jugador2");
                i.putExtra("CantSets", setsJug1+setsJug2);
                //TODO startactivityforresult?
                startActivity(i);
            }
        }
    }

    public void cambiarTab(int nueva, int anterior){
        tabhost.getTabWidget().getChildTabViewAt(nueva).setEnabled(true);
        tabhost.getTabWidget().getChildTabViewAt(anterior).setClickable(false);
        tabhost.getTabWidget().getChildTabViewAt(anterior).setFocusable(false);
        tabhost.setCurrentTab(nueva);
    }

    public Intent putExtrasParaEstadisticas(Intent i){
        i.putExtra("Jug1", jugador1); i.putExtra("Jug2", jugador2);
        i.putExtra("TotalPtosJug1", totalPuntosJugador1); i.putExtra("TotalPtosJug2", totalPuntosJugador2);
        i.putExtra("PuntJug1", puntActualJugador1); i.putExtra("PuntJug2", puntActualJugador2);
        i.putExtra("Set1Jug1", set1Jug1); i.putExtra("Set1Jug2", set1Jug2);
        i.putExtra("Set2Jug1", set2Jug1); i.putExtra("Set2Jug2", set2Jug2);
        i.putExtra("Set3Jug1", set3Jug1); i.putExtra("Set3Jug2", set3Jug2);
        i.putExtra("Set4Jug1", set4Jug1); i.putExtra("Set4Jug2", set4Jug2);
        i.putExtra("Set5Jug1", set5Jug1); i.putExtra("Set5Jug2", set5Jug2);
        i.putExtra("AcesJug1", acesJugador1); i.putExtra("AcesJug2", acesJugador2);
        i.putExtra("DobleFaltasJug1", dobleFaltasJugador1); i.putExtra("DobleFaltasJug2", dobleFaltasJugador2);
        i.putExtra("SaquesJug1", puntosSacandoJugador1); i.putExtra("SaquesJug2", puntosSacandoJugador2);
        i.putExtra("PrimeroAdentroJug1", primerSaqueMetidoJugador1); i.putExtra("PrimeroAdentroJug2", primerSaqueMetidoJugador2);
        i.putExtra("GanadosPrimeroJug1", puntosGanadosPrimerSaqueJugador1); i.putExtra("GanadosPrimeroJug2", puntosGanadosPrimerSaqueJugador2);
        i.putExtra("GanadosSegundoJug1", puntosGanadosSegundoSaqueJugador1); i.putExtra("GanadosSegundoJug2", puntosGanadosSegundoSaqueJugador2);
        i.putExtra("GanadosDevolJug1", puntosGanadosDevolucionJugador1); i.putExtra("GanadosDevolJug2", puntosGanadosDevolucionJugador2);
        i.putExtra("WinnersDerechaJug1", winnersDerechaJugador1); i.putExtra("WinnersDerechaJug2", winnersDerechaJugador2);
        i.putExtra("WinnersRevesJug1", winnersRevesJugador1); i.putExtra("WinnersRevesJug2", winnersRevesJugador2);
        i.putExtra("ErroresDerechaJug1", errorNoForzadoDerechaJugador1); i.putExtra("ErroresDerechaJug2", errorNoForzadoDerechaJugador2);
        i.putExtra("ErroresRevesJug1", errorNoForzadoRevesJugador1); i.putExtra("ErroresRevesJug2", errorNoForzadoRevesJugador2);
        i.putExtra("EnLaRedJug1", subidasRedJugador1); i.putExtra("EnLaRedJug2", subidasRedJugador2);
        i.putExtra("GanadosEnLaRedJug1", ptosGanadosRedJugador1); i.putExtra("GanadosEnLaRedJug2", ptosGanadosRedJugador2);
        i.putExtra("Terminado", partidoTerminado);
        i.putExtra("TiempoInicio", tiempoInicioPartido);
        i.putExtra("TiempoFinal", System.currentTimeMillis());
        return i;
    }

    private void deshacerAccion(Accion action) {
        int jugador = action.getJugador(), saque = action.getSaque();
        switch(action.getAccion()){
            case "ace": {
                deshacerAce(jugador, saque);
                break;
            }
            case "falta": {
                deshacerFalta(jugador, saque);
                break;
            }
            case "doble_falta": {
                deshacerDobleFalta(jugador, saque);
                break;
            }
            case "saque_ganador": {
                deshacerSaqueGanador(jugador, saque);
                break;
            }
            case "en_juego": {
                deshacerEnJuego(jugador, saque);
                break;
            }
            case "winner_derecha_red": {
                deshacerWinnerDerechaRed(jugador, saque);
                break;
            }
            case "winner_derecha_fondo": {
                deshacerWinnerDerecha(jugador, saque);
                break;
            }
            case "winner_reves_red": {
                deshacerWinnerRevesRed(jugador, saque);
                break;
            }
            case "winner_reves_fondo": {
                deshacerWinnerReves(jugador, saque);
                break;
            }
            case "error_derecha_red": {
                deshacerErrorDerechaRed(jugador, saque);
                break;
            }
            case "error_derecha_fondo": {
                deshacerErrorDerecha(jugador, saque);
                break;
            }
            case "error_reves_red": {
                deshacerErrorRevesRed(jugador, saque);
                break;
            }
            case "error_reves_fondo": {
                deshacerErrorReves(jugador, saque);
                break;
            }
            case "pto_ganado_red": {
                deshacerPtoGanadoRed(jugador, saque);
                break;
            }
            case "pto_ganado_fondo": {
                deshacerPtoGanado(jugador, saque);
                break;
            }
            default: {
                Toast.makeText(this,"ERROR! No se pudo deshacer la acción", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void deshacerAce(int jugador, int saque) {
        if(jugador==1){
            puntActualJugador1--;
            totalPuntosJugador1--;
            acesJugador1--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            if(saque==1){
                puntosSacandoJugador1--;
                primerSaqueMetidoJugador1--;
                puntosGanadosPrimerSaqueJugador1--;
            }
            else{
                puntosGanadosSegundoSaqueJugador1--;
                flagFalta=true;
                btnFalta.setText("Doble Falta");
                ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
            }
            iconoPelotaJug1.setVisibility(View.VISIBLE);
            iconoPelotaJug2.setVisibility(View.INVISIBLE);
            sacaJugador1=true; sacaJugador2=false;
        }
        else{
            puntActualJugador2--;
            totalPuntosJugador2--;
            acesJugador2--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            if(saque==1){
                puntosSacandoJugador2--;
                primerSaqueMetidoJugador2--;
                puntosGanadosPrimerSaqueJugador2--;
            }
            else{
                puntosGanadosSegundoSaqueJugador2--;
                flagFalta=true;
                btnFalta.setText("Doble Falta");
                ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
            }
            iconoPelotaJug2.setVisibility(View.VISIBLE);
            iconoPelotaJug1.setVisibility(View.INVISIBLE);
            sacaJugador1=false; sacaJugador2=true;
        }
    }

    private void deshacerFalta(int jugador, int saque) {
        if(jugador==1){
            puntosSacandoJugador1--;
        }
        else{
            puntosSacandoJugador2--;
        }
        flagFalta=false;
        btnFalta.setText("Falta");
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
    }

    private void deshacerDobleFalta(int jugador, int saque) {
        flagFalta=true;
        if(jugador==1){
            dobleFaltasJugador1--;
            puntosGanadosDevolucionJugador2--;
            puntActualJugador2--;
            totalPuntosJugador2--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            sacaJugador1=true; sacaJugador2=false;
            iconoPelotaJug1.setVisibility(View.VISIBLE);
            iconoPelotaJug2.setVisibility(View.INVISIBLE);
        }
        else{
            dobleFaltasJugador2--;
            puntosGanadosDevolucionJugador1--;
            puntActualJugador1--;
            totalPuntosJugador1--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            sacaJugador2=true; sacaJugador1=false;
            iconoPelotaJug2.setVisibility(View.VISIBLE);
            iconoPelotaJug1.setVisibility(View.INVISIBLE);
        }
        btnFalta.setText("Doble Falta");
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
    }

    private void deshacerSaqueGanador(int jugador, int saque){
        if(jugador==1){
            puntActualJugador1--;
            totalPuntosJugador1--;
            puntosSacandoJugador1--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            if(saque==1){
                puntosGanadosPrimerSaqueJugador1--;
                primerSaqueMetidoJugador1--;
            }
            else{
                puntosGanadosSegundoSaqueJugador1--;
                flagFalta=true;
                btnFalta.setText("Doble Falta");
                ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
            }
            sacaJugador1=true; sacaJugador2=false;
            iconoPelotaJug1.setVisibility(View.VISIBLE);
            iconoPelotaJug2.setVisibility(View.INVISIBLE);
        }
        else{
            puntActualJugador2--;
            totalPuntosJugador2--;
            puntosSacandoJugador2--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            if(saque==1){
                puntosGanadosPrimerSaqueJugador2--;
                primerSaqueMetidoJugador2--;
            }
            else{
                puntosGanadosSegundoSaqueJugador2--;
                flagFalta=true;
                btnFalta.setText("Doble Falta");
                ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
            }
            sacaJugador2=true; sacaJugador1=false;
            iconoPelotaJug2.setVisibility(View.VISIBLE);
            iconoPelotaJug1.setVisibility(View.INVISIBLE);
        }
    }

    private void deshacerEnJuego(int jugador, int saque){
        cambiarTab(0,1);
        if(jugador==1){
            if(saque == 1){
                puntosSacandoJugador1--;
                primerSaqueMetidoJugador1--;
                btnFalta.setText("Falta");
            }
            else{
                flagFalta=true;
                btnFalta.setText("Doble Falta");
                ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
            }
        }
        else {
            if(saque == 1){
                puntosSacandoJugador2--;
                primerSaqueMetidoJugador2--;
                btnFalta.setText("Falta");
            }
            else{
                flagFalta=true;
                btnFalta.setText("Doble Falta");
                ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
            }
        }
    }

    private void deshacerWinnerDerecha(int jugador, int saque){
        if(jugador==1){
            totalPuntosJugador1--;
            puntActualJugador1--;
            winnersDerechaJugador1--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            switch (saque){
                case 1: {
                    puntosGanadosPrimerSaqueJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosSegundoSaqueJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
        else{
            totalPuntosJugador2--;
            puntActualJugador2--;
            winnersDerechaJugador2--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            switch (saque){
                case 1: {
                    puntosGanadosPrimerSaqueJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosSegundoSaqueJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
            sacaJugador2=true; sacaJugador1=false;
            iconoPelotaJug2.setVisibility(View.VISIBLE);
            iconoPelotaJug1.setVisibility(View.INVISIBLE);
        }
    }

    private void deshacerWinnerDerechaRed(int jugador, int saque){
        if(jugador==1){
            subidasRedJugador1--;
            ptosGanadosRedJugador1--;
        }
        else{
            subidasRedJugador2--;
            ptosGanadosRedJugador2--;
        }
        deshacerWinnerDerecha(jugador, saque);
    }

    private void deshacerWinnerReves(int jugador, int saque){
        if(jugador==1){
            totalPuntosJugador1--;
            puntActualJugador1--;
            winnersRevesJugador1--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            switch (saque){
                case 1: {
                    puntosGanadosPrimerSaqueJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosSegundoSaqueJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
            sacaJugador1=true; sacaJugador2=false;
            iconoPelotaJug1.setVisibility(View.VISIBLE);
            iconoPelotaJug2.setVisibility(View.INVISIBLE);
        }
        else{
            totalPuntosJugador2--;
            puntActualJugador2--;
            winnersRevesJugador2--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            switch (saque){
                case 1: {
                    puntosGanadosPrimerSaqueJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosSegundoSaqueJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
    }

    private void deshacerWinnerRevesRed(int jugador, int saque){
        if(jugador==1){
            subidasRedJugador1--;
            ptosGanadosRedJugador1--;
        }
        else{
            subidasRedJugador2--;
            ptosGanadosRedJugador2--;
        }
        deshacerWinnerReves(jugador, saque);
    }

    private void deshacerErrorDerecha(int jugador, int saque){
        if(jugador==1){
            totalPuntosJugador2--;
            puntActualJugador2--;
            errorNoForzadoDerechaJugador1--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            switch (saque){
                case 1: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosPrimerSaqueJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosSegundoSaqueJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
        else{
            totalPuntosJugador1--;
            puntActualJugador1--;
            errorNoForzadoDerechaJugador2--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            switch (saque){
                case 1: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosPrimerSaqueJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosSegundoSaqueJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
    }

    private void deshacerErrorDerechaRed(int jugador, int saque){
        if(jugador==1){
            subidasRedJugador1--;
            ptosGanadosRedJugador1--;
        }
        else{
            subidasRedJugador2--;
            ptosGanadosRedJugador2--;
        }
        deshacerErrorDerecha(jugador, saque);
    }

    private void deshacerErrorReves(int jugador, int saque){
        if(jugador==1){
            totalPuntosJugador2--;
            puntActualJugador2--;
            errorNoForzadoRevesJugador1--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            switch (saque){
                case 1: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosPrimerSaqueJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosSegundoSaqueJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
        else{
            totalPuntosJugador1--;
            puntActualJugador1--;
            errorNoForzadoRevesJugador2--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            switch (saque){
                case 1: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosPrimerSaqueJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosSegundoSaqueJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
    }

    private void deshacerErrorRevesRed(int jugador, int saque){
        if(jugador==1){
            subidasRedJugador1--;
            ptosGanadosRedJugador1--;
        }
        else{
            subidasRedJugador2--;
            ptosGanadosRedJugador2--;
        }
        deshacerErrorReves(jugador, saque);
    }

    private void deshacerPtoGanado(int jugador, int saque){
        if(jugador==1){
            totalPuntosJugador1--;
            puntActualJugador1--;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            switch (saque){
                case 1: {
                    puntosGanadosPrimerSaqueJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosSegundoSaqueJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosDevolucionJugador1--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
        }
        else{
            totalPuntosJugador2--;
            puntActualJugador2--;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            switch (saque){
                case 1: {
                    puntosGanadosPrimerSaqueJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 2: {
                    puntosGanadosSegundoSaqueJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador2=true; sacaJugador1=false;
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    break;
                }
                case 10: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=false;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
                case 20: {
                    puntosGanadosDevolucionJugador2--;
                    flagFalta=true;
                    ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");
                    sacaJugador1=true; sacaJugador2=false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            cambiarTab(1,0);
            sacaJugador2=true; sacaJugador1=false;
            iconoPelotaJug2.setVisibility(View.VISIBLE);
            iconoPelotaJug1.setVisibility(View.INVISIBLE);
        }
    }

    private void deshacerPtoGanadoRed(int jugador, int saque){
        if(jugador==1){
            subidasRedJugador1--;
            ptosGanadosRedJugador1--;
        }
        else{
            subidasRedJugador2--;
            ptosGanadosRedJugador2--;
        }
        deshacerPtoGanado(jugador, saque);
    }

    @Override
    public void onBackPressed() {
        //TODO en el futuro hacer esta validacion con un Dialog ("En verdad desea salir del partido?")
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Presione ATRAS otra vez para abandonar el partido", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 3000);
        } else {
            //super.onBackPressed();
            generarDialogAbandonar();
            return;
        }
    }

    private void chequearQuienSaca(){
        int n = (setsJug1+setsJug2) % 2;
        switch (n){
            case 0:{
                if(intent.getStringExtra("AlSaque").equals("Jugador 1")) {
                    sacaJugador1 = true;
                    sacaJugador2 = false;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                }
                else {
                    sacaJugador2 = true;
                    sacaJugador1 = false;
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                }
                break;
            }
            case 1: {
                if(intent.getStringExtra("AlSaque").equals("Jugador 1")) {
                    sacaJugador1 = false;
                    sacaJugador2 = true;
                    iconoPelotaJug1.setVisibility(View.INVISIBLE);
                    iconoPelotaJug2.setVisibility(View.VISIBLE);
                }
                else {
                    sacaJugador2 = false;
                    sacaJugador1 = true;
                    iconoPelotaJug1.setVisibility(View.VISIBLE);
                    iconoPelotaJug2.setVisibility(View.INVISIBLE);
                }
                break;
            }
        }
    }

    private void generarDialogAbandonar(){
        final Dialog dialogAbandonar = new Dialog(this);
        dialogAbandonar.setContentView(R.layout.custom_dialog);
        dialogAbandonar.setTitle("Abandonar partido?");
        dialogAbandonar.setCancelable(true);
        dialogAbandonar.show();
        Button btnAceptar = (Button) dialogAbandonar.findViewById(R.id.btn_si); btnAceptar.setText("Aceptar");
        Button btnCancelar = (Button) dialogAbandonar.findViewById(R.id.btn_no); btnCancelar.setText("Cancelar");
        ((TextView) dialogAbandonar.findViewById(R.id.tw_texto)).setText("Está seguro que desea abandonar el partido?"+"\n"+"Si lo hace, ningún dato sobre este partido será registrado.");

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PartidoScoreActivity.this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                dialogAbandonar.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAbandonar.dismiss();
            }
        });
    }

}
