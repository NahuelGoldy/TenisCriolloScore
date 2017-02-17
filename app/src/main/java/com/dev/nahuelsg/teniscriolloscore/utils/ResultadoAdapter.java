package com.dev.nahuelsg.teniscriolloscore.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dev.nahuelsg.teniscriolloscore.R;
import com.dev.nahuelsg.teniscriolloscore.activities.EstadisticasActivity;
import com.dev.nahuelsg.teniscriolloscore.modelo.PartidoStats;
import com.dev.nahuelsg.teniscriolloscore.modelo.Resultado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahuel SG on 10/02/2017.
 */

public class ResultadoAdapter extends BaseAdapter {
    private List<Resultado> listaResultado;
    private List<PartidoStats> listaPartidoStats;
    private LayoutInflater inflater;
    private View row;
    private TextView jugadores, marcador, fecha, tiempoDeJuego;
    private Button btnVer, btnCompartir;
    private Context mCont;

    public ResultadoAdapter(Context context, List<Resultado> listaResult, List<PartidoStats> listaPartStats) {
        super();
        this.listaResultado = new ArrayList<>();
        this.listaResultado.addAll(listaResult);
        if(listaPartStats!=null && listaPartStats.size()>0){
            this.listaPartidoStats = new ArrayList<>();
            listaPartidoStats.addAll(listaPartStats);
        }
        inflater = LayoutInflater.from(context);
        mCont = context;
    }

    @Override
    public int getCount() {
        return listaResultado.size();
    }

    @Override
    public Object getItem(int position) {
        return listaResultado.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        if(row == null){
            row = inflater.inflate(R.layout.row_resultados_anteriores,parent,false);
        }
        cargarVariables();
        llenarAdapter(position);
        return(row);
    }

    private void cargarVariables(){
        jugadores = (TextView) row.findViewById(R.id.textViewJugadores);
        marcador = (TextView) row.findViewById(R.id.textViewMarcador);
        fecha = (TextView) row.findViewById(R.id.textViewFechaPartido);
        tiempoDeJuego = (TextView) row.findViewById(R.id.textViewTiempoDeJuegoPartido);
        btnVer = (Button) row.findViewById(R.id.btnVerDetalles);
        btnCompartir = (Button) row.findViewById(R.id.btnCompartir);
    }

    private void llenarAdapter(final int position) {
        jugadores.setText(((Resultado)this.getItem(position)).getGanadorYPerdedor());
        marcador.setText(((Resultado)this.getItem(position)).getScore());
        fecha.setText(((Resultado)this.getItem(position)).getFecha());
        tiempoDeJuego.setText(((Resultado)this.getItem(position)).getTiempoDeJuego());

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idView = v.getId();
                if(idView == R.id.btnVerDetalles){
                    //TODO encontrar PartidoStats en la lista generada desde el json
                    Intent i = new Intent(mCont,EstadisticasActivity.class);
                    i = putExtrasParaEstadisticas(i, listaPartidoStats.get(position));
                    mCont.startActivity(i);
                }
            }
        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idView = v.getId();
                if(idView == R.id.btnCompartir){
                    //TODO ver como compartir esta instancia en aplicaciones externas
                    String shareBody = jugadores.getText().toString()+ "\n"
                            + "Marcador: "+marcador.getText().toString()+ "\n"
                            + "Duracion del partido: "+tiempoDeJuego.getText().toString()+ "\n"
                            + "Jugado el: "+fecha.getText().toString();
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Partido registrado en Tenis Criollo Score");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    mCont.startActivity(Intent.createChooser(sharingIntent, "Compartir resultado - TENIS CRIOLLO SCORE APP"));
                }
            }
        });
    }

    private Intent putExtrasParaEstadisticas(Intent i, PartidoStats ps) {

        i.putExtra("Jug1", ps.getJugador1()); i.putExtra("Jug2", ps.getJugador2());
        i.putExtra("TotalPtosJug1", ps.getTotalPuntosJug1()); i.putExtra("TotalPtosJug2", ps.getTotalPuntosJug2());
        i.putExtra("PuntJug1", 0); i.putExtra("PuntJug2", 0);
        i.putExtra("Set1Jug1", ps.getSet1Jug1()); i.putExtra("Set1Jug2", ps.getSet1Jug2());
        i.putExtra("Set2Jug1", ps.getSet2Jug1()); i.putExtra("Set2Jug2", ps.getSet2Jug2());
        i.putExtra("Set3Jug1", ps.getSet3Jug1()); i.putExtra("Set3Jug2", ps.getSet3Jug2());
        i.putExtra("Set4Jug1", ps.getSet4Jug1()); i.putExtra("Set4Jug2", ps.getSet4Jug2());
        i.putExtra("Set5Jug1", ps.getSet5Jug1()); i.putExtra("Set5Jug2", ps.getSet5Jug2());
        i.putExtra("AcesJug1", ps.getAcesJug1()); i.putExtra("AcesJug2", ps.getAcesJug2());
        i.putExtra("DobleFaltasJug1", ps.getDoblFaltasJug1()); i.putExtra("DobleFaltasJug2", ps.getDoblFaltasJug2());
        i.putExtra("SaquesJug1", ps.getPuntosSacandoJug1()); i.putExtra("SaquesJug2", ps.getPuntosSacandoJug2());
        i.putExtra("PrimeroAdentroJug1", ps.getPrimerosAdentroJug1()); i.putExtra("PrimeroAdentroJug2", ps.getPrimerosAdentroJug2());
        i.putExtra("GanadosPrimeroJug1", ps.getGanadosPrimeroJug1()); i.putExtra("GanadosPrimeroJug2", ps.getGanadosPrimeroJug2());
        i.putExtra("GanadosSegundoJug1", ps.getGanadosSegundoJug1()); i.putExtra("GanadosSegundoJug2", ps.getGanadosSegundoJug2());
        i.putExtra("GanadosDevolJug1", ps.getGanadosDevolJug1()); i.putExtra("GanadosDevolJug2", ps.getGanadosDevolJug2());
        i.putExtra("WinnersDerechaJug1", ps.getWinnersDerechaJug1()); i.putExtra("WinnersDerechaJug2", ps.getWinnersDerechaJug2());
        i.putExtra("WinnersRevesJug1", ps.getWinnersRevesJug1()); i.putExtra("WinnersRevesJug2", ps.getWinnersRevesJug2());
        i.putExtra("ErroresDerechaJug1", ps.getErroresDerechaJug1()); i.putExtra("ErroresDerechaJug2", ps.getErroresDerechaJug2());
        i.putExtra("ErroresRevesJug1", ps.getErroresRevesJug1()); i.putExtra("ErroresRevesJug2", ps.getErroresRevesJug2());
        i.putExtra("EnLaRedJug1", ps.getSubidasJug1()); i.putExtra("EnLaRedJug2", ps.getSubidasJug2());
        i.putExtra("GanadosEnLaRedJug1", ps.getGanadosRedJug1()); i.putExtra("GanadosEnLaRedJug2", ps.getGanadosRedJug2());
        i.putExtra("Terminado", true);
        i.putExtra("TiempoInicio", ps.getTiempoInicio());
        i.putExtra("TiempoFinal", ps.getTiempoFinal());
        i.putExtra("Ganador", ps.getGanador());
        i.putExtra("CantSets", ps.getCantSets());
        i.putExtra("Ver", true);

        return i;
    }
}
