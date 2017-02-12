package com.dev.nahuelsg.teniscriolloscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahuel SG on 10/02/2017.
 */

public class ResultadoAdapter extends BaseAdapter {
    private List<Resultado> listaResultado;
    private LayoutInflater inflater;
    private View row;
    private TextView jugadores, marcador, fecha, tiempoDeJuego;
    private Context mCont;

    public ResultadoAdapter(Context context, List<Resultado> listaResult)
    {
        super();
        this.listaResultado = new ArrayList<Resultado>();
        this.listaResultado.addAll(listaResult);
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
    }

    private void llenarAdapter(final int position) {
        jugadores.setText(((Resultado)this.getItem(position)).getGanadorYPerdedor());
        marcador.setText(((Resultado)this.getItem(position)).getScore());
        fecha.setText(((Resultado)this.getItem(position)).getFecha());
        tiempoDeJuego.setText(((Resultado)this.getItem(position)).getTiempoDeJuego());
    }
}
