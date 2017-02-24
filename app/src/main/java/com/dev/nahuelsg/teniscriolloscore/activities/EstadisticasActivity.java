package com.dev.nahuelsg.teniscriolloscore.activities;

import android.content.Intent;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.dev.nahuelsg.teniscriolloscore.R;
import com.dev.nahuelsg.teniscriolloscore.modelo.PartidoStats;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nahuel SG on 02/02/2017.
 */

public class EstadisticasActivity extends AppCompatActivity {
    private float aux=0;
    private long tiempoInicio, tiempoFinal;
    private Intent intent;
    private String jugador1, jugador2;
    private int puntActualJugador1, puntActualJugador2, totalPuntosJugador1, totalPuntosJugador2;
    private int puntosSacandoJugador1, puntosSacandoJugador2;
    private int primerSaqueMetidoJugador1, primerSaqueMetidoJugador2;
    private int acesJugador1, acesJugador2;
    private int dobleFaltasJugador1, dobleFaltasJugador2;
    private int puntosGanadosPrimerSaqueJugador1, puntosGanadosPrimerSaqueJugador2;
    private int puntosGanadosSegundoSaqueJugador1, puntosGanadosSegundoSaqueJugador2;
    private int puntosGanadosDevolucionJugador1, puntosGanadosDevolucionJugador2;
    private int subidasRedJugador1, subidasRedJugador2;
    private int ptosGanadosRedJugador1, ptosGanadosRedJugador2;
    private int winnersDerechaJugador1, winnersDerechaJugador2;
    private int winnersRevesJugador1,winnersRevesJugador2;
    private int errorNoForzadoDerechaJugador1, errorNoForzadoDerechaJugador2;
    private int errorNoForzadoRevesJugador1, errorNoForzadoRevesJugador2;
    private boolean terminado=false, ver=false;
    private String duracionPartido;

    private Button btnAlign, btnSet1, btnSet2, btnSet3, btnSet4, btnSet5;
    private Button btnLabelJug1, btnSet1Jug1, btnSet2Jug1, btnSet3Jug1, btnSet4Jug1, btnSet5Jug1;
    private Button btnLabelJug2, btnSet1Jug2, btnSet2Jug2, btnSet3Jug2, btnSet4Jug2, btnSet5Jug2;

    private TextView labelJug1, labelJug2, scoreJug1, scoreJug2, labelGuionScore;
    private TextView labelEstadisticasServicio, labelEstadisticasGolpes, labelDuracionPartido, tiempoDeJuego;

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

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        //Nombre jugador 1
        labelJug1 = (TextView) findViewById(R.id.label_jugador1);
        jugador1 = intent.getStringExtra("Jug1");
        if(jugador1.length()<9) labelJug1.setText(jugador1);
        else labelJug1.setText(jugador1.substring(0,9));
        //Nombre jugador 2
        labelJug2 = (TextView) findViewById(R.id.label_jugador2);
        jugador2 = intent.getStringExtra("Jug2");
        if(jugador2.length()<9) labelJug2.setText(jugador2);
        else labelJug2.setText(jugador2.substring(0,9));
        //Score jugador 1
        scoreJug1 = (TextView) findViewById(R.id.score_jug1);
        puntActualJugador1 = intent.getIntExtra("PuntJug1", 0);
        scoreJug1.setText(String.valueOf(puntActualJugador1));
        //Score jugador 2
        scoreJug2 = (TextView) findViewById(R.id.score_jug2);
        puntActualJugador2 = intent.getIntExtra("PuntJug2", 0);
        scoreJug2.setText(String.valueOf(puntActualJugador2));
        labelGuionScore = (TextView) findViewById(R.id.textView5);
        totalPuntosJugador1 = intent.getIntExtra("TotalPtosJug1", 0);
        totalPuntosJugador2 = intent.getIntExtra("TotalPtosJug2", 0);

        labelDuracionPartido = (TextView) findViewById(R.id.tv_label_duracion_partido);
        labelDuracionPartido.setText("Duración del partido: ");
        tiempoDeJuego = (TextView) findViewById(R.id.tv_duracion_partido);
        duracionPartido = generarDuracionPartido();
        tiempoDeJuego.setText(duracionPartido);

        btnAlign = (Button) findViewById(R.id.button_align_estadisticas);
        btnSet1 = (Button) findViewById(R.id.label_set1_estadisticas);
        btnSet2 = (Button) findViewById(R.id.label_set2_estadisticas);
        btnSet3 = (Button) findViewById(R.id.label_set3_estadisticas);
        btnSet4 = (Button) findViewById(R.id.label_set4_estadisticas);
        btnSet5 = (Button) findViewById(R.id.label_set5_estadisticas);
        btnLabelJug1 = (Button) findViewById(R.id.label_jugador1_sets_estadisticas);
        if(jugador1.length()>3) btnLabelJug1.setText(jugador1.substring(0,4)); else btnLabelJug1.setText(jugador1);
        btnLabelJug2 = (Button) findViewById(R.id.label_jugador2_sets_estadisticas);
        if(jugador2.length()>3) btnLabelJug2.setText(jugador2.substring(0,4)); else btnLabelJug2.setText(jugador2);
        btnSet1Jug1 = (Button) findViewById(R.id.set1_jug1_estadisticas);
        btnSet1Jug1.setText(String.valueOf(intent.getIntExtra("Set1Jug1", 0)));
        btnSet1Jug2 = (Button) findViewById(R.id.set1_jug2_estadisticas);
        btnSet1Jug2.setText(String.valueOf(intent.getIntExtra("Set1Jug2", 0)));
        btnSet2Jug1 = (Button) findViewById(R.id.set2_jug1_estadisticas);
        btnSet2Jug1.setText(String.valueOf(intent.getIntExtra("Set2Jug1", 0)));
        btnSet2Jug2 = (Button) findViewById(R.id.set2_jug2_estadisticas);
        btnSet2Jug2.setText(String.valueOf(intent.getIntExtra("Set2Jug2", 0)));
        btnSet3Jug1 = (Button) findViewById(R.id.set3_jug1_estadisticas);
        btnSet3Jug1.setText(String.valueOf(intent.getIntExtra("Set3Jug1", 0)));
        btnSet3Jug2 = (Button) findViewById(R.id.set3_jug2_estadisticas);
        btnSet3Jug2.setText(String.valueOf(intent.getIntExtra("Set3Jug2", 0)));
        btnSet4Jug1 = (Button) findViewById(R.id.set4_jug1_estadisticas);
        btnSet4Jug1.setText(String.valueOf(intent.getIntExtra("Set4Jug1", 0)));
        btnSet4Jug2 = (Button) findViewById(R.id.set4_jug2_estadisticas);
        btnSet4Jug2.setText(String.valueOf(intent.getIntExtra("Set4Jug2", 0)));
        btnSet5Jug1 = (Button) findViewById(R.id.set5_jug1_estadisticas);
        btnSet5Jug1.setText(String.valueOf(intent.getIntExtra("Set5Jug1", 0)));
        btnSet5Jug2 = (Button) findViewById(R.id.set5_jug2_estadisticas);
        btnSet5Jug2.setText(String.valueOf(intent.getIntExtra("Set5Jug2", 0)));

        labelEstadisticasServicio = (TextView) findViewById(R.id.textView_titulo1);

        labelAces = (TextView) findViewById(R.id.label_aces);
        //Aces jugador 1
        acesJug1 = (TextView) findViewById(R.id.aces_jugador1);
        acesJugador1 = intent.getIntExtra("AcesJug1", 0);
        acesJug1.setText(String.valueOf(acesJugador1));
        //Aces jugador 2
        acesJug2 = (TextView) findViewById(R.id.aces_jugador2);
        acesJugador2 = intent.getIntExtra("AcesJug2", 0);
        acesJug2.setText(String.valueOf(acesJugador2));

        labelDobleFaltas = (TextView) findViewById(R.id.label_doblefaltas);
        //Doble faltas jugador 1
        dobleFaltasJug1 = (TextView) findViewById(R.id.doblefaltas_jugador1);
        dobleFaltasJugador1 = intent.getIntExtra("DobleFaltasJug1", 0);
        dobleFaltasJug1.setText(String.valueOf(dobleFaltasJugador1));
        //Doble faltas jugador 2
        dobleFaltasJug2 = (TextView) findViewById(R.id.doblefaltas_jugador2);
        dobleFaltasJugador2 = intent.getIntExtra("DobleFaltasJug2", 0);
        dobleFaltasJug2.setText(String.valueOf(dobleFaltasJugador2));

        labelEfectPrimerSaque = (TextView) findViewById(R.id.label_efec1er);
        //Efect primer saque jugador 1
        efectPrimerSaqueJug1 = (TextView) findViewById(R.id.efect1er_jugador1);
        puntosSacandoJugador1 = intent.getIntExtra("SaquesJug1", 0);
        primerSaqueMetidoJugador1 = intent.getIntExtra("PrimeroAdentroJug1", 0);
        if(puntosSacandoJugador1==0) aux=0;
        else aux = ((float)primerSaqueMetidoJugador1/puntosSacandoJugador1)*100;
        efectPrimerSaqueJug1.setText(String.valueOf((int)aux)+"%");
        //Efect primer saque jugador 2
        efectPrimerSaqueJug2 = (TextView) findViewById(R.id.efect1er_jugador2);
        puntosSacandoJugador2 = intent.getIntExtra("SaquesJug2", 0);
        primerSaqueMetidoJugador2 = intent.getIntExtra("PrimeroAdentroJug2", 0);
        if(puntosSacandoJugador2==0) aux=0;
        else aux = ((float)primerSaqueMetidoJugador2/puntosSacandoJugador2)*100;
        efectPrimerSaqueJug2.setText(String.valueOf((int)aux)+"%");

        labelPtosPrimerSaque = (TextView) findViewById(R.id.label_ptos1er);
        //Puntos ganados primer saque jugador 1
        ptosPrimerSaqueJug1 = (TextView) findViewById(R.id.ptos1er_jugador1);
        puntosGanadosPrimerSaqueJugador1 = intent.getIntExtra("GanadosPrimeroJug1", 0);
        if(primerSaqueMetidoJugador1==0) aux=0;
        else aux = ((float)puntosGanadosPrimerSaqueJugador1/primerSaqueMetidoJugador1)*100;
        ptosPrimerSaqueJug1.setText(String.valueOf((int)aux)+"%");
        //Puntos ganados primer saque jugador 2
        ptosPrimerSaqueJug2 = (TextView) findViewById(R.id.ptos1er_jugador2);
        puntosGanadosPrimerSaqueJugador2 = intent.getIntExtra("GanadosPrimeroJug2", 0);
        if(primerSaqueMetidoJugador2==0) aux=0;
        else aux = ((float)puntosGanadosPrimerSaqueJugador2/primerSaqueMetidoJugador2)*100;
        ptosPrimerSaqueJug2.setText(String.valueOf((int)aux)+"%");

        labelPtosSegundoSaque = (TextView) findViewById(R.id.label_ptos2do);
        //Puntos ganados segundo saque jugador 1
        ptosSegundoSaqueJug1 = (TextView) findViewById(R.id.ptos2do_jugador1);
        puntosGanadosSegundoSaqueJugador1 = intent.getIntExtra("GanadosSegundoJug1", 0);
        if((puntosSacandoJugador1-primerSaqueMetidoJugador1-dobleFaltasJugador1)==0) aux=0;
        else aux = ((float)puntosGanadosSegundoSaqueJugador1/(puntosSacandoJugador1-primerSaqueMetidoJugador1-dobleFaltasJugador1))*100;
        ptosSegundoSaqueJug1.setText(String.valueOf((int)aux)+"%");
        //Puntos ganados segundo saque jugador 2
        ptosSegundoSaqueJug2 = (TextView) findViewById(R.id.ptos2do_jugador2);
        puntosGanadosSegundoSaqueJugador2 = intent.getIntExtra("GanadosSegundoJug2", 0);
        if((puntosSacandoJugador2-primerSaqueMetidoJugador2-dobleFaltasJugador2)==0) aux=0;
        else aux = ((float)puntosGanadosSegundoSaqueJugador2/(puntosSacandoJugador2-primerSaqueMetidoJugador2-dobleFaltasJugador2))*100;
        ptosSegundoSaqueJug2.setText(String.valueOf((int)aux)+"%");

        labelPtosDevolucion = (TextView) findViewById(R.id.label_ptosdevol);
        //Puntos ganados devolucion jugador 1
        ptosDevolucionJug1 = (TextView) findViewById(R.id.ptosdevol_jugador1);
        puntosGanadosDevolucionJugador1 = intent.getIntExtra("GanadosDevolJug1", 0);
        if(puntosSacandoJugador2==0) aux=0;
        else aux=((float)puntosGanadosDevolucionJugador1/puntosSacandoJugador2)*100;
        ptosDevolucionJug1.setText(String.valueOf((int)aux)+"%");
        //Puntos ganados devolucion jugador 2
        ptosDevolucionJug2 = (TextView) findViewById(R.id.ptosdevol_jugador2);
        puntosGanadosDevolucionJugador2 = intent.getIntExtra("GanadosDevolJug2", 0);
        if(puntosSacandoJugador1==0) aux=0;
        else aux=((float)puntosGanadosDevolucionJugador2/puntosSacandoJugador1)*100;
        ptosDevolucionJug2.setText(String.valueOf((int)aux)+"%");

        labelEstadisticasGolpes = (TextView) findViewById(R.id.textView_titulo2);

        labelWinnersDerecha = (TextView) findViewById(R.id.label_winners_derecha);
        //winners derecha jugador 1
        winnersDerechaJug1 = (TextView) findViewById(R.id.winners_derecha_jugador1);
        winnersDerechaJugador1 = intent.getIntExtra("WinnersDerechaJug1", 0);
        winnersDerechaJug1.setText(String.valueOf(winnersDerechaJugador1));
        //winners derecha jugador 2
        winnersDerechaJug2 = (TextView) findViewById(R.id.winners_derecha_jugador2);
        winnersDerechaJugador2 = intent.getIntExtra("WinnersDerechaJug2", 0);
        winnersDerechaJug2.setText(String.valueOf(winnersDerechaJugador2));

        labelWinnersReves = (TextView) findViewById(R.id.label_winners_reves);
        //winners reves jugador 1
        winnersRevesJug1 = (TextView) findViewById(R.id.winners_reves_jugador1);
        winnersRevesJugador1 = intent.getIntExtra("WinnersRevesJug1", 0);
        winnersRevesJug1.setText(String.valueOf(winnersRevesJugador1));
        //winners reves jugador 2
        winnersRevesJug2 = (TextView) findViewById(R.id.winners_reves_jugador2);
        winnersRevesJugador2 = intent.getIntExtra("WinnersRevesJug2", 0);
        winnersRevesJug2.setText(String.valueOf(winnersRevesJugador2));

        labelWinners = (TextView) findViewById(R.id.label_winners);
        //winners jugador 1
        winnersJug1 = (TextView) findViewById(R.id.winners_jugador1);
        winnersJug1.setText(String.valueOf(winnersDerechaJugador1+winnersRevesJugador1));
        //winners jugador 1
        winnersJug2 = (TextView) findViewById(R.id.winners_jugador2);
        winnersJug2.setText(String.valueOf(winnersDerechaJugador2+winnersRevesJugador2));

        labelErroresNoForzadosDerecha = (TextView) findViewById(R.id.label_errores_derecha);
        //errores no forzados derecha jugador 1
        erroresNoForzadosDerechaJug1 = (TextView) findViewById(R.id.errores_noforz_derecha_jugador1);
        errorNoForzadoDerechaJugador1 = intent.getIntExtra("ErroresDerechaJug1", 0);
        erroresNoForzadosDerechaJug1.setText(String.valueOf(errorNoForzadoDerechaJugador1));
        //errores no forzados derecha jugador 2
        erroresNoForzadosDerechaJug2 = (TextView) findViewById(R.id.errores_noforz_derecha_jugador2);
        errorNoForzadoDerechaJugador2 = intent.getIntExtra("ErroresDerechaJug2", 0);
        erroresNoForzadosDerechaJug2.setText(String.valueOf(errorNoForzadoDerechaJugador2));

        labelErroresNoForzadosReves = (TextView) findViewById(R.id.label_errores_reves);
        //errores no forzados reves jugador 1
        erroresNoForzadosRevesJug1 = (TextView) findViewById(R.id.errores_noforz_reves_jugador1);
        errorNoForzadoRevesJugador1 = intent.getIntExtra("ErroresRevesJug1", 0);
        erroresNoForzadosRevesJug1.setText(String.valueOf(errorNoForzadoRevesJugador1));
        //errores no forzados reves jugador 2
        erroresNoForzadosRevesJug2 = (TextView) findViewById(R.id.errores_noforz_reves_jugador2);
        errorNoForzadoRevesJugador2 = intent.getIntExtra("ErroresRevesJug2", 0);
        erroresNoForzadosRevesJug2.setText(String.valueOf(errorNoForzadoRevesJugador2));

        labelErroresNoForzados = (TextView) findViewById(R.id.label_errores);
        //errores no forzados jugador 1
        erroresNoForzadosJug1 = (TextView) findViewById(R.id.errores_noforz_jugador1);
        erroresNoForzadosJug1.setText(String.valueOf(errorNoForzadoDerechaJugador1+errorNoForzadoRevesJugador1));
        //errores no forzados jugador 2
        erroresNoForzadosJug2 = (TextView) findViewById(R.id.errores_noforz_jugador2);
        erroresNoForzadosJug2.setText(String.valueOf(errorNoForzadoDerechaJugador2+errorNoForzadoRevesJugador2));

        labelEnLaRed = (TextView) findViewById(R.id.label_enlared);
        //en la red jugador 1
        enLaRedJug1 = (TextView) findViewById(R.id.enlared_jugador1);
        subidasRedJugador1 = intent.getIntExtra("EnLaRedJug1", 0);
        ptosGanadosRedJugador1 = intent.getIntExtra("GanadosEnLaRedJug1", 0);
        enLaRedJug1.setText(String.valueOf(ptosGanadosRedJugador1)+" / "+String.valueOf(subidasRedJugador1));
        //en la red jugador 2
        enLaRedJug2 = (TextView) findViewById(R.id.enlared_jugador2);
        subidasRedJugador2 = intent.getIntExtra("EnLaRedJug2", 0);
        ptosGanadosRedJugador2 = intent.getIntExtra("GanadosEnLaRedJug2", 0);
        enLaRedJug2.setText(String.valueOf(ptosGanadosRedJugador2)+" / "+String.valueOf(subidasRedJugador2));

        terminado = intent.getBooleanExtra("Terminado", false);
        ver = intent.getBooleanExtra("Ver", false);

        //Si el partido terminó, persistirlo en el json de Resultados (SharedPreferences)
        if(terminado && !ver){
            Gson gson = new Gson();
            List<PartidoStats> statsPartidosViejosList;
            String vencedor = intent.getStringExtra("Ganador");
            int cantSetsJugados = intent.getIntExtra("CantSets", 1);
            PartidoStats partidoStats = generarStats(vencedor, cantSetsJugados);

            //tratamiento para la lista de Stats
            //leer desde SharedPreferences -> el string guardado es un json
            String listaPartidosViejosStatsJson = PreferenceManager.getDefaultSharedPreferences(this).getString("listaPartidosStatsTerminados", "");
            if(listaPartidosViejosStatsJson.equals("")){
                statsPartidosViejosList = new ArrayList<PartidoStats>();
            }
            else{
                //obtener la lista de Resultados desde el Json
                Type type = new TypeToken<List<PartidoStats>>() {}.getType();
                statsPartidosViejosList = gson.fromJson(listaPartidosViejosStatsJson, type);
            }
            statsPartidosViejosList.add(partidoStats);
            listaPartidosViejosStatsJson = gson.toJson(statsPartidosViejosList);
            //volver a persistir en SharedPreferences la lista actualizada
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("listaPartidosStatsTerminados", listaPartidosViejosStatsJson).apply();
        }
    }

    @Override
    public void onBackPressed() {
        if (terminado) {
            boolean flagIntentListaPartidos = intent.getBooleanExtra("volver_lista_partidos", false);
            Intent in;
            if(!flagIntentListaPartidos){
                in = new Intent(EstadisticasActivity.this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
            else {
                super.onBackPressed();
            }
        }
        else {
            super.onBackPressed();
        }
    }

    private String generarFechaHoraPartido(){
        String fecha, hora;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
        fecha = df.format(c.getTime());
        df = new SimpleDateFormat("HH:mm");
        hora = df.format(c.getTime());
        return (fecha + " a las "+ hora);
    }

    private String generarDuracionPartido() {
        String duracion;

        tiempoInicio = intent.getLongExtra("TiempoInicio", System.currentTimeMillis());
        tiempoFinal = intent.getLongExtra("TiempoFinal", System.currentTimeMillis());
        long diffMs = tiempoFinal - tiempoInicio;
        long diffSec = diffMs / 1000;
        long min = diffSec / 60;
        long sec = diffSec % 60;
        long hs = min / 60;
        duracion = String.valueOf(hs)+"hs:"+String.valueOf(min)+"min:"+String.valueOf(sec)+"seg";

        return duracion;
    }

    private PartidoStats generarStats(String ganador, int cantSets){
        PartidoStats stats = new PartidoStats();
        stats.setJugador1(jugador1); stats.setJugador2(jugador2);
        stats.setTiempoDeJuego(generarDuracionPartido());
        stats.setFecha(generarFechaHoraPartido());
        stats.setTotalPuntosJug1(totalPuntosJugador1); stats.setTotalPuntosJug2(totalPuntosJugador2);
        stats.setAcesJug1(acesJugador1); stats.setAcesJug2(acesJugador2);
        stats.setDoblFaltasJug1(dobleFaltasJugador1); stats.setDoblFaltasJug2(dobleFaltasJugador1);
        stats.setPuntosSacandoJug1(puntosSacandoJugador1); stats.setPuntosSacandoJug2(puntosSacandoJugador2);
        stats.setPrimerosAdentroJug1(primerSaqueMetidoJugador1); stats.setPrimerosAdentroJug2(primerSaqueMetidoJugador2);
        stats.setGanadosPrimeroJug1(puntosGanadosPrimerSaqueJugador1); stats.setGanadosPrimeroJug2(puntosGanadosPrimerSaqueJugador2);
        stats.setGanadosSegundoJug1(puntosGanadosSegundoSaqueJugador1); stats.setGanadosSegundoJug2(puntosGanadosSegundoSaqueJugador2);
        stats.setGanadosDevolJug1(puntosGanadosDevolucionJugador1); stats.setGanadosDevolJug2(puntosGanadosDevolucionJugador2);
        stats.setWinnersDerechaJug1(winnersDerechaJugador1); stats.setWinnersDerechaJug2(winnersDerechaJugador2);
        stats.setWinnersRevesJug1(winnersRevesJugador1); stats.setWinnersRevesJug2(winnersRevesJugador2);
        stats.setErroresDerechaJug1(errorNoForzadoDerechaJugador1); stats.setErroresDerechaJug2(errorNoForzadoDerechaJugador2);
        stats.setErroresRevesJug1(errorNoForzadoRevesJugador1); stats.setErroresRevesJug2(errorNoForzadoRevesJugador2);
        stats.setSubidasJug1(subidasRedJugador1); stats.setSubidasJug2(subidasRedJugador2);
        stats.setGanadosRedJug1(ptosGanadosRedJugador1); stats.setGanadosRedJug2(ptosGanadosRedJugador2);
        stats.setCantSets(cantSets);
        stats.setSet1Jug1(Integer.parseInt(btnSet1Jug1.getText().toString())); stats.setSet1Jug2(Integer.parseInt(btnSet1Jug2.getText().toString()));
        stats.setSet2Jug1(Integer.parseInt(btnSet2Jug1.getText().toString())); stats.setSet2Jug2(Integer.parseInt(btnSet2Jug2.getText().toString()));
        stats.setSet3Jug1(Integer.parseInt(btnSet3Jug1.getText().toString())); stats.setSet3Jug2(Integer.parseInt(btnSet3Jug2.getText().toString()));
        stats.setSet4Jug1(Integer.parseInt(btnSet4Jug1.getText().toString())); stats.setSet4Jug2(Integer.parseInt(btnSet4Jug2.getText().toString()));
        stats.setSet5Jug1(Integer.parseInt(btnSet5Jug1.getText().toString())); stats.setSet5Jug2(Integer.parseInt(btnSet5Jug2.getText().toString()));
        stats.setGanador(ganador);
        stats.setTiempoInicio(getIntent().getLongExtra("TiempoInicio", System.currentTimeMillis()));
        stats.setTiempoFinal(getIntent().getLongExtra("TiempoFinal", System.currentTimeMillis()));

        return stats;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
