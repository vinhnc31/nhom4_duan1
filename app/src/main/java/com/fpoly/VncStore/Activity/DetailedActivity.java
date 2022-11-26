package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tv_tensp, tv_giamsp, tv_khuyenmai, tv_mota, textView;
    Button btn_themvagiohang;
    private Sanpham sanpham;
    private Boolean isAddToCart;
    List<Sanpham> sanphamList;

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
    }

    public void Anhxa() {
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Sanpham) {
            sanpham = (Sanpham) object;
        }
        isAddToCart = false;
        imageView = findViewById(R.id.img_sanpham);
        tv_tensp = findViewById(R.id.tv_tensp);
        tv_khuyenmai = findViewById(R.id.tv_khuyenmai);
        tv_giamsp = findViewById(R.id.tv_gia);
        btn_themvagiohang = findViewById(R.id.btn_addtocart);
        tv_mota = findViewById(R.id.tv_thongtinsanpham);
        textView = findViewById(R.id.back_tk);
        if (sanphamList == null) {
            sanphamList = new ArrayList<>();
        }
    }

    public void xulyxukien() {
        if (sanpham != null) {
            Picasso.get().load(sanpham.getImage()).placeholder(R.drawable.dienthoai).fit().centerCrop().into(imageView);
            tv_tensp.setText("" + sanpham.getName());
            tv_giamsp.setText("Giá: " + sanpham.getGia() + " VNĐ");
            tv_khuyenmai.setText("Khuyến Mại "+sanpham.getKhuyenmai() + "%");
            tv_mota.setText(sanpham.getMoTa());
            for (int i = 0; i < sanphamList.size(); i++) {
                // Nếu sản  phẩm đã dc add
                if (sanphamList.get(i).getName().equals(sanpham.getName())) {
                    isAddToCart = true;
                    btn_themvagiohang.setText("Đã Mua");
                    btn_themvagiohang.setBackgroundResource(R.drawable.custom_button);
                    break;
                }
            }

            btn_themvagiohang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAddToCart) {
                        Toast.makeText(DetailedActivity.this, "Sản Phẩm này đã tồn tại trong giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        isAddToCart = true;
                        btn_themvagiohang.setText("Đã Mua");
                        btn_themvagiohang.setBackgroundResource(R.drawable.custom_button);
                        Toast.makeText(DetailedActivity.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
