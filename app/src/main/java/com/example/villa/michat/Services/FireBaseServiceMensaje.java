package com.example.villa.michat.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.LocalBroadcastManager;

import com.example.villa.michat.Mesajes.Mensajeria;
import com.example.villa.michat.Preferences;
import com.example.villa.michat.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URL;
import java.util.Random;

/**
 * Created by Villa on 16/12/2017.
 */

public class FireBaseServiceMensaje extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String mensaje = remoteMessage.getData().get("mensaje");
        String hora = remoteMessage.getData().get("hora");
        String cabezera = remoteMessage.getData().get("cabezera");
        String cuerpo = remoteMessage.getData().get("cuerpo");
        String receptor = remoteMessage.getData().get("receptor");
        String emisorPHP = remoteMessage.getData().get("emisor");
        String emisor = Preferences.getString(this,Preferences.PREFERENCE_USUARIO);
        if(emisor.equals(receptor)){
            Mensaje(mensaje,hora,emisorPHP);
            showNotificacion(cabezera,cuerpo);
        }
    }

    public boolean equals(Object obj){ return (getApplication().getClass() == obj);}

    private void Mensaje(String mensaje,String hora,String emisor){
        Intent intent = new Intent(Mensajeria.MENSAJE);
        intent.putExtra("key_mensaje",mensaje);
        intent.putExtra("key_hora",hora);
        intent.putExtra("key_emisor_PHP",emisor);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    private void showNotificacion(String cabezera, String cuerpo){
        Intent intent = new Intent(this,Mensajeria.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri soundNotificacion = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Builder builder = new Builder(this);
        builder.setAutoCancel(true);
        builder.setContentTitle(cabezera);
        builder.setContentText(cuerpo);
        builder.setSound(soundNotificacion);
        builder.setSmallIcon(R.drawable.chat);
        builder.setTicker(cuerpo);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Random random = new Random();

        notificationManager.notify(random.nextInt(),builder.build());
    }
}
