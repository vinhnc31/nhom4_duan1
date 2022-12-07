package com.fpoly.VncStore.Adapter;


import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Activity.ChitietActivity;
import com.fpoly.VncStore.Activity.DetailedActivity;
import com.fpoly.VncStore.Model.Hoadon;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Search_SanPham_Adapter extends RecyclerView.Adapter<Search_SanPham_Adapter.SanPhamViewHolde> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Sanpham> list;
    private Context context;

    public Search_SanPham_Adapter(List<Sanpham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SanPhamViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new SanPhamViewHolde(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolde holder, int position) {
        Sanpham item=list.get(position);
        if (item == null){
            return;
        }
        Picasso.get().load(item.getImage()).into(holder.img_anh);
        holder.tv_ten.setText(item.getName());
        holder.tv_gia.setText(formatPrice.format(item.getGia()) + " VNĐ");
        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra("detail",list.get(position));
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        if (list.size()!=0){
            return list.size();
        }
        return 0;
    }

    public class SanPhamViewHolde extends RecyclerView.ViewHolder{

        ImageView img_anh;
        TextView tv_ten,tv_gia;

        public SanPhamViewHolde(@NonNull View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.img_search);
            tv_ten = itemView.findViewById(R.id.tv_search_name);
            tv_gia = itemView.findViewById(R.id.tv_search_price);
        }
    }
}