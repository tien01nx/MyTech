package com.example.example_btl_androidnc.students.fragment;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.students.addItem.Change_PassWord;
import com.example.example_btl_androidnc.students.addItem.Edit_Profile;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.authentication.LoginActivity;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.ImageHelper;
import com.example.example_btl_androidnc.students.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_UserFragment extends Fragment {
    private MySharedPreferences mySharedPreferences;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button Bt_sign_out, Bt_infor_mail, bt_infor_change_pass, bt_infor_address, bt_infor_phone;
    private ImageView img_user_photo;
    private TextView tv_Edit, Name_user;
    private Context context;
    private Users users;

    public Profile_UserFragment() {
        // Required empty public constructor
    }

    public static Profile_UserFragment newInstance(String param1, String param2) {
        Profile_UserFragment fragment = new Profile_UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getUserProfile();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile__user, container, false);

        View view = inflater.inflate(R.layout.fragment_profile__user, container, false);

        Bt_sign_out = view.findViewById(R.id.bt_sign_out);
        tv_Edit = view.findViewById(R.id.tv_edit_profile);

        Name_user = view.findViewById(R.id.name_user);
        Bt_infor_mail = view.findViewById(R.id.bt_infor_mail);
        bt_infor_change_pass = view.findViewById(R.id.bt_infor_change_pass);

        bt_infor_address = view.findViewById(R.id.bt_infor_address);
        img_user_photo = view.findViewById(R.id.img_user_photo);
        bt_infor_phone = view.findViewById(R.id.bt_infor_phone);

        Bt_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySharedPreferences.clearData();
                Log.d("testtoken", " đăng xuất đã xóa token");
                startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                getActivity().getSupportFragmentManager().beginTransaction().remove(Profile_UserFragment.this).commit();

            }
        });
        tv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Edit_Profile.class);
                i.putExtra("user", users);
                //Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                startActivity(i);
            }
        });
        bt_infor_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Change_PassWord.class);
                //Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                startActivity(i);
            }
        });
        return view;
    }


    public void getUserProfile() {
        MySharedPreferences mySharedPreferences1 = new MySharedPreferences(getContext());

        String token = mySharedPreferences1.getToken();
        GetAPI_Service getAPI_service = RetrofitClient.getInstance(getContext(), RetrofitClient.BASE_URL, token).create(GetAPI_Service.class);;
        Call<Users> call = getAPI_service.getUserProfile("Bearer " + token);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    users = response.body();
                    // Hiển thị thông tin của người dùng lên giao diện

                    Name_user.setText(users.getName());
                    Bt_infor_mail.setText(users.getEmail());
                    bt_infor_address.setText(users.getAddress());
                    bt_infor_phone.setText(users.getPhone());
                    if (users.getImage()!=null){
                        Glide.with(img_user_photo.getContext())
                                .load(BASE_IMG + users.getImage())
                                .into(img_user_photo);
                    }

                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(getContext(), "Không thể lấy thông tin người dùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Xử lý khi có lỗi
//                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireActivity().getApplicationContext();
        mySharedPreferences = new MySharedPreferences(context);
    }
}