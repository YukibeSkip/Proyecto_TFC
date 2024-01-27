package com.multimedia.proyecto_tfc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptadorSeries extends RecyclerView.Adapter<AdaptadorSeries.MyViewHolder>
        implements View.OnClickListener{

    private List<Serie> listaDeSeries;
    private View.OnClickListener listener;

    public AdaptadorSeries(List<Serie> listaDeSeries){
        this.listaDeSeries = listaDeSeries;
    }

    //Getters y Setters


    public List<Serie> getListaDeSeries() {
        return listaDeSeries;
    }

    public void setListaDeSeries(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public AdaptadorSeries.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View filaSerie = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_series, viewGroup, false);

        filaSerie.setOnClickListener(this);

        return new MyViewHolder(filaSerie);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorSeries.MyViewHolder myViewHolder, int i) {
        Serie serie = listaDeSeries.get(i);

        //Obtenemos los datos de la lista
        String numero_serie = serie.getNumero_series();
        String director = serie.getDirector();
        String nombre = serie.getNombre();
        String estado = serie.getEstado();
        String valoracion = serie.getValoracion();

        //Y poner a los TextsView los datos con setText
        myViewHolder.textView_NumeroS.setText(numero_serie);
        myViewHolder.textView_DirectorS.setText(director);
        myViewHolder.textView_NombreS.setText(nombre);
        myViewHolder.textView_EstadoS.setText(estado);
        myViewHolder.textView_ValoracionS.setText(valoracion);
    }

    @Override
    public int getItemCount() {
        return listaDeSeries.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView_NumeroS, textView_NombreS, textView_DirectorS, textView_EstadoS, textView_ValoracionS;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.textView_NumeroS = (TextView) itemView.findViewById(R.id.textView_NumeroS);
            this.textView_DirectorS = (TextView) itemView.findViewById(R.id.textView_DirectorS);
            this.textView_NombreS = (TextView) itemView.findViewById(R.id.textView_NombreS);
            this.textView_EstadoS = (TextView) itemView.findViewById(R.id.textView_EstadoS);
            this.textView_ValoracionS = (TextView) itemView.findViewById(R.id.textView_ValoracionS);
        }
    }
}
