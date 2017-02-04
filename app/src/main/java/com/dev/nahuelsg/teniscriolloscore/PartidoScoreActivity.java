package com.dev.nahuelsg.teniscriolloscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Nahuel SG on 01/02/2017.
 */

public class PartidoScoreActivity extends AppCompatActivity {
    private String jugador1, jugador2;
    private int maxSets, maxPuntos, puntActualJugador1, puntActualJugador2, totalPuntosJugador1, totalPuntosJugador2;
    private int set1Jug1, set2Jug1, set3Jug1, set4Jug1, set5Jug1;
    private int set1Jug2, set2Jug2, set3Jug2, set4Jug2, set5Jug2;
    private boolean sacaJugador1, sacaJugador2;
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

    private TextView tvJugador1, tvJugador2, tvScoreJugador1, tvScoreJugador2, tvGuionScore;
    private ImageView iconoPelotaJug1, iconoPelotaJug2;
    private Button btnEsquina, btnLabelSet1, btnLabelSet2, btnLabelSet3, btnLabelSet4, btnLabelSet5;
    private Button btnJug1, btnSet1Jug1, btnSet2Jug1, btnSet3Jug1, btnSet4Jug1, btnSet5Jug1;
    private Button btnJug2, btnSet1Jug2, btnSet2Jug2, btnSet3Jug2, btnSet4Jug2, btnSet5Jug2;
    private TabHost tabhost;
    private Button btnAce, btnFalta, btnWinnerDevolucion, btnErrorDevolucion, btnBolaEnJuego;
    private ToggleButton enLaRedJug1, enLaRedJug2;
    private Button winnerDerechaJug1, winnerDerechaJug2, winnerRevesJug1, winnerRevesJug2;
    private Button errorDerechaJug1,errorDerechaJug2, errorRevesJug1, errorRevesJug2;
    private Button puntoGenericoJug1, puntoGenericoJug2;

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
        spec = tabhost.newTabSpec("2do saque");
        spec.setContent(R.id.seg_saque);
        spec.setIndicator("2do saque");
        tabhost.addTab(spec);

        //Tab 3
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
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_estadisticas) {
            /** TODO intent a Activity Estadisticas */
            Intent i = new Intent(PartidoScoreActivity.this,EstadisticasActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
