package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.VncStore.Adapter.ChitietAdapter;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Oder;
import com.fpoly.VncStore.R;

import java.text.DecimalFormat;

public class ChitietActivity extends AppCompatActivity {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private Oder order;
    TextView tv_madon,tv_ngaydat,tv_tenkhach,tv_diachi,tv_sodt,tv_sosanpham,tv_tongtien,tv_trangthai;
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
        order= (Oder) getIntent().getSerializableExtra("oder");
        adapter.SetData(order.getHoadonList());
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rcv_chitiet.setLayoutManager(manager);
        rcv_chitiet.setAdapter(adapter);
    }

    private void getView() {
        tv_madon.setText(order.getOrderNo().toUpperCase());
        tv_ngaydat.setText(order.getNgaymua());
        tv_tenkhach.setText(order.getTenkhachhang());
        tv_diachi.setText(order.getDiachi());
        tv_sodt.setText(order.getPhone());
        tv_sosanpham.setText(String.valueOf(order.getSoluong()));
        tv_tongtien.setText(formatPrice.format(order.getTongtien()) + "VNĐ");
        tv_trangthai.setText(order.getTrangthai());
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
        tv_trangthai=findViewById(R.id.tv_trangthaichitiet);
        img_back=findViewById(R.id.img_backchitiet);
        img_back.setOnClickListener(v ->{
            super.onBackPressed();
        });
    }
}