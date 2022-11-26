package com.fpoly.VncStore.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Activity.DetailedActivity;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.Spviewhoder> {
    private List<Sanpham> sanphamList;
    private Context context;

    public SanphamAdapter(Context context, List<Sanpham> sanphamList) {
        this.sanphamList = sanphamList;
        this.context = context;
        Log.d("zzzz", "SanphamAdapter: size= " +sanphamList.size() );
        for(int i =0; i<sanphamList.size(); i++)
            Log.d("zzzzz", "SanphamAdapter: "+ sanphamList.get(i).getName());
    }

    @NonNull
    @Override
    public Spviewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sanpham, parent, false);
        return new Spviewhoder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull Spviewhoder holder,int position) {
        Sanpham sp = sanphamList.get(position);
        Picasso.get().load(sp.getImage()).placeholder(R.drawable.dienthoai).fit().centerCrop().into(holder.imagesp);
        holder.tv_tensp.setText(""+sp.getName());
        holder.tv_giasp.setText(sp.getGia());
        holder.tv_khuyenmai.setText(sp.getKhuyenmai()+"%");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",sanphamList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (sanphamList != null){
            return sanphamList.size();
        }
        return 0;
    }

    public class Spviewhoder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imagesp;
        private TextView tv_tensp;
        private TextView tv_giasp;
        private TextView tv_khuyenmai;

        public Spviewhoder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardsp);
            imagesp = itemView.findViewById(R.id.img_spdienthoai1);
            tv_tensp = itemView.findViewById(R.id.tv_tensp1);
            tv_khuyenmai = itemView.findViewById(R.id.tv_khuyenmai);
            tv_giasp = itemView.findViewById(R.id.tv_giatien);
        }
    }
}
