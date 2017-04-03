package com.apokommata.apokommata;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseService.class.getSimpleName();

    private FirebaseDatabase database;
    private DatabaseReference callRef;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        database = FirebaseDatabase.getInstance();
        callRef = database.getReference("call").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        callRef.child(remoteMessage.getData().get("room")).child("time").setValue(remoteMessage.getData().get("time"));
        Log.e(TAG,"message"+remoteMessage.getData());
        sendNotification(remoteMessage.getData());
    }

    private void sendNotification(Map<String,String> messages) {

    }
}
