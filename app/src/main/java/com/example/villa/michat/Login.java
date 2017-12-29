package com.example.villa.michat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.villa.michat.ActividadDeUsuarios.ActivityUsuarios;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    private Button ingresar;
    private Button registro;
    private RadioButton secion;

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private String USER = "";
    private String PASS = "";

    private static final String url = "http://he03villa.000webhostapp.com/chat/Controlador/usuario/Login.php?user=";
    private static final String IP_TOKEN = "http://he03villa.000webhostapp.com/chat/Controlador/token/InsertarandActualizar.php";

    private boolean isActivateRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Preferences.getBoolean(this,Preferences.STRING_ESTADO))iniciarActividadSiguiente();

        volleyRP = VolleyRP.getInstance(this);
        mRequest = volleyRP.getRequestQueue();

        user = (EditText) findViewById(R.id.txtuser);
        pass = (EditText) findViewById(R.id.txtpass);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        registro = (Button) findViewById(R.id.btnregistrar);
        secion = (RadioButton) findViewById(R.id.RDsecion);

        isActivateRadioButton = secion.isChecked();

        secion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActivateRadioButton) secion.setChecked(false);
                isActivateRadioButton = secion.isChecked();
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verificar(user.getText().toString(),pass.getText().toString());
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registro.class);
                startActivity(intent);
            }
        });

    }

    public void verificar(String user,String pass){
        USER = user;
        PASS = pass;
        SolicitudJSON(url+user);
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    VerificarLogin(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"Error en la conexion",Toast.LENGTH_LONG).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volleyRP);
    }

    public void VerificarLogin(JSONObject datos){
        try {
            String estado = datos.getString("resultado");
            if(estado.equals("CC")){
                JSONObject jsonObject = new JSONObject(datos.getString("datos"));
                String usuario = jsonObject.getString("usuario");
                String pass = jsonObject.getString("password");
                if(usuario.equals("null"))Toast.makeText(this,"El usuario no existe",Toast.LENGTH_LONG).show();
                else{
                    if(usuario.equals(USER) && pass.equals(PASS)){
                        String Token=FirebaseInstanceId.getInstance().getToken();
                        if(Token != null){
                            if((""+Token.charAt(0)).equalsIgnoreCase("{")){
                                JSONObject js = new JSONObject(Token); //SOLO SI LES APARECE {"token":"...."} o "aksjdlkasj"
                                String tokenrecortado = js.getString("token");
                                SubirToken(tokenrecortado);
                            }else SubirToken(Token);

                        }
                        else Toast.makeText(this,"El token es nulo",Toast.LENGTH_LONG).show();
                    }else Toast.makeText(this,"La contraseña es incorrecta",Toast.LENGTH_LONG).show();
                }
            }else Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void SubirToken(String token){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",USER);
        hashMap.put("token",token);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,IP_TOKEN, new JSONObject(hashMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Preferences.saveBoolean(Login.this,secion.isChecked(),Preferences.STRING_ESTADO);
                Preferences.saveString(Login.this,USER,Preferences.PREFERENCE_USUARIO);
                try {
                    Toast.makeText(Login.this,response.getString("resultado"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {}
                iniciarActividadSiguiente();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"El Token no se pudo guardar",Toast.LENGTH_LONG).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volleyRP);
    }

    public void iniciarActividadSiguiente(){
        Intent intent = new Intent(Login.this,ActivityUsuarios.class);
        startActivity(intent);
        finish();
    }
}
