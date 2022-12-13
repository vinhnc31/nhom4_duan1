package com.fpoly.VncStore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Model.Order;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ChitietAdapter extends RecyclerView.Adapter<ChitietAdapter.ChiTietViewHolder>{
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Sanpham> list;

    public void SetData(List<Sanpham> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChiTietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet,parent,false);
        return new ChiTietViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietViewHolder holder, int position) {
        Sanpham item=list.get(position);
        if (item == null){
            return;
        }
            Picasso.get().load(item.getImage()).into(holder.img_anh);
            holder.tv_ten.setText(item.getName());
            holder.tv_so.setText(String.valueOf(item.getNumProduct()));
            holder.tv_gia.setText(formatPrice.format(item.getGia()) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        if (list.size()!=0){
            return list.size();
        }
        return 0;
    }

    public class ChiTietViewHolder extends RecyclerView.ViewHolder{

        ImageView img_anh;
        TextView tv_ten,tv_so,tv_gia,tv_trangthai;

        public ChiTietViewHolder(@NonNull View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.img_anhct);
            tv_ten = itemView.findViewById(R.id.tv_tenspct);
            tv_so = itemView.findViewById(R.id.tv_soluongct1);
            tv_gia = itemView.findViewById(R.id.tv_giact);
        }
    }
}
