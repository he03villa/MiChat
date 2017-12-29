package com.example.villa.michat.ActividadDeUsuarios.Solicitudes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.villa.michat.R;

import java.util.List;

/**
 * Created by Villa on 28/12/2017.
 */

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.solicitudesHolder> {

    private List<Solicitudes> listaSolicitudes;
    private Context context;

    public SolicitudesAdapter(List<Solicitudes> listaSolicitudes, Context context){
        this.listaSolicitudes = listaSolicitudes;
        this.context = context;
    }

    @Override
    public solicitudesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_solicitudes,parent,false);
        return new solicitudesHolder(v);
    }

    @Override
    public void onBindViewHolder(solicitudesHolder holder, int position) {
        holder.imageView.setImageResource(listaSolicitudes.get(position).getFoto());
        holder.nombre.setText(listaSolicitudes.get(position).getNombre());
        holder.hora.setText(listaSolicitudes.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return listaSolicitudes.size();
    }

    static class solicitudesHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nombre;
        TextView hora;
        Button aceptar;
        Button cancelar;
        public solicitudesHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.FotoDePerfilSolicitud);
            nombre = (TextView) itemView.findViewById(R.id.txtnombresolicitud);
            hora = (TextView) itemView.findViewById(R.id.txthora_solicitud);
            aceptar = (Button) itemView.findViewById(R.id.btnaceptarSolicitud);
            cancelar = (Button) itemView.findViewById(R.id.btnaceptarCancelar);
        }
    }
}
