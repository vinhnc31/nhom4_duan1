package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.VncStore.Adapter.ChitietAdapter;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Oder;
import com.fpoly.VncStore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.HashMap;

public class ChitietActivity extends AppCompatActivity {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private Oder order;
    TextView tv_madon,tv_ngaydat,tv_tenkhach,tv_diachi,tv_sodt,tv_sosanpham,tv_tongtien,tv_trangthai;
    RecyclerView rcv_chitiet;
    ImageView img_back;
    ChitietAdapter adapter;
    Button btn_xacnhan;
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
        Log.e("hdkasdjlka",""+order.getHoadonList().size());
        adapter.SetData(order.getHoadonList());
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rcv_chitiet.setLayoutManager(manager);
        rcv_chitiet.setAdapter(adapter);
        if (order.getHoadonList().get(0).getTrangthai().equalsIgnoreCase("Đang vận chuyển")){
            btn_xacnhan.setVisibility(View.VISIBLE);
            btn_xacnhan.setOnClickListener(v ->{
                FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email1 = user.getEmail();
                email1 = email1.replace(".", "_");
                DatabaseReference mreference = mdatabase.getReference("Oder/" + email1);
                DatabaseReference mreference1 = mdatabase.getReference("OderAdmin");
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("trangthai","Đã Nhận");
                mreference.child(order.getOrderNo()).child("detail").child(order.getHoadonList().get(0).getIdHoadon()).updateChildren(hashMap);
                mreference1.child(order.getOrderNo()).child("detailadmin").child(order.getHoadonList().get(0).getIdHoadon()).updateChildren(hashMap);
            });
        }
    }

    private void getView() {
        tv_madon.setText(order.getOrderNo().toUpperCase());
        tv_ngaydat.setText(order.getNgaymua());
        tv_tenkhach.setText(order.getTenkhachhang());
        tv_diachi.setText(order.getDiachi());
        tv_sodt.setText(order.getPhone());
        tv_sosanpham.setText(String.valueOf(order.getSoluong()));
        tv_tongtien.setText(formatPrice.format(order.getTongtien()) + "VNĐ");
    }

    public void anhxa(){
        btn_xacnhan=findViewById(R.id.btn_xacnhan);
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
        });
    }
}