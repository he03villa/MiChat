package com.example.villa.michat.ActividadDeUsuarios.Solicitudes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.villa.michat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Villa on 28/12/2017.
 */

public class FragmentSolicitudes extends Fragment {

    private RecyclerView rv;
    private SolicitudesAdapter adapter;
    private List<Solicitudes> listSolicitudes;
    private LinearLayout ln;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitud_amistad,container,false);

        listSolicitudes = new ArrayList<>();

        rv = (RecyclerView) v.findViewById(R.id.cardviewSolicitudes);
        ln = (LinearLayout) v.findViewById(R.id.layoutVacioSolicitudes);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);;

        adapter = new SolicitudesAdapter(listSolicitudes,getContext());
        rv.setAdapter(adapter);

        for(int i = 0;i<10;i++){
            agregarTarjetasDeSolicitud(R.drawable.ic_account_circle,"Usuario "+i,"00:00");
        }

        return v;
    }

    public void verificarSolicitudes(){
        if(listSolicitudes.isEmpty()){
            ln.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }else{
            ln.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }

    public void agregarTarjetasDeSolicitud(int foto,String nombre,String hora){
        Solicitudes solicitudes = new Solicitudes();
        solicitudes.setFoto(foto);
        solicitudes.setNombre("Usuario "+nombre);
        solicitudes.setNombre(hora);
        listSolicitudes.add(solicitudes);
        actualizarTarjetas();
    }

    public void actualizarTarjetas(){
        adapter.notifyDataSetChanged();
        verificarSolicitudes();
    }
}
