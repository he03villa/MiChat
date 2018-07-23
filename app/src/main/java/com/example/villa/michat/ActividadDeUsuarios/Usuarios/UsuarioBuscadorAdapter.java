package com.example.villa.michat.ActividadDeUsuarios.Usuarios;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villa.michat.R;

import java.util.List;

public class UsuarioBuscadorAdapter extends RecyclerView.Adapter<HolderUsuarioBuscador>{

    private List<UsuarioBuscadorAtributo> atributoList;
    private Context context;

    public UsuarioBuscadorAdapter(List<UsuarioBuscadorAtributo> atributoList, Context context) {
        this.atributoList = atributoList;
        this.context = context;
    }

    @Override
    public HolderUsuarioBuscador onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_buscar_usuarios,parent,false);
        return new HolderUsuarioBuscador(v);
    }

    @Override
    public void onBindViewHolder(HolderUsuarioBuscador holder, int position) {
        holder.getFotoPerfil().setImageResource(atributoList.get(position).getFotoPerfil());
        holder.getNameUsuario().setText(atributoList.get(position).getNombre());
        holder.getEstadoUsuario().setText(atributoList.get(position).getEstado());

    }

    @Override
    public int getItemCount() {
        return atributoList.size();
    }
}
