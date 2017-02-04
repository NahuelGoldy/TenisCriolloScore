package com.dev.nahuelsg.teniscriolloscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nahuel SG on 01/02/2017.
 */

public class NuevoPartidoActivity extends AppCompatActivity {

    private String nombreJugador1;
    private String nombreJugador2;
    private String reglasPuntajeSelected;
    private TextView labelJug1, labelJug2, labelReglas, labelDescripcion, labelFecha, fecha;
    private EditText nombreJug1, nombreJug2;
    private Spinner listaPuntuaciones;
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

        nombreJug1 = (EditText) findViewById(R.id.editText_jugador1);
        nombreJug2 = (EditText) findViewById(R.id.editText_jugador2);

        listaPuntuaciones = (Spinner) findViewById(R.id.spinner_reglas_puntaje);

        botonEmpezarPartido = (Button) findViewById(R.id.button_empezar);


    }

}
