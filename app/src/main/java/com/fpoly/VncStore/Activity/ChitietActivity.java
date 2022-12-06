package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.VncStore.Adapter.ChitietAdapter;
import com.fpoly.VncStore.Model.ChitietHoaDon;
import com.fpoly.VncStore.R;

import java.text.DecimalFormat;

public class ChitietActivity extends AppCompatActivity {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private ChitietHoaDon chitietHoaDon;
    TextView tv_madon,tv_ngaydat,tv_tenkhach,tv_diachi,tv_sodt,tv_sosanpham,tv_tongtien;
    RecyclerView rcv_chitiet;
    ImageView img_back;
    ChitietAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        anhxa();
        setData();
        getView();
    }

    private void setData() {
        chitietHoaDon= (ChitietHoaDon) getIntent().getSerializableExtra("oder");
        adapter.SetData(chitietHoaDon.getHoadonList());
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rcv_chitiet.setLayoutManager(manager);
        rcv_chitiet.setAdapter(adapter);
    }

    private void getView() {
        tv_madon.setText(chitietHoaDon.getIdChitietHoaDon().toUpperCase());
        tv_ngaydat.setText(chitietHoaDon.getNgaymua());
        tv_tenkhach.setText(chitietHoaDon.getTenkhachhang());
        tv_diachi.setText(chitietHoaDon.getDiachi());
        tv_sodt.setText(chitietHoaDon.getPhone());
        tv_sosanpham.setText(String.valueOf(chitietHoaDon.getSoluong()));
        tv_tongtien.setText(formatPrice.format(chitietHoaDon.getTongtien()) + "VNĐ");
    }

    public void anhxa(){
        rcv_chitiet=findViewById(R.id.rcv_chitiet);
        adapter=new ChitietAdapter();
        tv_madon=findViewById(R.id.tv_madonhangct);
        tv_ngaydat=findViewById(R.id.tv_ngaydatct);
        tv_tenkhach=findViewById(R.id.tv_tenkhachct);
        tv_diachi=findViewById(R.id.tv_diachikhachct);
        tv_sodt=findViewById(R.id.tv_sodtct);
        tv_sosanpham=findViewById(R.id.tv_sospct);
        tv_tongtien=findViewById(R.id.tv_tongtienct);
        img_back=findViewById(R.id.img_backchitiet);
        img_back.setOnClickListener(v ->{
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_left_to_right,R.anim.exit_left_to_right);
            adapter.notifyDataSetChanged();
        });
    }
}