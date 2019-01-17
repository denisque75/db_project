package com.denis_telezhenko.universityhelper.core.push_service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.denis_telezhenko.universityhelper.R;
import com.denis_telezhenko.universityhelper.ui.main.view.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class StudentHelperFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "StudentHelperFirebaseMe";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: " + remoteMessage.getNotification().getBody());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "dadasd")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Yo bitch");

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
}
