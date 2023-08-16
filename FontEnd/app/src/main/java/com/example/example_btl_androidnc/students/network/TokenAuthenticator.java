package com.example.example_btl_androidnc.students.network;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.RefreshTokenRequest;
import com.example.example_btl_androidnc.students.model.Users;


import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Response;

public class TokenAuthenticator implements Authenticator {
    private Context context;

    public TokenAuthenticator(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public Request authenticate(@NonNull Route route, okhttp3.Response response) throws IOException {
        // Lấy token và refresh token từ SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", "");
        String refreshToken = sharedPreferences.getString("refreshToken", "");

        // Tạo instance của AuthService
        GetAPI_Service authService = RetrofitClient.getInstance(context, "https://your_base_url", authToken).create(GetAPI_Service.class);

        // Gửi yêu cầu làm mới token
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(refreshToken);

        Call<Users> call = authService.refreshToken(refreshTokenRequest);
        Response<Users> refreshResponse = call.execute();

        if (refreshResponse.isSuccessful()) {
            // Lưu token mới vào SharedPreferences
            Users jwtResponse = refreshResponse.body();
            if (jwtResponse != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("authToken", jwtResponse.getAccessToken());
                editor.putString("refreshToken", jwtResponse.getRefreshToken());
                editor.apply();

                // Thêm token mới vào request
                return response.request().newBuilder()
                        .header("Authorization", "Bearer " + jwtResponse.getAccessToken())
                        .build();
            }
        }

        // Nếu không thành công, người dùng sẽ được chuyển hướng về màn hình đăng nhập
        return null;
    }

    // Phương thức authenticate
}