package com.example.villa.michat.ActividadDeUsuarios.Solicitudes;

/**
 * Created by Villa on 28/12/2017.
 */

public class Solicitudes {
    private int foto;
    private String nombre;
    private String hora;

    public Solicitudes(){}

    public Solicitudes(int foto, String nombre, String hora) {
        this.foto = foto;
        this.nombre = nombre;
        this.hora = hora;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
