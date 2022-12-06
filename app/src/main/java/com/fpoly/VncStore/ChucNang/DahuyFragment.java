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
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.ChitietHoaDon;
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


public class DahuyFragment extends Fragment {
    RecyclerView rcv;
    private List<Hoadon> listDetailOrder;
    private List<ChitietHoaDon> chitietHoaDonList;
    LichsuAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dahuy, container, false);
        chitietHoaDonList = new ArrayList<>();
        listDetailOrder = new ArrayList<>();
        rcv =v.findViewById(R.id.rcv_dahuy);
        findOrder();
        return v;
    }
    private void setDataHistoryProductAdapter(){
        adapter = new LichsuAdapter();
        adapter.setData(listDetailOrder,chitietHoaDonList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(adapter);
    }

    // Lấy thông tin order
    private void findOrder(){
        // Clear các list dữ liệu khi tìm kiếm
        chitietHoaDonList.clear();
        listDetailOrder.clear();

        // Kết nối tới data base
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String email1=user.getEmail();
        email1=email1.replace(".","_");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Oder/"+email1);
        // Lấy thông tin order
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chitietHoaDonList.clear();
                for (DataSnapshot dataOrder : snapshot.getChildren()){
                    Log.d("TAG", "onDataChange: " + dataOrder.toString());
                    ChitietHoaDon order = dataOrder.getValue(ChitietHoaDon.class);
                    order.setIdChitietHoaDon(dataOrder.getKey());
                    Log.e("w111www",""+chitietHoaDonList.size());
                    Log.d("zzzzzzz", "onDataChange: " + order.getTenkhachhang());
                }
                if (chitietHoaDonList.size() > 0){
                    // Lấy thông tin detail order
                    findDetailOrder(myRef);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Không lấy được thông tin đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Lấy thông tin detail order
    private void findDetailOrder( DatabaseReference myRef){
        listDetailOrder.clear();
        if (chitietHoaDonList.size() > 0){
            for (int i = 0; i<chitietHoaDonList.size(); i++){
                ChitietHoaDon order = chitietHoaDonList.get(i);
                myRef.child(order.getIdChitietHoaDon()).child("detail").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataDetail : snapshot.getChildren()){
                            Hoadon detailOrder = dataDetail.getValue(Hoadon.class);
                            detailOrder.setIdHoadon(dataDetail.getKey());
                            listDetailOrder.add(0,detailOrder);
                        }
                        // set data HistoryProductAdapter
                        setDataHistoryProductAdapter();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),"Không lấy được chi tiết đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}