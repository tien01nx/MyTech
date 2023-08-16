package com.example.example_btl_androidnc.students.addItem;


import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.example_btl_androidnc.students.adapter.ViewPagetAdapter;
import com.example.example_btl_androidnc.students.model.PortraitActivity;
import com.example.example_btl_androidnc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SetAdmin_Activity extends PortraitActivity {

    //Button btSignOut;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_admin);

        getLinkViews();

        setUpViewPager();

        getControls();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void setUpViewPager() {
        ViewPagetAdapter viewPagetAdapter = new ViewPagetAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagetAdapter);
        // bắt sự khiện khi vuốt màn hình chuyển tap
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_qrcode).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getControls() {
//        btSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AGConnectAuth.getInstance().signOut();
//                startActivity(new Intent(AdminActivity.this, LogInActivity.class));
//                finish();
//            }
//        });

        // bắt sự kiện khi nhấn vào các nút ở bottom navication và chuyển tap tương ứng
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        //Toast.makeText(AdminActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_qrcode:
                        viewPager.setCurrentItem(1);
                        //Toast.makeText(AdminActivity.this, "List", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_setting:
                        viewPager.setCurrentItem(2);
                        //Toast.makeText(AdminActivity.this, "Setting", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }

    // liên kết các đối tượng view trên màn hình
    private void getLinkViews() {
        //btSignOut = findViewById(R.id.bt_sign_out);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);
    }

    @Override // nếu nhân thoát 2 lần sẽ thoát
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}