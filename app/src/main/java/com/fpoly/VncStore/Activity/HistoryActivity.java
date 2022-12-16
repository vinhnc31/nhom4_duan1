package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.fpoly.VncStore.Adapter.ViewPageAdapter;
import com.fpoly.VncStore.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HistoryActivity extends AppCompatActivity {
    TextView back_donhang;
    TabLayout tabLayout;
    ViewPageAdapter pageAdapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initItem();
    }
    private void initItem(){
        pageAdapter =new ViewPageAdapter(this);
        back_donhang = findViewById(R.id.back_donhang);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(pageAdapter);
        back_donhang.setOnClickListener(v ->{
            finish();
            overridePendingTransition(R.anim.enter_left_to_right,R.anim.exit_left_to_right);
        });

        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {
            switch (position){
                case 0: {
                    tab.setText("Tất Cả");
                    break;
                }
                case 1: {
                    tab.setText("Chờ xử lý");
                    break;
                }
                case 2: {
                    tab.setText("Đã Hủy");
                    break;
                }
                case 3: {
                    tab.setText("Đang giao");
                    break;
                }
                case 4: {
                    tab.setText("Đã Nhận");
                    break;
                }
            }
        }).attach();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}