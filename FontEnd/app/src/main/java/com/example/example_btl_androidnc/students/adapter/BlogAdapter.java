package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.example_btl_androidnc.students.model.Blog;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder>{
    private Context context;
    private List<Blog> BlogList;

    public BlogAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        BlogList = blogList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.list_blog, parent, false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        Blog blog = BlogList.get(i);
        setBlogData(holder, blog);
       // holder.item.setOnClickListener(view -> openRegisterCourseActivity(course));
    }

    @Override
    public int getItemCount() {
        return BlogList.size();
    }

    private void setBlogData(MyViewHolder holder, Blog blog) {
        holder.txtTitle.setText(blog.getTitle());
        holder.txtContent.setText(blog.getContent());

        Glide.with(holder.image_blog.getContext())
                .load(BASE_IMG + blog.getImage())
                .into(holder.image_blog);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_blog;
        TextView  txtTitle,txtContent;
        RelativeLayout item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.news_title);
            txtContent = itemView.findViewById(R.id.news_title);
            image_blog = itemView.findViewById(R.id.image_blog);
            item = itemView.findViewById(R.id.item_blog);
        }
    }

}
