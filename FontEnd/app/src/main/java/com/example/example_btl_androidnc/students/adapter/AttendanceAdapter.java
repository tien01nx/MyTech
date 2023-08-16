package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {
    private Context context;
    private List<Users> users;

    public AttendanceAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }

    public void setUsers(ArrayList<Users> users){
        this.users = new ArrayList<>();
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_student, parent, false);
        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        Users user = users.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
        if (user.getImage() != null) {
            Glide.with(holder.image_User.getContext())
                    .load(BASE_IMG + user.getImage())
                    .into(holder.image_User);
        }

        holder.imageCheck.setVisibility(user.isChecked() ? View.VISIBLE : View.GONE);

        holder.itemView.findViewById(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setChecked(!user.isChecked());
                holder.imageCheck.setVisibility(user.isChecked() ? View.VISIBLE : View.GONE);
            }
        });
    }
    public ArrayList<Users> getSelected() {
        ArrayList<Users> selected = new ArrayList<>();
        for (Users user : users) {
            if (user.isChecked()) {
                selected.add(user);
            }
        }
        return selected;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView emailTextView;
        ImageView image_User,imageCheck;
        // add other views as needed

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            emailTextView = itemView.findViewById(R.id.text_view_email);

            image_User = itemView.findViewById(R.id.image_User);

            imageCheck = itemView.findViewById(R.id.imageCheck);
            // find other views as needed
        }
    }
}
