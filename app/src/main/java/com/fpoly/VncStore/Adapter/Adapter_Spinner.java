package com.fpoly.VncStore.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fpoly.VncStore.R;

public class Adapter_Spinner extends ArrayAdapter {
    private Context context;
    private String[] list;
    private int[] ListIcon;
    private ImageView imageView;
    private TextView textView;

    public Adapter_Spinner(@NonNull Context context, String[] list, int[] ListIcon) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.ListIcon = ListIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_spinner, null);
        }
        imageView = view.findViewById(R.id.img_chucnang);
        textView = view.findViewById(R.id.tv_chucnang);
        if (list.length != 0 && ListIcon.length != 0) {
            imageView.setImageResource(ListIcon[position]);
            textView.setText(list[position]);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_spinner_drop, null);
        }
        imageView = view.findViewById(R.id.img_chucnang);
        textView = view.findViewById(R.id.tv_chucnang);
        if (list.length != 0 && ListIcon.length != 0) {
            imageView.setImageResource(ListIcon[position]);
            textView.setText(list[position]);
        }
        return view;
    }
}
