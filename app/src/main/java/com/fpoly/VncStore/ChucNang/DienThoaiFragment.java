package com.fpoly.VncStore.ChucNang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpoly.VncStore.Adapter.SanphamAdapter;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class DienThoaiFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Sanpham> list;
    private SanphamAdapter adapter;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Sanpham");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dien_thoai, container, false);
        recyclerView = view.findViewById(R.id.rcvdienthoai);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}