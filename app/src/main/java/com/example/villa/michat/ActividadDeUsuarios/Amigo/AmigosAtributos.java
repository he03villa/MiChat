package com.example.villa.michat.ActividadDeUsuarios.Amigo;

/**
 * Created by Villa on 19/12/2017.
 */

public class AmigosAtributos {
    private int fotoDePerfil;
    private String nombre;
    private String mensaje;
    private String hora;
    private String id;

    public AmigosAtributos(){}

    public AmigosAtributos(int fotoDePerfil, String nombre, String mensaje, String hora) {
        this.fotoDePerfil = fotoDePerfil;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.hora = hora;
    }

    public int getFotoDePerfil() {
        return fotoDePerfil;
    }

    public void setFotoDePerfil(int fotoDePerfil) {
        this.fotoDePerfil = fotoDePerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
