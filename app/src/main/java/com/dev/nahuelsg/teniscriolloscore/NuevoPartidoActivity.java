package com.dev.nahuelsg.teniscriolloscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Nahuel SG on 01/02/2017.
 */

public class NuevoPartidoActivity extends AppCompatActivity  {

    private String nombreJugador1;
    private String nombreJugador2;
    private String maxSets, maxPuntosPorSet;
    private String empiezaSaque;
    private String textoDescripcion;
    private TextView labelJug1, labelJug2, labelReglas, labelDescripcion, labelFecha, fecha, empiezaSacando;
    private EditText nombreJug1, nombreJug2;
    private Spinner listaPuntuaciones, listaEmpiezaSacando;
    private Button botonEmpezarPartido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_partido);

        labelJug1 = (TextView) findViewById(R.id.textView_jugador1);
        labelJug2 = (TextView) findViewById(R.id.textView_jugador2);
        labelReglas = (TextView) findViewById(R.id.textView_reglas_puntaje);
        labelDescripcion = (TextView) findViewById(R.id.textView_descripcion_puntaje);
        labelFecha = (TextView) findViewById(R.id.textView_fecha_label);
        fecha = (TextView) findViewById(R.id.textView_fecha);
        empiezaSacando = (TextView) findViewById(R.id.textView_empieza_sacando_label);

        nombreJug1 = (EditText) findViewById(R.id.editText_jugador1);
        nombreJug2 = (EditText) findViewById(R.id.editText_jugador2);

        listaPuntuaciones = (Spinner) findViewById(R.id.spinner_reglas_puntaje);
        listaPuntuaciones.setOnItemSelectedListener(comboListener);
        listaEmpiezaSacando = (Spinner) findViewById(R.id.spinner_empieza_sacando);

        botonEmpezarPartido = (Button) findViewById(R.id.button_empezar);
        botonEmpezarPartido.setOnClickListener(btnEmpezarPartidoListener);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
        fecha.setText(df.format(c.getTime()));

    }

    private View.OnClickListener btnEmpezarPartidoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nombreJugador1 = (nombreJug1).getText().toString();
            nombreJugador2 = (nombreJug2).getText().toString();
            if(nombreJugador1.equals("") || nombreJugador2.equals("")){
                Toast.makeText(NuevoPartidoActivity.this, "Debe ingresar AMBOS jugadores",
                        Toast.LENGTH_LONG).show();
            }
            else{

                empiezaSaque = (listaEmpiezaSacando).getSelectedItem().toString();
                Intent i = new Intent(NuevoPartidoActivity.this,PartidoScoreActivity.class);
                i.putExtra("Jugador1", nombreJugador1);
                i.putExtra("Jugador2", nombreJugador2);
                i.putExtra("AlSaque", empiezaSaque);
                i.putExtra("MaxSets", maxSets);
                i.putExtra("MaxPuntos", maxPuntosPorSet);
                startActivity(i);
            }
        }
    };


    private AdapterView.OnItemSelectedListener comboListener = new  AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            switch (pos) {
                case 0: {
                    maxSets = "1";
                    maxPuntosPorSet = "16";
                    textoDescripcion = "\n - Set único \n - A 16 puntos \n - A partir del 15-15, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
                    labelDescripcion.setText(textoDescripcion);
                    break;
                }
                case 1: {
                    maxSets = "3";
                    maxPuntosPorSet = "16";
                    textoDescripcion = "\n - Mejor de tres (3) sets \n - A 16 puntos \n - A partir del 15-15, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
                    labelDescripcion.setText(textoDescripcion);
                    break;
                }
                case 2: {
                    maxSets = "5";
                    maxPuntosPorSet = "16";
                    textoDescripcion = "\n - Mejor de cinco (5) sets \n - A 16 puntos \n - A partir del 15-15, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
                    labelDescripcion.setText(textoDescripcion);
                    break;
                }
                case 3: {
                    maxSets = "1";
                    maxPuntosPorSet = "20";
                    textoDescripcion = "\n - Set único \n - A 20 puntos \n - A partir del 19-19, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
                    labelDescripcion.setText(textoDescripcion);
                    break;
                }
                case 4: {
                    maxSets = "3";
                    maxPuntosPorSet = "20";
                    textoDescripcion = "\n - Mejor de tres (3) sets \n - A 20 puntos \n - A partir del 19-19, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
                    labelDescripcion.setText(textoDescripcion);
                    break;
                }
                case 5: {
                    maxSets = "5";
                    maxPuntosPorSet = "20";
                    textoDescripcion = "\n - Mejor de cinco (5) sets \n - A 20 puntos \n - A partir del 19-19, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
                    labelDescripcion.setText(textoDescripcion);
                    break;
                }
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            maxSets = "1";
            maxPuntosPorSet = "16";
            textoDescripcion = "\n - Set único \n - A 16 puntos \n - A partir del 15-15, se definirá por diferencia de dos (2) puntos \n - El turno de saque cambia de jugador cada cuatro (4) puntos";
            labelDescripcion.setText(textoDescripcion);
        }
    };
}
