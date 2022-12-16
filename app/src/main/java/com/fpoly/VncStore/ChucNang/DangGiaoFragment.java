package com.fpoly.VncStore.ChucNang;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fpoly.VncStore.Adapter.LichsuAdapter;
import com.fpoly.VncStore.Model.Order;
import com.fpoly.VncStore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DangGiaoFragment extends Fragment {

    RecyclerView rcv;
    private List<Order> listOrder;
    LichsuAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dahuy, container, false);
        listOrder = new ArrayList<>();
        rcv =v.findViewById(R.id.rcv_dahuy);
        findOrder();
        return v;
    }
    private void setDataHistoryProductAdapter(){
        adapter = new LichsuAdapter();
        adapter.setData(listOrder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(adapter);
    }

    // Lấy thông tin order
    private void findOrder(){
        // Kết nối tới data base
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String email1=user.getEmail();
        email1=email1.replace(".","_");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Order");
        // Lấy thông tin order
        myRef.orderByChild("idemail").equalTo(email1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOrder.clear();
                for (DataSnapshot dataOrder : snapshot.getChildren()){
                    Order order = dataOrder.getValue(Order.class);
                    order.setOrderNo(dataOrder.getKey());
                    if (order.getTrangthai().equalsIgnoreCase("Đang vận chuyển")){
                        listOrder.add(0,order);
                    }
                }
                setDataHistoryProductAdapter();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Không lấy được thông tin đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
            }
        });
    }
}