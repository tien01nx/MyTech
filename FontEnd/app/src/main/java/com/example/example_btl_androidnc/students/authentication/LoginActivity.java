package com.example.example_btl_androidnc.students.authentication;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.example_btl_androidnc.students.addItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Users;
import com.example.example_btl_androidnc.teachers.activity.SetTeacher_Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// private SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
public class LoginActivity extends AppCompatActivity {
    public EditText edtemail, edtpassword;
    public TextView forgot_password, tv_register;
    Button btnlogin;
    private MySharedPreferences mySharedPreferences;
    @Override
    protected void onStart() {
        super.onStart();
        checkSavedCredentials();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_password);
        tv_register = findViewById(R.id.tv_register);
        forgot_password = findViewById(R.id.forgot_password);
        btnlogin = findViewById(R.id.btn_login);
        mySharedPreferences = new MySharedPreferences(LoginActivity.this);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogForgotPassword();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = edtemail.getText().toString();
                String pass_text = edtpassword.getText().toString();
                if (validateInput(email_text, pass_text)) {
                    login(email_text, pass_text);
                }
            }
        });
    }
    public void login(String email, String password) {
        GetAPI_Service authService = RetrofitClient.getInstance(LoginActivity.this, RetrofitClient.BASE_URL, "").create(GetAPI_Service.class);
        Users users = new Users(email, password);
        Call<Users> call = authService.login(users);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users jwtResponse = response.body();
                    Log.d("testtoken", jwtResponse.toString());
                    if (jwtResponse != null) {
                        Users users = response.body();
                        List<String> roles = users.getRoles();
                        if (roles.contains("ROLE_USER"))
                        {
                            mySharedPreferences.saveData(jwtResponse.getAccessToken(), jwtResponse.getId(), jwtResponse.getEmail(), password, jwtResponse.getName(),jwtResponse.getRoles().get(0));
                            Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                            startActivity(intent);
                            finish();
                        }else if (roles.contains("ROLE_TEACHER")) {
                            mySharedPreferences.saveData(jwtResponse.getAccessToken(), jwtResponse.getId(), jwtResponse.getEmail(), password, jwtResponse.getName(),jwtResponse.getRoles().get(0));
                            Intent intent = new Intent(LoginActivity.this, SetTeacher_Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "tạch hihi", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Không thể nhận được thông tin đăng nhập, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng kiểm tra thông tin đăng nhập và thử lại!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Kết nối thất bại, vui lòng kiểm tra kết nối và thử lại!" + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("testtoken", t.toString());
            }
        });
    }
    public boolean validateInput(String email, String password) {
        boolean isValid = true;
        if (TextUtils.isEmpty(email)) {
            edtemail.setError("Email không được để trống.");
            isValid = false;
        }
        if (TextUtils.isEmpty(password)) {
            edtpassword.setError("Mật khẩu không được để trống.");
            isValid = false;
        }
        return isValid;
    }
    public void checkSavedCredentials() {
        String email = mySharedPreferences.getEmail();
        String password = mySharedPreferences.getPassword();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            login(email, password);
            Log.d("testtoken", " token đã bị thay đổi khi đăng nhập lần sau: ");
        }
    }
    private void dialogForgotPassword() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_forgot_password);
        // Không cho thoát khi bấm ra ngoài màn hình
        dialog.setCanceledOnTouchOutside(false);
        //ánh xạ
        Button btnForgotPassword = dialog.findViewById(R.id.btnForgotPassword);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        EditText edtEmailResetPassword = dialog.findViewById(R.id.edtEmailResetPassword);
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword(edtEmailResetPassword.getText().toString());
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void forgotPassword(String email) {
        GetAPI_Service authService = RetrofitClient.getInstance(LoginActivity.this, RetrofitClient.BASE_URL, "").create(GetAPI_Service.class);
        Call<Void> call = authService.processForgotPassword(email);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra thư email", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn kiểm tra thư email ngay bây giờ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Mở ứng dụng Gmail và chuyển đến hộp thư đến của người dùng
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("mailto:" + email));
                            intent.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                } else {
                    Toast.makeText(LoginActivity.this, "Email nhập vào không đúng", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra
            }
        });
    }
}