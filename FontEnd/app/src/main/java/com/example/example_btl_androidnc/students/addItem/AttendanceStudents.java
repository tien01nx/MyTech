package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.AttendanceAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceStudents extends AppCompatActivity {
    RecyclerView recyclerView;
    AttendanceAdapter adapter;
    private List<Users> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_students);
        Intent intent = getIntent();
        String courseId = intent.getStringExtra("courseId");
        String scheduleId = intent.getStringExtra("scheduleId");


        // Tạo danh sách điểm danh
        CreateAttendance(scheduleId);
        // lấy danh sách sinh viên
        getDataStudent(scheduleId);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button getSelectedStudentsBtn = findViewById(R.id.get_selected_students_btn);
        getSelectedStudentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //điểm danh
                attendanceStudents(scheduleId);

            }
        });

    }

    // Điểm danh
    public void attendanceStudents(String scheduleId){
        ArrayList<Users> selectedUsers = adapter.getSelected();
        ArrayList<Users> selectedStudents = new ArrayList<>();

        for (Users user : selectedUsers) {
            selectedStudents.add(new Users(user.getId(), user.isChecked()));
        }
        Log.d("hihi",selectedStudents.toString());
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<Void> call = getAPI_service.updateAttendance(scheduleId,selectedStudents);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AttendanceStudents.this, "Điểm danh thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AttendanceStudents.this,SetAdmin_Activity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(AttendanceStudents.this, "Điểm danh thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AttendanceStudents.this, "Điểm danh thất bại  do server", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getDataStudent(String scheduleId){

        // Khởi tạo Retrofit và UserService
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<Users>> call = getAPI_service.getUserAndAttendanceBySchedule(scheduleId);
        Log.d("testloi",scheduleId);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Log.d("test",response.toString());
                if (response.isSuccessful()) {
                    usersList = response.body();
                    adapter = new AttendanceAdapter(AttendanceStudents.this,usersList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(AttendanceStudents.this, "Lỗi khi lấy danh sách sinh viên từ server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(AttendanceStudents.this, "Lỗi khi kết nối tới server" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void CreateAttendance(String scheduleId){
        // Khởi tạo Retrofit và UserService
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<ResponseBody> call = getAPI_service.getUserOfCourseListByScheduleId(scheduleId);
      call.enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {
              Toast.makeText(AttendanceStudents.this, t.toString(), Toast.LENGTH_SHORT).show();

          }
      });
    }


}