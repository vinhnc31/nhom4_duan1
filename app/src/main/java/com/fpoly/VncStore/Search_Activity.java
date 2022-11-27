package com.fpoly.VncStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;

import com.fpoly.VncStore.Model.Sanpham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {

    Sanpham sanpham;
    DatabaseReference databaseReference;
    private AutoCompleteTextView txt_Search;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        databaseReference = FirebaseDatabase.getInstance().getReference("SanPham");
        txt_Search = findViewById(R.id.search_id);
        listView = findViewById(R.id.lv_id);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addListenerForSingleValueEvent(eventListener);
    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> nameSP = new ArrayList<>();
        if(snapshot.exists()) {
            for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                String names = dataSnapshot.child("name").getValue(String.class);
                nameSP.add(names);
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameSP);
            txt_Search.setAdapter(arrayAdapter);
            txt_Search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = txt_Search.getText().toString();
                    searchName(name);
                }
            });

        } else {
            Log.d("SanPham", "Khong co du lieu");
        }
    }

    private void searchName(String name) {
        Query query = databaseReference.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    ArrayList<String> listSanPham = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        listSanPham = new ArrayList<>();
                        sanpham = new Sanpham(dataSnapshot.child("name").getValue(String.class), dataSnapshot.child("Gia").getValue(String.class), dataSnapshot.child("image").getValue(int.class), dataSnapshot.child("khuyenmai").getValue(String.class), dataSnapshot.child("moTa").getValue(String.class));
                        listSanPham.add(sanpham.getName() + "\n" + sanpham.getGia() + "\n" + sanpham.getImage() + "\n" + sanpham.getKhuyenmai() + "\n" + sanpham.getMoTa());
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listSanPham);
                    listView.setAdapter(arrayAdapter);
                } else {
                    Log.d("SanPham", "ko co san pham");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}