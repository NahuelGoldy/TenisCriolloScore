package com.dev.nahuelsg.teniscriolloscore.activities;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dev.nahuelsg.teniscriolloscore.R;
import com.dev.nahuelsg.teniscriolloscore.modelo.PartidoStats;
import com.dev.nahuelsg.teniscriolloscore.modelo.Resultado;
import com.dev.nahuelsg.teniscriolloscore.utils.ResultadoAdapter;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Nahuel SG on 20/02/2017.
 */

public class ListarPartidosTerminadosActivity extends AppCompatActivity {
    private Gson gson;
    private ArrayList<Resultado> listaResultadosViejos;
    private ListView listView;
    private ResultadoAdapter resAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_partidos_terminados);
        listView = (ListView) findViewById(R.id.listView_partidos_anteriores);

        poblarListaResultados(this);
    }

    private void poblarListaResultados(Context context){
        gson = new Gson();
        String jsonResultadosPartidosViejos = PreferenceManager.getDefaultSharedPreferences(context).getString("listaPartidosTerminados", "");
        listaResultadosViejos = new ArrayList<>();
        Type type = new TypeToken<List<Resultado>>() {}.getType();
        listaResultadosViejos = gson.fromJson(jsonResultadosPartidosViejos, type);

        if(listaResultadosViejos!=null && listaResultadosViejos.size()>0){
            //ordeno la lista por fecha
            Collections.sort(listaResultadosViejos, new Comparator<Resultado>() {
                public int compare(Resultado resultado1, Resultado resultado2) {
                    return resultado2.getFecha().compareTo(resultado1.getFecha());
                }
            });

            listView = (ListView) findViewById(R.id.listView_partidos_anteriores);
            resAdapter = new ResultadoAdapter(this, listaResultadosViejos, obtenerListaPartidosStatsDesdeJson(ListarPartidosTerminadosActivity.this));
            listView.setAdapter(resAdapter);
        }
    }

    private ArrayList<PartidoStats> obtenerListaPartidosStatsDesdeJson(Context context){
        Gson gson = new Gson();
        String jsonPartidosStatsViejos = PreferenceManager.getDefaultSharedPreferences(context).getString("listaPartidosStatsTerminados", "");
        ArrayList<PartidoStats> listaPartidosStatsViejos = new ArrayList<>();
        Type type = new TypeToken<List<PartidoStats>>() {}.getType();
        listaPartidosStatsViejos = gson.fromJson(jsonPartidosStatsViejos, type);

        if(listaPartidosStatsViejos!=null && listaPartidosStatsViejos.size()>0){
            //ordeno la lista por fecha
            Collections.sort(listaPartidosStatsViejos, new Comparator<PartidoStats>() {
                public int compare(PartidoStats p1, PartidoStats p2) {
                    return p2.getFecha().compareTo(p1.getFecha());
                }
            });
        }

        return listaPartidosStatsViejos;
    }

}
