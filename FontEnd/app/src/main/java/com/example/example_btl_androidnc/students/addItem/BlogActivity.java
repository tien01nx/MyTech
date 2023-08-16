package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.BlogAdapter;
import com.example.example_btl_androidnc.students.adapter.CourseAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.authentication.LoginActivity;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Blog;
import com.example.example_btl_androidnc.students.model.Course;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity {
    List<Blog> BlogList;
    RecyclerView recyclerview;
    TextView News_title,News_content;
    //Context context = getApplicationContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        News_title = findViewById(R.id.news_title);
        News_content = findViewById(R.id.news_content);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        BlogList = new ArrayList<>();
//        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);;
//        Call<List<Blog>> call  = getAPI_service.getBlogs();
//        call.enqueue(new Callback<List<Blog>>()  {
//
//            @Override
//            public void onResponse(Call<List<Blog>> call , Response<List<Blog>> response) {
//                if (response.isSuccessful()) {
//                    List<Blog> blogs = response.body();
//                    for (Blog blog : blogList) {
//                        String description = blog.getDescription();
//                        String title = blog.getTitle();
//                        News_content.setText(description);
//                        News_title.setText(title);
//                    }
//                }
//                else {
//                    Toast.makeText(BlogActivity.this, "Gà", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Blog>> call, Throwable t) {
//                Toast.makeText(BlogActivity.this, "Lỗi call", Toast.LENGTH_SHORT).show();
//
//            }
//
//        });

        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<Blog>> call = getAPI_service.getBlogs();
        call.enqueue(new Callback<List<Blog>>() {
            @Override
            public void onResponse(Call<List<Blog>> call, Response<List<Blog>> response) {
//                Log.d("test",response.body().toString());
                if (response.code()!=200){
                    Log.d("test", "Response code: " + response.code());
                    Log.d("test", "Response message: " + response.message());


                }
                List<Blog>movies=  response.body();
                for(Blog movie: movies) BlogList.add(movie);
                Log.d("test","thêm dữ liệu thành công");
                PutDataIntoRecyclerView(BlogList);
                Toast.makeText(BlogActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Blog>> call, Throwable t) {
                Log.d("test",t.toString() +" _______onfailue______");
                Toast.makeText(BlogActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();

            }

        });
//

    }
    private void PutDataIntoRecyclerView(List<Blog> movieList) {
        BlogAdapter adapter = new BlogAdapter(BlogActivity.this,movieList);
        recyclerview.setLayoutManager(new LinearLayoutManager(BlogActivity.this));
        recyclerview.setAdapter(adapter);
        //PutDataIntoRecyclerView(courses);
    }


}