package com.example.example_btl_androidnc.students.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.example_btl_androidnc.MainActivity;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.TokenRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseInstanceIdService extends FirebaseMessagingService {

    String TAG="FCM";
    private MySharedPreferences mySharedPreferences;
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.v("FCM", "Token: " + token);
        mySharedPreferences = new MySharedPreferences(this);
        mySharedPreferences.saveToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d(TAG, "From: " + message.getFrom());
        if (message.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + message.getData());
        }
        if (message.getNotification() != null) {
            Log.v("FCM", "Notification Title: " + message.getNotification().getTitle());
                Log.v("FCM", "Notification Body: " + message.getNotification().getBody());
            // hiển thị thông báo trên thanh thông báo của thiết bị
            showNotification(message.getNotification().getTitle(), message.getNotification().getBody());
        }
    }



    private void sendCustomNotification() {
        // Cài âm thanh thông báo
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.messaging);

        // Tạo NotificationCompat.Builder và cấu hình thuộc tính
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.logo_app)
                .setContentTitle("Custom Notification Title")
                .setContentText("Custom Notification Message")
                .setAutoCancel(true)
                .setSound(sound);
    }
    private void showNotification(String title, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        // Thêm icon vào thông báo
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo_app);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.logo_app)
                .setLargeIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }


}
