package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.fpoly.VncStore.R;

public class Loading extends AppCompatActivity {
    Context context;
    Dialog dialog;
    public Loading(Context context){
        this.context=context;
    }
    public void showDialog(){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.activity_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.create();
        dialog.show();
    }
    public void dismiss(){
        dialog.dismiss();
    }
}