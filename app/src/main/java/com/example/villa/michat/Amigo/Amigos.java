package com.example.villa.michat.Amigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.villa.michat.Login;
import com.example.villa.michat.Mesajes.Mensajeria;
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

public class Amigos extends AppCompatActivity{

    private RecyclerView rv;
    private List<AmigosAtributos> atributolista;
    private AmigosAdapter adapter;

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private static final String URL = "http://he03villa.000webhostapp.com/chat/Controlador/Amigos/Lista.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigo);
        setTitle("Amigos");
        atributolista = new ArrayList<>();

        volleyRP = VolleyRP.getInstance(this);
        mRequest = volleyRP.getRequestQueue();

        rv = (RecyclerView)findViewById(R.id.rvAmigos);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        adapter = new AmigosAdapter(atributolista,this);
        rv.setAdapter(adapter);
        SolicitudJSON();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_amigos,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.CerrarSesionMenu){
            Preferences.saveBoolean(Amigos.this,false,Preferences.STRING_ESTADO);
            Intent intent = new Intent(Amigos.this,Login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
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
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String datos = response.getString("resultado");
                    JSONArray array = new JSONArray(datos);
                    for(int i = 0; i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        Toast.makeText(Amigos.this,"El usuario "+i+" es "+object.getString("id"),Toast.LENGTH_LONG).show();
                        agrgarAmigo(R.drawable.ic_account_circle,object.getString("nombre"),"mensaje "+i,"00:00",object.getString("id"));
                    }
                } catch (JSONException e) {
                    Toast.makeText(Amigos.this,"Error en la descompresion del json",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Amigos.this,"Error en la conexion",Toast.LENGTH_LONG).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volleyRP);
    }
}
