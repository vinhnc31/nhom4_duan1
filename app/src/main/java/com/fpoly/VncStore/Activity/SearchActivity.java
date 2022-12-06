package com.fpoly.VncStore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Adapter.Search_SanPham_Adapter;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    TextView tv_back;
    RecyclerView rcv_search;
    SearchView searchView;
    Search_SanPham_Adapter adapter;
    List<Sanpham> sanphamList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhxa();
    }
    private void anhxa(){
        findViewById(R.id.back_sea).setOnClickListener(view -> {super.onBackPressed();});
        searchView=findViewById(R.id.search_view);
        rcv_search=findViewById(R.id.search_hienthi);
        sanphamList= getDataProduct("iphone");
        adapter=new Search_SanPham_Adapter(sanphamList,this);
        LinearLayoutManager  manager=new LinearLayoutManager(this);
        rcv_search.setLayoutManager(manager);
        rcv_search.setAdapter(adapter);

    }
    private List<Sanpham> getDataProduct(String name){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("SanPham");
        List<Sanpham> mListProduct = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    Sanpham product = data.getValue(Sanpham.class);
                    product.setIdSanPham(data.getKey());
                    if (product.getName().contains(name)){
                        mListProduct.add(product);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchActivity.this,"Không tải được dữ liệu từ firebase"
                        +error.toString(),Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled"+ error.toString());
            }
        });
        return mListProduct;
    }
}