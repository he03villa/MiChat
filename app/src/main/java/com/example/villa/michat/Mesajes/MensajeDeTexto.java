package com.example.villa.michat.Mesajes;

/**
 * Created by Villa on 15/12/2017.
 */

public class MensajeDeTexto {
    private String id;
    private String mensaje;
    private int tipoMensaje;
    private String HoraDelMensaje;

    public MensajeDeTexto(){}

    public MensajeDeTexto(String id, String mensaje, int tipoMensaje, String horaDelMensaje) {
        this.id = id;
        this.mensaje = mensaje;
        this.tipoMensaje = tipoMensaje;
        HoraDelMensaje = horaDelMensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getHoraDelMensaje() {
        return HoraDelMensaje;
    }

    public void setHoraDelMensaje(String horaDelMensaje) {
        HoraDelMensaje = horaDelMensaje;
    }
}
