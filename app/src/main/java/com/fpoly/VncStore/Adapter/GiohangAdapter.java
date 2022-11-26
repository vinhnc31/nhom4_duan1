package com.fpoly.VncStore.Adapter;

import com.fpoly.VncStore.ChucNang.GioHangFragment;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.Viewhoder> {
    private List<Sanpham> sanphamList;
    private int countProduct;
    private Context context;
    private GioHangFragment gioHangFragment;

    public GiohangAdapter(List<Sanpham> sanphamList, Context context, GioHangFragment gioHangFragment) {
        this.sanphamList = sanphamList;
        this.context = context;
        this.gioHangFragment = gioHangFragment;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_giohang, parent, false);
        return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Sanpham sanpham = sanphamList.get(position);
        Picasso.get().load(sanpham.getImage()).placeholder(R.drawable.dienthoai).fit().centerCrop().into(holder.img_sanpham);
        holder.tv_ten.setText(sanpham.getName());
        holder.tv_gia.setText(sanpham.getGia());
        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (sanphamList != null) {
            return sanphamList.size();
        }
        return 0;
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        private TextView tv_ten;
        private TextView tv_gia;
        private TextView tv_soluong;
        private ImageView img_delete;
        private ImageView img_remove;
        private ImageView img_add;
        private ImageView img_sanpham;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            tv_ten = itemView.findViewById(R.id.tv_tensp2);
            tv_gia = itemView.findViewById(R.id.tv_giasp);
            tv_soluong = itemView.findViewById(R.id.tv_soluong);
            img_add = itemView.findViewById(R.id.img_add);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_remove = itemView.findViewById(R.id.img_remove);
            img_sanpham = itemView.findViewById(R.id.img_giohang1);
        }
    }
}
