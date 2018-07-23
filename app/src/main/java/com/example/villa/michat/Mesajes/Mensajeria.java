package com.example.villa.michat.Mesajes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.villa.michat.Login;
import com.example.villa.michat.Preferences;
import com.example.villa.michat.R;
import com.example.villa.michat.Services.FireBaseId;
import com.example.villa.michat.VolleyRP;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Villa on 15/12/2017.
 */

public class Mensajeria extends AppCompatActivity{

    public static final String MENSAJE = "MENSAJE";

    private BroadcastReceiver bR;

    private RecyclerView rv;
    private Button btnenviar;
    private EditText txtmensaje;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajeAdapter adapter;

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private String MENSAJE_ENVIAR="";
    private String EMISOR = "";
    private String RECEPTOR;

    private static final String IP_MENSAJE = "http://he03villa.000webhostapp.com/chat/Controlador/Enviar_Mensajes.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        mensajeDeTextos = new ArrayList<>();

        volleyRP = volleyRP.getInstance(this);
        mRequest = volleyRP.getRequestQueue();

        EMISOR = Preferences.getString(this,Preferences.PREFERENCE_USUARIO);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            RECEPTOR = bundle.getString("hey_receptor");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnenviar = (Button) findViewById(R.id.btnenviar);
        txtmensaje = (EditText) findViewById(R.id.txtmensaje);

        rv = (RecyclerView) findViewById(R.id.rvMensaje);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);//Mensajeria exclusivamente
        rv.setLayoutManager(lm);

        adapter = new MensajeAdapter(mensajeDeTextos,this);
        rv.setAdapter(adapter);

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje =txtmensaje.getText().toString().trim();//   hola
                if(!mensaje.isEmpty() && !RECEPTOR.isEmpty()){
                    MENSAJE_ENVIAR=mensaje;
                    MandarMendaje();
                    CreateMendaje(mensaje,"00:00",1);
                    txtmensaje.setText("");
                }
                //if(TOKEN!=null) Toast.makeText(Mensajeria.this,TOKEN,Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setScrollbarChat();

        bR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String mensaje = intent.getStringExtra("key_mensaje");
                String hora = intent.getStringExtra("key_hora");
                String horaParametro[] = hora.split("\\,");
                String emisor = intent.getStringExtra("key_emisor_PHP");
                if(emisor.equals(RECEPTOR)) CreateMendaje(mensaje,horaParametro[0],2);
            }
        };
    }

    public void MandarMendaje(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("emisor",EMISOR);
        hashMap.put("receptor",RECEPTOR);
        hashMap.put("mensaje",MENSAJE_ENVIAR);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,IP_MENSAJE, new JSONObject(hashMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(Mensajeria.this,response.getString("resultado"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {}
                Intent intent = new Intent(Mensajeria.this,Mensajeria.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Mensajeria.this,"Ocurrio un error",Toast.LENGTH_LONG).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volleyRP);
    }

    public void CreateMendaje(String mensaje,String hora,int tipo_mensaje){
        MensajeDeTexto mensajeDeTexto = new MensajeDeTexto();
        mensajeDeTexto.setId("0");
        mensajeDeTexto.setMensaje(mensaje);
        mensajeDeTexto.setTipoMensaje(tipo_mensaje);
        mensajeDeTexto.setHoraDelMensaje(hora);
        mensajeDeTextos.add(mensajeDeTexto);
        adapter.notifyDataSetChanged();
        txtmensaje.setText("");
        setScrollbarChat();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bR,new IntentFilter(MENSAJE));
    }

    public void setScrollbarChat(){
        rv.scrollToPosition(adapter.getItemCount() - 1);
    }
}
