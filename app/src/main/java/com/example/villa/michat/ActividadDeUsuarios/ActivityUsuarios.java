package com.example.villa.michat.ActividadDeUsuarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.villa.michat.Login;
import com.example.villa.michat.Preferences;
import com.example.villa.michat.R;

/**
 * Created by Villa on 26/12/2017.
 */

public class ActivityUsuarios extends AppCompatActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mensajeria");
        setContentView(R.layout.activity_usuario);

        tableLayout = (TabLayout) findViewById(R.id.tabLayoutUsuario);
        viewPager = (ViewPager) findViewById(R.id.viewPageUsuario);
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new AdpterUsuario(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){} //setTitle("Login");
                else{
                    if(position == 1){} //setTitle("Registro");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_amigos,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.CerrarSesionMenu){
            Preferences.saveBoolean(ActivityUsuarios.this,false,Preferences.STRING_ESTADO);
            Intent intent = new Intent(ActivityUsuarios.this,Login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
