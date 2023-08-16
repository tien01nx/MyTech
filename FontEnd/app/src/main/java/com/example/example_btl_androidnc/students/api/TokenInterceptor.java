package com.example.example_btl_androidnc.students.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class TokenInterceptor implements Interceptor {
    private String authToken;

    public TokenInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer " + authToken)
                .method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
