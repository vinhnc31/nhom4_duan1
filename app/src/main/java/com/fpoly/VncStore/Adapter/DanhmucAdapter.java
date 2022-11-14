package com.fpoly.VncStore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.ChucNang.DienThoaiFragment;
import com.fpoly.VncStore.ChucNang.DoiMatkhauFragment;
import com.fpoly.VncStore.ChucNang.LaptopFragment;
import com.fpoly.VncStore.ChucNang.OplungFragment;
import com.fpoly.VncStore.ChucNang.PhuKienFragment;
import com.fpoly.VncStore.ChucNang.TabletFragment;
import com.fpoly.VncStore.Model.Danhmuc;
import com.fpoly.VncStore.R;

import java.util.ArrayList;

public class DanhmucAdapter extends RecyclerView.Adapter<DanhmucAdapter.ViewHodel> {
    ArrayList<Danhmuc> list;

    public DanhmucAdapter(ArrayList<Danhmuc> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc,parent,false);
        return new ViewHodel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhmucAdapter.ViewHodel holder, int position) {
        Danhmuc danhmuc=list.get(position);
        if (danhmuc==null){
            return;
        }
        holder.img_anh.setOnClickListener(view -> {
            switch (position){
                case 0:{
                    AppCompatActivity appCompatActivity=(AppCompatActivity) view.getContext();
                    Fragment fragment=new LaptopFragment();
                    FragmentTransaction fragmentTransaction=appCompatActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction
                            .setCustomAnimations(R.anim.item_last,R.anim.item_pop_last,R.anim.item_next,R.anim.item_pop_next)
                            .replace(R.id.chuyen0, fragment).addToBackStack("uu").commit();
                    break;
                }
                case 1:{
                    AppCompatActivity appCompatActivity=(AppCompatActivity) view.getContext();
                    Fragment fragment=new DienThoaiFragment();
                    FragmentTransaction fragmentTransaction=appCompatActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.item_last,R.anim.item_pop_last,R.anim.item_next,R.anim.item_pop_next)
                            .replace(R.id.chuyen0, fragment).addToBackStack(null).commit();
                    break;
                }
                case 2:{
                    AppCompatActivity appCompatActivity=(AppCompatActivity) view.getContext();
                    Fragment fragment=new PhuKienFragment();
                    FragmentTransaction fragmentTransaction=appCompatActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.item_last,R.anim.item_pop_last,R.anim.item_next,R.anim.item_pop_next)
                            .replace(R.id.chuyen0, fragment).addToBackStack(null).commit();
                    break;
                }
                case 3:{
                    AppCompatActivity appCompatActivity=(AppCompatActivity) view.getContext();
                    Fragment fragment=new TabletFragment();
                    FragmentTransaction fragmentTransaction=appCompatActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.item_last,R.anim.item_pop_last,R.anim.item_next,R.anim.item_pop_next)
                            .replace(R.id.chuyen0, fragment).addToBackStack(null).commit();
                    break;
                }
                case 4:{
                    AppCompatActivity appCompatActivity=(AppCompatActivity) view.getContext();
                    Fragment fragment=new OplungFragment();
                    FragmentTransaction fragmentTransaction=appCompatActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.item_last,R.anim.item_pop_last,R.anim.item_next,R.anim.item_pop_next)
                            .replace(R.id.chuyen0, fragment).addToBackStack(null).commit();
                    break;
                }

            }
        });
        int anh =danhmuc.getAnh();
        String name =danhmuc.getTen();
        holder.setData(anh,name);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView tv_ten;
        ImageView img_anh;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            tv_ten=itemView.findViewById(R.id.tv_ten);
            img_anh=itemView.findViewById(R.id.img_anh1);
        }

        public void setData(int anh, String name) {
            img_anh.setImageResource(anh);
            tv_ten.setText(name);
        }
    }
}
