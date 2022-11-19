package com.fpoly.VncStore.Activity;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.fpoly.VncStore.ChucNang.GioHang;
import com.fpoly.VncStore.ChucNang.Home;
import com.fpoly.VncStore.ChucNang.TaiKhoan;

import com.fpoly.VncStore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
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
    @Override
    public void onBackPressed() {
        Exit();
    }
    public void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_thoat, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.show();

        Button btnHuy = view.findViewById(R.id.btn_dialog_Huy);
        Button btnOut = view.findViewById(R.id.btn_dialog_Thoat);

        btnOut.setOnClickListener(v -> {
            alertDialog.dismiss();
            System.exit(0);
        });

        btnHuy.setOnClickListener(v -> alertDialog.dismiss());
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
