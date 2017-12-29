package com.example.villa.michat.ActividadDeUsuarios;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.villa.michat.ActividadDeUsuarios.Amigo.FragmentAmigos;
import com.example.villa.michat.ActividadDeUsuarios.Solicitudes.FragmentSolicitudes;

/**
 * Created by Villa on 26/12/2017.
 */

public class AdpterUsuario extends FragmentPagerAdapter {

    public AdpterUsuario(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0) return new FragmentAmigos();
        else{
            if(position == 1) return new FragmentSolicitudes();
        }
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) return "Chat";
        else{
            if(position == 1) return "Solicitudes";
        }
        return null;
    }
}
