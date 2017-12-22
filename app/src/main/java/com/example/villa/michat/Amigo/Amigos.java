package com.example.villa.michat.Amigo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.villa.michat.Mesajes.MensajeDeTexto;
import com.example.villa.michat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Villa on 19/12/2017.
 */

public class Amigos extends AppCompatActivity{

    private RecyclerView rv;
    private List<AmigosAtributos> atributolista;
    private AmigosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigo);
        setTitle("Amigos");

        rv = (RecyclerView)findViewById(R.id.rvAmigos);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        adapter = new AmigosAdapter(atributolista,this);
        rv.setAdapter(adapter);
        for (int i=0;i<10;i++) agrgarAmigo(R.drawable.ic_account_circle,"usuario "+i,"mensaje "+i,"00:00");
    }

    public void agrgarAmigo(int fotoDePerfil,String nombre,String mensaje,String hora){
        AmigosAtributos atributos = new AmigosAtributos();
        atributos.setFotoDePerfil(fotoDePerfil);
        atributos.setMensaje(mensaje);
        atributos.setNombre(nombre);
        atributos.setHora(hora);
        atributolista.add(atributos);
        adapter.notifyDataSetChanged();
    }
}
