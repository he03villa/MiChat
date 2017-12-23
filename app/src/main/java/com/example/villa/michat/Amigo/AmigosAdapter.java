package com.example.villa.michat.Amigo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.villa.michat.Mesajes.Mensajeria;
import com.example.villa.michat.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Villa on 19/12/2017.
 */

public class AmigosAdapter extends RecyclerView.Adapter<AmigosAdapter.HolderAmigos>{

    private List<AmigosAtributos> atributolista;
    private Context context;

    public AmigosAdapter(List<AmigosAtributos> lista,Context context){
        this.atributolista = lista;
        this.context = context;
    }

    @Override
    public HolderAmigos onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_amigos,parent,false);
        return new AmigosAdapter.HolderAmigos(v);
    }

    @Override
    public void onBindViewHolder(HolderAmigos holder, final int position) {
        holder.img.setImageResource(atributolista.get(position).getFotoDePerfil());
        holder.amigo.setText(atributolista.get(position).getNombre());
        holder.mensaje.setText(atributolista.get(position).getMensaje());;
        holder.hora.setText(atributolista.get(position).getHora());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Mensajeria.class);
                intent.putExtra("key_receptor",atributolista.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return atributolista.size();
    }

    static class HolderAmigos extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView img;
        TextView amigo;
        TextView mensaje;
        TextView hora;

        public HolderAmigos(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewAmigos);
            img = (ImageView) itemView.findViewById(R.id.imagen);
            amigo = (TextView) itemView.findViewById(R.id.txtnombreusuario);
            mensaje = (TextView) itemView.findViewById(R.id.txtmensaje_recivido);
            hora = (TextView) itemView.findViewById(R.id.txthora_enviado);
        }
    }
}
