package com.example.villa.michat.ActividadDeUsuarios.Usuarios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villa.michat.R;

/**
 * Created by Villa on 08/01/2018.
 */

public class FragmentUsuario extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar_usuarios,container,false);
        return v;
    }
}
