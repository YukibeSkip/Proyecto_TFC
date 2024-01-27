package com.multimedia.proyecto_tfc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdpatadorLibros extends
        RecyclerView.Adapter<AdpatadorLibros.MyViewHolder>
        implements View.OnClickListener {

    private List<Libro> listaDeLibros;
    private View.OnClickListener listener;

    //Getters y Setters
    public List<Libro> getListaDeLibros() {
        return listaDeLibros;
    }

    public void setListaDeLibros(List<Libro> listaDeLibros) {
        this.listaDeLibros = listaDeLibros;
    }

    public AdpatadorLibros(List<Libro> listaDeLibros){
        this.listaDeLibros = listaDeLibros;
    }

    @NonNull
    @Override
    public AdpatadorLibros.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        View filaLibro = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_libros, viewGroup, false);

        filaLibro.setOnClickListener(this);

        return new MyViewHolder(filaLibro);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpatadorLibros.MyViewHolder myViewHolder, int i){
        Libro libro = listaDeLibros.get(i);
        //Obtenemos los datos de la lista
        String numero_libro = libro.getNumero_libro();
        String autor = libro.getAutor();
        String titulo = libro.getTitulo();
        String estado = libro.getEstado();
        String valoracion = libro.getValoracion();

        //Y poner a los TextView los datos con setText
        myViewHolder.textView_Numero.setText(numero_libro);
        myViewHolder.textView_Autor.setText(autor);
        myViewHolder.textView_TituloL.setText(titulo);
        myViewHolder.textView_Estado.setText(estado);
        myViewHolder.textView_Valo.setText(valoracion);

    }

    @Override
    public int getItemCount(){ return listaDeLibros.size();}

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_Numero, textView_Autor, textView_TituloL, textView_Estado, textView_Valo;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.textView_Numero = (TextView) itemView.findViewById(R.id.textView_NumeroP);
            this.textView_Autor = (TextView) itemView.findViewById(R.id.textView_Director);
            this.textView_TituloL = (TextView) itemView.findViewById(R.id.textView_TituloP);
            this.textView_Estado = (TextView) itemView.findViewById(R.id.textView_EstadoP);
            this.textView_Valo = (TextView) itemView.findViewById(R.id.textView_ValoP);
        }
    }
}
