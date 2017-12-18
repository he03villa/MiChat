package com.example.villa.michat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.example.villa.michat.Mesajes.Mensajeria;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Villa on 17/12/2017.
 */

public class Registro extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasena;
    private EditText nombre;
    private EditText apellido;
    private EditText dia;
    private EditText mes;
    private EditText ano;
    private EditText correo;
    private EditText telefono;
    private RadioButton hombre;
    private RadioButton mujer;
    private Button registrar;

    private RequestQueue mRequest;
    private VolleyRP volleyRP;

    private static final String url = "http://he03villa.000webhostapp.com/chat/Controlador/DatosPersonales/Insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = (EditText) findViewById(R.id.txtusuario);
        contrasena = (EditText) findViewById(R.id.txtcontraseña);
        nombre = (EditText) findViewById(R.id.txtnombre);
        apellido = (EditText) findViewById(R.id.txtapellido);
        dia = (EditText) findViewById(R.id.txtdia);
        mes = (EditText) findViewById(R.id.txtmes);
        ano = (EditText) findViewById(R.id.txtaño);
        correo = (EditText) findViewById(R.id.txtcorreo);
        telefono = (EditText) findViewById(R.id.txttelefono);
        hombre = (RadioButton) findViewById(R.id.RDhombre);
        mujer = (RadioButton) findViewById(R.id.RDmujer);
        registrar = (Button) findViewById(R.id.btnRegistar);

        volleyRP = VolleyRP.getInstance(this);
        mRequest = volleyRP.getRequestQueue();

        hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mujer.setChecked(false);
            }
        });

        mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hombre.setChecked(false);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genero = "";
                if(hombre.isChecked()) genero="hombre";
                else if(mujer.isChecked()) genero="mujer";
                if(!getStringET(usuario).isEmpty() && !getStringET(contrasena).isEmpty() && !getStringET(nombre).isEmpty() && !getStringET(apellido).isEmpty() && !getStringET(dia).isEmpty() && !getStringET(mes).isEmpty() && !getStringET(ano).isEmpty() && !getStringET(correo).isEmpty() && !getStringET(telefono).isEmpty() && !genero.isEmpty()){
                    String usua = validarCadena(getStringET(usuario));
                    String pass = validarCadena(getStringET(contrasena));
                    String name = validarCadena(getStringET(nombre));
                    String last = validarCadena(getStringET(apellido));
                    String fecha = getStringET(dia)+"/"+getStringET(mes)+"/"+getStringET(ano);
                    String email = validarCadena(getStringET(correo));
                    String phone = validarCadena(getStringET(telefono));
                    Insertar(usua,pass,name,last,fecha,email,phone,genero);
                }else Toast.makeText(Registro.this," El Genero es ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getStringET(EditText e){
        return e.getText().toString();
    }

    private String validarCadena(String cadena){
        for (int i=0;i<cadena.length();i++) if(!(""+cadena.charAt(i)).equalsIgnoreCase(" "))return  cadena.substring(i,cadena.length());
        return "";
    }

    private void Insertar(String user,String pass,String name,String last,String fecha,String email,String phone,String genero){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id",user);
        hashMap.put("nombre",pass);
        hashMap.put("apellido",name);
        hashMap.put("fechanacimiento",last);
        hashMap.put("correo",fecha);
        hashMap.put("telefono",email);
        hashMap.put("genero",phone);
        hashMap.put("pass",genero);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(hashMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String res = response.getString("resultado");
                    if(res.equalsIgnoreCase("error")) Toast.makeText(Registro.this,"El usuario ya existe",Toast.LENGTH_LONG).show();
                    else{
                        Toast.makeText(Registro.this,res,Toast.LENGTH_LONG).show();
                        finish();
                    }
                } catch (JSONException e) {Toast.makeText(Registro.this,"El usuario no se pudo guardar",Toast.LENGTH_LONG).show();}
                /*Intent intent = new Intent(Registro.this,Mensajeria.class);
                intent.putExtra("key_emisor",USER);
                startActivity(intent);*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registro.this,"El usuario no se pudo guardar",Toast.LENGTH_LONG).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volleyRP);
    }
}
