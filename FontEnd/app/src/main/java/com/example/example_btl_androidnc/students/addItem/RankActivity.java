package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;
import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.databinding.ActivityRankBinding;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.students.model.Rank;
import com.example.example_btl_androidnc.students.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankActivity extends AppCompatActivity {
    private Users users;
    ActivityRankBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        binding = ActivityRankBinding.inflate(getLayoutInflater());
         users = (Users) getIntent().getSerializableExtra("users");
        String courseId = getIntent().getStringExtra("courseId");

        setContentView(binding.getRoot());
        getData();
        getDataDiem(courseId,users.getId());
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String midtermGradeString = binding.midtermGrades.getText().toString();
                String finalGradeString = binding.finalGrades.getText().toString();
                String examGradeString = binding.exams.getText().toString();

                float midtermGrade = midtermGradeString.isEmpty() ? 0 : Float.parseFloat(midtermGradeString);
                float finalGrade = finalGradeString.isEmpty() ? 0 : Float.parseFloat(finalGradeString);
                float examGrade = examGradeString.isEmpty() ? 0 : Float.parseFloat(examGradeString);

                Rank rank = new Rank(midtermGrade, finalGrade, examGrade);
                createGrades(courseId, users.getId(), rank);
            }
        });


    }

    private void getData() {
        if (users != null) {
            setTextIfExists(binding.name,users.getName());
            setTextIfExists(binding.email, users.getEmail());
            setTextIfExists(binding.gender, users.getGender());
            setTextIfExists(binding.dateOfBirth,convertDateFormat(users.getDateOfBirth()));
            setTextIfExists(binding.address,users.getAddress());
            setTextIfExists(binding.phone,users.getPhone());

            loadImageIfExists(binding.image, users.getImage());
        }
    }

    private void setTextIfExists(TextView textView, String text) {
        if (text != null) {
            textView.setText(text);
        }
    }



    private void loadImageIfExists(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {
            Glide.with(imageView.getContext())
                    .load(BASE_IMG + imageUrl)
                    .into(imageView);
        }
    }

    private void createGrades(String  courseId, String userId, Rank req) {
        GetAPI_Service apiService = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<Void> call = apiService.createGrades(courseId, userId, req);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Grades updated successfully

                    Toast.makeText(getApplicationContext(), "Grades updated successfully", Toast.LENGTH_SHORT).show();
                    binding.midtermGrades.clearFocus();
                    binding.finalGrades.clearFocus();
                    binding.exams.clearFocus();
                } else {
                    // Handle error case
                    Toast.makeText(getApplicationContext(), "Error updating grades", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network failure or any other error
                Toast.makeText(getApplicationContext(), "Failed to update grades", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getDataDiem(String courseId,String userId){
        GetAPI_Service apiService = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<Rank> call = apiService.getGradesByUserIdAndCourseId(courseId, userId);
        call.enqueue(new Callback<Rank>() {
            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {
                if (response.isSuccessful()) {
                    Rank rankReq = response.body();
                    // Use rankReq to update your UI or process the data
                    binding.midtermGrades.setText(String.valueOf(rankReq.getMidtermGrades()));
                    binding.finalGrades.setText(String.valueOf(rankReq.getFinalGrades()));
                    binding.exams.setText(String.valueOf(rankReq.getExams()));
                } else {
                    // Handle error case
                }
            }

            @Override
            public void onFailure(Call<Rank> call, Throwable t) {
                // Handle network failure or other errors
            }
        });
    }





}