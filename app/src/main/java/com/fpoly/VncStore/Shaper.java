package com.fpoly.VncStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Space;

import com.fpoly.VncStore.Login.SignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Shaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaper);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },10000);
    }
    public void nextActivity(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            // chưa login
            startActivity(new Intent(Shaper.this, SignIn.class));
        } else {
            // đã login
            startActivity(new Intent(Shaper.this,MainActivity.class));
        }
        finishAffinity();
    }
}