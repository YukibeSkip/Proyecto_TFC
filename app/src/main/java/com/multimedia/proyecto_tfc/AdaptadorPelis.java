package com.multimedia.proyecto_tfc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPelis extends RecyclerView.Adapter<AdaptadorPelis.MyViewHolder>
        implements View.OnClickListener{

    private List<Peli> listaDepelis;
    private View.OnClickListener listener;

    public AdaptadorPelis(List<Peli> listaDepelis) {
        this.listaDepelis = listaDepelis;
    }

    //Getters y Setters
    public List<Peli> getLista_pelis() {
        return listaDepelis;
    }

    public void setLista_pelis(List<Peli> lista_pelis) {
        this.listaDepelis = lista_pelis;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public AdaptadorPelis.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View filaPeli = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_pelis, viewGroup, false);

       filaPeli.setOnClickListener(this);

       return new MyViewHolder(filaPeli);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPelis.MyViewHolder myViewHolder, int i) {
        Peli peli = listaDepelis.get(i);
        //Obtenemos los datos de lista
        String numero_peli = peli.getNumero_peli();
        String director = peli.getDirector();
        String nombre = peli.getNombre();
        String estado = peli.getEstado();
        String valoracion = peli.getValoracion();

        //Y poner a los TextView los datos con setText
        myViewHolder.textView_NumeroP.setText(numero_peli);
        myViewHolder.textView_Director.setText(director);
        myViewHolder.textView_TituloP.setText(nombre);
        myViewHolder.textView_EstadoP.setText(estado);
        myViewHolder.textView_ValoP.setText(valoracion);


    }

    @Override
    public int getItemCount() {
       return listaDepelis.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_NumeroP, textView_Director, textView_TituloP, textView_EstadoP, textView_ValoP;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.textView_NumeroP = (TextView) itemView.findViewById(R.id.textView_NumeroP);
            this.textView_Director = (TextView) itemView.findViewById(R.id.textView_Director);
            this.textView_TituloP = (TextView) itemView.findViewById(R.id.textView_TituloP);
            this.textView_EstadoP = (TextView) itemView.findViewById(R.id.textView_EstadoP);
            this.textView_ValoP = (TextView) itemView.findViewById(R.id.textView_ValoP);
        }
    }




    
}
