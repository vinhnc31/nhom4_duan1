package com.fpoly.VncStore.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.fpoly.VncStore.Adapter.Adapter_Spinner;
import com.fpoly.VncStore.Adapter.SanphamAdapter;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class DienThoaiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Spinner spinner;
    TextView textView;
    FirebaseDatabase mdatabase;
    DatabaseReference mreference;
    FirebaseStorage mstorage;
    SanphamAdapter adapter;
    List<Sanpham> list;
    ProgressBar progressBar;
    private SpinnerAdapter spadapter;
    private final String[] listchucnang = {"Tất Cả", "Gía Cao Đến Thấp", "Gía Thấp Đến Cao"};
    private final int[] listIcon = {
            R.drawable.ic_baseline_phone_android_24,
            R.drawable.ic_baseline_trending_down_24,
            R.drawable.ic_baseline_trending_up_24,
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        recyclerView = findViewById(R.id.rcv_dienthoai);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        spinner = findViewById(R.id.spin_sp);
        spadapter = new Adapter_Spinner(getApplicationContext(), listchucnang, listIcon);
        spinner.setAdapter(spadapter);
        textView = findViewById(R.id.back_tk);
        progressBar = new ProgressBar(this);
        mdatabase = FirebaseDatabase.getInstance();
        mreference = mdatabase.getReference().child("Sanpham");
        mstorage = FirebaseStorage.getInstance();
        list = new ArrayList<Sanpham>();
        adapter = new SanphamAdapter(DienThoaiActivity.this, list);
        recyclerView.setAdapter(adapter);
        textView.setOnClickListener(view -> {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        });
        mreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Sanpham sanpham = snapshot.getValue(Sanpham.class);
                list.add(sanpham);
                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        xulyspinner();
    }

    public void xulyspinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: {
                        getdatagiam();
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    case 1: {
                        getdatatang();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getdatatang() {
        Query query = mreference.orderByChild("GiaTien");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Sanpham sanpham = snapshot.getValue(Sanpham.class);
                list.add(sanpham);
                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getdatagiam() {
        Query query = mreference.orderByChild("GiaTien");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Sanpham sanpham = snapshot.getValue(Sanpham.class);
                list.add(0, sanpham);
                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}