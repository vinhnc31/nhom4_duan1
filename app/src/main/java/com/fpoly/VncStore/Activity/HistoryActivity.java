package com.fpoly.VncStore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Adapter.LichsuAdapter;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Oder;
import com.fpoly.VncStore.R;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<Hoadon> listDetailOrder;
    private List<Oder> listOrder;
    TextView back_donhang;
    LichsuAdapter adapter;
    RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initItem();
        setDataHistoryProductAdapter();
    }

    private void initItem(){
        listOrder = new ArrayList<>();
        listDetailOrder = new ArrayList<>();

        adapter = new LichsuAdapter();
        back_donhang = findViewById(R.id.back_donhang);
        rcv = findViewById(R.id.rcv_histor);
        back_donhang.setOnClickListener(v ->{
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_left_to_right,R.anim.exit_left_to_right);
        });
        findOrder();
    }

    // set data cho HistoryProductAdapter
    private void setDataHistoryProductAdapter(){
        adapter.setData(listDetailOrder,listOrder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(adapter);
    }

    // Lấy thông tin order
    private void findOrder(){
        // Clear các list dữ liệu khi tìm kiếm
        listOrder.clear();
        listDetailOrder.clear();

        // Kết nối tới data base
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String email1=user.getEmail();
        email1=email1.replace(".","_");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Hoadon/"+email1);
        // Lấy thông tin order
        myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataOrder : snapshot.getChildren()){
                            Log.d("TAG", "onDataChange: " + dataOrder.toString());
                            Oder order = dataOrder.getValue(Oder.class);
                            order.setOrderNo(dataOrder.getKey());
                            Log.d("zzzzzzz", "onDataChange: " + order.getTenkhachhang());
                            listOrder.add(order);
                        }
                        adapter.notifyDataSetChanged();
                        if (listOrder.size() > 0){
                            // Lấy thông tin detail order
                            findDetailOrder(myRef);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HistoryActivity.this,"Không lấy được thông tin đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Lấy thông tin detail order
    private void findDetailOrder( DatabaseReference myRef){
        if (listOrder.size() > 0){
            for (int i = 0; i<listOrder.size(); i++){
                Oder order = listOrder.get(i);
                myRef.child(order.getOrderNo()).child("detail").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        adapter.notifyDataSetChanged();
                        for (DataSnapshot dataDetail : snapshot.getChildren()){
                            adapter.notifyDataSetChanged();
                            Hoadon detailOrder = dataDetail.getValue(Hoadon.class);
                            listDetailOrder.add(detailOrder);
                        }

                        // set data HistoryProductAdapter
                        if (listDetailOrder.size() > 0){
                            setDataHistoryProductAdapter();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HistoryActivity.this,"Không lấy được chi tiết đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    public void Chitiet(Oder oder){

    }

}