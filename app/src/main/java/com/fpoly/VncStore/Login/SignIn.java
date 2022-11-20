package com.fpoly.VncStore.Login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.fpoly.VncStore.Activity.Loading;
import com.fpoly.VncStore.Activity.MainActivity;
import com.fpoly.VncStore.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    TextInputLayout intro1,intro2;
    EditText ed_tendn, ed_mk;
    Button btn_login;
    TextView tv_dk,tv_login,tv_acc;
    CardView cart;
    ImageView img_login,layout1,layout2,layout3,imglogo;
    private FirebaseAuth auth;
    Loading dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cart=findViewById(R.id.cart);
        imglogo=findViewById(R.id.imagelogo);
        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        intro1=findViewById(R.id.TextInputLayout1);
        intro2=findViewById(R.id.TextInputLayout2);
        ed_tendn = findViewById(R.id.ed_userName);
        ed_mk = findViewById(R.id.ed_pasWord);
        tv_acc=findViewById(R.id.text_acc);
        btn_login = findViewById(R.id.btn_login);
        tv_dk = findViewById(R.id.tv_signUp);
        img_login=findViewById(R.id.imagelogo);
        tv_login=findViewById(R.id.tv_login);
        auth = FirebaseAuth.getInstance();
        tv_dk.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUp.class));
            overridePendingTransition(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
        });
        amintranstion();
        dialog=new Loading(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicksingin();
            }
        });
    }
    private void amintranstion(){
        layout1.setTranslationY(-1000);
        layout2.setTranslationX(-700);
        layout3.setTranslationX(700);
        //ẩn
        tv_acc.setAlpha(0);
        imglogo.setAlpha(0);
        tv_login.setAlpha(0);
        cart.setAlpha(0);
        intro1.setAlpha(0);
        intro2.setAlpha(0);
        tv_dk.setAlpha(0);
        btn_login.setAlpha(0);
        // animation
        layout1.animate().translationY(0).setDuration(1000).setStartDelay(700);
        layout2.animate().translationX(0).setDuration(1000).setStartDelay(700);
        layout3.animate().translationX(0).setDuration(1000).setStartDelay(700);
        cart.animate().alpha(1).setDuration(1200).setStartDelay(1400).start();
        imglogo.animate().alpha(1).setDuration(1200).setStartDelay(1700).start();
        tv_login.animate().alpha(1).setDuration(1200).setStartDelay(2000).start();
        intro1.animate().alpha(1).setDuration(1200).setStartDelay(2300).start();
        intro2.animate().alpha(1).setDuration(1200).setStartDelay(2500).start();
        tv_acc.animate().alpha(1).setDuration(1200).setStartDelay(2700).start();
        tv_dk.animate().alpha(1).setDuration(1200).setStartDelay(2900).start();
        btn_login.animate().alpha(1).setDuration(1200).setStartDelay(2900).start();
    }

    private void onClicksingin() {
        if (validate() > 0) {
        String email = ed_tendn.getText().toString().trim();
        String password = ed_mk.getText().toString().trim();
            dialog.showDialog();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignIn.this, MainActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(SignIn.this, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public int validate() {
        int check = 1;
        String checkemail = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)";
        if (ed_tendn.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Email không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (!ed_tendn.getText().toString().matches(checkemail)) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Email không đúng định dạng.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Password không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() < 6) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Password phải lớn hơn 6 kí tự.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        }
        return check;
    }
}
