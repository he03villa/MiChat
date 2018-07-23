package com.example.villa.michat.ActividadDeUsuarios.Usuarios;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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
 * Created by Villa on 08/01/2018.
 */

public class FragmentUsuario extends Fragment{

    private List<UsuarioBuscadorAtributo> atributoList;
    private List<UsuarioBuscadorAtributo> listAuxiliar;
    private RecyclerView rv;
    private UsuarioBuscadorAdapter adapter;
    private EditText search;

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private static final String URL = "https://he03villa.000webhostapp.com/chat/Controlador/usuario/ListaUsuario.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar_usuarios,container,false);

        volleyRP = volleyRP.getInstance(getContext());
        mRequest = volleyRP.getRequestQueue();

        atributoList = new ArrayList<>();
        listAuxiliar = new ArrayList<>();

        rv = (RecyclerView) v.findViewById(R.id.rvUsuarios);
        search = (EditText) v.findViewById(R.id.searchUsuarios);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        adapter = new UsuarioBuscadorAdapter(atributoList,getContext());
        rv.setAdapter(adapter);

        SolicitudJSON();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscador("" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    public void insertarUsuario(int fotoPerfil, String nombre,String estado,String id){
        UsuarioBuscadorAtributo buscadorAtributo = new UsuarioBuscadorAtributo();
        buscadorAtributo.setFotoPerfil(fotoPerfil);
        buscadorAtributo.setNombre(nombre);
        buscadorAtributo.setEstado(estado);
        buscadorAtributo.setId(id);
        atributoList.add(buscadorAtributo);
        listAuxiliar.add(buscadorAtributo);
        adapter.notifyDataSetChanged();
    }

    public void buscador(String texto){
        atributoList.clear();
        for (int i = 0;  i<listAuxiliar.size(); i++){
            if(listAuxiliar.get(i).getNombre().toLowerCase().contains(texto.toLowerCase())) atributoList.add(listAuxiliar.get(i));
            adapter.notifyDataSetChanged();
        }
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
                        if(!js.getString("id").equals(nuestroUsuario)) insertarUsuario(R.drawable.ic_account_circle,js.getString("nombre"),"no  amigo",js.getString("id"));
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
