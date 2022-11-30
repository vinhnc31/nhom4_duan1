package com.fpoly.VncStore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.fpoly.VncStore.ChucNang.HomeFragment;
import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchSanPhamAdapter extends ArrayAdapter<Sanpham> {

    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Sanpham> listSearchSanPham;

    public SearchSanPhamAdapter(@NonNull Context context, int resource, @NonNull List<Sanpham> objects) {
        super(context, resource, objects);
        listSearchSanPham= new ArrayList<>(objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            //de y from
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_sanpham, parent,false);
        }

        ImageView imgSearch = convertView.findViewById(R.id.img_item_search_id);
        TextView tvSearchName = convertView.findViewById(R.id.tv_search_tenSP_id);
        TextView tvSearchGia = convertView.findViewById(R.id.tv_search_giaSP_id);

        Sanpham sanpham = getItem(position);

        Glide.with(imgSearch.getContext()).load(sanpham.getImage()).into(imgSearch);
        tvSearchName.setText(sanpham.getName());
        tvSearchGia.setText(formatPrice.format(sanpham.getGia()) + "VNĐ");

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Sanpham> listSanPhamSuggest = new ArrayList<>();

                if(constraint == null || constraint.length() == 0) {
                    listSanPhamSuggest.addAll(listSearchSanPham);
                } else {
                    String filter = constraint.toString().toLowerCase().trim();
                    for (Sanpham sp: listSearchSanPham) {
                        if (sp.getName().toLowerCase().contains(filter)) {
                            listSanPhamSuggest.add(sp);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listSanPhamSuggest;
                filterResults.count = listSanPhamSuggest.size();


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List<Sanpham>) results.values);
                notifyDataSetInvalidated();

            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Sanpham) resultValue).getName();
            }
        };
    }
}
