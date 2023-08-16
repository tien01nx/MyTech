package com.example.example_btl_androidnc.students.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    Button bt_Signup;
    ImageView img_Back;
    EditText edt_Name, edt_Email, edt_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        img_Back = findViewById(R.id.img_back);
        edt_Name = findViewById(R.id.edt_Name);
        edt_Email = findViewById(R.id.edt_Email);
        edt_Password = findViewById(R.id.edt_Password);
        bt_Signup = findViewById(R.id.bt_Signup);

        bt_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_Name.getText().toString();
                String email = edt_Email.getText().toString();
                String password = edt_Password.getText().toString();
                register(name, email, password);
            }
        });

        getControls();
    }

    public void register(String name, String email, String password) {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);

        Call<Users> call = getAPI_service.createAccount(new Users(name, email, password));

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    showToast("Đăng kí thành công");
                    navigateToLogin();
                } else {
                    showToast("Đăng kí không thành công");
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                showToast("Lỗi kết nối. Vui lòng thử lại.");
                Log.d("test", " Đăng kí không thành công \n " + t);
                Log.d("test", call.toString());
            }
        });
    }

    private void getControls() {
        // nút thoát
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void navigateToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}