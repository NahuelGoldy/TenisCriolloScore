package com.dev.nahuelsg.teniscriolloscore;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by Nahuel SG on 01/02/2017.
 */

public class PartidoScoreActivity extends AppCompatActivity {
    public String jugador1, jugador2;
    public int setsJug1=0, setsJug2=0;
    public int maxSets, maxPuntos, puntActualJugador1=0, puntActualJugador2=0, totalPuntosJugador1=0, totalPuntosJugador2=0;
    public int set1Jug1=0, set2Jug1=0, set3Jug1=0, set4Jug1=0, set5Jug1=0;
    public int set1Jug2=0, set2Jug2=0, set3Jug2=0, set4Jug2=0, set5Jug2=0;
    public boolean sacaJugador1=false, sacaJugador2=false, flagFalta=false, partidoTerminado=false;
    public int puntosSacandoJugador1=0, puntosSacandoJugador2=0;
    public int primerSaqueMetidoJugador1=0, primerSaqueMetidoJugador2=0;
    public int acesJugador1=0, acesJugador2=0;
    public int dobleFaltasJugador1=0, dobleFaltasJugador2=0;
    public int puntosGanadosPrimerSaqueJugador1=0, puntosGanadosPrimerSaqueJugador2=0;
    public int puntosGanadosSegundoSaqueJugador1=0, puntosGanadosSegundoSaqueJugador2=0;
    public int puntosGanadosDevolucionJugador1=0, puntosGanadosDevolucionJugador2=0;
    public int subidasRedJugador1=0, subidasRedJugador2=0;
    public int ptosGanadosRedJugador1=0, ptosGanadosRedJugador2=0;
    public int winnersDerechaJugador1=0, winnersDerechaJugador2=0;
    public int winnersRevesJugador1=0,winnersRevesJugador2=0;
    public int errorNoForzadoDerechaJugador1=0, errorNoForzadoDerechaJugador2=0;
    public int errorNoForzadoRevesJugador1=0, errorNoForzadoRevesJugador2=0;

    public TextView tvJugador1, tvJugador2, tvScoreJugador1, tvScoreJugador2, tvGuionScore;
    public ImageView iconoPelotaJug1, iconoPelotaJug2;
    public Button btnEsquina, btnLabelSet1, btnLabelSet2, btnLabelSet3, btnLabelSet4, btnLabelSet5;
    public Button btnJug1, btnSet1Jug1, btnSet2Jug1, btnSet3Jug1, btnSet4Jug1, btnSet5Jug1;
    public Button btnJug2, btnSet1Jug2, btnSet2Jug2, btnSet3Jug2, btnSet4Jug2, btnSet5Jug2;
    public TabHost tabhost;
    public Button btnAce, btnFalta, btnSaqueGanador, btnEnJuego;
    public ToggleButton enLaRedJug1, enLaRedJug2;
    public Button winnerDerechaJug1, winnerDerechaJug2, winnerRevesJug1, winnerRevesJug2;
    public Button errorDerechaJug1,errorDerechaJug2, errorRevesJug1, errorRevesJug2;
    public Button puntoGenericoJug1, puntoGenericoJug2;

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

/*
        //Tab1 y Tab2
        btnAce = (Button) findViewById(R.id.button_ace);
        btnFalta = (Button) findViewById(R.id.button_falta);
        btnWinnerDevolucion = (Button) findViewById(R.id.button_devol_ganadora);
        btnErrorDevolucion = (Button) findViewById(R.id.button_devol_error);
        btnBolaEnJuego = (Button) findViewById(R.id.button_bola_en_juego);

        //Tab3
        enLaRedJug1 = (ToggleButton) findViewById(R.id.red_jugador1);
        enLaRedJug2 = (ToggleButton) findViewById(R.id.red_jugador2);
        winnerDerechaJug1 = (Button) findViewById(R.id.winner_derecha_jugador1);
        winnerDerechaJug2 = (Button) findViewById(R.id.winner_derecha_jugador2);
        winnerRevesJug1 = (Button) findViewById(R.id.winner_reves_jugador1);
        winnerRevesJug2 = (Button) findViewById(R.id.winner_reves_jugador2);
        errorDerechaJug1 = (Button) findViewById(R.id.error_derecha_jugador1);
        errorDerechaJug2 = (Button) findViewById(R.id.error_derecha_jugador2);
        errorRevesJug1 = (Button) findViewById(R.id.error_reves_jugador1);
        errorRevesJug2 = (Button) findViewById(R.id.error_reves_jugador2);
        puntoGenericoJug1 = (Button) findViewById(R.id.button_ptoganado_jugador1);
        puntoGenericoJug2 = (Button) findViewById(R.id.button_ptoganado_jugador2);
*/
        intent = getIntent();
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

        tvJugador1.setText(jugador1);
        tvJugador2.setText(jugador2);
        tvScoreJugador1.setText("0"); tvScoreJugador2.setText("0");
        if(jugador1.length()>3) btnJug1.setText(jugador1.substring(0,4)); else btnJug1.setText(jugador1);
        if(jugador2.length()>3) btnJug2.setText(jugador2.substring(0,4)); else btnJug2.setText(jugador2);
        btnSet1Jug1.setText("-"); btnSet1Jug2.setText("-");
        btnSet2Jug1.setText("-"); btnSet2Jug2.setText("-");
        btnSet3Jug1.setText("-"); btnSet3Jug2.setText("-");
        btnSet4Jug1.setText("-"); btnSet4Jug2.setText("-");
        btnSet5Jug1.setText("-"); btnSet5Jug2.setText("-");

        tabhost.getTabWidget().getChildTabViewAt(1).setEnabled(false);

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
                    System.out.println("--------Entro por el primer saque--------");
                    accionBotonEnJuegoPrimerSaque();
                }
                else{
                    System.out.println("--------Entro por el segundo saque--------");
                    accionBotonEnJuegoSegundoSaque();
                    btnFalta.setText("Falta");
                }
                //cambiarTab(0,1);
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

        if (id == R.id.action_estadisticas) {
            if(tabhost.getCurrentTab()==0){
                Intent i = new Intent(PartidoScoreActivity.this,EstadisticasActivity.class);
                i = putExtrasParaEstadisticas(i);
                //TODO startactivityforresult?
                startActivity(i);
            }
            else{
                Toast.makeText(PartidoScoreActivity.this, "No puede ver las estadisticas durante un punto",
                        Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void accionBotonAcePrimerSaque(){
        if(sacaJugador1){
            puntActualJugador1++;
            puntosGanadosPrimerSaqueJugador1++;
            totalPuntosJugador1++;
            puntosSacandoJugador1++;
            acesJugador1++;
            primerSaqueMetidoJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            puntActualJugador2++;
            puntosGanadosPrimerSaqueJugador2++;
            totalPuntosJugador2++;
            puntosSacandoJugador2++;
            acesJugador2++;
            primerSaqueMetidoJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void accionBotonFaltaPrimerSaque(){
        flagFalta = true;
        if(sacaJugador1){
            puntosSacandoJugador1++;
        }
        else{
            puntosSacandoJugador2++;
        }
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("2do saque");

    }

    public void accionBotonSaqueGanadorPrimerSaque(){
        if(sacaJugador1){
            puntActualJugador1++;
            puntosGanadosPrimerSaqueJugador1++;
            totalPuntosJugador1++;
            puntosSacandoJugador1++;
            primerSaqueMetidoJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            puntActualJugador2++;
            puntosGanadosPrimerSaqueJugador2++;
            totalPuntosJugador2++;
            puntosSacandoJugador2++;
            primerSaqueMetidoJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void accionBotonEnJuegoPrimerSaque(){
        /** TODO acciones de juego... */

        cambiarTab(1,0);
    }

    public void accionBotonAceSegundoSaque(){
        flagFalta=false;
        if(sacaJugador1){
            puntActualJugador1++;
            puntosGanadosSegundoSaqueJugador1++;
            totalPuntosJugador1++;
            acesJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            puntActualJugador2++;
            puntosGanadosSegundoSaqueJugador2++;
            totalPuntosJugador2++;
            acesJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)%4==0){
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
            dobleFaltasJugador1++;
            puntActualJugador2++;
            totalPuntosJugador2++;
            puntosGanadosDevolucionJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            dobleFaltasJugador2++;
            puntActualJugador1++;
            totalPuntosJugador1++;
            puntosGanadosDevolucionJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)%4==0){
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
            puntActualJugador1++;
            puntosGanadosSegundoSaqueJugador1++;
            totalPuntosJugador1++;
            tvScoreJugador1.setText(String.valueOf(puntActualJugador1));
            chequearEsFinSetJugador1();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=false; sacaJugador2=true;
                iconoPelotaJug1.setVisibility(View.INVISIBLE);
                iconoPelotaJug2.setVisibility(View.VISIBLE);
            }
        }
        else{
            puntActualJugador2++;
            puntosGanadosSegundoSaqueJugador2++;
            totalPuntosJugador2++;
            tvScoreJugador2.setText(String.valueOf(puntActualJugador2));
            chequearEsFinSetJugador2();
            if((puntActualJugador1+puntActualJugador2)%4==0){
                sacaJugador1=true; sacaJugador2=false;
                iconoPelotaJug1.setVisibility(View.VISIBLE);
                iconoPelotaJug2.setVisibility(View.INVISIBLE);
            }
        }
        ((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
    }

    public void accionBotonEnJuegoSegundoSaque(){
        /** TODO acciones de juego... */

        cambiarTab(1,0);
        flagFalta=false;

        //((TextView)tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title)).setText("1er saque");
    }

    public void chequearEsFinSetJugador1(){
        if(puntActualJugador1 == maxPuntos){
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
            if(setsJug1 >= (maxSets/2)) {
                partidoTerminado=true;
                /** TODO terminar partido */
            }
        }
    }

    public void chequearEsFinSetJugador2(){
        if(puntActualJugador2 == maxPuntos){
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
            if(setsJug1 >= (maxSets/2)) {
                partidoTerminado=true;
                /** TODO terminar partido */
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
        return i;
    }

}
