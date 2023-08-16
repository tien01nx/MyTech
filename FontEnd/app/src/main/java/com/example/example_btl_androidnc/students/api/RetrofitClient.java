package com.example.example_btl_androidnc.students.api;

import android.content.Context;

import com.example.example_btl_androidnc.students.network.AuthInterceptor;
import com.example.example_btl_androidnc.students.network.TokenAuthenticator;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://10.0.2.2:8082/";
    public static final String BASE_IMG = "http://10.0.2.2:8082";

//192.168.3.199
//192.168.0.102
//    10.0.2.2
    //172.20.10.13


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            retrofit = builder.client(httpClient.build()).build();
        }
        return retrofit;
    }


    public static Retrofit getClient(String authToken) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new TokenInterceptor(authToken));

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static Retrofit getInstance(Context context, String baseUrl, String authToken) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(authToken))
                .authenticator(new TokenAuthenticator(context))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
