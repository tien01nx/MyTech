package com.example.example_btl_androidnc.students.tets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.example_btl_androidnc.R;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.List;

public class Demo extends AppCompatActivity {
    SliderLayout sliderLayout;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        getViews();
        AddImagesToSlider();


    }

    public void getViews() {


        sliderLayout = findViewById(R.id.slider);


    }

    public void AddImagesToSlider(){
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.luffy1);
        images.add(R.drawable.luffy2);
        images.add(R.drawable.boruto1);
        images.add(R.drawable.boruto2);
        for (int i =0;i<images.size();i++){
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.setRequestOption(new RequestOptions().centerCrop());
            defaultSliderView.image(images.get(i));
            int finalI = i;
            defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    switch (finalI) {
                        case 0:
                            Toast.makeText(Demo.this, "Bạn đã nhấn vào Luffy 1", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(Demo.this, "Bạn đã nhấn vào Luffy 2", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(Demo.this, "Bạn đã nhấn vào Boruto 1", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(Demo.this, "Bạn đã nhấn vào Boruto 2", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            });
            sliderLayout.addSlider(defaultSliderView);


        }
        sliderLayout.startAutoCycle();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(3000);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }
}