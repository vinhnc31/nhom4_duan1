package com.fpoly.VncStore.Adapter;

//<<<<<<< HEAD
//
//=======
//>>>>>>> Loc
import com.fpoly.VncStore.ChucNang.GioHangFragment;
import com.fpoly.VncStore.MainActivity;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
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

    private int countProduct;
    private List<Sanpham> sanphamList;
    MainActivity mainActivity;

    public void setData(List<Sanpham> sanphamList, MainActivity mainActivity, GioHangFragment gioHangFragment) {
        this.sanphamList = sanphamList;
        this.mainActivity = mainActivity;
        this.gioHangFragment = gioHangFragment;
    }

    private GioHangFragment gioHangFragment;


    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_giohang, parent, false);
        return new Viewhoder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder,int position) {
        Sanpham sanpham = sanphamList.get(position);
        Picasso.get().load(sanpham.getImage()).placeholder(R.drawable.dienthoai).fit().centerCrop().into(holder.img_sanpham);
        holder.tv_ten.setText(sanpham.getName());
        holder.tv_gia.setText(sanpham.getGia());
        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countProduct = Integer.parseInt(holder.tv_soluong.getText().toString());
                if (countProduct < 10){
                    countProduct++;
                    holder.tv_soluong.setText(String.valueOf(countProduct));
                    mainActivity.setCountForProduct(position,countProduct);
                    gioHangFragment.setTotalPrice(1,1, Integer.parseInt(sanpham.getGia()));
                    notifyDataSetChanged();
                }
            }
        });
        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countProduct = Integer.parseInt(holder.tv_soluong.getText().toString());
                if (countProduct > 1){
                    countProduct--;
                    holder.tv_soluong.setText(String.valueOf(countProduct));
                    mainActivity.setCountForProduct(position,countProduct);
                    gioHangFragment.setTotalPrice(0,1, Integer.parseInt(sanpham.getGia()));
                    notifyDataSetChanged();
                }
            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countProduct = Integer.parseInt(holder.tv_soluong.getText().toString());

                gioHangFragment.setTotalPrice(0,countProduct, Integer.parseInt(sanpham.getGia()));

                sanphamList.remove(position);
                if (sanphamList.size() == 0){
                    gioHangFragment.setVisibilityEmptyCart();
                }
                notifyDataSetChanged();
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
            img_delete = itemView.findViewById(R.id.img_clear);
            img_remove = itemView.findViewById(R.id.img_remove);
            img_sanpham = itemView.findViewById(R.id.img_giohang1);
        }
    }
}
