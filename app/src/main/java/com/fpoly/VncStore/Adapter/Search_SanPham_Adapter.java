package com.fpoly.VncStore.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fpoly.VncStore.Model.Sanpham;
import com.fpoly.VncStore.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Search_SanPham_Adapter extends ArrayAdapter<Sanpham> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Sanpham> listSearchProduct;

    public Search_SanPham_Adapter(@NonNull Context context, int resource, @NonNull List<Sanpham> objects) {
        super(context, resource, objects);
        listSearchProduct= new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        }

        ImageView imgSearch = convertView.findViewById(R.id.img_search);
        TextView tvSearchName = convertView.findViewById(R.id.tv_search_name);
        TextView tvSearchPrice = convertView.findViewById(R.id.tv_search_price);

        Sanpham product = getItem(position);

        Picasso.get().load(product.getImage()).into(imgSearch);
        tvSearchName.setText(product.getName());
        tvSearchPrice.setText(formatPrice.format(product.getGia()) + " VNĐ");

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
                    listSuggest.addAll(listSearchProduct);
                }
                else {
                    String filter = constraint.toString().toLowerCase().trim();
                    for (Sanpham p : listSearchProduct){
                        if (p.getName().toLowerCase().contains(filter)){
                            Log.e("3333333",p.getName());
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