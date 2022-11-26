package com.fpoly.VncStore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fpoly.VncStore.Adapter.LichsuAdapter;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Oder;
import com.fpoly.VncStore.R;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<Hoadon> list;
    private List<Oder> oderList;
    EditText ed_phone;
    ImageView img_search;
    LichsuAdapter adapter;
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ed_phone = findViewById(R.id.ed_search);
        img_search = findViewById(R.id.img_search);
        list = new ArrayList<>();
        oderList = new ArrayList<>();
        adapter = new LichsuAdapter();
        rcv = findViewById(R.id.rcv_histor);
        adapter.setData(list, oderList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv.setLayoutManager(manager);
        rcv.setAdapter(adapter);
        Search();
    }

    private void Search() {
        list.clear();
        oderList.clear();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Hoadon");
        reference.orderByChild("phone").equalTo(ed_phone.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.notifyDataSetChanged();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Oder oder = snapshot1.getValue(Oder.class);
                    oder.setOrderNo(snapshot1.getKey());
                    oderList.add(oder);
                }
                if (oderList.size() > 0) {
                    // Lấy thông tin detail order
                    findDetailOrder(reference);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void findDetailOrder(DatabaseReference reference) {
        if (oderList.size() > 0) {
            for (int i = 0; i < oderList.size(); i++) {
                Oder oder = oderList.get(i);
                reference.child(oder.getOrderNo()).child("detal").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            adapter.notifyDataSetChanged();
                            Hoadon hoadon=snapshot1.getValue(Hoadon.class);
                            list.add(hoadon);
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

}