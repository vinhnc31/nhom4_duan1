package com.fpoly.VncStore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Model.Photo;
import com.fpoly.VncStore.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoVIewhoder>{
    private List<Photo> list;

    public PhotoAdapter(List<Photo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PhotoVIewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider,parent,false);
        return new PhotoVIewhoder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVIewhoder holder, int position) {
        Photo photo=list.get(position);
        if (photo==null){
            return;
        }
        holder.img.setImageResource(photo.getAnh());

    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class PhotoVIewhoder extends RecyclerView.ViewHolder{
        private ImageView img;
        public PhotoVIewhoder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_silder);
        }
    }
}
