package com.dev.nahuelsg.teniscriolloscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        listaEmpiezaSacando = (Spinner) findViewById(R.id.spinner_empieza_sacando);

        botonEmpezarPartido = (Button) findViewById(R.id.button_empezar);
        botonEmpezarPartido.setOnClickListener(btnEmpezarPartidoListener);

    }

    private View.OnClickListener btnEmpezarPartidoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(NuevoPartidoActivity.this,PartidoScoreActivity.class);
            startActivity(i);
        }
    };

}
