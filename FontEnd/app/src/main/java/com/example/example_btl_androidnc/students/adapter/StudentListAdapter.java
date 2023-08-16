package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.addItem.RankActivity;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
    private Context context;
    private List<Users> users;
    private String courseId;

    public StudentListAdapter(Context context, List<Users> users, String courseId) {
        this.context = context;
        this.users = users;
        this.courseId = courseId;
    }

    public void setUsers(ArrayList<Users> users){
        this.users = new ArrayList<>();
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Users user = users.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
        if (user.getImage() != null) {
            Glide.with(holder.image_User.getContext())
                    .load(BASE_IMG + user.getImage())
                    .into(holder.image_User);
        }


        holder.itemView.findViewById(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RankActivity.class);
                i.putExtra("users",user);
                i.putExtra("courseId",courseId);
                context.startActivity(i);

            }
        });
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView emailTextView;
        ImageView image_User,imageCheck;
        // add other views as needed

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            emailTextView = itemView.findViewById(R.id.text_view_email);

            image_User = itemView.findViewById(R.id.image_User);
            // find other views as needed
        }
    }
}
