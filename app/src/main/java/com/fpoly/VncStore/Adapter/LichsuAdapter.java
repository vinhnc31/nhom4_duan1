package com.fpoly.VncStore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Oder;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LichsuAdapter extends RecyclerView.Adapter<LichsuAdapter.LichsuViewHodel> {
    private List<Hoadon> list;
    private List<Oder> oderList;
    private Oder oder;

    public LichsuAdapter(List<Hoadon> list, List<Oder> oderList, Oder oder) {
        this.list = list;
        this.oderList = oderList;
        this.oder = oder;
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
        if (hoadon == null) {
            return;
        }
        Picasso.get().load(hoadon.getImge()).into(holder.img_anh);
        holder.ten.setText(hoadon.getNamesp());
        holder.soluong.setText(String.valueOf(hoadon.getSoluong()));
        holder.gia.setText(hoadon.getGiasp());
        holder.trangthai.setText(hoadon.getTrangthai());
        holder.madonhang.setText(hoadon.getOrderNo().toUpperCase());
        for (Oder oder : oderList) {
            if (oder.getNgay().equals(hoadon.getOrderNo())) {
                holder.ngay.setText(oder.getNgay());
                break;
            }
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

        public LichsuViewHodel(@NonNull View itemView) {
            super(itemView);
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
