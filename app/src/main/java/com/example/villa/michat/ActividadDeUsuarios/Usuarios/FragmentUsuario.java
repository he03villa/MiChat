package com.example.villa.michat.ActividadDeUsuarios.Usuarios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villa.michat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Villa on 08/01/2018.
 */

public class FragmentUsuario extends Fragment{

    private List<UsuarioBuscadorAtributo> atributoList;
    private RecyclerView rv;
    private UsuarioBuscadorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar_usuarios,container,false);
        atributoList = new ArrayList<>();
        rv = (RecyclerView) v.findViewById(R.id.rvUsuarios);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        adapter = new UsuarioBuscadorAdapter(atributoList,getContext());
        rv.setAdapter(adapter);
        return v;
    }

    public void insertarUsuario(int fotoPerfil, String nombre,String estado){
        UsuarioBuscadorAtributo buscadorAtributo = new UsuarioBuscadorAtributo();
        buscadorAtributo.setFotoPerfil(fotoPerfil);
        buscadorAtributo.setNombre(nombre);
        buscadorAtributo.setEstado(estado);
        atributoList.add(buscadorAtributo);
        adapter.notifyDataSetChanged();
    }
}
