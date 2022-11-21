package com.fpoly.VncStore.ChucNang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpoly.VncStore.Adapter.GiohangAdapter;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;

import java.util.List;


public class GioHangFragment extends Fragment {
    private int totalPrice;
    private Sanpham sanpham;
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private View v;
    private List<Sanpham> sanphamList;
    private TextView tv_giatien;
    private GiohangAdapter giohangAdapter;
    private RecyclerView recyclerView;
    private EditText ed_name, ed_diachi, ed_phone;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        Anhxa();
        return v;
    }

    public void Anhxa() {
        sanpham = new Sanpham();
//       giohangAdapter = new GiohangAdapter();
       tv_giatien = v.findViewById(R.id.tv_tongtien);
        recyclerView = v.findViewById(R.id.rcv_giohang);
        imageView = v.findViewById(R.id.img_giohang);
        ed_name = v.findViewById(R.id.edt_name);
        ed_diachi = v.findViewById(R.id.edt_home);
        ed_phone = v.findViewById(R.id.edt_phone);
    }

}