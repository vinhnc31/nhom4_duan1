package com.fpoly.VncStore.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Activity.ChitietActivity;
import com.fpoly.VncStore.Model.Order;
import com.fpoly.VncStore.R;
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
    private List<Order> oderList;

    public void setData( List<Order> oderList) {
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
        Order oder1= oderList.get(position);
        if (oder1 ==null){
            return;
        }
        Picasso.get().load(oder1.getSanphamList().get(0).getImage()).into(holder.img_anh);
        holder.ten.setText(oder1.getSanphamList().get(0).getName());
        holder.soluong.setText(String.valueOf(oder1.getSoluong()));
        holder.gia.setText(formatPrice.format(oder1.getSanphamList().get(0).getGia()) + " VND");
        holder.trangthai.setText(oder1.getTrangthai());
        holder.madonhang.setText(oder1.getOrderNo());
        holder.ngay.setText(oder1.getNgaymua());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ChitietActivity.class);
            intent.putExtra("order", oder1);
            AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
            view.getContext().startActivity(intent);
            appCompatActivity.overridePendingTransition(R.anim.enter_right_to_left, R.anim.exit_right_to_left);
        });
        holder.huydon.setOnClickListener(v -> {
            oder1.setTrangthai("Đã Hủy");
            DatabaseReference mReference=FirebaseDatabase.getInstance().getReference("Order");
            HashMap<String,Object> hashMap =new HashMap<>();
            hashMap.put("trangthai","Đã Hủy");
            mReference.child(oder1.getOrderNo()).updateChildren(hashMap);
        });
        if (oder1.getTrangthai().equals("Đã Hủy")) {
            holder.huydon.setVisibility(View.GONE);
        } else {
            holder.huydon.setVisibility(View.VISIBLE);
        }
        if (oder1.getTrangthai().equals("Đã Nhận")) {
            holder.huydon.setVisibility(View.GONE);
        }
        if (oder1.getTrangthai().equals("Đang vận chuyển")) {
            holder.huydon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
            return oderList.size();

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
