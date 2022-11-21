package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailedActivity extends AppCompatActivity {
ImageView imageView;
TextView tv_tensp,tv_giamoisp,tv_giacusp,tv_soluongsp,tv_mota,textView;
int soluongsp = 1;
ImageView additem,removeitem;
Button button;
private Sanpham sanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Anhxa();
        xulyxukien();
        textView.setOnClickListener(view -> {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        });
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soluongsp < 10){
                    soluongsp++;
                    tv_soluongsp.setText(String.valueOf(soluongsp));
                }
            }
        });
        removeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soluongsp > 0){
                    soluongsp--;
                    tv_soluongsp.setText(String.valueOf(soluongsp));
                }
            }
        });
    }
    public void Anhxa(){
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Sanpham){
            sanpham = (Sanpham) object;
        }
        imageView = findViewById(R.id.img_sanpham);
        tv_tensp = findViewById(R.id.tv_tensp);
        tv_giamoisp = findViewById(R.id.tv_gia);
        tv_giacusp = findViewById(R.id.tv_khuyenmai);
        tv_soluongsp = findViewById(R.id.tv_soluong);
        additem = findViewById(R.id.btn_addtocart);
        button = findViewById(R.id.btn_addtocart);
        tv_mota = findViewById(R.id.tv_thongtinsanpham);
        textView = findViewById(R.id.back_tk);
    }
    public void xulyxukien(){
        if (sanpham != null){
            Picasso.get().load(sanpham.getImage()).placeholder(R.drawable.dienthoai).fit().centerCrop().into(imageView);
            tv_tensp.setText(""+sanpham.getName());
            tv_giamoisp.setText(""+sanpham.getGia()+" Vnd");
            tv_giacusp.setText("Khuyến Mại"+sanpham.getKhuyenmai()+"%");
             tv_mota.setText(sanpham.getMoTa());
        }
    }
}