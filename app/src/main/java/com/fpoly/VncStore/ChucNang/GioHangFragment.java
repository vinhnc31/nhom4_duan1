package com.fpoly.VncStore.ChucNang;

import static com.fpoly.VncStore.Activity.MainActivity.badgeDrawable;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Activity.HistoryActivity;
import com.fpoly.VncStore.Activity.MainActivity;
import com.fpoly.VncStore.Adapter.GiohangAdapter;
import com.fpoly.VncStore.Model.Order;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.Model.User;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GioHangFragment extends Fragment {
    private int totalPrice;
    private Sanpham sanpham;
    private RelativeLayout relativeLayout, relativeLayout1;
    private View v;
    private MainActivity mainActivity;
    private TextView tv_giatien;
    private GiohangAdapter giohangAdapter;
    private RecyclerView recyclerView;
    private EditText ed_name, ed_diachi, ed_phone;
    private Button button;
    private DecimalFormat format;
    Toast toast;

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
        mainActivity = (MainActivity) getActivity();
        giohangAdapter = new GiohangAdapter(getContext());
        tv_giatien = v.findViewById(R.id.tv_tongtien);
        relativeLayout = v.findViewById(R.id.giohang1);
        relativeLayout1 = v.findViewById(R.id.giohangrong);
        recyclerView = v.findViewById(R.id.rcv_giohang);
        ed_name = v.findViewById(R.id.edt_name);
        ed_diachi = v.findViewById(R.id.edt_home);
        ed_phone = v.findViewById(R.id.edt_phone);
        button = v.findViewById(R.id.btn_dathang);
        toast = new Toast(getContext());
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                // Th??m d??? li???u c??c th??ng tin ???? order
                addDataOrder();
            }
        });
        format = new DecimalFormat("###,###,###");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                if (u != null) {
                    ed_name.setText(user.getDisplayName());
                    ed_phone.setText(u.getSo());
                    ed_diachi.setText(u.getDiachi());
                } else {
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Set tr???ng th??i hi???n th??? c??c view
    private void setVisibilityView() {
        if (MainActivity.sanphamList.size() == 0) {
            // Hi???n th??? gi??? h??ng r???ng
            setVisibilityEmptyCart();
        } else {
            // Hi???n th??? gi??? h??ng
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

    // Hi???n th??? gi??? h??ng r???ng
    public void setVisibilityEmptyCart() {
        relativeLayout.setVisibility(View.GONE);
        relativeLayout1.setVisibility(View.VISIBLE);
    }

    // Hi???n th??? gi??? h??ng
    private void setVisibilityCart() {
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout1.setVisibility(View.GONE);
        String total = format.format(getTotalPrice());
        tv_giatien.setText(total + " VN??");
    }

    // l???y gi?? tr??? t???ng ti???n t???t c??? s???n ph???m trong gi??? h??ng
    private int getTotalPrice() {
        totalPrice = 0;
        for (Sanpham sanpham : MainActivity.sanphamList) {
            int priceProduct = sanpham.getGia();
            totalPrice = totalPrice + priceProduct * sanpham.getNumProduct();
        }
        return totalPrice;
    }

    // Set gi?? tr??? hi???n th??? t???ng ti???n khi t??ng gi???m s??? l?????ng
    // mode = 0 : gi???m
    // mode = 1 : t??ng
    public void setTotalPrice(int mode, int count, int priceProduct) {
        if (mode == 0) {
            totalPrice = totalPrice - priceProduct * count;
        } else if (mode == 1) {
            totalPrice = totalPrice + priceProduct * count;
        }

        tv_giatien.setText(format.format(totalPrice) + " VN??");
    }

    // Set s?? l?????ng s???n ph???m sau nh???n n??t t??ng gi???m
    public void setCountForProduct(int possion, int countProduct) {
        MainActivity.sanphamList.get(possion).setNumProduct(countProduct);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addDataOrder() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email1 = user.getEmail();
        email1 = email1.replace(".", "_");
        Date date = new Date(System.currentTimeMillis());
        int num = 0;
        for (Sanpham sanpham : MainActivity.sanphamList) {
            num = num + sanpham.getNumProduct();
        }
        View view_true = LayoutInflater.from(getContext()).inflate(R.layout.dialog_dathang_true,null);
        View view_fail = LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_fail,null);
        if (validate() > 0) {
            Order order = new Order(ed_diachi.getText().toString(), ed_name.getText().toString(), ed_phone.getText().toString(), date.toString(), num, totalPrice, "??ang ch??? x??c nh???n", email1, MainActivity.sanphamList);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Order");
            String finalEmail = email1;
            reference.child(reference.push().getKey()).setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    MainActivity.sanphamList.clear();
                    badgeDrawable.setNumber(MainActivity.sanphamList.size());
                    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Cart").child(finalEmail);
                    reference2.removeValue();
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(view_true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    startActivity(new Intent((MainActivity) getActivity(), HistoryActivity.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(view_fail);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
        }
    }

    public int validate() {
        int check = 1;
        String phone = "(84|0[3|5|7|8|9])+([0-9]{8})";
        if (ed_name.getText().length() == 0) {
            Toast.makeText(mainActivity, "T??n kh??ch h??ng kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return check - 1;

        } else if (ed_diachi.getText().length() == 0) {
            Toast.makeText(mainActivity, "?????a ch??? kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return check - 1;

        } else if (ed_phone.getText().length() == 0) {
            Toast.makeText(mainActivity, "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return check - 1;

        } else if (!ed_phone.getText().toString().matches(phone)) {
            Toast.makeText(mainActivity, "S??? ??i???n tho???i kh??ng ????ng ?????nh d???ng", Toast.LENGTH_SHORT).show();
            return check - 1;
        }

        return check;
    }

    @Override
    public void onResume() {
        super.onResume();
        setDataProductCartAdapter();
        setVisibilityCart();
        setVisibilityView();
    }
}