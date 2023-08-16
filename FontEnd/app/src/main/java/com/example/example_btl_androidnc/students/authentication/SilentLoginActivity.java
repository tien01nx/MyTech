package com.example.example_btl_androidnc.students.authentication;


import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.addItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;

import java.util.Timer;
import java.util.TimerTask;

public class SilentLoginActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silent_login);
        mySharedPreferences =new MySharedPreferences(SilentLoginActivity.this);
        startApplication();
        createNotificationChannel();

    }

    private void startApplication() {
        Timer RunSplash = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SilentLoginActivity.this, mySharedPreferences.getName(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(SilentLoginActivity.this, "Chào mừng bạn trở lại !", Toast.LENGTH_SHORT).show();
                        if (mySharedPreferences.getName().isEmpty()){
                            startActivity(new Intent(SilentLoginActivity.this, LoginActivity.class));
                            finish();
                        }
                        else {
                            startActivity(new Intent(SilentLoginActivity.this, SetAdmin_Activity.class));
                            finish();
                        }


                    }
                });
            }
        };
        RunSplash.schedule(timerTask, SPLASH_TIME_OUT);
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Your Channel Name";
            String description = "Your Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);

            // Đăng ký kênh thông báo với hệ thống
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }






}