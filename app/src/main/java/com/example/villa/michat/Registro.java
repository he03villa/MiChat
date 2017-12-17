package com.example.villa.michat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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
                Toast.makeText(Registro.this,
                        "El usuario "+getStringET(usuario)+
                        " La contraseña "+getStringET(contrasena)+
                        " El nombre es "+getStringET(nombre)+
                        " El apellido es "+getStringET(apellido)+
                        " El dia es "+getStringET(dia)+
                        " El Mes es "+getStringET(mes)+
                        " El Año es "+getStringET(ano)+
                        " El Correo es "+getStringET(correo)+
                        " El Telefono es "+getStringET(telefono)+
                        " El Genero es "+genero,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getStringET(EditText e){
        return e.getText().toString();
    }
}
