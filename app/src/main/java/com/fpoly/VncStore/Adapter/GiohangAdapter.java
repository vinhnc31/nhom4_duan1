package com.fpoly.VncStore.Adapter;

import com.fpoly.VncStore.Activity.MainActivity;
import com.fpoly.VncStore.ChucNang.GioHangFragment;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.Viewhoder> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private int countProduct;
    private List<Sanpham> sanphamList;
    MainActivity mainActivity;
    Context context;

    public GiohangAdapter(Context context) {
        this.context = context;
    }

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

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Sanpham sanpham = sanphamList.get(position);
        Picasso.get().load(sanpham.getImage()).placeholder(R.drawable.dienthoai).fit().centerCrop().into(holder.img_sanpham);
        holder.tv_ten.setText(sanpham.getName());
        holder.tv_gia.setText(formatPrice.format(sanpham.getGia()) + " VNĐ");
        holder.tv_soluong.setText(String.valueOf(sanpham.getNumProduct()));
        holder.img_add.setOnClickListener(view -> {
            countProduct = Integer.parseInt(holder.tv_soluong.getText().toString());
            if (countProduct < 100) {
                countProduct++;
                holder.tv_soluong.setText(String.valueOf(countProduct));
                mainActivity.setCountForProduct(position, countProduct);
                gioHangFragment.setTotalPrice(1, 1, sanpham.getGia());
                notifyDataSetChanged();
            }
        });
        holder.img_remove.setOnClickListener(view -> {
            countProduct = Integer.parseInt(holder.tv_soluong.getText().toString());
            if (countProduct > 1) {
                countProduct--;
                holder.tv_soluong.setText(String.valueOf(countProduct));
                mainActivity.setCountForProduct(position, countProduct);
                gioHangFragment.setTotalPrice(0, 1, sanpham.getGia());
                notifyDataSetChanged();
            }
        });
        holder.img_delete.setOnClickListener(view -> {
            final Dialog dialog = new Dialog(context, com.airbnb.lottie.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_delete);
            dialog.show();
            Button btn_ok = dialog.findViewById(R.id.btn_ok_delete);
            btn_ok.setOnClickListener(view1 -> {
                countProduct = Integer.parseInt(holder.tv_soluong.getText().toString());
                gioHangFragment.setTotalPrice(0, countProduct, sanpham.getGia());

                sanphamList.remove(position);
                if (sanphamList.size() == 0) {
                    gioHangFragment.setVisibilityEmptyCart();
                }
                MainActivity.badgeDrawable.setNumber(sanphamList.size());
                mainActivity.Remove(position);
                notifyDataSetChanged();
                dialog.dismiss();
            });
           Button btn_calcle = dialog.findViewById(R.id.btn_calcle_delete);
           btn_calcle.setOnClickListener(view1 -> {
               dialog.dismiss();
           });
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
