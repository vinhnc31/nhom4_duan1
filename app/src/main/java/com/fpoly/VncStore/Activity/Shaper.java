package com.fpoly.VncStore.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.fpoly.VncStore.Login.SignIn;
import com.fpoly.VncStore.MainActivity;
import com.fpoly.VncStore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Shaper extends AppCompatActivity {
    ImageView imglogo,img_nen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaper);
        img_nen=findViewById(R.id.img_intro);
        imglogo=findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                nextActivity();
                startActivity(new Intent(Shaper.this, SignIn.class));
            }

        },3120);
    }
    public void nextActivity(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            // chưa login
            startActivity(new Intent(Shaper.this, SignIn.class));
        } else {
            // đã login
            startActivity(new Intent(Shaper.this, MainActivity.class));
        }
        finishAffinity();
    }
}