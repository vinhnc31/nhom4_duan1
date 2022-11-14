package com.fpoly.VncStore.ChucNang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpoly.VncStore.Adapter.DanhmucAdapter;
import com.fpoly.VncStore.Adapter.PhotoAdapter;
import com.fpoly.VncStore.Model.Danhmuc;
import com.fpoly.VncStore.Model.Photo;
import com.fpoly.VncStore.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class Home extends Fragment {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    ViewPager2 viewPager2;
    CircleIndicator3 circleIndicator3;
    private ArrayList<Danhmuc>lists=new ArrayList<>();
    private List<Photo> mlist;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == mlist.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = v.findViewById(R.id.viewPager2);
        circleIndicator3 = v.findViewById(R.id.circle);
        mlist = getList();
        PhotoAdapter adapter = new PhotoAdapter(mlist);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);
        recyclerView=v.findViewById(R.id.recyclerView);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
        recyclerview();
        return v;
    }
    private void recyclerview() {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        ArrayList<Danhmuc> list=new ArrayList<>();
        list.add(new Danhmuc("Laptop",R.drawable.laptop));
        list.add(new Danhmuc("Điện Thoại",R.drawable.dienthoai));
        list.add(new Danhmuc("Phụ Kiện",R.drawable.phukien));
        list.add(new Danhmuc("Tablet",R.drawable.tablet));
        list.add(new Danhmuc("Ốp lưng",R.drawable.oplung));
        adapter=new DanhmucAdapter(list);
        recyclerView.setAdapter(adapter);
        lists=new ArrayList<>();
        adapter=new DanhmucAdapter(lists);
    }

    private List<Photo> getList() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.anh1));
        list.add(new Photo(R.drawable.anh2));
        list.add(new Photo(R.drawable.anh3));
        list.add(new Photo(R.drawable.anh4));
        list.add(new Photo(R.drawable.anh5));
        list.add(new Photo(R.drawable.anh6));
        list.add(new Photo(R.drawable.anh7));
        list.add(new Photo(R.drawable.anh8));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}