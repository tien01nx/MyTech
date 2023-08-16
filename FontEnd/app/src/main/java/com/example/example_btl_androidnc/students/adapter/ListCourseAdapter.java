package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;
import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.addItem.ScheduleList;
import com.example.example_btl_androidnc.students.model.UserCourse;

import java.util.List;

public class ListCourseAdapter extends RecyclerView.Adapter<ListCourseAdapter.ListViewHolder> {

    private Context context;
    private List<UserCourse> courseList;


    public ListCourseAdapter(Context context, List<UserCourse> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_register_course, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int i) {
        UserCourse course = courseList.get(i);
        holder.name_info.setText(course.getName());
        holder.infor_class.setText("Ngày kết thúc: "+ convertDateFormat(course.getEnrollDate()));

        if (course.getImage()!=null){
            Glide.with(holder.imageView.getContext())
                    .load(BASE_IMG + course.getImage())
                    .into(holder.imageView);
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // đổ sang trang lịch học của course
                Intent i = new Intent(context, ScheduleList.class);
                i.putExtra("courseId", course.getCourseId());
                i.putExtra("address",course.getAddress());
                i.putExtra("nameCourse",course.getName());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView name_info,infor_class;
        RelativeLayout relativeLayout;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_info);
            name_info = itemView.findViewById(R.id.name_info);
            infor_class = itemView.findViewById(R.id.infor_class);
            relativeLayout = itemView.findViewById(R.id.item);
        }
    }
}
