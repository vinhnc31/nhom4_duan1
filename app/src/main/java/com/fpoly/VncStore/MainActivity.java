package com.fpoly.VncStore;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.fpoly.VncStore.ChucNang.GioHang;
import com.fpoly.VncStore.ChucNang.Home;
import com.fpoly.VncStore.ChucNang.TaiKhoan;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.tabLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Home()).addToBackStack(null).commit();
        navigationView.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home1: {
                    fragment = new Home();
                    break;
                }
                case R.id.giohang: {
                    fragment = new GioHang();
                    break;
                }
                case R.id.taikhoan: {
                    fragment = new TaiKhoan();
                    break;
                }
            }
            fragmentTransaction.replace(R.id.framelayout, fragment).addToBackStack(null).commit();
            return true;
        });

    }

}
