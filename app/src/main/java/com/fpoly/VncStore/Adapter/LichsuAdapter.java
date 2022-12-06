package com.fpoly.VncStore.Adapter;

import static com.fpoly.VncStore.Activity.MainActivity.badgeDrawable;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Activity.ChitietActivity;
import com.fpoly.VncStore.Activity.MainActivity;
import com.fpoly.VncStore.Activity.TabletActivity;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Oder;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class LichsuAdapter extends RecyclerView.Adapter<LichsuAdapter.LichsuViewHodel> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Hoadon> list;
    private List<Oder> oderList;
    private Oder oder;

    public void setData(List<Hoadon> list, List<Oder> oderList) {
        this.list = list;
        this.oderList = oderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichsuViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new LichsuViewHodel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LichsuViewHodel holder, int position) {
        Hoadon hoadon = list.get(position);
        Oder oder1= oderList.get(position);
        if (hoadon == null) {
            return;
        }
        Picasso.get().load(hoadon.getImge()).into(holder.img_anh);
        holder.ten.setText(hoadon.getNamesp());
        holder.soluong.setText(String.valueOf(hoadon.getSoluong()));
        holder.gia.setText(formatPrice.format(hoadon.getGiasp()) + " VND");
        holder.trangthai.setText(hoadon.getTrangthai());
        holder.madonhang.setText(hoadon.getIdOder());
        for (Oder order : oderList){
            if (order.getOrderNo().equals(hoadon.getIdOder()) ){
                holder.ngay.setText(order.getNgaymua());
                break;
            }
        }
        for (Oder od : oderList) {
            if (od.getOrderNo().equals(hoadon.getIdOder())) {
                oder = od;
                break;
            }
        }
        for (Hoadon hd : list) {
            if (hoadon.getIdOder().equals(hd.getIdOder())) {
                oder.addListHoaDon(hd);
            }
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ChitietActivity.class);
            intent.putExtra("oder", oder1);
            Log.e("iuhafaflaj",""+oder1.getHoadonList().size());
            AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
            view.getContext().startActivity(intent);
            appCompatActivity.overridePendingTransition(R.anim.enter_right_to_left, R.anim.exit_right_to_left);
        });
        holder.huydon.setOnClickListener(v -> {
            hoadon.setTrangthai("Đã Hủy");
            list.set(position,hoadon);
            FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String email1 = user.getEmail();
            email1 = email1.replace(".", "_");
            DatabaseReference mreference = mdatabase.getReference("Oder/" + email1);
            DatabaseReference mreference1 = mdatabase.getReference("OderAdmin");
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("trangthai","Đã Hủy");
            mreference.child(oder1.getOrderNo()).child("detail").child(hoadon.getIdHoadon()).updateChildren(hashMap);
            mreference1.child(oder1.getOrderNo()).child("detailadmin").child(hoadon.getIdHoadon()).updateChildren(hashMap);

        });
        if (hoadon.getTrangthai().equals("Đã Hủy")) {
            holder.huydon.setVisibility(View.GONE);
        } else {
            holder.huydon.setVisibility(View.VISIBLE);
        }
        if (hoadon.getTrangthai().equals("Đã Nhận")) {
            holder.huydon.setVisibility(View.GONE);
        }
        if (hoadon.getTrangthai().equals("Đang vận chuyển")) {
            holder.huydon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;

    }

    public class LichsuViewHodel extends RecyclerView.ViewHolder {
        TextView madonhang, ten, ngay, soluong, gia, trangthai;
        ImageView img_anh;
        Button huydon;

        public LichsuViewHodel(@NonNull View itemView) {
            super(itemView);
            huydon = itemView.findViewById(R.id.btn_huy);
            madonhang = itemView.findViewById(R.id.tv_madathang);
            ten = itemView.findViewById(R.id.tv_tenspls);
            soluong = itemView.findViewById(R.id.tv_soluong);
            gia = itemView.findViewById(R.id.tv_gials);
            ngay = itemView.findViewById(R.id.tv_ngay1);
            trangthai = itemView.findViewById(R.id.tv_trangthai);
            img_anh = itemView.findViewById(R.id.img_anhls);

        }
    }
}
