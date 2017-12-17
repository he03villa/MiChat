package com.example.villa.michat.Mesajes;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.villa.michat.R;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.*;

/**
 * Created by Villa on 15/12/2017.
 */

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MemsajeViewHolder> {

    private List<MensajeDeTexto> mensajeDeTextos;
    private Context context;

    public MensajeAdapter(List<MensajeDeTexto> mensajeDeTextos,Context context) {
        this.mensajeDeTextos = mensajeDeTextos;
        this.context=context;
    }

    @Override
    public MemsajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mensajes,parent,false);
        return new MemsajeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MemsajeViewHolder holder, int position) {

        RelativeLayout.LayoutParams rl =(RelativeLayout.LayoutParams) holder.cardView.getLayoutParams();
        FrameLayout.LayoutParams fl = (FrameLayout.LayoutParams) holder.mensajeBG.getLayoutParams();
        LinearLayout.LayoutParams llMensaje = (LinearLayout.LayoutParams) holder.TvMensaje.getLayoutParams();
        LinearLayout.LayoutParams llHora = (LinearLayout.LayoutParams) holder.TvHora.getLayoutParams();

        if(mensajeDeTextos.get(position).getTipoMensaje() == 1){//Emisor
            holder.mensajeBG.setBackgroundResource(R.drawable.in_message_bg);
            rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT,0);
            rl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            llMensaje.gravity =Gravity.RIGHT;
            llHora.gravity = Gravity.RIGHT;
            fl.gravity = Gravity.RIGHT;
            holder.TvMensaje.setGravity(Gravity.RIGHT);
        }else{
            if(mensajeDeTextos.get(position).getTipoMensaje() == 2){//Reseptor
                holder.mensajeBG.setBackgroundResource(R.drawable.out_message_bg);
                rl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,0);
                rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                llMensaje.gravity =Gravity.LEFT;
                llHora.gravity = Gravity.LEFT;
                fl.gravity = Gravity.LEFT;
                holder.TvMensaje.setGravity(Gravity.LEFT);
            }
        }

        holder.cardView.setLayoutParams(rl);
        holder.mensajeBG.setLayoutParams(fl);
        holder.TvMensaje.setLayoutParams(llMensaje);
        holder.TvHora.setLayoutParams(llHora);

        holder.TvMensaje.setText(mensajeDeTextos.get(position).getMensaje());
        holder.TvHora.setText(mensajeDeTextos.get(position).getHoraDelMensaje());
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) holder.cardView.getBackground().setAlpha(0);
        else holder.cardView.setBackgroundColor(ContextCompat.getColor(context, color.transparent));

    }

    @Override
    public int getItemCount() {
        return mensajeDeTextos.size();
    }

    static class MemsajeViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        LinearLayout mensajeBG;
        TextView TvMensaje;
        TextView TvHora;
        MemsajeViewHolder(View itemView){
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvMensajes);
            mensajeBG = (LinearLayout) itemView.findViewById(R.id.mesajeBG);
            TvMensaje = (TextView) itemView.findViewById(R.id.msText);
            TvHora = (TextView) itemView.findViewById(R.id.msHora);
        }
    }
}
