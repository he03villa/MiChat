package com.example.villa.michat.ActividadDeUsuarios.Usuarios;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.villa.michat.R;

public class HolderUsuarioBuscador extends RecyclerView.ViewHolder{

    private ImageView fotoPerfil;
    private TextView nameUsuario;
    private TextView estadoUsuario;
    private Button enviarSolicitud;

    public HolderUsuarioBuscador(View itemView) {
        super(itemView);
        fotoPerfil = (ImageView) itemView.findViewById(R.id.FotoDePerfilSolicitud);
        nameUsuario = (TextView) itemView.findViewById(R.id.txtnombresolicitud);
        estadoUsuario = (TextView) itemView.findViewById(R.id.estadoUsuario);
        enviarSolicitud = (Button) itemView.findViewById(R.id.btnenviarSolicitud);
    }

    public ImageView getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(ImageView fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public TextView getNameUsuario() {
        return nameUsuario;
    }

    public void setNameUsuario(TextView nameUsuario) {
        this.nameUsuario = nameUsuario;
    }

    public TextView getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(TextView estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Button getEnviarSolicitud() {
        return enviarSolicitud;
    }

    public void setEnviarSolicitud(Button enviarSolicitud) {
        this.enviarSolicitud = enviarSolicitud;
    }
}
