package com.fpoly.VncStore.ChucNang;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
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
import android.widget.Toast;

import com.fpoly.VncStore.Adapter.GiohangAdapter;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class GioHangFragment extends Fragment {
    private int totalPrice;
    private Sanpham sanpham;
    private RelativeLayout relativeLayout, relativeLayout1;
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
        setVisibilityView();
        return v;
    }

    public void Anhxa() {
        sanpham = new Sanpham();
        sanphamList = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();
        giohangAdapter = new GiohangAdapter();
        tv_giatien = v.findViewById(R.id.tv_tongtien);
        relativeLayout = v.findViewById(R.id.giohang1);
        relativeLayout1 = v.findViewById(R.id.giohanggiong);
        recyclerView = v.findViewById(R.id.rcv_giohang);
        ed_name = v.findViewById(R.id.edt_name);
        ed_diachi = v.findViewById(R.id.edt_home);
        ed_phone = v.findViewById(R.id.edt_phone);
        button = v.findViewById(R.id.btn_dathang);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                // Thêm dữ liệu các thông tin đã order
                addDataOrder();
            }
        });
    }

    // Set trạng thái hiển thị các view
    private void setVisibilityView() {
        if (MainActivity.sanphamList.size() == 0) {
            // Hiển thị giỏ hàng rỗng
            setVisibilityEmptyCart();
        } else {
            // Hiển thị giỏ hàng
            setVisibilityCart();
            setDataProductCartAdapter();
        }
    }

    // set data ProductCartAdapter
    private void setDataProductCartAdapter() {
        giohangAdapter.setData(MainActivity.sanphamList, mainActivity, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(giohangAdapter);
    }

    // Hiển thị giỏ hàng rỗng
    public void setVisibilityEmptyCart() {
        relativeLayout.setVisibility(View.GONE);
        relativeLayout1.setVisibility(View.VISIBLE);
    }

    // Hiển thị giỏ hàng
    private void setVisibilityCart() {
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout1.setVisibility(View.GONE);
        tv_giatien.setText(getTotalPrice() + " VNĐ");
    }

    // lấy giá trị tổng tiền tất cả sản phẩm trong giỏ hàng
    private int getTotalPrice() {
        for (Sanpham sanpham : MainActivity.sanphamList) {
            int priceProduct = Integer.parseInt(sanpham.getGia());
            totalPrice = totalPrice + priceProduct * sanpham.getNumProduct();
        }
        return totalPrice;
    }

    // Set giá trị hiển thị tổng tiền khi tăng giảm số lượng
    // mode = 0 : giảm
    // mode = 1 : tăng
    public void setTotalPrice(int mode, int count, int priceProduct) {
        if (mode == 0) {
            totalPrice = totalPrice - priceProduct * count;
        } else if (mode == 1) {
            totalPrice = totalPrice + priceProduct * count;
        }

        tv_giatien.setText(totalPrice + " VNĐ");
    }

    // Set sô lượng sản phẩm sau nhấn nút tăng giảm
    public void setCountForProduct(int possion, int countProduct) {
        MainActivity.sanphamList.get(possion).setNumProduct(countProduct);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addDataOrder() {
        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
        DatabaseReference mreference = mdatabase.getReference("Hoadon");

        HashMap<String, Object> hashMap = new HashMap<>();
        Date date = new Date(System.currentTimeMillis());
        hashMap.put("ngaymua", date.toString());
        hashMap.put("tenkhachhang", ed_name.getText().toString());
        hashMap.put("diachi", ed_diachi.getText().toString());
        hashMap.put("phone", ed_phone.getText().toString());
        int num = 0;
        for (Sanpham sanpham : MainActivity.sanphamList) {
            num = num + sanpham.getNumProduct();
        }
        hashMap.put("soluong", num);
        hashMap.put("tongtien", totalPrice);
        String oderkey = mreference.push().getKey();
        mreference.child(oderkey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Hoadon> listDetailOrder = makeDetailOrder(oderkey);
                // Add thông tin detail order
                for (Hoadon detailOrder : listDetailOrder) {

                    mreference.child(oderkey).child("detail").child(mreference.push().getKey())
                            .setValue(detailOrder).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Đã đăng ký đơn hàng", Toast.LENGTH_SHORT).show();
                                    MainActivity.sanphamList.clear();
                                    setVisibilityEmptyCart();
                                }
                            });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Đăng ký đơn hàng thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Hoadon> makeDetailOrder( String odrNo){
        List<Hoadon> listDetailOrder = new ArrayList<>();
        for (Sanpham product : mainActivity.getListCartProduct()){
            Hoadon detailOrder = new Hoadon();
            detailOrder.setOrderNo(odrNo);
            detailOrder.setNamesp(product.getName());
            detailOrder.setGiasp(product.getGia());
            detailOrder.setImge(product.getImage());
            detailOrder.setSoluong(product.getNumProduct());
            detailOrder.setTrangthai("Đang chờ xác nhận");
            listDetailOrder.add(detailOrder);
        }
        return listDetailOrder;
    }
}