package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.addItem.AttendanceStudents;
import com.example.example_btl_androidnc.students.addItem.ScheduleList;
import com.example.example_btl_androidnc.students.addItem.StudentList;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserCourse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private Context context;
    private List<Schedule> schedules;
    private String address;
    private String idCourse;
    private MySharedPreferences mySharedPreferences;


    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    public ScheduleAdapter(Context context, List<Schedule> schedules, String address, String idCourse) {
        this.context = context;
        this.schedules = schedules;
        this.address = address;
        this.idCourse = idCourse;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        mySharedPreferences = new MySharedPreferences(context);
        String role = mySharedPreferences.getRole();
//
//        if ("ROLE_USER".equals(role)) {
//            holder.swipelayout.setLockDrag(true);
//        } else {
//            holder.swipelayout.setLockDrag(false);
//        }

        Schedule schedule = schedules.get(position);




        if (schedule.getStatus() == 0) {
            holder.tvHienLich.setBackgroundResource(R.drawable.custom_button_red);
            holder.tvHienLich.setText("Được nghỉ");
        } else {
            holder.tvHienLich.setBackgroundResource(R.drawable.custom_button_green);
            holder.tvHienLich.setText("Đi học");
        }
        holder.tvBuoiHoc.setText(String.valueOf(position + 1));
        holder.tvThu.setText(schedule.getDayOfWeek());
        holder.tvNgay.setText(convertDateFormat(schedule.getDay()));
        holder.tvGio.setText(schedule.getDuration());
        holder.tvDiaChi.setText(address);

        // Set a click listener for text views
        View.OnClickListener txtClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAPI_Service apiService = RetrofitClient.getClient().create(GetAPI_Service.class);

                Schedule req = new Schedule();
                if (v.getId() == R.id.txtEdit) {
                    req.setStatus(0); // Set status to 0
                } else if (v.getId() == R.id.txtDelete) {
                    req.setStatus(1); // Set status to 1
                }

                Call<Void> call = apiService.updateSchedule(schedule.getId(), req);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("test:111",response.toString());
                        if (response.isSuccessful()) {
                            Log.d("UpdateSchedule", "Cập nhật lịch học thành công");
                            Toast.makeText(context, "Cập nhật lịch học thành công", Toast.LENGTH_SHORT).show();
                            if (req.getStatus() == 0) {
                                holder.tvHienLich.setBackgroundResource(R.drawable.custom_button_red);
                                holder.tvHienLich.setText("Được nghỉ");
                            } else {
                                holder.tvHienLich.setBackgroundResource(R.drawable.custom_button_green);
                                holder.tvHienLich.setText("Đi học");
                            }
                            holder.swipelayout.close(true);
                        } else {
                            Log.e("UpdateSchedule", "Lỗi khi cập nhật lịch học");
                            Toast.makeText(context, "Lỗi khi cập nhật lịch học", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("UpdateSchedule", "Lỗi khi gọi API: " + t.getMessage());
                        Toast.makeText(context, "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };


        holder.txtEdit.setOnClickListener(txtClickListener);
        holder.txtDelete.setOnClickListener(txtClickListener);

        if ("ROLE_USER".equals(role)) {
            holder.swipelayout.setLockDrag(true);
        } else {
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, AttendanceStudents.class);
                    i.putExtra("scheduleId", schedule.getId());
                    i.putExtra("courseId",idCourse);
                    context.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView tvBuoiHoc, tvThu, tvNgay, tvGio, tvDiaChi, tvHienLich;
        LinearLayout item;
        SwipeRevealLayout swipelayout;
        TextView txtDelete, txtEdit;
        CardView cardView;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            tvBuoiHoc = itemView.findViewById(R.id.tvBuoiHoc);
            tvThu = itemView.findViewById(R.id.tvThu);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvGio = itemView.findViewById(R.id.tvGio);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            item = itemView.findViewById(R.id.item);
            tvHienLich = itemView.findViewById(R.id.txtHienThi);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            txtEdit = itemView.findViewById(R.id.txtEdit);
            swipelayout = itemView.findViewById(R.id.swipelayout);

            cardView = itemView.findViewById(R.id.idDiemDanh);
        }
    }
}
