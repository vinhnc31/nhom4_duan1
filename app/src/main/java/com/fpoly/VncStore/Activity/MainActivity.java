package com.fpoly.VncStore.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.fpoly.VncStore.ChucNang.GioHangFragment;
import com.fpoly.VncStore.ChucNang.HomeFragment;
import com.fpoly.VncStore.ChucNang.TaiKhoanFragment;

import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private FragmentManager fragmentManager;
    public static List<Sanpham> sanphamList = new ArrayList<Sanpham>();
    public static BadgeDrawable badgeDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        navigationView = findViewById(R.id.tabLayout);
        if (sanphamList == null) {
            sanphamList = new ArrayList<>();
        }
        getListUserCart();

        badgeDrawable= navigationView.getOrCreateBadge(R.id.giohang);
        badgeDrawable.setVisible(true);
        badgeDrawable.setVerticalOffset(dpToPx(MainActivity.this,3));
        badgeDrawable.setBadgeTextColor(getResources().getColor(R.color.orange));

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
        sanphamList.add(sanpham);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email1 = user.getEmail();
        email1 = email1.replace(".", "_");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cart");
        reference.child(email1).setValue(sanphamList);
    }

    public void getListUserCart(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email1 = user.getEmail();
        email1 = email1.replace(".", "_");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cart");
        reference.child(email1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sanphamList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Sanpham sanpham =dataSnapshot.getValue(Sanpham.class);
                    sanphamList.add(sanpham);
                }
                badgeDrawable.setNumber(MainActivity.sanphamList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    // Lấy ra các sản phẩm đã thêm vào giỏ hàng
    public List<Sanpham> getListCartProduct() {
        return sanphamList;
    }

    // Set lại số lượng của sản phẩm khi mua nhiều
    public void setCountForProduct(int possion, int countProduct) {
        sanphamList.get(possion).setNumProduct(countProduct);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email1 = user.getEmail();
        email1 = email1.replace(".", "_");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cart");
        reference.child(email1).setValue(sanphamList);
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
    public static int dpToPx(Context context,int dp){
        Resources resources=context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resources.getDisplayMetrics()));
    }
    public void Remove(int position){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email1 = user.getEmail();
        email1 = email1.replace(".", "_");
        DatabaseReference reference2= FirebaseDatabase.getInstance().getReference("Cart").child(email1);
        reference2.setValue(sanphamList);

    }
}
