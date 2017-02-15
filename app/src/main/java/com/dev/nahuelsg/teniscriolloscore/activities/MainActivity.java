package com.dev.nahuelsg.teniscriolloscore.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private Gson gson;
    private ArrayList<Resultado> listaResultadosViejos;
    private ListView listView;
    private ResultadoAdapter resAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button nuevoPartido, limpiarLista;
        nuevoPartido = (Button) findViewById(R.id.button_nuevo_partido);
        nuevoPartido.setOnClickListener(btnNuevoPartidoListener);
        limpiarLista = (Button) findViewById(R.id.button_otracosa);
        limpiarLista.setOnClickListener(btnLimpiarListaListener);

        poblarListaResultados(this);
        if(listView != null) listView.setFocusable(false); //"parche" para la incompatibilidad visual entre listview con nestedscrollview

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        menu.setGroupVisible(0,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_estadisticas) {
            /** TODO intent a activity_estadisticas */
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener btnNuevoPartidoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this,NuevoPartidoActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener btnLimpiarListaListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("listaPartidosTerminados", "").apply();
            listView = (ListView) findViewById(R.id.listView_resultados_anteriores);
            resAdapter = new ResultadoAdapter(MainActivity.this, new ArrayList<Resultado>());
            resAdapter.notifyDataSetChanged();
            listView.setAdapter(resAdapter);
        }
    };

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Presione ATRAS otra vez para salir", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 3000);
        } else {
            super.onBackPressed();
            return;
        }
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
            //si la lista es muy larga, sólo muestro los 9 partidos más recientes
            while(listaResultadosViejos.size()>9){
                listaResultadosViejos.remove(listaResultadosViejos.size()-1);
            }

            listView = (ListView) findViewById(R.id.listView_resultados_anteriores);
            resAdapter = new ResultadoAdapter(this, listaResultadosViejos);
            listView.setAdapter(resAdapter);
        }
    }

}
