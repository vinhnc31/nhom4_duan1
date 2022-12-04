package com.fpoly.VncStore.Adapter;


import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Search_SanPham_Adapter extends ArrayAdapter<Sanpham> {
    private List<Sanpham> list;
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");


    public Search_SanPham_Adapter(@NonNull Context context, int resource, @NonNull List<Sanpham> objects) {
        super(context, resource, objects);
        list = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);

        }
        ImageView img_anh = convertView.findViewById(R.id.img_search);
        TextView tv_ten = convertView.findViewById(R.id.tv_search_name);
        TextView tv_gia = convertView.findViewById(R.id.tv_search_price);

        Sanpham sanpham = list.get(position);
        Picasso.get().load(sanpham.getImage()).into(img_anh);
        tv_ten.setText(sanpham.getName());
        tv_gia.setText(formatPrice.format(sanpham.getGia()) + " VNĐ");

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Sanpham> listSuggest = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    listSuggest.addAll(list);
                }
                else {
                    String filter = constraint.toString().toLowerCase().trim();
                    for (Sanpham p : list){
                        if (p.getName().toLowerCase().contains(filter)){
                            listSuggest.add(p);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listSuggest;
                filterResults.count = listSuggest.size();

                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll( (List<Sanpham>) results.values);
                notifyDataSetInvalidated();
            }
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ( (Sanpham) resultValue).getName();
            }
        };
    }
}
