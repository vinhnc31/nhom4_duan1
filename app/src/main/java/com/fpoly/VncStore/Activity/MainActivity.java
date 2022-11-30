package com.fpoly.VncStore.Activity;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.fpoly.VncStore.ChucNang.GioHangFragment;
import com.fpoly.VncStore.ChucNang.HomeFragment;
import com.fpoly.VncStore.ChucNang.TaiKhoanFragment;

import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private FragmentManager fragmentManager;
   public static List<Sanpham> sanphamList = new ArrayList<Sanpham>();
    private int countProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        navigationView = findViewById(R.id.tabLayout);
        if(sanphamList == null){
            sanphamList = new ArrayList<>();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).addToBackStack(null).commit();
        navigationView.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.home1: {
                    fragment = new HomeFragment();
                    break;
                }
                case R.id.giohang: {
                    fragment = new GioHangFragment();
                    break;
                }
                case R.id.taikhoan: {
                    fragment = new TaiKhoanFragment();
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

    // Thêm sản phẩm đã chọn vào giỏ hàng
    public void addToListCartProdct(Sanpham sanpham) {
       MainActivity.sanphamList.add(sanpham);
    }

    // Lấy ra các sản phẩm đã thêm vào giỏ hàng
    public List<Sanpham> getListCartProduct() {
        return sanphamList;
    }

    // Set lại số lượng của sản phẩm khi mua nhiều
    public void setCountForProduct(int possion, int countProduct) {
        sanphamList.get(possion).setNumProduct(countProduct);
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
