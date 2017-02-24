package com.dev.nahuelsg.teniscriolloscore.activities;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.dev.nahuelsg.teniscriolloscore.R;
import com.dev.nahuelsg.teniscriolloscore.modelo.PartidoStats;
import com.dev.nahuelsg.teniscriolloscore.utils.PartidoStatsAdapter;
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
    private ArrayList<PartidoStats> listaPartidosViejos;
    private ListView listView;
    private PartidoStatsAdapter partAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_partidos_terminados);
        listView = (ListView) findViewById(R.id.listView_partidos_anteriores);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        poblarListaResultados(this);
    }

    private void poblarListaResultados(Context context){
        listaPartidosViejos = obtenerListaPartidosStatsDesdeJson(this);
        partAdapter = new PartidoStatsAdapter(this, listaPartidosViejos);
        listView.setAdapter(partAdapter);
    }

    private ArrayList<PartidoStats> obtenerListaPartidosStatsDesdeJson(Context context){
        gson = new Gson();
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
