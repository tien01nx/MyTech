package com.example.example_btl_androidnc.students.fragment;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_URL;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.example_btl_androidnc.students.addItem.Edit_Profile;
import com.example.example_btl_androidnc.students.addItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.adapter.CourseAdapter;
import com.example.example_btl_androidnc.students.authentication.LoginActivity;
import com.example.example_btl_androidnc.students.authentication.SignUpActivity;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<Course> CourseList;
    Button Bt_dn ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        connectWebSocket();
        recyclerView = view.findViewById(R.id.recyclerview);
        Bt_dn = view.findViewById(R.id.bt_dn);
        CourseList = new ArrayList<>();
Bt_dn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), SignUpActivity.class);
        startActivity(intent);
        //finish();
    }
});
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);

        Call<List<Course>> call = getAPI_service.getCourse();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
//                Log.d("test",response.body().toString());
                if (response.code()!=200){
                    Log.d("test", "Response code: " + response.code());
                    Log.d("test", "Response message: " + response.message());


                }
                List<Course>movies=  response.body();
                for(Course movie: movies) CourseList.add(movie);
                Log.d("test","thêm dữ liệu thành công");
                PutDataIntoRecyclerView(CourseList);

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("test",t.toString() +" _______onfailue______");
            }

        });
//
        return view;
    }
    private void PutDataIntoRecyclerView(List<Course> movieList) {
        CourseAdapter adapter = new CourseAdapter(getContext(),movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }




    private void connectWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL+"my-websocket-endpoint")
       // ws://192.168.80.149:8082/my-websocket-endpoint

                .build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosed(webSocket, code, reason);
                Log.d("hihi: ", reason + "onClosed.1");
            }

            @Override
            public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosing(webSocket, code, reason);
                Log.d("hihi: ", reason + "onClosing.2");
            }

            @Override
            public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable okhttp3.Response response) {
                super.onFailure(webSocket, t, response);
//                Log.d("hihi: ", response.toString() + "onFailure.3");

            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                super.onMessage(webSocket, text);
                Log.d("hihi", text.toString() + "   _____onMessage.4_______");
                List<Course> courses= new Gson().fromJson(text, new TypeToken<List<Course>>(){}.getType());

               getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Hiển thị danh sách Category lên giao diện
                        PutDataIntoRecyclerView(courses);
                        System.out.println(courses.toString());
                    }
                });

            }


            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                Log.d("hihi: ", bytes.toString() + "onMessage.5");


            }

            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull okhttp3.Response response) {
                super.onOpen(webSocket, response);
                Log.d("hihi: ", response + "onOpen.5 ket noi thanh cong");
                webSocket.send("test");
                webSocket.send("subscribe:my-websocket-endpoint");
                webSocket.request();

            }
        });
    }
}
