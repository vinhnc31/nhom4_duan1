package com.fpoly.VncStore.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fpoly.VncStore.ChucNang.ChoxulyFragment;
import com.fpoly.VncStore.ChucNang.DagiaoFragment;
import com.fpoly.VncStore.ChucNang.DahuyFragment;
import com.fpoly.VncStore.ChucNang.DangGiaoFragment;
import com.fpoly.VncStore.ChucNang.TatCaFragment;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:{
                return new TatCaFragment();
            }
            case 1:{
                return new ChoxulyFragment();
            }
            case 2:{
                return new DahuyFragment();
            }
            case 3:{
                return new DangGiaoFragment();
            }
            case 4:{
                return new DagiaoFragment();
            }
            default:{
                return new TatCaFragment();
            }
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
