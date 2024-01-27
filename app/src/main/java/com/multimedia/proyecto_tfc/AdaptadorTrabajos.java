package com.multimedia.proyecto_tfc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorTrabajos extends RecyclerView.Adapter<AdaptadorTrabajos.MyViewHolder>
    implements View.OnClickListener {

    private List<Trabaj> listaDeTrabajo;
    private View.OnClickListener listener;

    public AdaptadorTrabajos(List<Trabaj> listaDeTrabajo){
        this.listaDeTrabajo = listaDeTrabajo;
    }

    //Getters y Setters
    public List<Trabaj> getListaDeTrabajo() {
        return listaDeTrabajo;
    }

    public void setListaDeTrabajo(List<Trabaj> listaDeTrabajo) {
        this.listaDeTrabajo = listaDeTrabajo;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public AdaptadorTrabajos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View filaTrabajo = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_trabajo, viewGroup, false);

        filaTrabajo.setOnClickListener(this);

        return new MyViewHolder(filaTrabajo);

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTrabajos.MyViewHolder myViewHolder, int i) {
        Trabaj trabajo = listaDeTrabajo.get(i);

        //Obtenemos los datos de la lista
        String numero_trabajo = trabajo.getNumero_trabajo();
        String nombre = trabajo.getNombre();
        String estado = trabajo.getEstado();
        String fecha = trabajo.getFecha();

        //Y poner a los TextViews los datos con setText
        myViewHolder.textView_numeroTrabajo.setText(numero_trabajo);
        myViewHolder.textView_nombreTra.setText(nombre);
        myViewHolder.textView_EstadoTra.setText(estado);
        myViewHolder.textViewFecha.setText(fecha);
    }

    @Override
    public int getItemCount() {
        return listaDeTrabajo.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView_numeroTrabajo, textView_nombreTra, textView_EstadoTra, textViewFecha;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.textView_numeroTrabajo = (TextView) itemView.findViewById(R.id.textView_numeroTrabajo);
            this.textView_nombreTra = (TextView) itemView.findViewById(R.id.textView_nombreTra);
            this.textView_EstadoTra = (TextView) itemView.findViewById(R.id.textView_EstadoTra);
            this.textViewFecha = (TextView) itemView.findViewById(R.id.textViewFecha);
        }

    }
}
