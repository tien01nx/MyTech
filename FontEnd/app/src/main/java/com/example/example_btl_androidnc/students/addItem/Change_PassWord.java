package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.ChangePass;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Change_PassWord extends AppCompatActivity {
    private ImageView imgBack;
    private TextInputLayout oldpassword,newpassword,retypepassword;
    private Button Btn_update_pass;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);
        mySharedPreferences = new MySharedPreferences(Change_PassWord.this);
        getViews();
        getControls();
        Btn_update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = oldpassword.getEditText().getText().toString();
                String newPassword = newpassword.getEditText().getText().toString();
                String confirmPassword = retypepassword.getEditText().getText().toString();
                if (newPassword.equals(confirmPassword)){
                    onChangePasswordClicked(oldPassword,newPassword,confirmPassword);
                }
                else
                    Toast.makeText(Change_PassWord.this, "Xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
              
            }
        });
    }

    private void getViews() {
        imgBack = findViewById(R.id.img_back);
        oldpassword = findViewById(R.id.oldpassword);
        newpassword = findViewById(R.id.newpassword);
        retypepassword = findViewById(R.id.retypepassword);
        Btn_update_pass = findViewById(R.id.btn_update_pass);
    }


    private void onChangePasswordClicked(String oldPassword, String newPassword, String confirmPassword) {

        String authToken = mySharedPreferences.getToken();
        if (authToken != null) {
            GetAPI_Service getAPI_service = RetrofitClient.getClient(authToken).create(GetAPI_Service.class);
            ChangePass changePass = new ChangePass(oldPassword, newPassword, confirmPassword);
            Call<ResponseBody> call = getAPI_service.changePassword(changePass);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Change_PassWord.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        // cập nhật lại mật khẩu khi đổi mật khẩu
                        mySharedPreferences.savePassword(newPassword);
                        Log.d("test"," đổi mật khẩu mới thành công");
                        setText();
                    } else {

                        Toast.makeText(Change_PassWord.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                        Log.e("ChangePassword", "Đổi mật khẩu không thành công: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Change_PassWord.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(Change_PassWord.this, "Không có Token", Toast.LENGTH_SHORT).show();
        }
    }


    private void getControls() {
        // nút thoát
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public  void setText(){
        oldpassword.getEditText().setText("");
        newpassword.getEditText().setText("");
        retypepassword.getEditText().setText("");
    }
}