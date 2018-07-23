package com.example.villa.michat.ActividadDeUsuarios.Amigo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.villa.michat.Preferences;
import com.example.villa.michat.R;
import com.example.villa.michat.VolleyRP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Villa on 19/12/2017.
 */

public class FragmentAmigos extends android.support.v4.app.Fragment{

    private RecyclerView rv;
    private List<AmigosAtributos> atributolista;
    private AmigosAdapter adapter;

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private static final String URL = "https://he03villa.000webhostapp.com/chat/Controlador/usuario/ListaUsuario.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_amigo,container,false);
        volleyRP = volleyRP.getInstance(getContext());
        mRequest = volleyRP.getRequestQueue();

        atributolista = new ArrayList<>();

        rv = (RecyclerView)v.findViewById(R.id.rvAmigos);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        adapter = new AmigosAdapter(atributolista,getContext());
        rv.setAdapter(adapter);
        SolicitudJSON();
        return v;
    }


    public void agrgarAmigo(int fotoDePerfil, String nombre, String mensaje, String hora,String id){
        AmigosAtributos atributos = new AmigosAtributos();
        atributos.setFotoDePerfil(fotoDePerfil);
        atributos.setMensaje(mensaje);
        atributos.setNombre(nombre);
        atributos.setHora(hora);
        atributos.setId(id);
        atributolista.add(atributos);
        adapter.notifyDataSetChanged();
    }

    public void SolicitudJSON(){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String datos = response.getString("resultado");
                    JSONArray array = new JSONArray(datos);
                    String nuestroUsuario = Preferences.getString(getContext(),Preferences.PREFERENCE_USUARIO);
                    for(int i = 0; i<array.length();i++){
                        JSONObject js = array.getJSONObject(i);
                        if(!js.getString("id").equals(nuestroUsuario)) agrgarAmigo(R.drawable.ic_account_circle,js.getString("nombre"),"mensaje "+i,"00:00",js.getString("id"));
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Error en la descompresion del json",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error en la conexion",Toast.LENGTH_LONG).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,getContext(),volleyRP);
    }
}
