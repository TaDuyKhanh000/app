package com.medium.music;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.medium.music.prefs.DataStoreManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application {

    // Firebase url
    public static final String FIREBASE_URL = "https://musicmedium-3b572-default-rtdb.firebaseio.com";

    public static final String CHANNEL_ID = "channel_music_basic_id";
    private static final String CHANNEL_NAME = "channel_music_basic_name";
    private FirebaseDatabase mFirebaseDatabase;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance(FIREBASE_URL);
        createChannelNotification();
        DataStoreManager.init(getApplicationContext());
    }

    private void createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_MIN);
            channel.setSound(null, null);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public DatabaseReference getSongsDatabaseReference() {
        return mFirebaseDatabase.getReference("/songs");
    }

    public DatabaseReference getFeedbackDatabaseReference() {
        return mFirebaseDatabase.getReference("/feedback");
    }

    public DatabaseReference getCountViewDatabaseReference(long songId) {
        return FirebaseDatabase.getInstance().getReference("/songs/" + songId + "/count");
    }
}
