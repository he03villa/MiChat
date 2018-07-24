package com.example.villa.michat.ActividadDeUsuarios.Solicitudes;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.villa.michat.ActividadDeUsuarios.ClasesComunicacion.Prueba;
import com.example.villa.michat.Preferences;
import com.example.villa.michat.R;
import com.example.villa.michat.VolleyRP;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private static final String URL = "https://he03villa.000webhostapp.com/chat/Controlador/Amigos/Lista.php?user=";

    private EventBus bus = EventBus.getDefault();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitud_amistad,container,false);

        volleyRP = volleyRP.getInstance(getContext());
        mRequest = volleyRP.getRequestQueue();

        listSolicitudes = new ArrayList<>();

        rv = (RecyclerView) v.findViewById(R.id.cardviewSolicitudes);
        ln = (LinearLayout) v.findViewById(R.id.layoutVacioSolicitudes);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);;

        adapter = new SolicitudesAdapter(listSolicitudes,getContext());
        rv.setAdapter(adapter);

        String usuario = Preferences.getString(getContext(),Preferences.PREFERENCE_USUARIO);
        SolicitudJSON(URL+usuario);
        verificarSolicitudes();

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

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Subscribe
    public void ejecutarLlmado(Prueba b){
        agregarTarjetasDeSolicitud(R.drawable.ic_account_circle,b.getNombre(),"00:00");
    }

    public void SolicitudJSON(String url){
        JsonObjectRequest solicitud = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String datos = response.getString("resultado");
                    JSONArray array = new JSONArray(datos);
                    for(int i = 0;  i < array.length(); i++){
                        JSONObject object = new JSONObject(array.getString(i));
                        agregarTarjetasDeSolicitud(R.drawable.ic_account_circle,object.getString("nombre")+" "+object.getString("apellido"),object.getString("fecha_amigos"));
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
