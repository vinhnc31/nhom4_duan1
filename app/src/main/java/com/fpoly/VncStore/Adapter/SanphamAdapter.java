package com.fpoly.VncStore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;

import java.util.List;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.Spviewhoder> {
    private List<Sanpham> sanphamList;
private Context context;
    public SanphamAdapter(Context context,List<Sanpham> sanphamList) {
        this.sanphamList = sanphamList;
        this.context = context;
    }

    @NonNull
    @Override
    public Spviewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sanpham, parent, false);
        return new Spviewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Spviewhoder holder, int position) {
        Sanpham sp = sanphamList.get(position);
        if (sp == null) {
            return;
        }
        Glide.with(context).load(sanphamList.get(position).getHinhanh()).into(holder.imagesp);
        holder.tv_tensp.setText(sp.getTensanpham());
        holder.tv_giamoi.setText(sp.getGiakhuyenmai());
        holder.tv_giasp.setText(sp.getGianiemyet());
    }

    @Override
    public int getItemCount() {
        if (sanphamList != null) {
            return sanphamList.size();
        }
        return 0;
    }

    public class Spviewhoder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imagesp;
        private TextView tv_tensp;
        private TextView tv_giasp;
        private TextView tv_giamoi;

        public Spviewhoder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardsp);
            imagesp = itemView.findViewById(R.id.img_spdienthoai);
            tv_tensp = itemView.findViewById(R.id.tv_tensp);
            tv_giasp = itemView.findViewById(R.id.tv_giatien);
            tv_giamoi = itemView.findViewById(R.id.tv_giatiencu);
        }
    }
}
